package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class AccoperKey {
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Integer idoper;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	private String cdmodule;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String cdsys;

	public Integer getIdoper() {
		return idoper;
	}

	public void setIdoper(Integer idoper) {
		this.idoper = idoper;
	}

	public String getCdmodule() {
		return cdmodule;
	}

	public void setCdmodule(String cdmodule) {
		this.cdmodule = cdmodule;
	}

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}
}
