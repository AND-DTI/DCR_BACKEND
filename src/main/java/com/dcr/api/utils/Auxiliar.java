package com.dcr.api.utils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.apache.bcel.generic.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import com.dcr.api.model.as400.Dcrlayout;
import com.dcr.api.service.TokenService;
import com.dcr.api.validator.Validator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import jakarta.servlet.http.HttpServletRequest;




public class Auxiliar {


	@Autowired
	TokenService tokenService;
 
	private static final String timezone = "GMT-4";
	private static final String dtFormat = "yyyyMMdd";
	private static final String hrFormat = "HH:mm:ss";
	private static final String hrFormatSemSegundo = "HH:mm";
	private static final String dtHrFormat = "dd MMM yyyy hh:mm:ss";
	

    public static String trimNull(String field) {
        return field == null ? "" : field.trim();
    }


    public static String nvl(String str, String nullText) {
        return str == null ? nullText : str;
    }


    public static String nvl2(String valor) {
        return valor + "####";
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
        char index[] = new char[8];

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
    

    public static String getDtHrFormated() {
    	Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat(dtHrFormat);
	
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		return sdf.format(date);
    }
    

	public static String getHrFormated() {
    	Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat(hrFormat);
	
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		return sdf.format(date);
    }
	

	public static String getHrFormatedSemSegundo() {
    	Date date = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat(hrFormatSemSegundo);
	
		sdf.setTimeZone(TimeZone.getTimeZone(timezone));
		return sdf.format(date);
    }
	

    public static Date getStrToDate(String strDate, String format) throws ParseException{

        //Date date = new Date();
        String timezone = "GMT-3";
        //String dtFormat = "yyyyMMdd";
        //String hrFormat = "HH:mm:ss";
        //String hrFormatSemSegundo = "HH:mm";
        //String dtHrFormat = "dd MM yyyy hh:mm:ss";		
        SimpleDateFormat sdf = new SimpleDateFormat(format);	
        sdf.setTimeZone(TimeZone.getTimeZone(timezone));

        Date dt = sdf.parse(strDate);

        return dt;

    }

    public static String getDateToStr(Date date, String format) throws ParseException{
        
        String timezone = "GMT-3";
        //String dtFormat = "yyyyMMdd";
        //String hrFormat = "HH:mm:ss";
        //String hrFormatSemSegundo = "HH:mm";
        //String dtHrFormat = "dd MM yyyy hh:mm:ss";		
        SimpleDateFormat sdf = new SimpleDateFormat(format);	
        sdf.setTimeZone(TimeZone.getTimeZone(timezone));

        String dt = sdf.format(date);

        return dt;

    }    

    public static Date addDaysToDate(Date currDate, int days){
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(currDate);                              
        cal.add(Calendar.DATE, days);
        Date dtFuture = cal.getTime();  

        return dtFuture;

    }


	public static Boolean validateField(ObjectType type, int size, Object field) {
		if(field.getClass().getTypeName().equals(type.getClassName())) {			
			return Boolean.TRUE;
		}
		if(type.getClassName().equals("java.lang.String")) {
			if(field.toString().length() <= size) {
				return Boolean.TRUE;
			}else { 
				return Boolean.FALSE;
			}
		}
		return Boolean.TRUE;
	}
	

	public static String getUser(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		String atributo = request.getHeader("Authorization");
		String token = atributo.replace("Bearer ", "");
		
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();

		String payload = new String(decoder.decode(chunks[1]));
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(payload);

		return jsonNode.get("sub").asText();
	}


	public static void preencheAuditoria(Object obj, HttpServletRequest request) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, JsonMappingException, JsonProcessingException, UnknownHostException {
        Class<?> classe = obj.getClass();
        
        Validator.validarTamanhos(obj);
        
        Field itaudsys = classe.getDeclaredField("itaudsys");
        itaudsys.setAccessible(true);
        itaudsys.set(obj, "DCR-Backend");
        
        Field itaudusr = classe.getDeclaredField("itaudusr");
        itaudusr.setAccessible(true);
        itaudusr.set(obj, getUser(request));
        
        Field itaudhst = classe.getDeclaredField("itaudhst");
        itaudhst.setAccessible(true);
        itaudhst.set(obj, getClientHost(request));
        
        Field itauddt = classe.getDeclaredField("itauddt");
        itauddt.setAccessible(true);
        itauddt.set(obj, getDtFormated());
        
        Field itaudhr = classe.getDeclaredField("itaudhr");
        itaudhr.setAccessible(true);
        itaudhr.set(obj, getHrFormated());
	}
	

	public static String formatName(String name) {
		
		String[] names = name.split(" ");
		if(names.length > 1) {
			name = names[0] + " " +  names[names.length-1];			
		}
		return name;
	}
	

	public static void formatResponseList(List<?> objects) { //old List objects) @@
		
		for (Object obj : objects) {
			Class<?> clazz = obj.getClass();
			
		    Field[] fields = clazz.getDeclaredFields();
		    	
		    for (Field field : fields) {
		    	
		        if (field.getType().equals(String.class)) {
		            try {
		                field.setAccessible(true);
		                String value = (String) field.get(obj);
		                if (value != null) {
		                    field.set(obj, value.trim());
		                }
		            } catch (IllegalAccessException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}
	}

    public static void formatResponseList2(List<?> objects) { 
		
        if (objects != null ){
            for (Object obj : objects) {
                formatResponse(obj);
            }
        }
        		
	}
	

	public static void formatResponse(Object obj) {		       

		Class<?> type = obj.getClass();
		if(type.equals(ArrayList.class) || type.equals(List.class)) {
			formatResponseList2((List<?>) obj);
			return;
		}
		if(type.equals(Optional.class)) { 
			obj = ((Optional<?>) obj).get(); 
		}
		Class<?> clazz = obj.getClass();
		
	    Field[] fields = clazz.getDeclaredFields();
	    	
	    for (Field field : fields) {
            try {

                field.setAccessible(true);
                Object value = field.get(obj);

                //Format String
                if (field.getType().equals(String.class)) {	                               
                    value = value != null? String.valueOf(value).trim() : "";                    	           
                }

                //Format Integer
                if (field.getType().equals(Integer.class)) {	                               
                    value = value != null? value : 0;                    	           
                }

                //Format Double
                if (field.getType().equals(Double.class)) {	                               
                    value = value != null? value : 0.;                    	           
                }

                //Format Long
                if (field.getType().equals(Long.class)) {	                               
                    value = value != null? value : 0L;                    	           
                }

                field.set(obj, value);

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
	    }

	}

    
    public static void formatResponse_OLD(Object obj) {		       

		Class<?> type = obj.getClass();
		if(type.equals(ArrayList.class) || type.equals(List.class)) {
			formatResponseList((List<?>) obj);
			return;
		}
		if(type.equals(Optional.class)) { 
			obj = ((Optional<?>) obj).get(); //old obj = ((Optional) obj).get();
		}
		Class<?> clazz = obj.getClass();
		
	    Field[] fields = clazz.getDeclaredFields();
	    	
	    for (Field field : fields) {
	    	
	        if (field.getType().equals(String.class)) {
	            try {
	                field.setAccessible(true);
	                String value = (String) field.get(obj);
	                if (value != null) {
	                    field.set(obj, value.trim());
	                }                    
	            } catch (IllegalAccessException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


    /*public static void trimResponseObject(Object obj) {
		

		Class<?> type = obj.getClass();
		
		if(type.equals(Optional.class)) {
			obj = ((Optional) obj).get();
		}
        
		Class<?> clazz = obj.getClass();
		
	    Field[] fields = clazz.getDeclaredFields();
	    	
	    for (Field field : fields) {
	    	
            try {
                Object fieldFormatado;
                String value = (String) field.get(obj);	                
                fieldFormatado = value != null ? value.trim() : value;	
                field.set(obj, fieldFormatado);

                if (field.get(obj).getClass().equals(String.class)) {
                    String val = "";
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }	

	    }
	}*/
	

    /*public static void trimResponseList(List objects){

        for (Object obj : objects) {
			Class<?> clazz = obj.getClass();
			
		    Field[] fields = clazz.getDeclaredFields();
		    	
		    for (Field field : fields) {
		    	
		        if (field.getType().equals(String.class)) {
		            try {
		                field.setAccessible(true);
		                String value = (String) field.get(obj);
		                if (value != null) {
		                    field.set(obj, value.trim());
		                }
		            } catch (IllegalAccessException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		}

    }*/

    
	public static String addCasasDecimais(Object obj, Integer tamanhoMax, Integer casas) {
        DecimalFormat df = new DecimalFormat("0".repeat(tamanhoMax -  casas) + "." + "0".repeat(casas));
        String formatted = df.format(obj).replace(".", "").replace(",", "");
        if (formatted.length() < tamanhoMax) {
            formatted = "0".repeat(tamanhoMax - formatted.length()) + formatted;
        }

        return formatted;
	}
	

	public static String addZeros(Object num, int tamanho) {
        String str = num.toString();
        if (str.length() >= tamanho) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder();
        while (sb.length() + str.length() < tamanho) {
            sb.append('0');
        }
        sb.append(str);
        return sb.toString();
    }
	
	
	public static String addSpaces(Object obj, int tamanho) {
		String str = obj.toString();
        if (str.length() >= tamanho) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < tamanho) {
            sb.append(' ');
        }
        return sb.toString();
    }
	

	public static String filterNumbers(String stringAFiltrar) {
        Pattern padrao = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+");

        Matcher matcher = padrao.matcher(stringAFiltrar);

        StringBuilder numerosString = new StringBuilder();

        while (matcher.find()) {
            numerosString.append(matcher.group()).append("");
        }

        return numerosString.toString();
    }
	

	public static Integer verificarCampoData(String campo) {
        Pattern pattern = Pattern.compile("-\\d+");
        Matcher matcher = pattern.matcher(campo);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group().substring(1)); 
        }

        return -1;
    }
	

	public static String verificarPreenchimento(Object reg, Dcrlayout campo, String campoAcessado) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
			Class<?> classe = reg.getClass();
			Field field = classe.getDeclaredField(campo.getCondfield().toLowerCase().trim());
			field.setAccessible(true);
			
			
			Field camposAcess = classe.getDeclaredField(campoAcessado);
			camposAcess.setAccessible(true);
			
			if(field.get(reg).equals(campo.getCondvalue().toLowerCase().trim()) || field.get(reg).equals(campo.getCondvalue().toUpperCase().trim()) ) {
				
				if(campo.getFillblank() != null) {
					return Auxiliar.addSpaces("", campo.getCampotam());
				}
				if(campo.getFillzero() != null) {
					return Auxiliar.addZeros("", campo.getCampotam());
				}
			}else {
				return Auxiliar.addSpaces(camposAcess.get(reg), campo.getCampotam());
			} 
			
			return Auxiliar.addSpaces(camposAcess.get(reg), campo.getCampotam());
    }


    public static boolean doesObjectContainField(Object object, String fieldName) {
        
        
        //return Arrays.stream(object.getClass().getFields())
        //        .anyMatch(f -> f.getName().equals(fieldName));
              
        
        Class<?> objectClass = object.getClass();
        //Object fields = objectClass.getDeclaredFields();

        for (Field field : objectClass.getDeclaredFields()) { //objectClass.getFields()
            
            //String fn = field.getName();            
            if (field.getName().equals(fieldName)) {
                return true;
            }
        }

        return false;

    }


    public static String removeSpecialChar(String text){

        String newText = text.replace("º", "r");

        newText = StringUtils.stripAccents(newText);
        //newText = newText.replaceAll("[^a-zA-Z0-9]", "");

        return newText;

    }
    

    public static String verificarPreenchimento2(Object reg, Dcrlayout layout, String campo) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        
        String condFieldName = trimNull(layout.getCondfield());
        String condValue = trimNull(layout.getCondvalue()).toUpperCase();
        Boolean fillBlank = trimNull(layout.getFillblank()).equals("S");
        Boolean fillZero = trimNull(layout.getFillzero()).equals("S");
        int fieldSize = layout.getCampotam();
        int casasdec = layout.getCasasdec();
        //Field[] fields = getDeclaredFields(reg.getClass());
        Class<?> classe = reg.getClass();
        campo = campo.toLowerCase().trim();
        Field campoAcessado = null;


        if (doesObjectContainField(reg, campo)){
            campoAcessado = classe.getDeclaredField(campo);  
            campoAcessado.setAccessible(true);         
        }else{
            return "";
        }     
                 

        //Substituição condicional:
        if(!condFieldName.isBlank()){

            Field condField = classe.getDeclaredField(condFieldName.toLowerCase().trim());
            condField.setAccessible(true);

            String valorPreenchido = trimNull(condField.get(reg).toString()).toUpperCase();
            String valorCondicional = condValue;
            if(valorPreenchido.equals(valorCondicional)){
                String campoFormatado = fillZero? addZeros("", fieldSize)
                                        :(fillBlank? addSpaces("", fieldSize)
                                        :campoAcessado.get(reg).toString());
                return campoFormatado;
            }
        
        }
      

        if(fieldSize == 1){    
            return campoAcessado.get(reg).toString();            
            //return campoAcessado.toString();
        }
        

        String type = campoAcessado.getType().equals(String.class)? "String":
                      campoAcessado.getType().equals(Double.class)? "Double"
                      :"Integer";  

        String campoFormatado = campoAcessado.get(reg).toString();        
        if(type.equals("String")){
            campoFormatado = addSpaces(campoAcessado.get(reg), fieldSize);
        }else if(type.equals("Double")){
            campoFormatado = addCasasDecimais(campoAcessado.get(reg), fieldSize, casasdec);
        }else if(type.equals("Integer")){
            campoFormatado = addZeros(campoAcessado.get(reg), fieldSize);
        }
       

        return campoFormatado;

    }



    static final Field[] getDeclaredFields(Class<?> clazz) {
        
        final Field[] fields = clazz.getDeclaredFields();
    
        if ( clazz.getSuperclass() != Object.class ) {
            final Field[] pFields = getDeclaredFields(clazz.getSuperclass());
            final Field[] allFields = new Field[fields.length + pFields.length];
            Arrays.setAll(allFields, i -> (i < pFields.length ? pFields[i] : fields[i - pFields.length]));
            return allFields;
        } else
            return fields;
    }
    

    static List<Field> getAllFields(Class<?> clazz) {

        if (clazz == null) {
            return Collections.emptyList();
        }

        List<Field> result = new ArrayList<>(getAllFields(clazz.getSuperclass()));
        List<Field> filteredFields = Arrays.stream(clazz.getDeclaredFields())
        .filter(f -> Modifier.isPublic(f.getModifiers()) || Modifier.isProtected(f.getModifiers()))
        .collect(Collectors.toList());
        result.addAll(filteredFields);
        return result;
        
    }


    static public int copyFiles(List<String> files, String origem, String destino ) throws IOException{

        int erros=0;

        try{
        

            for(String file : files){
                        
                //Copy source file            
                File original = new File(origem+"//"+file);
                File copied = new File(destino+"//"+file);
                //FileUtils.moveFile(original, copied);
                FileUtils.copyFile(original, copied);

            }


        }catch(IOException e){            
            erros++;
            e.printStackTrace();
        }

        return erros;

    }

}
