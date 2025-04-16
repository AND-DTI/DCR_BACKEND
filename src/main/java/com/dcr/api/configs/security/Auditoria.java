package com.dcr.api.configs.security;
import java.net.UnknownHostException;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;
//import java.lang.reflect.Field;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;



public class Auditoria {


    private String itaudsys;  
    private String itaudusr;  
    private String itaudhst;  
    private String itauddt; 
    private String itaudhr;


    public Auditoria(HttpServletRequest request) 
        throws JsonMappingException, JsonProcessingException, UnknownHostException {

        this.itaudsys = "DCR-Backend";
        this.itaudusr = Auxiliar.getUser(request);
        this.itaudhst = Auxiliar.getClientHost(request);
        this.itauddt =  Auxiliar.getDtFormated();
        this.itaudhr = Auxiliar.getHrFormated(); 

    }


    public String getItaudsys() {        return itaudsys;    }
    public void setItaudsys(String itaudsys) {        this.itaudsys = itaudsys;    }

    public String getItaudusr() {        return itaudusr;    }
    public void setItaudusr(String itaudusr) {        this.itaudusr = itaudusr;    }

    public String getItaudhst() {        return itaudhst;    }
    public void setItaudhst(String itaudhst) {        this.itaudhst = itaudhst;    }

    public String getItauddt() {        return itauddt;    }
    public void setItauddt(String itauddt) {        this.itauddt = itauddt;    }

    public String getItaudhr() {        return itaudhr;    }
    public void setItaudhr(String itaudhr) {        this.itaudhr = itaudhr;    }    


}

//private String data_audit;
//private String hora_audit;
//public String getData_audit() {        return data_audit;    }
//public String getHora_audit() {        return hora_audit;    }

//public Auditoria() {
    //LocalDateTime agora = LocalDateTime.now();
    //this.data_audit = agora.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    //this.hora_audit = agora.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//}    
    