package com.dcr.api.model.projection;


public class TipoProjection2 {


	private String tpPrd;
    private String dscPor;


	public TipoProjection2(){

	}
	
	public TipoProjection2(String tpPrd, String dscPor) {
		this.tpPrd = tpPrd;
		this.dscPor = dscPor;
	}

	public String getTpPrd() {		return tpPrd;	}
	public void setTpPrd(String tpPrd) {		this.tpPrd = tpPrd;	}

	public String getDscPor() {		return dscPor;	}
	public void setDscPor(String dscPor) {		this.dscPor = dscPor;	}
	

}
