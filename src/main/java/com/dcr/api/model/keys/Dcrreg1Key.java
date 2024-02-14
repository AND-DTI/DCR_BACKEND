package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class Dcrreg1Key {

	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Long idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private String tpprd;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	private Integer modelo;
	
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
	public Integer getModelo() {
		return modelo;
	}
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
}
