package com.dcr.api.utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;



public class FileUtil {
    

    public void saveFile(String content, String name_and_extension, String path, String encode) throws IOException{

        String finalFile = path+"\\"+name_and_extension;
        PrintStream ps = new PrintStream(finalFile, encode);
        ps.print(content);        
        ps.close();

    }    


    public /*static*/ void modifyFile(String filePath, String oldString, String newString){

        File fileToBeModified = new File(filePath);         
        String oldContent = "";         
        BufferedReader reader = null;         
        FileWriter writer = null;
         
        try{

            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent             
            String line = reader.readLine();
             
            while (line != null) 
            {
                
                line = line.replaceAll("^\"|\"$", ""); //remove quotes from then beginning and end of the String
                oldContent = oldContent + line + System.lineSeparator();                
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent             
            String newContent = oldContent.replaceAll(oldString, newString);
             
            //Rewriting the input text file with newContent             
            writer = new FileWriter(fileToBeModified);             
            writer.write(newContent);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally  {
            try  {                
                reader.close();                 
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }    
 

    public static List<String> getFiles(String path, String prefix, String extension){


        List<String> files = new ArrayList<String>();
        File dir = new File(path);
        File[] foundFiles = dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.startsWith(prefix) && name.endsWith(extension);
            }
        });
        
        for (File file : foundFiles) {
            files.add(file.getName());
        }   
        //Of course, change the condition in the accept() method to whatever you need. 
        //So maybe name.startsWith("Ab-") && name.endsWith(".txt").


        return files;

        
    }


    public static int movFiles(List<String> files, String origem, String destino, Log log) throws IOException{
    

        String fileName="";
        int erros=0;        
        

        try{
        
            for(String file : files){
                
                fileName = file;    
                //Copy source file            
                File original = new File(origem+"//"+file);
                File copied = new File(destino+"//"+file);
                FileUtils.moveFile(original, copied);
                //FileUtils.copyFile(original, copied);

            }

        }catch(IOException e){
            log.saveLog("Erro ao mover arqruivo "+fileName+" (origem/destino: "+origem+"; "+destino+")! ["+e+"]"); 
            erros++;
        }


        return erros;
    
    }

    
    public static int movFile(String file, String origem, String destino, Log log) throws IOException{
    

        String fileName="";
        int erros=0;        
        

        try{
                                  
            fileName = file;    
            //Copy source file            
            File original = new File(origem+"//"+file);
            File copied = new File(destino+"//"+file);
            FileUtils.moveFile(original, copied);                
           

        }catch(IOException e){
            log.saveLog("Erro ao mover arqruivo "+fileName+" (origem/destino: "+origem+"; "+destino+")! ["+e+"]"); 
            erros++;
        }


        return erros;
    
    }



    public static FileProperties setFileProperties(String file_name) throws IOException{

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String fileYar, fileMonth, fileDay, fileTime, fileYYYYMMDD, user_file;
        FileProperties fp = new FileProperties();

        try{
                
            Path file_path = Paths.get(file_name);
            Path fName = file_path.getFileName();
            File carga_deletion = new File(file_name);
            String fileDate = sdf.format(carga_deletion.lastModified());            
            //System.out.println("File last modified date " + fileDate);

            fileDay = fileDate.substring(0, 2);
            fileMonth = fileDate.substring(3, 5);
            fileYar = fileDate.substring(6, 10);
            fileTime = fileDate.substring(11, 19);
            fileYYYYMMDD = fileYar+fileMonth+fileDay;                                                
            UserPrincipal owner = Files.getOwner(file_path);            
            user_file = owner.getName().replace("SAU\\", "");

            fp.setName(fName.toString());
            fp.setDateModified(fileYYYYMMDD);
            fp.setTimeModified(fileTime);
            fp.setOwner(user_file);

        }catch(Exception e){
            //logAUX.saveLog("Erro ao pegar propriedades do arqruivo ("+file_name+")! ["+e+"]"); 
        }        

        return fp;

    }


}
