package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class Dcrcoli1Key {
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String dcre;
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String cdclient;
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private Integer modelo;
	public String getDcre() {
		return dcre;
	}
	public void setDcre(String dcre) {
		this.dcre = dcre;
	}
	public String getCdclient() {
		return cdclient;
	}
	public void setCdclient(String cdclient) {
		this.cdclient = cdclient;
	}
	public Integer getModelo() {
		return modelo;
	}
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
}
