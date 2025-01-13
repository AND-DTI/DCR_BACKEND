package com.dcr.api.model.dto; 

public class PendastecDTO3 {
        
    private Integer idmatriz;   
    private String partnum;   
    private Integer numpend;
    
    private String cdpend;
    private String obsresol;
    private Integer status;

    private Long flex1flw;	
	private Long flex2flw;		
	private String flex3flw;	
	private String flex4flw;
	private String flex5flw;


    public PendastecDTO3(Integer idmatriz, String partnum, String cdpend, String flex5flw){
        this.idmatriz = idmatriz;
        this.partnum = partnum;
        this.cdpend = cdpend;
        this.obsresol = "";
        this.flex1flw = 0L;
        this.flex2flw = 0L;
        this.flex3flw = "";
        this.flex4flw = "";
        this.flex5flw = flex5flw;
    }

    public Integer getIdmatriz() {
        return idmatriz;
    }
    public void setIdmatriz(Integer idmatriz) {
        this.idmatriz = idmatriz;
    }
    public String getPartnum() {
        return partnum;
    }
    public void setPartnum(String partnum) {
        this.partnum = partnum;
    }
    public Integer getNumpend() {
        return numpend;
    }
    public void setNumpend(Integer numpend) {
        this.numpend = numpend;
    }
    public String getCdpend() {
        return cdpend;
    }
    public void setCdpend(String cdpend) {
        this.cdpend = cdpend;
    }
    public String getObsresol() {
        return obsresol;
    }
    public void setObsresol(String obsresol) {
        this.obsresol = obsresol;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Long getFlex1flw() {
        return flex1flw;
    }
    public void setFlex1flw(Long flex1flw) {
        this.flex1flw = flex1flw;
    }
    public Long getFlex2flw() {
        return flex2flw;
    }
    public void setFlex2flw(Long flex2flw) {
        this.flex2flw = flex2flw;
    }
    public String getFlex3flw() {
        return flex3flw;
    }
    public void setFlex3flw(String flex3flw) {
        this.flex3flw = flex3flw;
    }
    public String getFlex4flw() {
        return flex4flw;
    }
    public void setFlex4flw(String flex4flw) {
        this.flex4flw = flex4flw;
    }
    public String getFlex5flw() {
        return flex5flw;
    }
    public void setFlex5flw(String flex5flw) {
        this.flex5flw = flex5flw;
    }
    
    


}
