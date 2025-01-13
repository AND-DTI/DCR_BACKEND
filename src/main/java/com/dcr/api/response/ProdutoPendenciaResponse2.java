package com.dcr.api.response;
import java.util.List;
import com.dcr.api.model.dto.CorProdutoDTO;



public class ProdutoPendenciaResponse2 {

    private Integer idMatriz; 
    private String partnumpd;
    private String tpprd;
    private String desccom;
    private String descrfb;
    private String tpdcre;

    private String produto;
    private String modelo;
    private int anomdl;
    private int protot;
    private int special;

	private String origprd;	//orig	
    private String dtneci;
    private String priourgen;
    private String prevfat;
    private String prioresp;
    private String priodtmnt;
    private String prioHRmnt; 
	private String unmed;   
	private Double preco; 
	private String ncmprd; 
    private String ppbprd;
    private String prddest;
    private int statusproc;        
    private Integer qtdependencias;
	private Integer qtdependenciasEmAberto;
    private CorProdutoDTO cor;    
    private List<PendenciaProdResponse> pendencias; //PendenciaResponse
	private List<InsumosProdResponse> insumos; 
    private List<DocumentosProdResponse> documentos;

    //private List<CoresResponse> cores;

    
    public Integer getIdMatriz() {
        return idMatriz;
    }
    public void setIdMatriz(Integer idMatriz) {
        this.idMatriz = idMatriz;
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
    public String getDesccom() {
        return desccom;
    }
    public void setDesccom(String desccom) {
        this.desccom = desccom;
    }
    public String getDescrfb() {
        return descrfb;
    }
    public void setDescrfb(String descrfb) {
        this.descrfb = descrfb;
    }
    public String getTpdcre() {
        return tpdcre;
    }
    public void setTpdcre(String tpdcre) {
        this.tpdcre = tpdcre;
    }
    public String getOrigprd() {
        return origprd;
    }
    public void setOrigprd(String origprd) {
        this.origprd = origprd;
    }
    public String getDtneci() {
        return dtneci;
    }
    public void setDtneci(String dtneci) {
        this.dtneci = dtneci;
    }
    public String getPriourgen() {
        return priourgen;
    }
    public void setPriourgen(String priourgen) {
        this.priourgen = priourgen;
    }
    public String getPrevfat() {
        return prevfat;
    }
    public void setPrevfat(String prevfat) {
        this.prevfat = prevfat;
    }
    public String getPrioresp() {
        return prioresp;
    }
    public void setPrioresp(String prioresp) {
        this.prioresp = prioresp;
    }
    public String getPriodtmnt() {
        return priodtmnt;
    }
    public void setPriodtmnt(String priodtmnt) {
        this.priodtmnt = priodtmnt;
    }
    public String getPrioHRmnt() {
        return prioHRmnt;
    }
    public void setPrioHRmnt(String prioHRmnt) {
        this.prioHRmnt = prioHRmnt;
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
    
    public String getNcmprd() {        return ncmprd;    }
    public void setNcmprd(String ncmprd) {        this.ncmprd = ncmprd;    }

    public String getPpbprd() {
        return ppbprd;
    }
    public void setPpbprd(String ppbprd) {
        this.ppbprd = ppbprd;
    }
    public String getPrddest() {
        return prddest;
    }
    public void setPrddest(String prddest) {
        this.prddest = prddest;
    }

    //public int getStatus() {        return status;    }
    //public void setStatus(int status) {        this.status = status;    }

    public int getStatusproc() {        return statusproc;    }
    public void setStatusproc(int statusproc) {        this.statusproc = statusproc;    }

    public Integer getQtdependencias() {
        return qtdependencias;
    }
    public void setQtdependencias(Integer qtdependencias) {
        this.qtdependencias = qtdependencias;
    }
    public Integer getQtdependenciasEmAberto() {
        return qtdependenciasEmAberto;
    }
    public void setQtdependenciasEmAberto(Integer qtdependenciasEmAberto) {
        this.qtdependenciasEmAberto = qtdependenciasEmAberto;
    }

    public CorProdutoDTO getCor() {        return cor;    }
    public void setCor(CorProdutoDTO cor) {        this.cor = cor;    }

    public List<PendenciaProdResponse> getPendencias() {
        return pendencias;
    }
    public void setPendencias(List<PendenciaProdResponse> pendencias) {
        this.pendencias = pendencias;
    }
    public List<InsumosProdResponse> getInsumos() {
        return insumos;
    }
    public void setInsumos(List<InsumosProdResponse> insumos) {
        this.insumos = insumos;
    }
    public List<DocumentosProdResponse> getDocumentos() {
        return documentos;
    }
    public void setDocumentos(List<DocumentosProdResponse> documentos) {
        this.documentos = documentos;
    }
    public String getProduto() {
        return produto;
    }
    public void setProduto(String produto) {
        this.produto = produto;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public int getAnomdl() {
        return anomdl;
    }
    public void setAnomdl(int anomdl) {
        this.anomdl = anomdl;
    }
    public int getProtot() {
        return protot;
    }
    public void setProtot(int protot) {
        this.protot = protot;
    }
    public int getSpecial() {
        return special;
    }
    public void setSpecial(int special) {
        this.special = special;
    }

}
