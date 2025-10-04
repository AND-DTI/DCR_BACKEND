package com.dcr.api.rpa.response;
import java.util.Map;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;




public class SessionResponse {
    

    private Integer status;
    private String message;
    private Map<String, String> loginCookies;
    private Map<String, String> defaultHeaders;
    private Browser browser;
    private Page page;
    private String host;
    
    

    public SessionResponse(Integer status, String message, Map<String, String> loginCookies) {
        this.status = status;
        this.message = message;
        this.loginCookies = loginCookies;
    }

    public SessionResponse() {
    }
    
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Map<String, String> getLoginCookies() {
        return loginCookies;
    }
    public void setLoginCookies(Map<String, String> loginCookies) {
        this.loginCookies = loginCookies;
    }

    public Map<String, String> getDefaultHeaders() {        return defaultHeaders;    }
    public void setDefaultHeaders(Map<String, String> defaultHeaders) {
        this.defaultHeaders = defaultHeaders;
    }

    public Browser getBrowser() {        return browser;    }
    public void setBrowser(Browser browser) {
        this.browser = browser;
    }

    public Page getPage() {        return page;    }
    public void setPage(Page page) {
        this.page = page;
    }

    public String getHost() {        return host;    }
    public void setHost(String host) {
        this.host = host;
    }


}
