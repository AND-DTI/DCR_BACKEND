package com.dcr.api.model.projection;
import java.util.List;




public class CadtppendJoinPendrespDTO {

    private String cdpend;
    private String descpend;
    private String obspend;
    private String tpreg;
    private List<PendrespProjection> responsaveis;
  
    public String getCdpend() {
        return cdpend;
    }
    public void setCdpend(String cdpend) {
        this.cdpend = cdpend;
    }
    public String getDescpend() {
        return descpend;
    }
    public void setDescpend(String descpend) {
        this.descpend = descpend;
    }
    public String getObspend() {
        return obspend;
    }
    public void setObspend(String obspend) {
        this.obspend = obspend;
    }
    public String getTpreg() {
        return tpreg;
    }
    public void setTpreg(String tpreg) {
        this.tpreg = tpreg;
    }
    public List<PendrespProjection> getResponsaveis() {
        return responsaveis;
    }
    public void setResponsaveis(List<PendrespProjection> responsaveis) {
        this.responsaveis = responsaveis;
    }
   
    
}
