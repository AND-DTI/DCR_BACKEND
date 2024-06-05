package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class DcrcorsipKey {
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	private String cnpjext;
	
	@TamanhoMaximo(16)
	@TamanhoMinimo(1)
	private String numip;

	public String getCnpjext() {
		return cnpjext;
	}

	public void setCnpjext(String cnpjext) {
		this.cnpjext = cnpjext;
	}

	public String getNumip() {
		return numip;
	}

	public void setNumip(String numip) {
		this.numip = numip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnpjext == null) ? 0 : cnpjext.hashCode());
		result = prime * result + ((numip == null) ? 0 : numip.hashCode());
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
		DcrcorsipKey other = (DcrcorsipKey) obj;
		if (cnpjext == null) {
			if (other.cnpjext != null)
				return false;
		} else if (!cnpjext.equals(other.cnpjext))
			return false;
		if (numip == null) {
			if (other.numip != null)
				return false;
		} else if (!numip.equals(other.numip))
			return false;
		return true;
	}
}
