package com.dcr.api.configs.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;

@SuppressWarnings("deprecation")
@Component
public class IpAddressValidation implements AccessDecisionVoter<Object> {
	@Autowired
    private HttpServletRequest request;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    private boolean isAllowedIP(String ip) {
        return "127.0.0.0".equals(ip);
    }

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        String ipAddress = request.getRemoteAddr();
        if (!isAllowedIP(ipAddress)) {
            return ACCESS_GRANTED;
        }
        return ACCESS_DENIED;
        
    }

	
}
