package com.dcr.api.schedule.dto;
import java.util.List;


public class TXTShowa {
    
    //REG 0
    private String dcre;        // from file properties
    private String dtdcre;      // from file properties
    private String cnpj;        // 02	15	14
    private String ppb;         // 27	106	80
    private String denom;       // 107	186	80
    private String ncm;         // 187	194	08
    private String undcom;      // 195	274	80
    private String peso;        // 275	288	14; 5deci
    private String tpdcre;      // 319	319	01
    private String dcrant;      // 320	329	10
    private String origdcr;     // 351	351	1
    private String tpcoef;      // 352	352	1
    //REG 1
    private String modelo;      // 02	05	04
    private String descricao;   // 06	85	80
    private String preco;       // 86	100	15; 2deci
    private String codint;      // 101	115	15
    private String cdclient;    //from field "descricao" after first "/" obs.: //insert into reg0 and reg1

    private List<Reg2TXT> reg2;
    private List<Reg3TXT> reg3;
    private List<Reg4TXT> reg4;
    
    public String getDcre() {
        return dcre;
    }
    public void setDcre(String dcre) {
        this.dcre = dcre;
    }
    public String getDtdcre() {
        return dtdcre;
    }
    public void setDtdcre(String dtdcre) {
        this.dtdcre = dtdcre;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getPpb() {
        return ppb;
    }
    public void setPpb(String ppb) {
        this.ppb = ppb;
    }
    public String getDenom() {
        return denom;
    }
    public void setDenom(String denom) {
        this.denom = denom;
    }
    public String getNcm() {
        return ncm;
    }
    public void setNcm(String ncm) {
        this.ncm = ncm;
    }
    public String getUndcom() {
        return undcom;
    }
    public void setUndcom(String undcom) {
        this.undcom = undcom;
    }
    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getTpdcre() {
        return tpdcre;
    }
    public void setTpdcre(String tpdcre) {
        this.tpdcre = tpdcre;
    }
    public String getDcrant() {
        return dcrant;
    }
    public void setDcrant(String dcrant) {
        this.dcrant = dcrant;
    }
    public String getOrigdcr() {
        return origdcr;
    }
    public void setOrigdcr(String origdcr) {
        this.origdcr = origdcr;
    }
    public String getTpcoef() {
        return tpcoef;
    }
    public void setTpcoef(String tpcoef) {
        this.tpcoef = tpcoef;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getPreco() {
        return preco;
    }
    public void setPreco(String preco) {
        this.preco = preco;
    }
    public String getCodint() {
        return codint;
    }
    public void setCodint(String codint) {
        this.codint = codint;
    }
    public String getCdclient() {
        return cdclient;
    }
    public void setCdclient(String cdclient) {
        this.cdclient = cdclient;
    }
    public List<Reg2TXT> getReg2() {
        return reg2;
    }
    public void setReg2(List<Reg2TXT> reg2) {
        this.reg2 = reg2;
    }
    public List<Reg3TXT> getReg3() {
        return reg3;
    }
    public void setReg3(List<Reg3TXT> reg3) {
        this.reg3 = reg3;
    }
    public List<Reg4TXT> getReg4() {
        return reg4;
    }
    public void setReg4(List<Reg4TXT> reg4) {
        this.reg4 = reg4;
    }

    //Not userd: 
    //  REG0: IDREG, CPFRL, SALARIOS, ENCARGOS, PROCRETIF, VRSPGD
            
        

}
