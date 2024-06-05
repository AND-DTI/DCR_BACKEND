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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dcre == null) ? 0 : dcre.hashCode());
		result = prime * result + ((denom == null) ? 0 : denom.hashCode());
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
		Dcrcoli0Key other = (Dcrcoli0Key) obj;
		if (dcre == null) {
			if (other.dcre != null)
				return false;
		} else if (!dcre.equals(other.dcre))
			return false;
		if (denom == null) {
			if (other.denom != null)
				return false;
		} else if (!denom.equals(other.denom))
			return false;
		return true;
	}
}
