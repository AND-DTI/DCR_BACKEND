package com.dcr.api.utils;
//import java.util.ArrayList;
//import java.util.List;
/*import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;*/
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
//import com.api4g.api.configs.security.Security;




@Configuration
public class RequestUtil {



    @Autowired //**works only with annotation @COnfiguration
    Environment env;
    //@Autowired
    //Security sec;

 

    /*public String[] getRequest(String url, String[][] payload, String body){


        HttpGet request = null;
        String[] responseBody = {"0", ""};

        try {
            
            //Config Proxy
            request = new HttpGet(url);
            Boolean setProxy = Boolean.valueOf(env.getProperty("data.setproxy"));
            if(setProxy){ request.setConfig(sec.configProxyClient()); }


            //Set header       
            request.setHeader("Content-Type", "application/json");
            //request.setHeader("Authorization", sec.getAuthenticationHeader("SEsuite"));
            
            
            //Set body:
            //String jsonParams = "{ \"IDUSER\":"+iduser+" }"; //can use IDUSER="-"  
            //StringEntity strEntity = new StringEntity(jsonParams, "text/xml; charset=utf-8");
            //request.setEntity(strEntity);  
                        
            //Call request                                    
            HttpClient client = HttpClientBuilder.create().build(); 
            responseBody = client.execute(request, responseHandler);

           
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            if(request != null){
                request.releaseConnection();
            }  
        }

        return responseBody;
       
    }*/



    /*public String[] postRequest(String url, String[][] payload, String body){


        HttpPost request = null;
        String[] responseBody = {"0", ""};

        try {
            
            //Config Proxy
            request = new HttpPost(url);
            Boolean setProxy = Boolean.valueOf(env.getProperty("data.setproxy"));
            if(setProxy){ request.setConfig(sec.configProxyClient()); }


            //Set header       
            //request.setHeader("Content-Type", "application/json");
            request.setHeader("Content-Type", " application/x-www-form-urlencoded");
            //request.setHeader("Authorization", sec.getAuthenticationHeader("SEsuite"));
            
            
            //Set body:
            //String jsonParams = "{ \"IDUSER\":"+iduser+" }"; //can use IDUSER="-"  
            //StringEntity strEntity = new StringEntity(jsonParams, "text/xml; charset=utf-8");
            //request.setEntity(strEntity);  
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for( String[] rows : payload) {
                params.add(new BasicNameValuePair(rows[0], rows[1]));
            }
            //for (String[] p : payload) {
            //    params.add(new BasicNameValuePair("username", "DEFAULT_USER"));
            //    params.add(new BasicNameValuePair("password", "DEFAULT_PASS"));
            //}
            if(payload.length == 0){
                request.setEntity(new UrlEncodedFormEntity(params));
            }
            
                        
            //Call request                                    
            //HttpClient client = HttpClientBuilder.create().build(); 
            //responseBody = client.execute(request, responseHandler);

            CloseableHttpClient client = HttpClients.createDefault();
            responseBody = client.execute(request, responseHandler);
        

           
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally{
            if(request != null){
                request.releaseConnection();
            }  
        }

        return responseBody;
       
    }*/



    /*public static ResponseHandler<String[]> responseHandler = response -> {

        int status = response.getStatusLine().getStatusCode();                
        String[] resp = {String.valueOf(status), ""};
        if (status >= 200 && status < 300) {
            HttpEntity entity = response.getEntity();
            resp[1] = entity != null ? EntityUtils.toString(entity).trim() : null;
            return resp;
            //return entity != null ? EntityUtils.toString(entity) : null;
        } else {
            throw new ClientProtocolException("Unexpected response status: " + status);
        }
    }*/      



    public static void removeComments(Node node) {
        // as we are removing child nodes while iterating, we cannot use a normal foreach over children,
        // or will get a concurrent list modification error.
        int i = 0;
        while (i < node.childNodes().size()) {
            Node child = node.childNode(i);
            if (child.nodeName().equals("#comment"))
                child.remove();
            else {
                removeComments(child);
                i++;
            }
        }
    }

    public static void cleanDefault(Document doc) {

        removeComments(doc);
        doc.getElementsByTag("head").remove();
        doc.getElementsByTag("script").remove();         

    }



}