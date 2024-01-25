package com.dcr.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Auxiliar {

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
}
