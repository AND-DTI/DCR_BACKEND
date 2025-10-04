package com.dcr.api.rpa.service;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.dcr.api.model.rpa.DiagnosticoDetalhe;
import com.dcr.api.model.rpa.DiagnosticoLayout;
import com.dcr.api.model.rpa.DiagnosticoResumo;
import com.dcr.api.model.rpa.ErroDiagnosticoDCR;
import com.dcr.api.model.rpa.ErroLayoutTXT;
import com.dcr.api.rpa.response.RPAResponse;
import com.dcr.api.rpa.response.SessionResponse;
import com.dcr.api.service.as400.UserService;
import com.dcr.api.utils.Auxiliar;
import com.dcr.api.utils.RequestUtil;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ClientCertificate;
import com.microsoft.playwright.options.Cookie;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import com.microsoft.playwright.options.WaitUntilState;



@Service
@ConditionalOnProperty(
  value="env.so", 
  havingValue = "windows", 
  matchIfMissing = false)
public class RPAService {


    //Playwright playwright = Playwright.create();            
    //Browser browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
    final Playwright playwright;
    Browser browser;    
    BrowserContext context;
    
    public RPAService(/*Playwright playwright, Browser browser*/) {

        //java.awt.Toolkit toolkit = Toolkit.getDefaultToolkit();
        //java.awt.Dimension screenSize = toolkit.getScreenSize();
        
        this.playwright = Playwright.create();//playwright;
        this.browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(false)
                );       
        //this.browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));        
        
