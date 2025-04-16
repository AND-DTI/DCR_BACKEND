package com.dcr.api.model.projection;


/*public interface PendrespProjection {

    String getCdpend();    
    String getSubtipo();
    String getCdresp();
    String getNmresp();
    
}*/

public class PendrespProjection {
    
    private String cdpend;
    private String subtipo;
    private String cdresp;
    private String nmresp;
    
    public String getCdpend() {
        return cdpend;
    }
    public void setCdpend(String cdpend) {
        this.cdpend = cdpend;
    }
    public String getSubtipo() {
        return subtipo;
    }
    public void setSubtipo(String subtipo) {
        this.subtipo = subtipo;
    }
    public String getCdresp() {
        return cdresp;
    }
    public void setCdresp(String cdresp) {
        this.cdresp = cdresp;
    }
    public String getNmresp() {
        return nmresp;
    }
    public void setNmresp(String nmresp) {
        this.nmresp = nmresp;
    }
    
}
