package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.model.dto.User;
import com.dcr.api.repository.as400.UserRepository;
import com.dcr.api.response.ErrorResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UserService {

    final UserRepository userRepository;
    
    @Autowired
    RoleService roleService;

    //@Autowired
    //private ModelMapper mapper;

    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        //this.mapper = mapper;

    }

    public ErrorResponse validateDto(User dto) {
    	ErrorResponse response = new ErrorResponse();
    	response.setIsValid(Boolean.TRUE);
    	
    	if(dto.username().length() > 10) {
    		response.setIsValid(Boolean.FALSE);
    		response.setMsg("O campo username é inválido!");
    	}else if(dto.name().length() > 100) {
    		response.setIsValid(Boolean.FALSE);
    		response.setMsg("O campo name é inválido!");
    	}else if(dto.email().length() > 70) {
    		response.setIsValid(Boolean.FALSE);
    		response.setMsg("O campo email é inválido!");
    	}else if(dto.idArea().length() > 20) {
    		response.setIsValid(Boolean.FALSE);
    		response.setMsg("O campo idArea é inválido!");
    	}else if(dto.ativo().length() > 1 || (!dto.ativo().toUpperCase().equals("S") && !dto.ativo().toUpperCase().equals("N"))) {
    		response.setIsValid(Boolean.FALSE);
    		response.setMsg("O campo ativo é inválido!");
    	}else if(dto.password().length() > 100) {
    		response.setIsValid(Boolean.FALSE);
    		response.setMsg("O campo password é inválido!");
    	}
    	
    	return response;
    }
    
    public List<Accuser> listarTodos() {

        return userRepository.findAll();

    }

    public List<Accuser> listarAtivos() {

        return userRepository.findDistinctByAtivo("S");

    }
    
    public void updatePassword(String username, String password) {

      userRepository.setPassword(username, password);

    }
    
    public List<Accuser> listByUsername(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return userRepository.findDistinctByUsername(username);

    }

    public List<Accuser> listByEmail(String email) {

        return userRepository.findDistinctByEmail(email);

    }

    public Optional<Accuser> getByUsernameOptional(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return userRepository.findByUsername(username);

    }

    public List<Accuser> getByEmail(String email) {

        return userRepository.findDistinctByEmail(email);

    }
    
    public List<Accuser> getByUserId(Integer userid) {

        return userRepository.findDistinctByUserid(userid);

    }
    
    public Accuser getByUsername(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return (Accuser) userRepository.findDistinctByUsername(username);

    }

    public Long getMatricula(String username) {

        Long matricula = 0L;

        try {
            username = Auxiliar.trimNull(username).toUpperCase();
        } catch (Exception e) {
        }

        Optional<Accuser> optUser = userRepository.findByUsername(username);

        if (!optUser.isEmpty()) {
            Accuser user = optUser.get();
            matricula = user.getUserid();
        }

        return matricula;

    }

//    public User saveUser(CtpuserDTO userDTO) {
//
//        Auditoria audit = new Auditoria();
//
//        User user = mapDTOToEntity(userDTO);
//        user.setUsername(user.getUsername().toUpperCase());
//        user.setItauddt(audit.getData_audit());
//        user.setItaudhr(audit.getHora_audit());
//
//        if (user.getToken().isEmpty()) {
//            user.setToken("");
//        }
//
//        User userNew = userRepository.save(user);
//
//        return userNew;
//
//    }

    public Accuser save(Accuser user, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
    	Auxiliar.preencheAuditoria(user, request);
    	for (User_Role role : user.getRoles()) {
    		Auxiliar.preencheAuditoria(role, request);
		}
    	
        return userRepository.save(user);

    }

    public Accuser save0(Accuser user) {

        return userRepository.save(user);

    }

//    public User mapDTOToEntity(CtpuserDTO userDTO) {
//
//        User user = mapper.map(userDTO, User.class);
//
//        return user;
//
//    }

}
