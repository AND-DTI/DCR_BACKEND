package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class AccModuleKey {
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	private String cdmodule;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String cdsys;

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
