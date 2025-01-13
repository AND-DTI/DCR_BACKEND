package com.dcr.api.model.dto;

public class CorProdutoDTO {
    

    private Long idmatriz;
    private String partnumpd;
    private String modelo; 
    private String codcor;
    private String partdesc;
    private String unmed;
    private Double preco; 
    //private String ncm; 
    private int priocor;
    private String cdbej;
    private String corpt;
    private String coreng;
    private String tppin;
    private int statusproc;
        
    public Long getIdmatriz() {
        return idmatriz;
    }
    public void setIdmatriz(Long idmatriz) {
        this.idmatriz = idmatriz;
    }
    public String getPartnumpd() {
        return partnumpd;
    }
    public void setPartnumpd(String partnumpd) {
        this.partnumpd = partnumpd;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getCodcor() {
        return codcor;
    }
    public void setCodcor(String codcor) {
        this.codcor = codcor;
    }
    public String getPartdesc() {
        return partdesc;
    }
    public void setPartdesc(String partdesc) {
        this.partdesc = partdesc;
    }
    public String getUnmed() {
        return unmed;
    }
    public void setUnmed(String unmed) {
        this.unmed = unmed;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    //public String getNcm() {        return ncm;    }
    //public void setNcm(String ncm) {        this.ncm = ncm;    }

    public int getStatusproc() {        return statusproc;    }
    public void setStatusproc(int statusproc) {        this.statusproc = statusproc;    }

    public int getPriocor() {
        return priocor;
    }
    public void setPriocor(int priocor) {
        this.priocor = priocor;
    }
    public String getCdbej() {
        return cdbej;
    }
    public void setCdbej(String cdbej) {
        this.cdbej = cdbej;
    }
    public String getCorpt() {
        return corpt;
    }
    public void setCorpt(String corpt) {
        this.corpt = corpt;
    }
    public String getCoreng() {
        return coreng;
    }
    public void setCoreng(String coreng) {
        this.coreng = coreng;
    }
    public String getTppin() {
        return tppin;
    }
    public void setTppin(String tppin) {
        this.tppin = tppin;
    }
    


}
