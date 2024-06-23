package com.dcr.api.model.projection;


public class TipoProjection {

	private Object tpPrd;
    private Object dscPor;


	public TipoProjection(){

	}
	
	public TipoProjection(Object tpPrd, Object dscPor) {
		this.tpPrd = tpPrd;
		this.dscPor = dscPor;
	}
	
	public Object getTpPrd() {			return tpPrd;		}
	public void setTpPrd(Object tpPrd) {	this.tpPrd = tpPrd;	}
	
	public Object getDscPor() {		return dscPor;	}
	public void setDscPor(Object dscPor) {		this.dscPor = dscPor;	}

}
