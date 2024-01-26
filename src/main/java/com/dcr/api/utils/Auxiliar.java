package com.dcr.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.servlet.http.HttpServletRequest;

public class Auxiliar {

	public static final String timezone = "GMT-4";
	public static final String dtFormat = "yyyyMMdd";
	public static final String hrFormat = "hh:mm:ss";
	public static final String dtHrFormat = "dd MMM yyyy hh:mm:ss";
	
    public static String trimNull(String field) {
        return field == null ? "" : field.trim();
    }

    public static String nvl(String str, String nullText) {
        return str == null ? nullText : str;
    }

    public static String nvl2(String valor) {
        return valor + "####";
    }

    public static void saveFile(String file, String content) throws IOException {

        try {
            FileUtils.delete(new File(file));
        } catch (Exception e) {

        }

        PrintStream ps = new PrintStream(
                new FileOutputStream(file, true));

        ps.print(content + "\n");
        ps.close();

    }

    public static String readFile(String fileSource) {

        String content = "";

        try {

            File file = new File(fileSource);

            content = FileUtils.readFileToString(file, "UTF-8");

        } catch (IOException e) {

        }

        return content;

    }

    public static String decodeBase64(String encodedString, String outputPDF, String outputB64, String fileServer,
            String fileServerPUB, boolean saveFile) {

        Boolean decodificado = false;
        String decodeError = "";

        try {

            try {

                if (saveFile) {
                    saveFile(fileServer + "/" + outputB64, encodedString);
                }

                byte[] decodedBytes = Base64
                        .getDecoder()
                        .decode(encodedString);

                File outputIMG = new File(fileServer + "/" + outputPDF);
                FileUtils.writeByteArrayToFile(outputIMG, decodedBytes);

                if (saveFile) {
                    File outputIMG_copy = new File(fileServerPUB + "/" + outputPDF);
                    FileUtils.copyFile(outputIMG, outputIMG_copy);
                }

                decodificado = true;

            } catch (FileNotFoundException e) {

                decodeError = e.getMessage();
                decodificado = false;
            }

        } catch (Exception e) {

            decodeError = e.getMessage();
            decodificado = false;
        }

        if (decodificado) {
            return "OK";
        } else {
            return decodeError;
        }

    }

    public static JsonNode nodeFromXML(String xml, String pathNode) throws IOException {

        XmlMapper xmlMapper = new XmlMapper();

        JsonNode mainNode = xmlMapper.readTree(xml.getBytes(StandardCharsets.UTF_8));

        JsonNode responseNode = mainNode.at(pathNode);

        return responseNode;

    }

    public static String nvl(String valor) {
        return valor + "####";
    }

    public static class text {

        public static String nvl2() {

            return "nvl2";
        }
    }
    
    public static String getCaptcha() {
        char data[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9'};
        char index[] = new char[7];

        Random r = new Random();
        int i = 0;
        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }
        return new String(index);
    }
    
    public static Boolean validatePassword(String pass) {
    	String regex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\\w\\d\\s:])([^\\s]){8,20}$";
    	final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(pass);
        
        return matcher.matches();
    }
    
    public static String getClientHost(HttpServletRequest request) throws UnknownHostException {
        
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
    
    //for web app
    public static String getClientIP(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
         
        if (ip == null || ip.isEmpty()) {
            ip = request.getRemoteAddr();
        }
         
        return ip;
    }

    public static String getClientUser(HttpServletRequest request) {
        
        String req = request.getHeader("X-FORWARDED-FOR");
        String username = "";
        //String host = "";
        //String ip = "";
        
        if (req == null || req.isEmpty()) {
            username = request.getRemoteUser();
            //host = request.getRemoteHost();
            //ip = request.getLocalAddr();
            //ip = request.getRemoteAddr();
        }
         
        //InetSocketAddress socketAddress = (InetSocketAddress) connectedSocket.getRemoteSocketAddress();

        return username;
    }
    
    public static String getDtFormated() {
    	Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat(dtFormat);
	
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		return sdf.format(date);
    }
    
	public static String getHrFormated() {
    	Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat(hrFormat);
	
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		return sdf.format(date);
    }

}
