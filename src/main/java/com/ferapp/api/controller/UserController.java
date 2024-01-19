package com.ferapp.api.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.ferapp.api.configs.security.Security;
import com.ferapp.api.model.ApiResponseCST;
import com.ferapp.api.model.ApiResponseCST.Status;
import com.ferapp.api.model.UserSE;
import com.ferapp.api.model.as400.User;
import com.ferapp.api.model.dto.CtpuserDTO;

import com.ferapp.api.model.dto.LoginAD;

import com.ferapp.api.service.AuthenticationService;
import com.ferapp.api.service.as400.UserService;
import com.ferapp.api.utils.Auxiliar;
import com.google.gson.Gson;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/securityuser")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationService authService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    Security sec;

    @Value("${data.source:hda}")
    String ENV;
    @Value("${data.api_se.adm:https://...}")
    String api_se_adm;
    @Value("${data.api_se.base_url:https://...}")
    String api_se_baseurl;
    @Value("${app.name:apiName}")
    String app_name;

    @GetMapping(value = "/checktoken", produces = "application/json")
    @Operation(summary = "Simple request to check token return")
    public ResponseEntity<String> checkToken() {

        return ResponseEntity.status(HttpStatus.OK).body("Token alive!");

    }

    @GetMapping(value = "/getAll", produces = "application/json")
    @Operation(summary = "Listar usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Nenhum usuário cadastrado!")
    })
    public ResponseEntity<List<User>> listAll(
            @PageableDefault(page = 0, size = 10, sort = "username", direction = Sort.Direction.ASC) Pageable pageable) {

        List<User> users = userService.listarTodos();
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);

    }

    @PutMapping(value = "/add", produces = "application/json")
    @Operation(summary = "Cadastrar usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso!"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> add(@RequestBody CtpuserDTO user, HttpServletRequest request) throws ParseException {

        LoginAD userAD = new LoginAD(request);

        Optional<User> optUser = userService.getByUsernameOptional(user.getUsername());
        if (!optUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header("Accept", "application/json")
                    .body("Usuário já consta cadastrado no sistema " + app_name + ".");
        }

        Boolean gravaSE = false;
        if (ENV.equals("hda") & gravaSE) {
            ApiResponseCST response = addUserSE(user);
            if (response != null) {

                if (response.getStatusCodeSOAP() != 1) {
                    String msgErro = "";
                    if (response.getStatus() == Status.OK) {
                        msgErro = "Requisição (@addUserSE) aceita, mas não efetivada [" +
                                "retorno: " + response.getStatus() + "; " +
                                "SOAP status: " + response.getStatusCodeSOAP() + " - " + response.getStatusDescSOAP()
                                + "; " +
                                "Msg: " + response.getMsg() + "]";
                    } else {
                        msgErro = "Requisição (@postUser) não retornou 200 [" +
                                "retorno: " + response.getStatus() + "]";
                    }
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .header("Accept", "application/json")
                            .body("Falha ao gravar usuário SE. " + msgErro);
                }

            } else {

            }
        }

        user.setAtivo("S");
        user.setPassword(encoder.encode(user.getPassword()));
        user.setItaudhst(userAD.getUserdns());
        userService.saveUser(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Accept", "application/json")
                .body("Usuário cadastrado com sucesso");

    }

    @PostMapping(value = "/update", produces = "application/json")
    @Operation(summary = "Alterar usuário.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário alterado com sucesso!"),
            @ApiResponse(responseCode = "403", description = "Usuário não cadastrado no sistema FERG.COM!"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> update(@RequestBody User user) throws ParseException {

        List<User> users = userService.listByUsername(user.getUsername());
        User userALT = null;

        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .header("Accept", "application/json")
                    .body(null);
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            userALT = userService.save(user);
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Accept", "application/json")
                .body(userALT);

    }

    private ApiResponseCST addUserSE(CtpuserDTO user) {

        ApiResponseCST response = null;
        String MATRICULA = "";
        boolean newRegister;

        try {

            MATRICULA = String.valueOf(user.getUserid());

            UserSE funcionario = new UserSE(
                    user.getUsername(),
                    MATRICULA,
                    user.getName(),
                    user.getEmail().toLowerCase(),
                    "",
                    "",
                    user.getIdarea(),
                    "",
                    "");

            UserSE usuarioSE = getUserSE(MATRICULA);
            if (usuarioSE == null) {
                newRegister = true;

                funcionario.setPassword(user.getPassword());
            } else {
                newRegister = false;
            }

            if (newRegister) {

                response = postUser(doEnvelopUser(funcionario, 0), "newUser");

            }

        } catch (Exception e) {

        } finally {

        }

        return response;

    }

    private String doEnvelopUser(UserSE entidade, int action) {

        String TagTypeAction = "", envelope;

        if (action == 1) {
            TagTypeAction = "editUser";
        } else {
            TagTypeAction = "newUser";
        }

        String envelopeNew = "<x:Envelope " + "\n" +
                "    xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\"" + "\n" +
                "    xmlns:urn=\"urn:admin\"> " + "\n" +
                "    <x:Header/> " + "\n" +
                "    <x:Body> " + "\n" +
                "        <urn:" + TagTypeAction + ">" + "\n" +
                "            <urn:IDUSER>{USER}</urn:IDUSER>" + "\n" +
                "            <urn:NAME>{NAME}</urn:NAME>" + "\n" +
                "            <urn:LOGIN>{LOGIN}</urn:LOGIN>" + "\n" +
                "            <urn:PASS>{PASS}</urn:PASS>" + "\n" +
                "            <urn:EMAIL>{EMAIL}</urn:EMAIL>" + "\n" +
                "            <urn:IDAREA>{AREA}</urn:IDAREA>" + "\n" +
                "            <urn:IDFUNC>{FUNCTION}</urn:IDFUNC>" + "\n" +
                "            <urn:IDACCGROUP>{GROUP}</urn:IDACCGROUP>" + "\n" +
                "            <urn:CDLEADER>{LEADER}</urn:CDLEADER>" + "\n" +
                "        </urn:" + TagTypeAction + ">" + "\n" +
                "    </x:Body> " + "\n" +
                "</x:Envelope>";

        String envelopeEdit = "<x:Envelope " + "\n" +
                "    xmlns:x=\"http://schemas.xmlsoap.org/soap/envelope/\"" + "\n" +
                "    xmlns:urn=\"urn:admin\"> " + "\n" +
                "    <x:Header/> " + "\n" +
                "    <x:Body> " + "\n" +
                "        <urn:" + TagTypeAction + ">" + "\n" +
                "            <urn:IDUSER>{USER}</urn:IDUSER>" + "\n" +
                "            <urn:NAME>{NAME}</urn:NAME>" + "\n" +
                "            <urn:LOGIN>{LOGIN}</urn:LOGIN>" + "\n" +
                "            <urn:CDLEADER>{LEADER}</urn:CDLEADER>" + "\n" +
                "        </urn:" + TagTypeAction + ">" + "\n" +
                "    </x:Body> " + "\n" +
                "</x:Envelope>";

        if (action == 1) {
            envelope = envelopeEdit
                    .replace("{USER}", String.valueOf(entidade.getIduser()))
                    .replace("{NAME}", entidade.getName())
                    .replace("{LOGIN}", entidade.getUsername());

        } else {
            envelope = envelopeNew
                    .replace("{USER}", String.valueOf(entidade.getIduser()))
                    .replace("{NAME}", entidade.getName())
                    .replace("{LOGIN}", entidade.getUsername())
                    .replace("{PASS}", entidade.getPassword())
                    .replace("{EMAIL}", entidade.getEmail())
                    .replace("{AREA}", entidade.getIdarea())
                    .replace("{FUNCTION}", "F001")
                    .replace("{GROUP}", "GR004")
                    .replace("{LEADER}", entidade.getIdleader());
        }

        return envelope;

    }

    private ApiResponseCST postUser(String envelope, String action) throws IOException {

        ApiResponseCST apiResponse = null;
        String URL = api_se_baseurl + "/se/ws/adm_ws.php";
        String typeAction, Action = "";

        try {

            String soapBody = envelope;
            typeAction = action;
            Action = "urn:admin#" + action;

            HttpClient client = new HttpClient();
            if (ENV.equals("hda")) {
                sec.configProxyClient2(client);
            }

            PostMethod method = new PostMethod(URL);

            method.setRequestHeader("SOAPAction", Action);
            method.setRequestHeader("Authorization", sec.getAuthenticationHeader("SEsuite"));
            method.setRequestHeader("Content-Type", "text/xml; charset=utf-8");

            StringRequestEntity strEntity = new StringRequestEntity(soapBody, "text/xml", "UTF-8");
            method.setRequestEntity(strEntity);

            client.executeMethod(method);
            int responseStatus = method.getStatusCode();

            String responseBody = method.getResponseBodyAsString();

            JsonNode responseNode = Auxiliar.nodeFromXML(responseBody, "/Body/" + typeAction + "Response");
            apiResponse = new ApiResponseCST(
                    responseStatus,
                    responseNode.get("Status").asText(),
                    responseNode.get("Code").asInt(),
                    responseNode.get("Detail").asText(),
                    "");

        } catch (Exception e) {

        } finally {

        }

        return apiResponse;

    }

    private UserSE getUserSE(String iduser) throws IOException, InterruptedException {

        HttpClient client = new HttpClient();

        if (ENV.equals("hda")) {
            sec.configProxyClient2(client);
        }
        PostMethod method = new PostMethod(api_se_baseurl + "/v1/dataset-integration/userarea");

        UserSE usuario = null;

        try {

            String jsonParams = "{ \"IDUSER\":" + iduser + " }";
            method.setRequestHeader("Authorization", sec.getAuthenticationHeader("SEsuite"));
            method.setRequestHeader("Content-Type", "application/json");
            StringRequestEntity jsonBody = new StringRequestEntity(jsonParams, "application/json", "UTF-8");
            method.setRequestEntity(jsonBody);

            client.executeMethod(method);
            String response = method.getResponseBodyAsString();

            if (method.getStatusCode() == HttpStatus.ACCEPTED.value()) {
                if (response.equals("[]")) {

                } else {
                    Gson gson = new Gson();
                    UserSE[] usuarios = gson.fromJson(response, UserSE[].class);
                    usuario = usuarios[0];
                }
            } else {

            }

        } catch (Exception e) {

        } finally {
            method.releaseConnection();
        }

        return usuario;

    }

}