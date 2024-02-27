package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class DcrlayoutKey {
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	private String idreg;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String campo;
	
	public String getIdreg() {
		return idreg;
	}
	public void setIdreg(String idreg) {
		this.idreg = idreg;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
}
