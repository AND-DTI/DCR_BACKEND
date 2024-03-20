package com.dcr.api.model.projection;

import java.util.List;

public class Nivel1Projection {
	private Object cdPrd;
    private Object descCom;
    private Object descRfb;
    private Object tpPrd;
    private Object prdDest;
    private Object ppbPrd;
    private Object modelo;
    private Object anoMdl;
    List<Nivel2Projection> itens;
	public Object getCdPrd() {
		return cdPrd;
	}
	public void setCdPrd(Object cdPrd) {
		this.cdPrd = cdPrd;
	}
	public Object getDescCom() {
		return descCom;
	}
	public void setDescCom(Object descCom) {
		this.descCom = descCom;
	}
	public Object getDescRfb() {
		return descRfb;
	}
	public void setDescRfb(Object descRfb) {
		this.descRfb = descRfb;
	}
	public Object getTpPrd() {
		return tpPrd;
	}
	public void setTpPrd(Object tpPrd) {
		this.tpPrd = tpPrd;
	}
	public Object getPrdDest() {
		return prdDest;
	}
	public void setPrdDest(Object prdDest) {
		this.prdDest = prdDest;
	}
	public Object getPpbPrd() {
		return ppbPrd;
	}
	public void setPpbPrd(Object ppbPrd) {
		this.ppbPrd = ppbPrd;
	}
	public Object getModelo() {
		return modelo;
	}
	public void setModelo(Object modelo) {
		this.modelo = modelo;
	}
	public Object getAnoMdl() {
		return anoMdl;
	}
	public void setAnoMdl(Object anoMdl) {
		this.anoMdl = anoMdl;
	}
	public List<Nivel2Projection> getItens() {
		return itens;
	}
	public void setItens(List<Nivel2Projection> itens) {
		this.itens = itens;
	}

}
