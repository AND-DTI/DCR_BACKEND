package com.dcr.api.utils;


public class FileProperties {
    

    private String name;
    private String dateModified;
    private String timeModified;
    private String owner;
    
    
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDateModified() {
        return dateModified;
    }
    public void setDateModified(String dateModified) {
        this.dateModified = dateModified;
    }
    public String getTimeModified() {
        return timeModified;
    }
    public void setTimeModified(String timeModified) {
        this.timeModified = timeModified;
    }
    public String getOwner() {
        return owner;
    }
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString(){        
        return "File [name:"+this.name+"; date modified:"+this.dateModified+" "+this.timeModified+"; owner:"+this.owner;
    } 

}
