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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dcre == null) ? 0 : dcre.hashCode());
		result = prime * result + ((cdclient == null) ? 0 : cdclient.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dcrcoli1Key other = (Dcrcoli1Key) obj;
		if (dcre == null) {
			if (other.dcre != null)
				return false;
		} else if (!dcre.equals(other.dcre))
			return false;
		if (cdclient == null) {
			if (other.cdclient != null)
				return false;
		} else if (!cdclient.equals(other.cdclient))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		return true;
	}
}
