package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import jakarta.persistence.Embeddable;

@Embeddable
public class Dcrreg2Key {

	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Long idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private String tpprd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private Integer numcomp;
	
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
	public Integer getNumcomp() {
		return numcomp;
	}
	public void setNumcomp(Integer numcomp) {
		this.numcomp = numcomp;
	}
	
}
