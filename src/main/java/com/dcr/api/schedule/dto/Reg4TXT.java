package com.dcr.api.schedule.dto;

public class Reg4TXT {
    
    private Integer numcomp;    //NUM_COMPONENTE_IMPORTADO	02	05	04
    private char impdireta;     // IN_IMP_DIRETA	        06	06	01
    private char suspens;       // IN_COM_SUSPENSAO	        07	07	01
    private String di;          // NUM_DI	                08	17	10
    private String adicao;      // NUM_ADICAO	            18	20	03
    private String itemadicao;  // NUM_ITEM	                21	22	02
    private Long numnf;         // NUM_NOTA_FISCAL	        23	32	10
    private String sernf;       // NUM_SERIE_NF	            33	37	05
    private String cnpjfor;     // CNPJ_FORNECEDOR	        38	51	14
    private String ie;          // INSCRIÇÃO_ESTADUAL	    52	66	15
    private String eminf;       // DATA_EMISSAO_NF	        67	74	08
    private String espec;       // ESPECIFICACAO	        75	154	80
    private String undcom;      // UNIDADE_COMERCIAL	    155	234	80
    private String ncm;         // NCM	                    235	242	08
    private Double qtde;        // QUANTIDADE	            243	257	15 (7deci)
    private char inreducii;     // IN_REDUÇÃO_II	        258	258	01
    private Double vlrunit;     // CUSTO_UNITARIO	        259	273	15 (6deci)
    
    public Reg4TXT(Integer numcomp, char impdireta, char suspens, String di, String adicao, String itemadicao,
                   Long numnf, String sernf, String cnpjfor, String ie, String eminf, String espec, String undcom, String ncm,
                   Double qtde, char inreducii, Double vlrunit) {
        this.numcomp = numcomp;
        this.impdireta = impdireta;
        this.suspens = suspens;
        this.di = di;
        this.adicao = adicao;
        this.itemadicao = itemadicao;
        this.numnf = numnf;
        this.sernf = sernf;
        this.cnpjfor = cnpjfor;
        this.ie = ie;
        this.eminf = eminf;
        this.espec = espec;
        this.undcom = undcom;
        this.ncm = ncm;
        this.qtde = qtde;
        this.inreducii = inreducii;
        this.vlrunit = vlrunit;
    }
    
    public Integer getNumcomp() {
        return numcomp;
    }
    public void setNumcomp(Integer numcomp) {
        this.numcomp = numcomp;
    }
    public char getImpdireta() {
        return impdireta;
    }
    public void setImpdireta(char impdireta) {
        this.impdireta = impdireta;
    }
    public char getSuspens() {
        return suspens;
    }
    public void setSuspens(char suspens) {
        this.suspens = suspens;
    }
    public String getDi() {
        return di;
    }
    public void setDi(String di) {
        this.di = di;
    }
    public String getAdicao() {
        return adicao;
    }
    public void setAdicao(String adicao) {
        this.adicao = adicao;
    }
    public String getItemadicao() {
        return itemadicao;
    }
    public void setItemadicao(String itemadicao) {
        this.itemadicao = itemadicao;
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
    public char getInreducii() {
        return inreducii;
    }
    public void setInreducii(char inreducii) {
        this.inreducii = inreducii;
    }
    public Double getVlrunit() {
        return vlrunit;
    }
    public void setVlrunit(Double vlrunit) {
        this.vlrunit = vlrunit;
    }

    
}
