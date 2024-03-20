package com.dcr.api.model.keys;

import java.util.Objects;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class AccoperolKey {
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Integer roleid;
	
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Integer idoper;

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String cdsys;
	public Integer getIdoper() {
		return idoper;
	}

	public void setIdoper(Integer idoper) {
		this.idoper = idoper;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idoper, roleid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		AccoperolKey other = (AccoperolKey) obj;
		return Objects.equals(idoper, other.idoper) && Objects.equals(roleid, other.roleid);
	}

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}

	
}
