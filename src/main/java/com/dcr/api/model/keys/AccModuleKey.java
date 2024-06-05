package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class AccModuleKey {
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	private String cdmodule;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String cdsys;

	public String getCdmodule() {
		return cdmodule;
	}

	public void setCdmodule(String cdmodule) {
		this.cdmodule = cdmodule;
	}

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdmodule == null) ? 0 : cdmodule.hashCode());
		result = prime * result + ((cdsys == null) ? 0 : cdsys.hashCode());
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
		AccModuleKey other = (AccModuleKey) obj;
		if (cdmodule == null) {
			if (other.cdmodule != null)
				return false;
		} else if (!cdmodule.equals(other.cdmodule))
			return false;
		if (cdsys == null) {
			if (other.cdsys != null)
				return false;
		} else if (!cdsys.equals(other.cdsys))
			return false;
		return true;
	}
}
