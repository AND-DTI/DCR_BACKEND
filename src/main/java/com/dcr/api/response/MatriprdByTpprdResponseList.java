package com.dcr.api.response;


public class MatriprdByTpprdResponseList {

    private Object partnumpd;
    private Object modelo; //j4 added
	private Object codcor;
    private Object partdesc;
    private Object unmed;
	private Object ncm; //j4 added    
	private Object priocor;
    private Object cdbej; //j4 - old cdbeg    
	private Object corpt;
    private Object coreng;
    private Object tppin;
	
	public Object getPartnumpd() {		return partnumpd;	}
	public void setPartnumpd(Object partnumpd) {		this.partnumpd = partnumpd;	}
	
	public Object getModelo() {		return modelo;	}
	public void setModelo(Object modelo) {		this.modelo = modelo;	}
	
	public Object getCodcor() {		return codcor;	}
	public void setCodcor(Object codcor) {		this.codcor = codcor;	}

	public Object getPartdesc() {		return partdesc;	}
	public void setPartdesc(Object partdesc) {		this.partdesc = partdesc;	}

	public Object getUnmed() {		return unmed;	}
	public void setUnmed(Object unmed) {		this.unmed = unmed;	}

	public Object getNcm() {		return ncm;	}
	public void setNcm(Object ncm) {		this.ncm = ncm;	}	

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

}
