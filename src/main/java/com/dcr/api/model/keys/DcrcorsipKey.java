package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class DcrcorsipKey {
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	private String cnpjext;
	
	@TamanhoMaximo(16)
	@TamanhoMinimo(1)
	private String numip;

	public String getCnpjext() {
		return cnpjext;
	}

	public void setCnpjext(String cnpjext) {
		this.cnpjext = cnpjext;
	}

	public String getNumip() {
		return numip;
	}

	public void setNumip(String numip) {
		this.numip = numip;
	}
}
