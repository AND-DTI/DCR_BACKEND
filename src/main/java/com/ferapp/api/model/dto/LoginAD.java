package com.ferapp.api.model.dto;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;

public class LoginAD {

    private String user;
    private String userdns;
    private String userip;

    private List<RoleDTO> roles = new ArrayList<RoleDTO>();

    public LoginAD(HttpServletRequest request) {

        try {

            this.user = getClientUser(request);
            this.userdns = getClientHost(request);
            this.userip = getClientIP(request);

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserdns() {
        return userdns;
    }

    public void setUserdns(String userdns) {
        this.userdns = userdns;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    private String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");

        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }

    private String getClientUser(HttpServletRequest request) {

        String req = request.getHeader("X-FORWARDED-FOR");
        String username = "";

        if (req == null || req.isEmpty()) {
            username = request.getRemoteUser();

        }

        return username;
    }

    private String getClientHost(HttpServletRequest request) throws UnknownHostException {

        String req = request.getHeader("X-FORWARDED-FOR");
        String ip = "";
        String host = "";

        if (req == null || req.isEmpty()) {
            host = request.getRemoteHost();
            ip = request.getRemoteAddr();
        }

        InetAddress addr = InetAddress.getByName(ip);
        host = addr.getHostName().replace(".sa.mds.honda.com", "");
        host = host.substring(0, host.length());

        return host;
    }

}
