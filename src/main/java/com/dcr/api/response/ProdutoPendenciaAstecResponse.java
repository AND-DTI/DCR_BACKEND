package com.dcr.api.response;

import java.util.List;

public class ProdutoPendenciaAstecResponse {
	private Object idMatriz;
    private Object partnumpd;
    private Object desccom;
    private Object descrfb;
    private Object unmed;
    private Object origprd;
    private Object dtneci;
    private Object priourgen;
    private Object prevfat;
    private Object prioresp;
    private Object priodtmnt;
    private Object prioHRmnt;
    private Object tpdcre;
    private Object status;
    private Object ppbprd;
    private Object prddest;
    private Object qtdependencias;
    
    private List<PendenciaAstecResponse> pendencias;
    private List<DocumentosAstecResponse> documentos;
	public Object getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(Object idMatriz) {
		this.idMatriz = idMatriz;
	}
	public Object getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(Object partnumpd) {
		this.partnumpd = partnumpd;
	}
	public Object getDesccom() {
		return desccom;
	}
	public void setDesccom(Object desccom) {
		this.desccom = desccom;
	}
	public Object getDescrfb() {
		return descrfb;
	}
	public void setDescrfb(Object descrfb) {
		this.descrfb = descrfb;
	}
	public Object getUnmed() {
		return unmed;
	}
	public void setUnmed(Object unmed) {
		this.unmed = unmed;
	}
	public Object getOrigprd() {
		return origprd;
	}
	public void setOrigprd(Object origprd) {
		this.origprd = origprd;
	}
	public Object getDtneci() {
		return dtneci;
	}
	public void setDtneci(Object dtneci) {
		this.dtneci = dtneci;
	}
	public Object getPriourgen() {
		return priourgen;
	}
	public void setPriourgen(Object priourgen) {
		this.priourgen = priourgen;
	}
	public Object getPrevfat() {
		return prevfat;
	}
	public void setPrevfat(Object prevfat) {
		this.prevfat = prevfat;
	}
	public Object getPrioresp() {
		return prioresp;
	}
	public void setPrioresp(Object prioresp) {
		this.prioresp = prioresp;
	}
	public Object getPriodtmnt() {
		return priodtmnt;
	}
	public void setPriodtmnt(Object priodtmnt) {
		this.priodtmnt = priodtmnt;
	}
	public Object getPrioHRmnt() {
		return prioHRmnt;
	}
	public void setPrioHRmnt(Object prioHRmnt) {
		this.prioHRmnt = prioHRmnt;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public Object getQtdependencias() {
		return qtdependencias;
	}
	public void setQtdependencias(Object qtdependencias) {
		this.qtdependencias = qtdependencias;
	}
	public List<PendenciaAstecResponse> getPendencias() {
		return pendencias;
	}
	public void setPendencias(List<PendenciaAstecResponse> pendencias) {
		this.pendencias = pendencias;
	}
	public List<DocumentosAstecResponse> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DocumentosAstecResponse> documentos) {
		this.documentos = documentos;
	}
	public Object getPpbprd() {
		return ppbprd;
	}
	public void setPpbprd(Object ppbprd) {
		this.ppbprd = ppbprd;
	}
	public Object getPrddest() {
		return prddest;
	}
	public void setPrddest(Object prddest) {
		this.prddest = prddest;
	}
	public Object getTpdcre() {
		return tpdcre;
	}
	public void setTpdcre(Object tpdcre) {
		this.tpdcre = tpdcre;
	}
}
