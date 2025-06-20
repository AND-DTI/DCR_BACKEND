package com.dcr.api.utils;
import java.time.LocalDateTime;
import java.io.*;


public class Log {
    
                
    private String logName;
    private String logPath;
    private String sysID;
    
    public String getSysID() {
        return sysID;
    }
    public void setSysID(String sysID) {
        this.sysID = sysID;
    }
    public String getLogName() {
        return logName;
    }
    public void setLogName(String logName) {
        this.logName = logName;
    }
    public String getLogPath() {
        return logPath;
    }
    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }
        
    public Log(String logName, String logPath, boolean sobrescrever, String sysID) {    
        this.logName = logName;
        this.logPath = logPath;
        this.sysID = sysID;
    }

    @Override
    public String toString() {        
        LocalDateTime agora = LocalDateTime.now();
        return "Log [LogDate=" + agora + ", logName=" + logName + ", logPath=" + logPath + "]";
    }
    

    public void saveLog(String msg) throws IOException{
        
        String fLog = logPath+"\\"+logName+".log";
        String finalMSG = LocalDateTime.now() + " ["+sysID+"] --> "+ msg;

        PrintStream ps = new PrintStream(
            new FileOutputStream(fLog, true)
        ); 
        
        ps.println(); 
        ps.print(finalMSG);        
        ps.close();

    }


    public void saveLogOver(String msg) throws IOException{

        String fLog = logPath+"\\"+logName+".log";
        String finalMSG = LocalDateTime.now() + " ["+sysID+"] --> "+ msg;                    
        PrintStream ps = new PrintStream(fLog);                

        ps.print(finalMSG);        
        ps.close();

    }


    public void saveLogID(String ID, String msg) throws IOException{

        String fLog = logPath+"\\"+logName+".log";        
        String finalMSG = LocalDateTime.now() + " ["+sysID+" "+ID+"] --> "+ msg;

        PrintStream ps = new PrintStream(
            new FileOutputStream(fLog, true)
        ); 
        
        ps.println(); 
        ps.print(finalMSG);        
        ps.close();

    }


    public void saveLogOverID(String ID, String msg) throws IOException{

        String fLog = logPath+"\\"+logName+".log";
        String finalMSG = LocalDateTime.now() + " ["+sysID+" "+ID+"] --> "+ msg;
        PrintStream ps = new PrintStream(fLog);                

        ps.print(finalMSG);        
        ps.close();

    }
        


}
