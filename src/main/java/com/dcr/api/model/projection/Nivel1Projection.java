package com.dcr.api.model.projection;

import java.util.List;

public class Nivel1Projection {
	
	private Object cdPrd;
	private Object uncome; //added j4	
	private Object anofat; //added j4
	private Object frstanofab; //added j4
	private Object lastanofab; //added j4
	private Object tpPrd;
	private Object dscpor; //added j4
	private Object descCom;
    private Object descRfb;
    private Object prdDest;
    private Object ppbPrd;
    private Object modelo;
    private Object anoMdl;
    List<Nivel2Projection> itens;


	public Object getCdPrd() {		return cdPrd;	}
	public void setCdPrd(Object cdPrd) {		this.cdPrd = cdPrd;	}

	public Object getUncome() {		return uncome;	}
	public void setUncome(Object uncome) {		this.uncome = uncome;	}

	public Object getAnofat() {		return anofat;	}
	public void setAnofat(Object anofat) {		this.anofat = anofat;	}

	public Object getFrstanofab() {		return frstanofab;	}
	public void setFrstanofab(Object frstanofab) {		this.frstanofab = frstanofab;	}

	public Object getLastanofab() {		return lastanofab;	}
	public void setLastanofab(Object lastanofab) {		this.lastanofab = lastanofab;	}

	public Object getTpPrd() {		return tpPrd;	}
	public void setTpPrd(Object tpPrd) {		this.tpPrd = tpPrd;	}	

    public Object getDscpor() {		return dscpor;	}
	public void setDscpor(Object dscpor) {		this.dscpor = dscpor;	}	

	public Object getDescCom() {		return descCom;	}
	public void setDescCom(Object descCom) {		this.descCom = descCom;	}

	public Object getDescRfb() {		return descRfb;	}
	public void setDescRfb(Object descRfb) {		this.descRfb = descRfb;	}

	public Object getPrdDest() {		return prdDest;	}
	public void setPrdDest(Object prdDest) {		this.prdDest = prdDest;	}

	public Object getPpbPrd() {		return ppbPrd;	}
	public void setPpbPrd(Object ppbPrd) {		this.ppbPrd = ppbPrd;	}

	public Object getModelo() {		return modelo;	}
	public void setModelo(Object modelo) {		this.modelo = modelo;	}

	public Object getAnoMdl() {		return anoMdl;	}
	public void setAnoMdl(Object anoMdl) {		this.anoMdl = anoMdl;	}

	public List<Nivel2Projection> getItens() {		return itens;	}
	public void setItens(List<Nivel2Projection> itens) {		this.itens = itens;	}


}
