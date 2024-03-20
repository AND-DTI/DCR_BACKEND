package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class AccrolesKey {

	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Integer roleid;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String cdsys;

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}
	
}
