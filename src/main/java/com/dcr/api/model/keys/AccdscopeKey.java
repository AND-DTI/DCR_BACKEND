package com.dcr.api.model.keys;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import jakarta.persistence.Column;

public class AccdscopeKey {
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Integer idoper;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(2)")
    private String codidioma;

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
    private String cdsys;
	
	public Integer getIdoper() {
		return idoper;
	}

	public void setIdoper(Integer idoper) {
		this.idoper = idoper;
	}

	public String getCodidioma() {
		return codidioma;
	}

	public void setCodidioma(String codidioma) {
		this.codidioma = codidioma;
	}

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}
}
