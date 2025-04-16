package com.dcr.api.model.dto;
import java.util.List;
import com.dcr.api.model.as400.Dcrreg0;
import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.as400.Dcrreg2;
import com.dcr.api.model.as400.Dcrreg3;
import com.dcr.api.model.as400.Dcrreg4;
import com.dcr.api.model.as400.Dcrreg9;



public class RegistroLoteDTO {
    
    private Integer idmatriz;		
	private String partnumpd;		
	private String tpprd;		
	
    private Dcrreg0 reg0;
    private List<Dcrreg1> reg1;
    private List<Dcrreg2> reg2;
    private List<Dcrreg3> reg3;
    private List<Dcrreg4> reg4;
    private Dcrreg9 reg9;

    

    public Integer getIdmatriz() {
        return idmatriz;
    }
    public void setIdmatriz(Integer idmatriz) {
        this.idmatriz = idmatriz;
    }
    public String getPartnumpd() {
        return partnumpd;
    }
    public void setPartnumpd(String partnumpd) {
        this.partnumpd = partnumpd;
    }
    public String getTpprd() {
        return tpprd;
    }
    public void setTpprd(String tpprd) {
        this.tpprd = tpprd;
    }
    public Dcrreg0 getReg0() {
        return reg0;
    }
    public void setReg0(Dcrreg0 reg0) {
        this.reg0 = reg0;
    }
    public List<Dcrreg1> getReg1() {
        return reg1;
    }
    public void setReg1(List<Dcrreg1> reg1) {
        this.reg1 = reg1;
    }
    public List<Dcrreg2> getReg2() {
        return reg2;
    }
    public void setReg2(List<Dcrreg2> reg2) {
        this.reg2 = reg2;
    }
    public List<Dcrreg3> getReg3() {
        return reg3;
    }
    public void setReg3(List<Dcrreg3> reg3) {
        this.reg3 = reg3;
    }
    public List<Dcrreg4> getReg4() {
        return reg4;
    }
    public void setReg4(List<Dcrreg4> reg4) {
        this.reg4 = reg4;
    }
    
    public Dcrreg9 getReg9() {        return reg9;    }
    public void setReg9(Dcrreg9 reg9) {        this.reg9 = reg9;    }
        
}