        this.context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        /*if (!java.awt.GraphicsEnvironment.isHeadless()) {
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(screenSize.width, screenSize.height));//1920, 1080
        }else{
            context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        }*/      
        
    }

    @Autowired
    UserService userService;

    @Autowired
    Environment env;


    public RPAResponse TransmiteTXT(SessionResponse session, String txtFile){ 

        
        RPAResponse response = new RPAResponse();

        try {
                 
            //////////////////////////////////////////////////////////////////////////////////////////////
            // 1. ENVIA TXT //////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            
            /********************************************************************************************/
            /* 1.1. Open page to upload file                                                            */
            /********************************************************************************************/
            Page page = session.getPage();
            String URL = session.getHost()+"/g36162/navDCRE?transacao=TR-DECL&etapa=Preparo";
            page.navigate(URL);
            URL = session.getHost()+"/g36162/navEnviarDCRE"; //url to set payload with memory
            //Dados do formulário:
			//transacao		TR-DECL - fixo no html
			//etapa			Envio   - fixo no html
			//arquivo		(binário)
            
            
            /********************************************************************************************/
            /* 1.2. Upload File                                                                         */
            /********************************************************************************************/            
            String fileServerPath = env.getProperty("storage.fileserver"); 
            String uplaodFile = fileServerPath+"\\"+txtFile; //"MN30053_MLGB140RZA.txt"
            Locator fileInput = page.locator("input[type='file']"); //or page.getByLabel("Upload file")            
            fileInput.setInputFiles(Paths.get(uplaodFile));            
            
            
            /********************************************************************************************/
            /* 1.3. Submit form                                                                         */
            /********************************************************************************************/        
            page.locator("form[name='formulario']").evaluate("form => form.submit()"); 
            String htmlContent = page.content(); //resposta submit
            traceDebug(htmlContent.trim(), "responseEnvioTXTparaDiagnostico.htm");
            

            /********************************************************************************************/
            /* 1.4. Get data e hora transmissao                                                         */
            /********************************************************************************************/            
            Document doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);   
            doc.getElementsByTag("input").remove();             
            doc.getElementsByTag("a").remove();
            traceDebug(doc.html().trim(), "responseEnvioTXTparaDiagnostico-Clean.htm");

            Element table = doc.select("table:contains(Data da Transmissão:)").first();
            String lastValue="-";
            String dataTransmissao="", horaTransmissao="";            
            if (table != null) {
                Elements rows = table.select("tr");//table.select("tr:contains(Data da Transmissão:)");
                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) {                                                                        
                        if(dataTransmissao.length()> 1 && horaTransmissao.length() > 1){ break; }
                        dataTransmissao = lastValue.equals("Data da Transmissão:")?cell.text():dataTransmissao;
                        horaTransmissao = lastValue.equals("Hora da Transmissão:")?cell.text():horaTransmissao;
                        lastValue = cell.text();                        
                    }
                    if(dataTransmissao.length()> 1 && horaTransmissao.length() > 1){ break; }
                }
                if(dataTransmissao.length()+horaTransmissao.length() > 2){
                    //save temp DCRPROTO? frontend allready save!
                    //System.out.println("Data: "+dataTransmissao+ "; Hora: "+horaTransmissao);                    
                    response.setStatusCode(200);
                    response.setMsg("Data / hora transmissão: "+dataTransmissao+' '+horaTransmissao);
                    response.setRecordKey(horaTransmissao);
                    String entity = "{ \"data\": \""+dataTransmissao+", \"hora\": \""+horaTransmissao+"\" }";
                    System.out.println(entity);
                    response.setReponseEntity(entity);
                    return response; 
                }            
            }


            /********************************************************************************************/
            /* 1.5. Pega Erros de layout/dados do arqurivo                                              */
            /********************************************************************************************/            
            if(dataTransmissao.length()<2 || horaTransmissao.length() < 2){
				
                List<ErroLayoutTXT> erros = new ArrayList<>();
                DiagnosticoLayout diagnostico = new DiagnosticoLayout();            
                int qtdErros=0;
                Element table1 = doc.select("table:contains(Erros na transmissão do arquivo)").first();
                if (table1 == null) {
                    response.setStatusCode(500);
                    response.setMsg("Falha ao ler erros do envio [tabela 'Erros na transmissão do arquivo' não encontrada]");
                    return response;
                }                
                diagnostico.setResultado("Erros na transmissão do arquivo");

				Elements rows = doc.getElementsByClass("tablinhadados"); //doc.select("tablinhadados");                
				if (rows != null) {                
					for (Element row : rows) {
						Elements cells = row.select("td"); 
                        for (Element cell : cells) { 
                            Element link = cell.select("a").first();
                            if(link == null){ //not read link - last line with button "retornar"
                                String v = cell.text();                                
                                String registro = v.substring(6,7);//v.substring(v.indexOf(" ")+1, 1); //reg. após primeiro espaço
                                String origem = registro.contains("3 4")?"Importado": (registro.equals("2")?"Nacional":"N/A");
                                String sequencia = v.substring(0, 5);
                                String observacao = v.substring(8, v.length());                                              
                                ErroLayoutTXT diagnosticoLinha = new ErroLayoutTXT(registro, origem, sequencia, observacao.trim());                        
                                erros.add(diagnosticoLinha);
                                qtdErros++;
                            }                                                      
                        }
					}                    
                    diagnostico.setErros(erros);
                    diagnostico.setQtdeErros(qtdErros);
                    
				} else {
                    response.setStatusCode(500);                    
                    response.setMsg("Falha ao ler erros do envio [linhas da tabela 'tablinhadados' não encontradas]");
                    return response;
				}

                if(!diagnostico.getErros().isEmpty() || !diagnostico.getResultado().isBlank()){                     
                    //System.out.println("Diagnostico layout: "+diagnostico);
					traceDebug(diagnostico.toString(), "Diagnostico erro layout.log");
					response.setStatusCode(200);
					response.setMsg("Erros de layout/dados encontrados no arquivo "+txtFile);					
                    response.setReponseEntity(diagnostico);                    
                    response.setRecordKey(txtFile);
					return response;
                }

                response.setStatusCode(500);
                response.setMsg("Falha ao enviar Arquivo para diagnóstico [Erros no arquivo]");				
                return response;
            }

          
        } catch (Exception e) {
            System.out.println(e);
            doLogErro("TransmiteTXT()", e.toString()); 
            response.setStatusCode(500);
            response.setMsg("Falha ao enviar Arquivo para diagnóstico [Erro: "+e+"]");				
            return response;			
        }

        response.setStatusCode(500);
        response.setMsg("Falha ao executar rotina TransmiteTXTP().");
        return response; 
        
    }            


    public RPAResponse TransmiteTXT_Simulacao(SessionResponse session, String txtFile, Boolean comErroLayout){ 

        
        RPAResponse response = new RPAResponse();

        try {
                 
            //////////////////////////////////////////////////////////////////////////////////////////////
            //1. ENVIA TXT ///////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            //1.1. Open page to upload file
            
            //1.2. Upload File
                      
            //1.3. Submit form
            
            //1.4. Get data e hora transmissao (Simula página resultado do submit form - etapa 1.3):            						                        
            String fileSimulacao = comErroLayout?"/responseEnvioTXTparaDiagnostico-ErroLayout.htm" : "/responseEnvioTXTparaDiagnostico.htm";
            String htmlFile = env.getProperty("storage.fileserver")+fileSimulacao;
            String htmlContent = Files.readString(Paths.get(htmlFile));

            Document doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);   
            doc.getElementsByTag("input").remove();            
            traceDebug(doc.html().trim(), fileSimulacao.replace(".htm", "-Clean.htm"));

            Element table = doc.select("table:contains(Data da Transmissão:)").first();
            String lastValue="-";
            String dataTransmissao="", horaTransmissao="";            
            if (table != null) {
                Elements rows = table.select("tr");//table.select("tr:contains(Data da Transmissão:)");
                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) {                                                                        
                        if(dataTransmissao.length()> 1 && horaTransmissao.length() > 1){ break; }
                        dataTransmissao = lastValue.equals("Data da Transmissão:")?cell.text():dataTransmissao;
                        horaTransmissao = lastValue.equals("Hora da Transmissão:")?cell.text():horaTransmissao;
                        lastValue = cell.text();                        
                    }
                    if(dataTransmissao.length()> 1 && horaTransmissao.length() > 1){ break; }
                }
                if(dataTransmissao.length()+horaTransmissao.length() > 2){
                    //save temp DCRPROTO? frontend allready save!
                    //System.out.println("Data: "+dataTransmissao+ "; Hora: "+horaTransmissao);
                    response.setStatusCode(200);
					response.setMsg("Data / hora transmissão: "+dataTransmissao+' '+horaTransmissao);
                    response.setRecordKey(horaTransmissao);
                    String entity = "{ \"data\": \""+dataTransmissao+", \"hora\": \""+horaTransmissao+"\" }";
                    //System.out.println(entity);
                    response.setReponseEntity(entity);
					return response;                                                                                 
                }            
            }

            //1.5. Erros de layout/dados do arqurivo:
            if(dataTransmissao.length()<2 || horaTransmissao.length() < 2){
                                    
                List<ErroLayoutTXT> erros = new ArrayList<>();
                DiagnosticoLayout diagnostico = new DiagnosticoLayout();            
                int qtdErros=0;
                Element table1 = doc.select("table:contains(Erros na transmissão do arquivo)").first();
                if (table1 == null) {
                    response.setStatusCode(500);
                    response.setMsg("Falha ao ler erros do envio [tabela 'Erros na transmissão do arquivo' não encontrada]");
                    return response;
                }                
                diagnostico.setResultado("Erros na transmissão do arquivo");

                Elements rows = doc.getElementsByClass("tablinhadados"); //doc.select("tablinhadados");                                
                if (rows != null) {                
                    for (Element row : rows) {
                        Elements cells = row.select("td"); 
                        for (Element cell : cells) { 
                            Element link = cell.select("a").first();
                            if(link == null){ //not read link - last line with button "retornar"
                                String v = cell.text();
                                String registro = v.substring(6,7);//v.substring(v.indexOf(" ")+1, 1); //reg. após primeiro espaço
                                String origem = registro.contains("3 4")?"Importado": (registro.equals("2")?"Nacional":"N/A");
                                String sequencia = v.substring(0, 5);
                                String observacao = v.substring(8, v.length());                                              
                                ErroLayoutTXT diagnosticoLinha = new ErroLayoutTXT(registro, origem, sequencia, observacao.trim());                        
                                erros.add(diagnosticoLinha);
                                qtdErros++;
                            }                                                      
                        }
                    }                    
                    diagnostico.setErros(erros);
                    diagnostico.setQtdeErros(qtdErros);
                    
                } else {
                    response.setStatusCode(500);                    
                    response.setMsg("Falha ao ler erros do envio [linhas da tabela 'tablinhadados' não encontradas]");
                    return response;
                }

                if(!diagnostico.getErros().isEmpty() || !diagnostico.getResultado().isBlank()){                     
                    //System.out.println("Diagnostico layout: "+diagnostico);
                    traceDebug(diagnostico.toString(), "Diagnostico erro layout.log");
                    response.setStatusCode(200);
                    response.setMsg("Erros de layout/dados encontrados no arquivo "+txtFile);					
                    response.setReponseEntity(diagnostico);                    
                    response.setRecordKey(txtFile);
                    return response;
                }

                response.setStatusCode(500);
                response.setMsg("Falha ao enviar Arquivo para diagnóstico [Erros no arquivo]");				
                return response;                				

            }

          
        } catch (Exception e) {            
            doLogErro("TransmiteTXT_Simulacao()", e.toString()); /*e.getMessage() trunca erro */
            response.setStatusCode(500);
            response.setMsg("Falha ao enviar Arquivo para diagnóstico [Erro: "+e+"]");				
            return response;
        }

        
        response.setStatusCode(500);
        response.setMsg("Falha ao executar rotina TransmiteTXTPlaywright_Simulacao()");
        return response;            


    }            


	public RPAResponse RecuperaDiagnostico(SessionResponse session, String horaTransmissao){ //** tentar 5 vezes de 30 em 30 sec - do frontend

        
        RPAResponse response = new RPAResponse();

        try {
                 
            //////////////////////////////////////////////////////////////////////////////////////////////
            //1. RECUPERA PROTOCOLO //////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
                                               
            //1.1. Entra página inicial da consulta **just for debug - in PD skep this step
            Page page = session.getPage();
			String URL= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Preparo";
            //page.navigate(URL);

            //1.2. Envia formulário de consulta (resulta na página com os links dos protocolos): 
            URL= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Escolha";
            String cnpj = "04337168000148"; //get from config
            URL+= "&hidCnpj="+cnpj;
            URL+= "&txtCNPJ="+Auxiliar.formataCNPJ(cnpj);
            page.navigate(URL);
            String htmlContent = page.content();
            traceDebug(htmlContent.trim(), "responseConsultaLinksProtocolos.htm"); //responseConsultaDiagnostico
            
            //1.3. Get protocolo (pela data-hora)			
            Document doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc.html().trim(), "responseConsultaLinksProtocolos-Clean.htm"); 

            Elements rows = doc.getElementsByClass("tablinhadados"); 
            String protocolo="", datahora="-", HORA_ENVIO=horaTransmissao, ID_PROTOCOLO="";            
            if (rows != null) {  

                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) { 
                        Element link = cell.select("a").first();
                        protocolo = link!=null?link.text():protocolo;
                        datahora = link==null?cell.text():datahora;
                        System.out.println("prot/data: "+protocolo+"/"+datahora);                        
                    }
                    if(datahora.equals(HORA_ENVIO)){ 
                        ID_PROTOCOLO = protocolo;
                        break; 
                    }                
                }                
                if(!ID_PROTOCOLO.isBlank()){ 
                    //save DCRPROCC(HORA_ENVIO, ID_PROTOCOLO, idmatriz, partnumpd)
                    System.out.println("Protocolo: "+ID_PROTOCOLO);
                }else{
                    System.out.println("Protocolo para o envio "+HORA_ENVIO+" ainda não processado!");
					response.setStatusCode(400);
					response.setMsg("Protocolo para o envio "+HORA_ENVIO+" ainda não processado!");
					return response;  
                }
                
            } else {
                System.out.println("Table not found 1.");				
                doLogErro("RecuperaDiagnosticoPlaywright()", "Table not found 1."); 
                response.setStatusCode(500);
                response.setMsg("Falha ao recuperar para o envio "+horaTransmissao+" [Table not found 1]");				
                return response;
            }


            //////////////////////////////////////////////////////////////////////////////////////////////
            //2. CONSULTA DIAGNÓSTICO DETALHADO //////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////            
            
            //2.1. Entra na página de apresentação do protocolo (com os botões resumo, salvar, registar; ou Erros) 
            String URL2= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Consulta";             
            URL2+= "&txtNumero="+protocolo.replaceAll("[^0-9]", "");
            page.navigate(URL2);
            htmlContent = page.content();
            traceDebug(htmlContent.trim(), "responseConsultaDiagnostico-Link-Detalhado.htm"); 
            
            //2.2. Pega resultado do protocolo - Erros ou Resumo pronto para registro
			Document doc2 = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc.html().trim(), "responseConsultaDiagnostico-Link-Detalhado-Clean.htm");

			Elements rows2 = doc2.select("fieldset");                        
            List<ErroDiagnosticoDCR> erros = new ArrayList<>();
            DiagnosticoDetalhe diagnostico = new DiagnosticoDetalhe();
            int linha=0, qtdErros=0, importados=0, nacionais=0;

            if (rows2 != null) {                
                for (Element row : rows2) {
                    linha++;
                    System.out.println(row.text());
                    if(linha == 1){                        
                        diagnostico.setResultado(row.text());
                    }else{
                        String v = row.text();
                        String tipo = v.contains("Subcomp.")?"Subcomponente":"Componente";
                        String origem = v.contains("Imp.")?"Importado":"Nacional";
                        String sequencia = v.substring(v.indexOf("/")+1, v.indexOf("/")+5);
                        String observacao = v.substring(v.indexOf("]")+1, v.length());
                        ErroDiagnosticoDCR diagnosticoLinha = new ErroDiagnosticoDCR(tipo, origem, sequencia, observacao.trim());                        
                        erros.add(diagnosticoLinha);
                        qtdErros++;
                        importados+= origem.equals("Importado")?1:0;
                        nacionais+= origem.equals("Nacional")?1:0;
                    }                    
                }
                diagnostico.setErros(erros);
                diagnostico.setQtdeErros(qtdErros);
                diagnostico.setQtdeErrosImp(importados);
                diagnostico.setQtdeErrosNac(nacionais);
                
                if(!diagnostico.getErros().isEmpty() || !diagnostico.getResultado().isBlank()){ 
                    //save em PROTOCOLO** frontend decidirá se grava protocolo
                    //System.out.println("Diagnostico: "+diagnostico);
					traceDebug(diagnostico.toString(), "Diagnostico-"+protocolo+".txt");
					response.setStatusCode(200);
					response.setMsg("Protocolo para o envio "+HORA_ENVIO+" encontrado: "+protocolo);					
                    response.setReponseEntity(diagnostico);
                    protocolo = protocolo.replaceAll("[^0-9]", "");
                    response.setRecordKey(protocolo);
					return response;
                }
            } else {                
                System.out.println("Table not found 2.");				
                doLogErro("RecuperaDiagnostico()", "Table not found 2."); 
                response.setStatusCode(500);
                response.setMsg("Falha ao recuperar diagnóstico para o envio "+horaTransmissao+" [Table not found 2]");
                return response;                
            }

            
        } catch (Exception e) {
            System.out.println(e);            
            doLogErro("RecuperaDiagnostico()", e.toString()); 
            response.setStatusCode(500);
            response.setMsg("Falha ao recuperar diagnóstico para o envio "+horaTransmissao+" [Erro: "+e+"]");
            return response;
        }


        response.setStatusCode(500);
        response.setMsg("Falha ao executar rotina RecuperaDiagnostico()");  
        return response;   


    }            


    public RPAResponse RecuperaDiagnostico_Simulacao(SessionResponse session, String horaTransmissao, Boolean comErro){ //** tentar 5 vezes de 30 em 30 sec - do frontend

        
        RPAResponse response = new RPAResponse();

        try {
                           
            //////////////////////////////////////////////////////////////////////////////////////////////
            //1. RECUPERA PROTOCOLO //////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            
            //1.1. Entra na página inicial da consulta **just for debug - in PD skep this step            
            
            //1.2. Envia formulário de consulta (resulta na página com os links dos protocolos): 
            
            //1.3. Get protocolo (pela data-hora)
			//Simula resultado das etapas 1.1 e 1.2
            String htmlFile = env.getProperty("storage.fileserver")+"\\responseConsultaLinksProtocolos.htm";
            String htmlContent = Files.readString(Paths.get(htmlFile)); 
            Document doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc.html().trim(), "responseConsultaLinksProtocolos-Clean.htm");

            Elements rows = doc.getElementsByClass("tablinhadados"); 
            String protocolo="", datahora="-", HORA_ENVIO=horaTransmissao, ID_PROTOCOLO="";            
            if (rows != null) {  

                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) { 
                        Element link = cell.select("a").first();
                        protocolo = link!=null?link.text():protocolo;
                        datahora = link==null?cell.text():datahora;
                        System.out.println("prot/data: "+protocolo+"/"+datahora);                        
                    }
                    if(datahora.equals(HORA_ENVIO)){ 
                        ID_PROTOCOLO = protocolo;
                        break; 
                    }                
                }                
                if(!ID_PROTOCOLO.isBlank()){ 
                    //save DCRPROTO(HORA_ENVIO, ID_PROTOCOLO, idmatriz, partnumpd)
                    System.out.println("Protocolo: "+ID_PROTOCOLO);
                }else{
                    System.out.println("Protocolo para o envio "+HORA_ENVIO+" ainda não processado!");
					response.setStatusCode(400);
					response.setMsg("Protocolo para o envio "+HORA_ENVIO+" ainda não processado!");
					return response;  
                }
                
            } else {
				System.out.println("Table not found 1.");				
                doLogErro("RecuperaDiagnosticoPlaywright_Simulacao()", "Table not found 1."); 
                response.setStatusCode(500);
                response.setMsg("Falha ao recuperar diagnóstico para o envio "+horaTransmissao+" [Table not found 1]");
                return response;    
            }


            //////////////////////////////////////////////////////////////////////////////////////////////
            //2. CONSULTA DIAGNÓSTICO DETALHADO //////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            
            //2.1. Entra na página de apresentação do protocolo (com os botões resumo, salvar, registar; ou Erros) 
            
            //2.2. Pega resultado do protocolo - Erros ou Resumo pronto para registro (Simula resultado do page.navigate(URL) da etapa 2.2):			
            String fileSimulacao = comErro?"\\responseConsultaDiagnostico-Link-Detalhado.htm" : "\\responseDiagnosticoSemErro-Apresentacao.htm";
            htmlFile = env.getProperty("storage.fileserver")+fileSimulacao;
			htmlContent = Files.readString(Paths.get(htmlFile)); 
			Document doc2 = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc2.html().trim(), fileSimulacao.replace(".htm", "-Clean.htm"));

			Elements rows2 = doc2.select("fieldset");            
            List<ErroDiagnosticoDCR> erros = new ArrayList<>();
            DiagnosticoDetalhe diagnostico = new DiagnosticoDetalhe();
            int linha=0, qtdErros=0, importados=0, nacionais=0;
            if (rows2 != null) {                
                for (Element row : rows2) {
                    linha++;
                    System.out.println(row.text());
                    if(linha == 1){
                        diagnostico.setResultado(row.text());
                    }else{
                        String v = row.text();
                        String tipo = v.contains("Subcomp.")?"Subcomponente":"Componente";
                        String origem = v.contains("Imp.")?"Importado":"Nacional";
                        String sequencia = v.substring(v.indexOf("/")+1, v.indexOf("/")+5);
                        String observacao = v.substring(v.indexOf("]")+1, v.length());
                        ErroDiagnosticoDCR diagnosticoLinha = new ErroDiagnosticoDCR(tipo, origem, sequencia, observacao.trim());                        
                        erros.add(diagnosticoLinha); 
                        qtdErros++;
                        importados+= origem.equals("Importado")?1:0;
                        nacionais+= origem.equals("Nacional")?1:0;
                    }                    
                }
                diagnostico.setErros(erros);
                diagnostico.setQtdeErros(qtdErros);
                diagnostico.setQtdeErrosImp(importados);
                diagnostico.setQtdeErrosNac(nacionais);

                if(!diagnostico.getErros().isEmpty() || !diagnostico.getResultado().isBlank()){ 
                    //save em PENDPROD-Diag
                    System.out.println("Diagnostico: "+diagnostico);					
                    traceDebug(diagnostico.toString(), "Diagnostico-"+protocolo+".txt");
					response.setStatusCode(200);                    
					response.setMsg("Protocolo para o envio "+HORA_ENVIO+" encontrado: "+protocolo);
                    response.setReponseEntity(diagnostico);
                    protocolo = protocolo.replaceAll("[^0-9]", "");
                    response.setRecordKey(protocolo);
					return response;  
                }
            } else {                
                System.out.println("Table not found 2.");				
                doLogErro("RecuperaDiagnosticoPlaywright_Simulacao()", "Table not found 2."); 
                response.setStatusCode(500);
                response.setMsg("Falha ao recuperar diagnóstico para o envio "+horaTransmissao+" [Table not found 2]");
                return response;     
            }

            
        } catch (Exception e) {
            System.out.println(e);            
            doLogErro("RecuperaDiagnosticoPlaywright_Simulacao()", e.toString()); 
            response.setStatusCode(500);
            response.setMsg("Falha ao recuperar diagnóstico para o envio "+horaTransmissao+" [Erro: "+e+"]");
            return response;
        }


        response.setStatusCode(500);
        response.setMsg("Falha ao executar rotina RecuperaDiagnosticoPlaywright_Simulacao()");  
        return response;


    }            


    public RPAResponse RecuperaResumoDiagnostico(SessionResponse session, String protocolo){ 

        
        RPAResponse response = new RPAResponse();

        try {
                           
            //////////////////////////////////////////////////////////////////////////////////////////////
            //1. RECUPERA RESUMO/TOTAIS DIAGNOSTICO //////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            //Obs.: **steps 1.q and 1.2 is just for debug - in PD skep this step

            //1.1. Entra página com os links dos protocolos 
            Page page = session.getPage();			            
            String URL= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Escolha";
            String cnpj = "04337168000148"; //get from config
            URL+= "&hidCnpj="+cnpj;
            URL+= "&txtCNPJ="+Auxiliar.formataCNPJ(cnpj);
            page.navigate(URL);
            String htmlContent = page.content();
            traceDebug(htmlContent.trim(), "responseConsultaLinksProtocolos.htm"); 
            
            //1.2. Entra na página de apresentação do protocolo (com os botões resumo, salvar, registar) 
            URL = session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Consulta&txtNumero="+protocolo;
            page.navigate(URL);
 
            //1.3. Get Resumo/Totais 			
            URL= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=DadosGerais";
            URL+= "&hidCnpj="+cnpj;
            URL+= "&txtNumero="+protocolo;
            page.navigate(URL);
            
            htmlContent = page.content();            
            Document doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc.html().trim(), "responseResumoDiagnostico-Clean.htm");

            String lastValue = "-";
            String totalNac="0", totalImp="0", cusTotal="0", iiSemReducao="0", iiReduzido="0", pesoBruto="0";            
            Elements rows = doc.getElementsByClass("tablinhadados"); 
            if (rows != null) {  

                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) { 
                        totalNac = lastValue.equals("Custo dos Componentes Nacionais (US$):")?cell.text():totalNac;
                        totalImp = lastValue.contains("Custo dos Componentes Importados")?cell.text(): totalImp;
                        cusTotal = lastValue.contains("Custo Total")?cell.text(): cusTotal;
                        iiSemReducao = lastValue.contains("Valor Total do II sem Redu")?cell.text(): iiSemReducao;
                        iiReduzido = lastValue.contains("Valor Total do II com Redu")?cell.text(): iiReduzido;
                        pesoBruto = lastValue.contains("Peso Bruto")?cell.text(): pesoBruto;
                        lastValue = cell.text();                                                
                    }                                   
                } 

                totalImp     = totalImp.replace(",", ".").replaceAll("[^0-9.]","");
                totalNac     = totalNac.replace(",", ".").replaceAll("[^0-9.]","");
                cusTotal     = cusTotal.replace(",", ".").replaceAll("[^0-9.]","");
                iiSemReducao = iiSemReducao.replace(",", ".").replaceAll("[^0-9.]","");
                iiReduzido   = iiReduzido.replace(",", ".").replaceAll("[^0-9.]","");
                pesoBruto    = pesoBruto.replace(",", ".").replaceAll("[^0-9.]","");

                DiagnosticoResumo resumo = new DiagnosticoResumo(
                    Double.valueOf(totalImp),
                    Double.valueOf(totalNac),
                    Double.valueOf(cusTotal),
                    Double.valueOf(iiSemReducao),
                    Double.valueOf(iiReduzido),
                    Double.valueOf(pesoBruto)
                );
                              
                traceDebug(resumo.toString(), "DiagnosticoResumo-"+protocolo+".log");
                response.setStatusCode(200);                    
                response.setMsg("Resumo do diagnóstico "+protocolo+" recuperado com sucesso!");
                response.setReponseEntity(resumo);                
                response.setRecordKey(protocolo);
                return response; 

            } else {
				System.out.println("Table not found 1.");				
                doLogErro("RecuperaResumoDiagnostico()", "Table not found 1."); 
                response.setStatusCode(500);
                response.setMsg("Falha ao recuperar resumo do protocolo "+protocolo+" [Table not found 1]");
                return response;    
            }
            
        } catch (Exception e) {
            System.out.println(e);            
            doLogErro("RecuperaResumoDiagnostico()", e.toString()); 
            response.setStatusCode(500);
            response.setMsg("Falha ao recuperar resumo do diagnóstico "+protocolo+" [Erro: "+e+"]");
            return response;
        }


    }            


    public RPAResponse RecuperaResumoDiagnostico_Simulacao(SessionResponse session, String protocolo){ 

        
        RPAResponse response = new RPAResponse();

        try {
                                       
            //////////////////////////////////////////////////////////////////////////////////////////////
            //1. RECUPERA RESUMO/TOTAIS DIAGN[OSTICO /////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            //Obs.: **steps 1.q and 1.2 is just for debug - in PD skep this step
            //1.1. Entra página com os links dos protocolos 
            
            //1.2. Entra na página de apresentação do protocolo (com os botões resumo, salvar, registar) 
            
            //1.3. Get Resumo/Totais 
			//Simula resultado das etapas 1.1 e 1.2
            String htmlFile = env.getProperty("storage.fileserver")+"\\responseDiagnosticoSemErro-Resumo-Exemplo-2.htm";
            String htmlContent = Files.readString(Paths.get(htmlFile)); 
            Document doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc.html().trim(), "responseResumoDiagnostico-Clean.htm");

            String lastValue = "-";
            String totalNac="0", totalImp="0", cusTotal="0", iiSemReducao="0", iiReduzido="0", pesoBruto="0";            
            Elements rows = doc.getElementsByClass("tablinhadados"); 
            if (rows != null) {  

                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) { 
                        totalNac = lastValue.equals("Custo dos Componentes Nacionais (US$):")?cell.text():totalNac;
                        totalImp = lastValue.contains("Custo dos Componentes Importados")?cell.text(): totalImp;
                        cusTotal = lastValue.contains("Custo Total")?cell.text(): cusTotal;
                        iiSemReducao = lastValue.contains("Valor Total do II sem Redu")?cell.text(): iiSemReducao;
                        iiReduzido = lastValue.contains("Valor Total do II com Redu")?cell.text(): iiReduzido;
                        pesoBruto = lastValue.contains("Peso Bruto")?cell.text(): pesoBruto;
                        lastValue = cell.text();                                                
                    }                                   
                } 
                
                totalImp     = totalImp.replace(",", ".").replaceAll("[^0-9.]","");
                totalNac     = totalNac.replace(",", ".").replaceAll("[^0-9.]","");
                cusTotal     = cusTotal.replace(",", ".").replaceAll("[^0-9.]","");
                iiSemReducao = iiSemReducao.replace(",", ".").replaceAll("[^0-9.]","");
                iiReduzido   = iiReduzido.replace(",", ".").replaceAll("[^0-9.]","");
                pesoBruto    = pesoBruto.replace(",", ".").replaceAll("[^0-9.]","");

                DiagnosticoResumo resumo = new DiagnosticoResumo(
                    Double.valueOf(totalImp),
                    Double.valueOf(totalNac),
                    Double.valueOf(cusTotal),
                    Double.valueOf(iiSemReducao),
                    Double.valueOf(iiReduzido),
                    Double.valueOf(pesoBruto)
                );

                traceDebug(resumo.toString(), "DiagnosticoResumo-"+protocolo+".log");
                response.setStatusCode(200);                    
                response.setMsg("Resumo do diagnóstico "+protocolo+" recuperado com sucesso!");
                response.setReponseEntity(resumo);                
                response.setRecordKey(protocolo);
                return response; 

            } else {
				System.out.println("Table not found 1.");				
                doLogErro("RecuperaDiagnostico_Simulacao()", "Table not found 1."); 
                response.setStatusCode(500);
                response.setMsg("Falha ao recuperar resumo do protocolo "+protocolo+" [Table not found 1]");
                return response;
            }
            
        } catch (Exception e) {
            System.out.println(e);            
            doLogErro("RecuperaResumoDiagnostico_Simulacao()", e.toString()); 
            response.setStatusCode(500);
            response.setMsg("Falha ao recuperar resumo do diagnóstico "+protocolo+" [Erro: "+e+"]");
            return response;
        }


    }            


    private void openNewSession(Boolean headless){
        
        this.browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions().setHeadless(headless)
        );
        
        this.context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));

    }


    public SessionResponse loginPlaywright2(){ /*create new active browser session */

        
        SessionResponse session = new SessionResponse();
               
        try{
            
            //Close current session (**can cause conflict if more than one user execute RPA)
            closeSesssion("context");
            closeSesssion("browser");
        
            //1. OPEN NEW BRWOSER CONTEXT 
            openNewSession(false);            
                                            
            //2. NAVIGATE TO LOGIN PAGE
            String HOST_URL = "https://www4c.receita.fazenda.gov.br";
            //String LOGIN_URL0 = HOST_URL+"/g33159/jsp/logon.jsp"; 
            String LOGIN_URL1 = HOST_URL+"/g33159/jsp/logon.jsp?ind=2"; 
            String LOGIN_URL2 = HOST_URL+"/g33159/jsp/LogonCertificado.jsp?ind=2";
            Page page = context.newPage();//browser.newPage();
            page.setDefaultTimeout(120000); //default 30sec - increased to test debug
            
            //page.navigate(LOGIN_URL0);
            page.navigate(LOGIN_URL1);
            //page.navigate(LOGIN_URL1, new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED));
            //page.goto("https://example.com", new Page.GotoOptions().setWaitUntil(LoadState.COMMIT));
            //page.keyboard().press("Enter");
            page.navigate(LOGIN_URL2);
            //page.waitForURL(LOGIN_URL0);
            //page.waitForURL(LOGIN_URL);


            //3. WAIT USER TO SELECT CERTIFICATE            
            //check if cookie was set cookie...        
            //Thread.sleep(2000);            
            List<Cookie> cookies = browser.contexts().get(0).cookies();
            if(cookies.isEmpty()){
                session.setStatus(500);
                session.setMessage("Sessão do usuário sem certificado selecionado!");
                return session;
            }
            

            //4. STORE SESSION COOKIES
            Map<String, String> loginCookies = new HashMap<>();            
            for (Cookie cookie : cookies) {
                loginCookies.put(cookie.name, cookie.value);                
            }
            Map<String, String> defaultHeaders = buildDefaultHeaders(loginCookies);
            userService.saveUserFile(loginCookies.toString(), "cookie.txt"); 

            //5. CLOSE SESSION
            /*playwright.close();


            //6. SET CERTIFICATE TO SSL CONTEXT **already on checkActiveSession            
            Certificate cert = certService.getCertificateByProcess("DCR"); //Get user certificate (by db)
            SSLEntity sslEntity = Security.createSSLContext(cert.getData(), cert.getPassword(), "pfx");
            //Seta o SSLContext globalmente para as requisições Jsoup:
            HttpsURLConnection.setDefaultSSLSocketFactory(sslEntity.getSslContext().getSocketFactory());
            */

            session.setStatus(200);
            session.setMessage("OK");
            session.setDefaultHeaders(defaultHeaders);
            session.setLoginCookies(loginCookies); 
            session.setBrowser(browser);
            session.setPage(page);  
            session.setHost(HOST_URL);
            return session; 
                                    

        } catch (Exception e) {           
            System.out.println(e);                        
            doLogErro("loginPlaywright2()", e.toString()); 
            session.setStatus(500);
            session.setMessage("Falha ao tantar realizar login no RPA [Erro: "+e+"]");
            return session;
        }
        
        
    }


    public SessionResponse loginPlaywright3(){ 

        
        SessionResponse session = new SessionResponse();
               
        try{

            //BrowserType chromium = playwright.chromium();
            //Browser browser = chromium.launch(new BrowserType.LaunchOptions().setHeadless(false));


            String HOST_URL = "https://www4c.receita.fazenda.gov.br";            
            //String LOGIN_URL1 = HOST_URL+"/g33159/jsp/logon.jsp?ind=2"; 
            String LOGIN_URL2 = HOST_URL+"/g33159/jsp/LogonCertificado.jsp?ind=2";

            // Define o certificado do cliente
            String origin = HOST_URL; //"https://dev.example.com:443";
            ClientCertificate cert = new ClientCertificate(origin);            
            cert.setPfxPath(Paths.get(userService.getUserPath()+"//rpacert.pfx")); 
            cert.setPassphrase("SANDRApfx2026"); //get from db

            // Cria o contexto do navegador com o certificado
            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                    .setClientCertificates(Collections.singletonList(cert))
                    .setIgnoreHTTPSErrors(true); // útil para ambientes de teste

            //BrowserContext context = browser.newContext(contextOptions);
            this.context = browser.newContext(contextOptions);
            Page page = context.newPage();

            // Navega para o site protegido
            page.navigate(origin);
            page.navigate(LOGIN_URL2);




            session.setStatus(200);
            session.setMessage("OK");
            //session.setDefaultHeaders(defaultHeaders);
            //session.setLoginCookies(loginCookies); 
            session.setBrowser(browser);
            //session.setPage(page);  
            //session.setHost(HOST_URL);
            return session; 
                                    

        } catch (Exception e) {           
            System.out.println(e);                        
            doLogErro("loginPlaywright3()", e.toString()); 
            session.setStatus(500);
            session.setMessage("Falha ao tantar realizar login no RPA [Erro: "+e+"]");
            return session;
        }
        
        
    }

	public SessionResponse mountCurrSession(){ /*return last active browser session */

        
        SessionResponse session = new SessionResponse();
               
        try{


            Map<String, String> loginCookies = new HashMap<>();                        
            List<Cookie> cookies = browser.contexts().get(0).cookies(); //replace by read cookie file...
            if(cookies.isEmpty()){
                session.setStatus(500);
                session.setMessage("Sessão do usuário sem certificado selecionado (mountCurrSession())!");
                return session;
            }
            for (Cookie cookie : cookies) {
                loginCookies.put(cookie.name, cookie.value);                
            }
            Map<String, String> defaultHeaders = buildDefaultHeaders(loginCookies);

            String HOST_URL = "https://www4c.receita.fazenda.gov.br";			
            Page page = browser.contexts().get(0).pages().get(0); //browser.newPage();

            session.setStatus(200);
            session.setMessage("OK");
            session.setDefaultHeaders(defaultHeaders);
            session.setLoginCookies(loginCookies); 
            session.setBrowser(browser);
            session.setPage(page);  
            session.setHost(HOST_URL); 
            return session; 
                                    

        } catch (Exception e) {
            System.out.println(e);                        
            doLogErro("mountCurrSession()", e.toString()); 
            session.setStatus(500);
            session.setMessage("Falha ao tantar montar sessão anterior (mountCurrSession) [Erro: "+e+"]");
            return session;
        }
        

    }            


    public void closeSesssion(String type) {

        switch (type) {
            case "context":
                if (context != null) { context.close(); }        
                break;
            case "browser":
                if (browser != null) { browser.close(); }        
                break;
            case "all":
                if (context != null) { context.close(); }                
                if (browser != null) { browser.close(); }
                if (playwright != null) { playwright.close(); } //if close - back must be restart
                break;        
            default:
                break;
        }
                             
    }
    

    private void traceDebug(String content, String fileName){

        Auxiliar.saveFileDebug(content, fileName, env.getProperty("storage.approot")); 

    }


    private void doLogErro(String processo, String errorMsg){
                
        Auxiliar.salvaLogErro(processo, errorMsg, env.getProperty("storage.approot"));
        
    }





    /*OLD:  */
    public RPAResponse TransmiteTXT_comDiagnosticoPlaywright(SessionResponse session){ //diagnosticoPlaywright

        
        RPAResponse response = new RPAResponse();

        try {
                 
            //////////////////////////////////////////////////////////////////////////////////////////////
            //1. ENVIA TXT ///////////////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            Page page = session.getPage();
            String URL = session.getHost()+"/g36162/navDCRE?transacao=TR-DECL&etapa=Preparo";
            page.navigate(URL);
            URL = session.getHost()+"/g36162/navEnviarDCRE"; //url to set payload with memory
            //Dados do formulário:
			//transacao		TR-DECL -  fixo no html
			//etapa			Envio   - fixo no html
			//arquivo		(binário)
            
            //1.1. Upload File
            String fileServerPath = ""; //"C:\\Users\\pande\\OneDrive\\Área de Trabalho\\_RPA"; //get from env
            String txtFile = "MN30053_MLGB140RZA.txt"; //pass by param
            String uplaodFile = fileServerPath+"\\"+txtFile;
            Locator fileInput = page.locator("input[type='file']"); //or page.getByLabel("Upload file")            
            fileInput.setInputFiles(Paths.get(uplaodFile));            
            
            //1.2. Submit form
            page.locator("form[name='formulario']").evaluate("form => form.submit()"); 
            String htmlContent = page.content(); //resposta submit
            traceDebug(htmlContent.trim(), "responseEnvioTXTparaDiagnostico.htm"); 

            //1.3. Get data e hora transmissao            
            //String htmlFile = "C:\\Users\\pande\\OneDrive\\Área de Trabalho\\_RPA\\responseTransmiteTXT.devtools.htm"; //pass by param
            //String htmlContent = Files.readString(Paths.get(htmlFile));            
            Document doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);   
            doc.getElementsByTag("input").remove();             
            doc.getElementsByTag("a").remove();
            traceDebug(doc.html().trim(), "responseEnvioTXTparaDiagnostico-Clean.htm");

            Element table = doc.select("table:contains(Data da Transmissão:)").first();
            String lastValue="-";
            String dataTransmissao="", horaTransmissao="";            
            if (table != null) {
                Elements rows = table.select("tr");//table.select("tr:contains(Data da Transmissão:)");
                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) {                                                                        
                        if(dataTransmissao.length()> 1 && horaTransmissao.length() > 1){ break; }
                        dataTransmissao = lastValue.equals("Data da Transmissão:")?cell.text():dataTransmissao;
                        horaTransmissao = lastValue.equals("Hora da Transmissão:")?cell.text():horaTransmissao;
                        lastValue = cell.text();                        
                    }
                    if(dataTransmissao.length()> 1 && horaTransmissao.length() > 1){ break; }
                }
                if(dataTransmissao.length()+horaTransmissao.length() > 2){
                    //save
                    System.out.println("Data: "+dataTransmissao);
                    System.out.println("Hora: "+horaTransmissao);
                }
            } else {
                System.out.println("Table not found.");
            }

            if(dataTransmissao.length()<2 || horaTransmissao.length() < 2){                
                response.setStatusCode(500);
                response.setMsg("Falha ao enviar Arquivo para diagnóstico");
                return response;
            }


            //////////////////////////////////////////////////////////////////////////////////////////////
            //2. RECUPERA PROTOCOLO //////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            //2.1. Entra página consulta **just for debug - in PD skep this step
            URL= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Preparo";
            page.navigate(URL);

            //2.2. Envia formulário de consulta: ** tentar 5 vezes de 30 em 30 sec - do frontend
            URL= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Escolha";
            String cnpj = "04337168000148"; //get from config
            URL+= "&hidCnpj="+cnpj;
            URL+= "&txtCNPJ="+Auxiliar.formataCNPJ(cnpj);
            page.navigate(URL);
            htmlContent = page.content();
            traceDebug(htmlContent.trim(), "responseConsultaDiagnostico.htm"); 
            
            //2.3. Get protocolo (pela data-hora) OLD: (mais recente)
            //htmlFile = "C:\\Users\\pande\\OneDrive\\Área de Trabalho\\_RPA\\responseConsultaDiagnostico.html"; //pass by param            
            //htmlContent = Files.readString(Paths.get(htmlFile)); 
            doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc.html().trim(), "responseConsultaDiagnostico-Clean.htm");

            Elements rows = doc.getElementsByClass("tablinhadados"); 
            String protocolo="", datahora="-", HORA_ENVIO=horaTransmissao/*"11:58:12"*/, ID_PROTOCOLO="";            
            if (rows != null) {  

                for (Element row : rows) {
                    Elements cells = row.select("td"); 
                    for (Element cell : cells) { 
                        Element link = cell.select("a").first();
                        protocolo = link!=null?link.text():protocolo;
                        datahora = link==null?cell.text():datahora;
                        System.out.println("prot/data: "+protocolo+"/"+datahora);                        
                    }
                    if(datahora.equals(HORA_ENVIO)){ 
                        ID_PROTOCOLO = protocolo;
                        break; 
                    }                
                }                
                if(!ID_PROTOCOLO.isBlank()){ 
                    //save
                    System.out.println("Protocolo: "+ID_PROTOCOLO);
                }else{
                    System.out.println("Protocolo para o envio "+HORA_ENVIO+" ainda não processado!");
                }
                
            } else {
                System.out.println("Table not found.");
            }


            //////////////////////////////////////////////////////////////////////////////////////////////
            //3. CONSULTA DIAGNÓSTICO ////////////////////////////////////////////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////
            URL= session.getHost()+"/g36162/navDCRE?transacao=CO-DIAG&etapa=Consulta"; 
            URL+= "&txtNumero="+protocolo.replace("/", "");            
            page.navigate(URL);
            htmlContent = page.content();
            traceDebug(htmlContent.trim(), "responseConsultaDiagnostico-Link-Detalhado.htm");               
            doc = Jsoup.parse(htmlContent);
            RequestUtil.cleanDefault(doc);
            traceDebug(doc.html().trim(), "responseConsultaDiagnostico-Link-Detalhado-Clean.htm");

            rows = doc.select("fieldset");             
            if (rows != null) {                
                for (Element row : rows) {
                    System.out.println(row.text());                                    
                }
                /*if(!protocolo.isBlank()){ 
                    //save
                    System.out.println("Protocolo: "+protocolo);
                }*/
            } else {
                System.out.println("Table not found.");
            }





            session.getBrowser().close();

            System.out.println(htmlContent);
        } catch (Exception e) {
            System.out.println(e);
        }

        return response;            


    }            


    public RPAResponse consultaDCRePlaywright(SessionResponse session, String numdcr){

        
        RPAResponse response = new RPAResponse();

        try {
                 
            
            //tst consulta:
            String URL = session.getHost()+"/g36162/navDCRE?transacao=CO-DCRE-NR&etapa=DadosGerais&txtNumero=2025102023&txtNumeroDCRE=2025%2F10202-3";
            session.getPage().navigate(URL);
            String html = session.getPage().content();
            System.out.println(html);


            /* 
            //Navega etapa 1            
            String URI      = session.getHost()+"/g36162/navDCRE"; 
            //String REFERER_URL = HOST_URL+"/g36162/html/FrameDCRE.html"; //"/g36162/navDCRE?transacao=CO-DCRE-NR&etapa=Preparo"
                        
            Map<String, String> params = new HashMap<>();
            String formatedNum = numdcr.substring(0, 4);
            formatedNum = formatedNum+"%2F"+numdcr.substring(4, 9);
            formatedNum = formatedNum+"-"+numdcr.substring(9, 10);
            params.put("transacao", "CO-DCRE-NR");
            params.put("etapa", "DadosGerais");
            params.put("txtNumero", numdcr);
            params.put("txtNumeroDCRE", formatedNum);
            String paramsStr= "";
            for (String key : params.keySet()) {                                
                paramsStr += paramsStr.isBlank()? "?":"&";                
                paramsStr += key+"="+params.get(key);
            }
            String GET_URL = URI+paramsStr;
            
            Page page = session.getBrowser().newPage();
            page.navigate(GET_URL);
            String html = page.content();
            traceDebug(html, "FrameDCR.htm"; 
            */
            //Document doc = connection.get(); ***see create document base atring
            //traceDebug(doc.html().trim(), "FrameDCR.htm"; 


            //doc.getElementsByTag("link").remove();
            //doc.getElementsByTag("script").remove();            
            //traceDebug(doc.html().trim(), "ConsultaDCR"+numdcr+".htm";            
            //Boolean expiredSession = doc.html().contains("Sua sessão caiu por tempo.") ? true:false;
            //Boolean dcrNaoEncontrado = doc.html().contains("Número do DCR-E não encontrado.") ? true:false;            
            
            /*if(expiredSession){                
                response.setStatusCode(401);
                response.setMsg("Sua sessão caiu por tempo.");
                return response;
            }
            if(dcrNaoEncontrado){
                response.setStatusCode(404);
                response.setMsg("Número do DCR-E não encontrado.");
                return response;
            }*/


        } catch (Exception e) {
            System.out.println(e);
        }

        return response;            


    }
	

	public Browser loginPlaywright(){


		Browser browser = null;

		try{
            
            //1. OPEN BRWOSER SESSION
            Playwright playwright = Playwright.create();            
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                                
            
            //2. NAVIGATE TO LOGIN PAGE
            String HOST_URL = "https://www4c.receita.fazenda.gov.br";
			String LOGIN_URL0 = HOST_URL+"/g33159/jsp/logon.jsp?ind=2";
            String LOGIN_URL = HOST_URL+"/g33159/jsp/LogonCertificado.jsp?ind=2";
            Page page = browser.newPage();
            page.navigate(LOGIN_URL0);
			page.navigate(LOGIN_URL);

			
            //3. WAIT USER TO SELECT CERTIFICATE            
            //check if cookie was set cookie...        
            //Thread.sleep(2000);            
            /*List<Cookie> cookies = browser.contexts().get(0).cookies();
            if(cookies.isEmpty()){
                session.setStatus(500);
                session.setMessage("Sessão do usuário sem certificado selecionado!");
                return session;
            }*/
            

            //4. STORE SESSION COOKIES
            /*Map<String, String> loginCookies = new HashMap<>();            
            for (Cookie cookie : cookies) {
                loginCookies.put(cookie.name, cookie.value);                
            }
            Map<String, String> defaultHeaders = buildDefaultHeaders(loginCookies);*/


            //5. CLOSE SESSION
            //playwright.close();


                       

        } catch (Exception e) {
           //doLogErro("TesteController-loginPlaywright()", e.toString()); 
        }


		return browser;

	}
    

    private Map<String, String> buildDefaultHeaders(Map<String, String> loginCookies){
     
        String cookieString = "";
        for (String key : loginCookies.keySet()) {            
            if(!cookieString.isBlank()){ cookieString += "; "; };
            cookieString += key +"="+ loginCookies.get(key);            
        }
        //"JSESSIONID=000081bUZY7sr8DqWsQSbkZoGgl:CA87832EDEE3E4D4000004280000003800000008; LogonCert=SiscomexWeb"
               

        Map<String, String> defaultHeaders = new HashMap<>();
        //defaultHeaders.put("Host", HOST_URL);
        defaultHeaders.put("Cookie", cookieString);
        defaultHeaders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        defaultHeaders.put("Accept-Encoding", "gzip, deflate, br, zstd");
        defaultHeaders.put("Accept-Language", "pt-BR,pt;q=0.9,en-US;q=0.8,en;q=0.7,pt-PT;q=0.6,fr;q=0.5");
        defaultHeaders.put("Connection", "keep-alive"); //restricted to httprequest apache
        defaultHeaders.put("Sec-ch-ua", "Chromium;v=\"134\", \"Not:A-Brand\";v=\"24\", \"Opera\";v=\"119\"");
        defaultHeaders.put("Sec-ch-ua-mobile", "?0");
        defaultHeaders.put("Sec-ch-ua-platform", "\"Windows\"");
        defaultHeaders.put("Sec-fetch-dest", "frame");
        defaultHeaders.put("Sec-fetch-mode", "navigate");
        defaultHeaders.put("Sec-fetch-site", "same-origin");
        defaultHeaders.put("Sec-fetch-user", "?1");
        defaultHeaders.put("upgrade-insecure-requests", "1");
        defaultHeaders.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36 OPR/119.0.0.0");

        return defaultHeaders;

        //.header("Accept-Language", "pt-BR,pt;q=0.8") // missing
        //.header("Accept-Encoding", "gzip,deflate,sdch") // missing
        //.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1700.107 Safari/537.36") // missing

    }


    
}
