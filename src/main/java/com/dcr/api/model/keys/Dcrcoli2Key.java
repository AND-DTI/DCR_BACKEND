package com.dcr.api.model.keys;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;


public class Dcrcoli2Key {
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String dcre;
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private Integer numcomp;
	public String getDcre() {
		return dcre;
	}
	public void setDcre(String dcre) {
		this.dcre = dcre;
	}
	public Integer getNumcomp() {
		return numcomp;
	}
	public void setNumcomp(Integer numcomp) {
		this.numcomp = numcomp;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dcre == null) ? 0 : dcre.hashCode());
		result = prime * result + ((numcomp == null) ? 0 : numcomp.hashCode());
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
		Dcrcoli2Key other = (Dcrcoli2Key) obj;
		if (dcre == null) {
			if (other.dcre != null)
				return false;
		} else if (!dcre.equals(other.dcre))
			return false;
		if (numcomp == null) {
			if (other.numcomp != null)
				return false;
		} else if (!numcomp.equals(other.numcomp))
			return false;
		return true;
	}
	
}
