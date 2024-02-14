package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Dcrreg0Key {

	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Long idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private String tpprd;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	private String denom;
	
	public Long getIdmatriz() {
		return idmatriz;
	}
	public void setIdmatriz(Long idmatriz) {
		this.idmatriz = idmatriz;
	}
	public String getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}
	public String getTpprd() {
		return tpprd;
	}
	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
}
