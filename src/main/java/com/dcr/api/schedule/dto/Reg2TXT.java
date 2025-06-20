package com.dcr.api.schedule.dto;

public class Reg2TXT {
    
    private Integer numcomp;    // NUM_COMPONENTE_NACIONAL	    02	05	04 (pos ini/fim/tam)
    private Long numnf;         // NUM_NOTA_FISCAL	            06	15	10
    private String sernf;       // NUM_SERIE_NF	                16	20	05
    private String cnpjfor;     // CNPJ_FORNECEDOR	            21	34	14
    private String ie;          // INSCRIÇÃO_ESTADUAL	        35	49	15
    private String eminf;       // DATA_EMISSAO_NF	            50	57	08 (AAAAMMDD)
    private String espec;       // ESPECIFICACAO	            58	137	80
    private String undcom;      // UNIDADE_COMERCIAL	        138	217	80
    private String ncm;         // NCM	                        218	225	08
    private Double qtde;        // QUANTIDADE                   226	240	15 (7deci)
    private Double vlrunit;     // CUSTO_UNITARIO	            241	255	15 (6deci)
    
    public Reg2TXT(Integer numcomp, Long numnf, String sernf, String cnpjfor, String ie, String eminf, String espec, String undcom, String ncm, Double qtde, Double vlrunit) {
        this.numcomp = numcomp;
        this.numnf = numnf;
        this.sernf = sernf;
        this.cnpjfor = cnpjfor;
        this.ie = ie;
        this.eminf = eminf;
        this.espec = espec;
        this.undcom = undcom;
        this.ncm = ncm;
        this.qtde = qtde;
        this.vlrunit = vlrunit;
    }

    public Integer getNumcomp() {
        return numcomp;
    }
    public void setNumcomp(Integer numcomp) {
        this.numcomp = numcomp;
    }
    public Long getNumnf() {
        return numnf;
    }
    public void setNumnf(Long numnf) {
        this.numnf = numnf;
    }
    public String getSernf() {
        return sernf;
    }
    public void setSernf(String sernf) {
        this.sernf = sernf;
    }
    public String getCnpjfor() {
        return cnpjfor;
    }
    public void setCnpjfor(String cnpjfor) {
        this.cnpjfor = cnpjfor;
    }
    public String getIe() {
        return ie;
    }
    public void setIe(String ie) {
        this.ie = ie;
    }
    public String getEminf() {
        return eminf;
    }
    public void setEminf(String eminf) {
        this.eminf = eminf;
    }
    public String getEspec() {
        return espec;
    }
    public void setEspec(String espec) {
        this.espec = espec;
    }
    public String getUndcom() {
        return undcom;
    }
    public void setUndcom(String undcom) {
        this.undcom = undcom;
    }
    public String getNcm() {
        return ncm;
    }
    public void setNcm(String ncm) {
        this.ncm = ncm;
    }
    public Double getQtde() {
        return qtde;
    }
    public void setQtde(Double qtde) {
        this.qtde = qtde;
    }
    public Double getVlrunit() {
        return vlrunit;
    }
    public void setVlrunit(Double vlrunit) {
        this.vlrunit = vlrunit;
    }
    

}
