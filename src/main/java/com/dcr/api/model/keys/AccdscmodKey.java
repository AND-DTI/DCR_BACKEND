package com.dcr.api.model.keys;

import java.util.Objects;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class AccdscmodKey {

	@TamanhoMinimo(1)
	@TamanhoMaximo(5)
	private String cdmodule;
	
	@TamanhoMinimo(1)
	@TamanhoMaximo(2)
	private String codidioma;
	
	@TamanhoMinimo(10)
	@TamanhoMaximo(2)
	private String cdsys;
	
	public String getCdmodule() {
		return cdmodule;
	}
	public void setCdmodule(String cdmodule) {
		this.cdmodule = cdmodule;
	}
	public String getCodidioma() {
		return codidioma;
	}
	public void setCodidioma(String codidioma) {
		this.codidioma = codidioma;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cdmodule, codidioma);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccdscmodKey other = (AccdscmodKey) obj;
		return Objects.equals(cdmodule, other.cdmodule) && Objects.equals(codidioma, other.codidioma);
	}
	public String getCdsys() {
		return cdsys;
	}
	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}
}
