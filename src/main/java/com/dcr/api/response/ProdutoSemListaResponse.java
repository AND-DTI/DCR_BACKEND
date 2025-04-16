package com.dcr.api.response;
import java.util.List;



public class ProdutoSemListaResponse {


	private Object idMatriz;
    private Object produto;
    private Object modelo;
    private Object anomdl;
    private Object desccom;     
    private Object descrfb;
	private Object ppbprd;    
	private Object tpprd;
    private Object protot;
    private Object special;
    private Object tpdcre;
    private Object orig;
    private Object dtneci;
    private Object priourgen;
    private Object prevfat;
    private Object prioresp;
    private Object priodtmnt;
    private Object prioHRmnt;
   
    private Object status;    
    private Object qtdependencias;
	private Object qtdependenciasEmAberto;  //j4 added 	
	private Object partnumpd;
    private Object codcor;
    private Object partdesc;
    private Object unmed;
	private Object preco; //j4 - added
	private Object ncm;   //j4 - added
    private Object priocor;
    private Object cdbej;
    private Object corpt;
    private Object coreng;
    private Object tppin;    
	private List<PendenciaResponse> pendencias; //j4 - old List<PendenciaResponseSemLista> 
	//private List<DocumentosResponse> documentos;

	
	public Object getIdMatriz() {		return idMatriz;	}
	public void setIdMatriz(Object idMatriz) {		this.idMatriz = idMatriz;	}
	
	public Object getProduto() {		return produto;	}
	public void setProduto(Object produto) {		this.produto = produto;	}
	
	public Object getModelo() {		return modelo;	}
	public void setModelo(Object modelo) {		this.modelo = modelo;	}
	
	public Object getAnomdl() {		return anomdl;	}
	public void setAnomdl(Object anomdl) {		this.anomdl = anomdl;	}
	
	public Object getDesccom() {		return desccom;	}	
	public void setDesccom(Object desccom) {		this.desccom = desccom;	}
	
	public Object getDescrfb() {		return descrfb;	}
	public void setDescrfb(Object descrfb) {		this.descrfb = descrfb;	}

	public Object getPpbprd() {		return ppbprd;	}
	public void setPpbprd(Object ppbprd) {		this.ppbprd = ppbprd;	}
	
	public Object getTpprd() {		return tpprd;	}
	public void setTpprd(Object tpprd) {		this.tpprd = tpprd;	}
	
	public Object getProtot() {		return protot;	}
	public void setProtot(Object protot) {		this.protot = protot;	}
	
	public Object getSpecial() {		return special;	}
	public void setSpecial(Object special) {		this.special = special;	}
	
	public Object getTpdcre() {		return tpdcre;	}
	public void setTpdcre(Object tpdcre) {		this.tpdcre = tpdcre;	}
	
	public Object getOrig() {		return orig;	}
	public void setOrig(Object orig) {		this.orig = orig;	}
	
	public Object getDtneci() {		return dtneci;	}
	public void setDtneci(Object dtneci) {		this.dtneci = dtneci;	}
	
	public Object getPriourgen() {		return priourgen;	}
	public void setPriourgen(Object priourgen) {		this.priourgen = priourgen;	}
	
	public Object getPrevfat() {		return prevfat;	}
	public void setPrevfat(Object prevfat) {		this.prevfat = prevfat;	}
	
	public Object getPrioresp() {		return prioresp;	}
	public void setPrioresp(Object prioresp) {		this.prioresp = prioresp;	}
	
	public Object getPriodtmnt() {		return priodtmnt;	}
	public void setPriodtmnt(Object priodtmnt) {		this.priodtmnt = priodtmnt;	}
	
	public Object getPrioHRmnt() {		return prioHRmnt;	}
	public void setPrioHRmnt(Object prioHRmnt) {		this.prioHRmnt = prioHRmnt;	}
	
	public Object getStatus() {		return status;	}
	public void setStatus(Object status) {		this.status = status;	}
	
	public Object getQtdependencias() {		return qtdependencias;	}
	public void setQtdependencias(Object qtdependencias) {		this.qtdependencias = qtdependencias;	}

	public Object getQtdependenciasEmAberto() {		return qtdependenciasEmAberto;	}
	public void setQtdependenciasEmAberto(Object qtdependenciasEmAberto) {		this.qtdependenciasEmAberto = qtdependenciasEmAberto;	}	
	
	public Object getPartnumpd() {		return partnumpd;	}
	public void setPartnumpd(Object partnumpd) {		this.partnumpd = partnumpd;	}
	
	public Object getCodcor() {		return codcor;	}
	public void setCodcor(Object codcor) {		this.codcor = codcor;	}
	
	public Object getPartdesc() {		return partdesc;	}
	public void setPartdesc(Object partdesc) {		this.partdesc = partdesc;	}
	
	public Object getUnmed() {		return unmed;	}
	public void setUnmed(Object unmed) {		this.unmed = unmed;	}
	
	public Object getPriocor() {		return priocor;	}
	public void setPriocor(Object priocor) {		this.priocor = priocor;	}
	
	public Object getCdbej() {		return cdbej;	}
	public void setCdbej(Object cdbej) {		this.cdbej = cdbej;	}
	
	public Object getCorpt() {		return corpt;	}
	public void setCorpt(Object corpt) {		this.corpt = corpt;	}
	
	public Object getCoreng() {		return coreng;	}
	public void setCoreng(Object coreng) {		this.coreng = coreng;	}
	
	public Object getTppin() {		return tppin;	}
	public void setTppin(Object tppin) {		this.tppin = tppin;	}
	
	public List<PendenciaResponse> getPendencias() {		return pendencias;	}
	public void setPendencias(List<PendenciaResponse> pendencias) {		this.pendencias = pendencias;	}
	public Object getPreco() {
		return preco;
	}
	public void setPreco(Object preco) {
		this.preco = preco;
	}
	public Object getNcm() {
		return ncm;
	}
	public void setNcm(Object ncm) {
		this.ncm = ncm;
	}

}
