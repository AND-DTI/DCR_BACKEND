package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class AccoperKey {
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Integer idoper;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	private String cdmodule;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String cdsys;

	public Integer getIdoper() {
		return idoper;
	}

	public void setIdoper(Integer idoper) {
		this.idoper = idoper;
	}

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
		result = prime * result + ((idoper == null) ? 0 : idoper.hashCode());
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
		AccoperKey other = (AccoperKey) obj;
		if (idoper == null) {
			if (other.idoper != null)
				return false;
		} else if (!idoper.equals(other.idoper))
			return false;
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
