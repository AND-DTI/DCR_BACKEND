package com.dcr.api.configs.security;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.auth.AuthScope;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;



@Configuration
public class Security {

    //@Value("${data.proxy.host}")
    //static String ENV_PROXY_HOST;
    //@Value("${data.proxy.port}")
    //static int ENV_PROXY_PORT;
    //@Value("${data.api_se.api_gateway}")
    //static String APIGATEWAY_SE;

    @Autowired
    Environment env;


    public static HttpClient configProxyClient(HttpClient client) {

        String PROXY_HOST = "gateway.zscloud.net";
        int PROXY_PORT = 443;

        HostConfiguration config = client.getHostConfiguration();
        config.setProxy(PROXY_HOST, PROXY_PORT);

        AuthScope authScope = new AuthScope(PROXY_HOST, PROXY_PORT);
        client.getState().setProxyCredentials(authScope, null);

        return client;

    }


    public HttpClient configProxyClient2(HttpClient client) {

        String PROXY_HOST = "";
        int PROXY_PORT = 0;

        try {
            PROXY_HOST = env.getProperty("data.proxy.host");
            PROXY_PORT = Integer.valueOf(env.getProperty("data.proxy.port"));

            HostConfiguration config = client.getHostConfiguration();
            config.setProxy(PROXY_HOST, PROXY_PORT);

            AuthScope authScope = new AuthScope(PROXY_HOST, PROXY_PORT);
            client.getState().setProxyCredentials(authScope, null);

        } catch (Exception e) {

        }

        return client;

    }


    public String getAuthenticationHeader(String module) {

        String apiToken = env.getProperty("data.api_se.api_gateway");
        return apiToken;

    }

    
    public static String getDefaultPass(String module) {

        String pass = "";

        if (module.equals("SESUITE")) {
            pass = "Manaus@01";
        }

        return pass;
    }

}