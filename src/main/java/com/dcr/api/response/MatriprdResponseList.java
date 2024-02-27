package com.dcr.api.response;

import java.util.List;

public class MatriprdResponseList {

    private Object partnumpd;
    private Object codcor;
    private Object partdesc;
    private Object unmed;
    private Object priocor;
    private Object cdbeg;
    private Object corpt;
    private Object coreng;
    private Object tppin;
	private List<InsumosProdResponse> insumos;
	public Object getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(Object partnumpd) {
		this.partnumpd = partnumpd;
	}
	public Object getCodcor() {
		return codcor;
	}
	public void setCodcor(Object codcor) {
		this.codcor = codcor;
	}
	public Object getPartdesc() {
		return partdesc;
	}
	public void setPartdesc(Object partdesc) {
		this.partdesc = partdesc;
	}
	public Object getUnmed() {
		return unmed;
	}
	public void setUnmed(Object unmed) {
		this.unmed = unmed;
	}
	public Object getPriocor() {
		return priocor;
	}
	public void setPriocor(Object priocor) {
		this.priocor = priocor;
	}
	public Object getCdbeg() {
		return cdbeg;
	}
	public void setCdbeg(Object cdbeg) {
		this.cdbeg = cdbeg;
	}
	public Object getCorpt() {
		return corpt;
	}
	public void setCorpt(Object corpt) {
		this.corpt = corpt;
	}
	public Object getCoreng() {
		return coreng;
	}
	public void setCoreng(Object coreng) {
		this.coreng = coreng;
	}
	public Object getTppin() {
		return tppin;
	}
	public void setTppin(Object tppin) {
		this.tppin = tppin;
	}
	public List<InsumosProdResponse> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<InsumosProdResponse> insumos) {
		this.insumos = insumos;
	}
}
