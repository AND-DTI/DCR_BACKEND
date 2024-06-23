package com.dcr.api.model.keys;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;


public class Dcrcoli3Key {
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String dcre;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private Integer numsubcomp;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private Integer numcomp;

	public String getDcre() {
		return dcre;
	}

	public void setDcre(String dcre) {
		this.dcre = dcre;
	}

	public Integer getNumsubcomp() {
		return numsubcomp;
	}

	public void setNumsubcomp(Integer numsubcomp) {
		this.numsubcomp = numsubcomp;
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
		result = prime * result + ((numsubcomp == null) ? 0 : numsubcomp.hashCode());
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
		Dcrcoli3Key other = (Dcrcoli3Key) obj;
		if (dcre == null) {
			if (other.dcre != null)
				return false;
		} else if (!dcre.equals(other.dcre))
			return false;
		if (numsubcomp == null) {
			if (other.numsubcomp != null)
				return false;
		} else if (!numsubcomp.equals(other.numsubcomp))
			return false;
		if (numcomp == null) {
			if (other.numcomp != null)
				return false;
		} else if (!numcomp.equals(other.numcomp))
			return false;
		return true;
	}
}
