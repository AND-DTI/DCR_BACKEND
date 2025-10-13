package com.dcr.api.service;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.dcr.api.utils.DateUtil;




@Service
public class AuditoriaService {

    @Value("${app.code}")
    private String systemAudit;
    @Value("${as400.datasource.username}")
    private String DB_USERSERVICE;
    

    
    
    public void preencheAuditoria(Object obj) throws Exception {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String logedUser = (String) authentication.getPrincipal();
        InetAddress addr = InetAddress.getLocalHost();

        Class<?> classe = obj.getClass();
                        
        Field itaudsys = classe.getDeclaredField("itaudsys");
        itaudsys.setAccessible(true);
        itaudsys.set(obj, systemAudit);
        
        Field itaudusr = classe.getDeclaredField("itaudusr");
        itaudusr.setAccessible(true);
        itaudusr.set(obj, logedUser);        
        
        Field itaudhst = classe.getDeclaredField("itaudhst");
        itaudhst.setAccessible(true);
        itaudhst.set(obj, addr.getHostName());        
        
        Field itauddt = classe.getDeclaredField("itauddt");
        itauddt.setAccessible(true);        
        itauddt.set(obj, DateUtil.format("yyyyMMdd"));
        
        Field itaudhr = classe.getDeclaredField("itaudhr");
        itaudhr.setAccessible(true);
        itaudhr.set(obj, DateUtil.format("HH:mm:ss"));
        
    }


    public void preencheAuditoriaNoUser(Object obj) throws Exception {
                        
        InetAddress addr = InetAddress.getLocalHost();

        Class<?> classe = obj.getClass();
                        
        Field itaudsys = classe.getDeclaredField("itaudsys");
        itaudsys.setAccessible(true);
        itaudsys.set(obj, systemAudit);
        
        Field itaudusr = classe.getDeclaredField("itaudusr");
        itaudusr.setAccessible(true);
        itaudusr.set(obj, DB_USERSERVICE);        
        
        Field itaudhst = classe.getDeclaredField("itaudhst");
        itaudhst.setAccessible(true);
        itaudhst.set(obj, addr.getHostName());        
        
        Field itauddt = classe.getDeclaredField("itauddt");
        itauddt.setAccessible(true);        
        itauddt.set(obj, DateUtil.format("yyyyMMdd"));
        
        Field itaudhr = classe.getDeclaredField("itaudhr");
        itaudhr.setAccessible(true);
        itaudhr.set(obj, DateUtil.format("HH:mm:ss"));
        
    }


    public String getUser() throws Exception {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String logedUser = (String) authentication.getPrincipal();
        
        return logedUser;

    }

    public String getSysname() {
                
        return systemAudit;

    }

    public String getHostname() throws UnknownHostException {
                
        return InetAddress.getLocalHost().getHostName();

    }

}