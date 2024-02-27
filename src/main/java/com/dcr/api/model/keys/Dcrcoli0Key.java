package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class Dcrcoli0Key {
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String dcre;
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	private String denom;
	public String getDcre() {
		return dcre;
	}
	public void setDcre(String dcre) {
		this.dcre = dcre;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
}
