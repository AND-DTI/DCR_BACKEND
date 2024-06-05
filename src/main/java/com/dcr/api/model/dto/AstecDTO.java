package com.dcr.api.model.dto;

import com.dcr.api.utils.Auxiliar;

public class AstecDTO {
    

    private String partnumpd;   
    private String desccom;
    private String descrfb;
    private String prddest;
    private String ppbprd;
    private String tpprd;
    private String dscpor;
    private String flex4flw;

    public String getPartnumpd() {
        return Auxiliar.trimNull(partnumpd);
    }
    public void setPartnumpd(String partnumpd) {
        this.partnumpd = partnumpd;
    }
    public String getDesccom() {
        return Auxiliar.trimNull(desccom);
    }
    public void setDesccom(String desccom) {
        this.desccom = desccom;
    }
    public String getDescrfb() {
        return Auxiliar.trimNull(descrfb);
    }
    public void setDescrfb(String descrfb) {
        this.descrfb = descrfb;
    }
    public String getPrddest() {
        return Auxiliar.trimNull(prddest);
    }
    public void setPrddest(String prddest) {
        this.prddest = prddest;
    }
    public String getPpbprd() {
        return Auxiliar.trimNull(ppbprd);
    }
    public void setPpbprd(String ppbprd) {
        this.ppbprd = ppbprd;
    }
    public String getTpprd() {
        return Auxiliar.trimNull(tpprd);
    }
    public void setTpprd(String tpprd) {
        this.tpprd = tpprd;
    }
    public String getDscpor() {
        return Auxiliar.trimNull(dscpor);
    }
    public void setDscpor(String dscpor) {
        this.dscpor = dscpor;
    }
    public String getFlex4flw() {
        return Auxiliar.trimNull(flex4flw);
    }
    public void setFlex4flw(String flex4flw) {
        this.flex4flw = flex4flw;
    }


}
