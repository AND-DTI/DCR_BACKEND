package com.dcr.api.model.keys;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;


public class DcrlayoutKey {
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	private String idreg;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String campo;
	
	public String getIdreg() {
		return idreg;
	}
	public void setIdreg(String idreg) {
		this.idreg = idreg;
	}
	public String getCampo() {
		return campo;
	}
	public void setCampo(String campo) {
		this.campo = campo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idreg == null) ? 0 : idreg.hashCode());
		result = prime * result + ((campo == null) ? 0 : campo.hashCode());
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
		DcrlayoutKey other = (DcrlayoutKey) obj;
		if (idreg == null) {
			if (other.idreg != null)
				return false;
		} else if (!idreg.equals(other.idreg))
			return false;
		if (campo == null) {
			if (other.campo != null)
				return false;
		} else if (!campo.equals(other.campo))
			return false;
		return true;
	}
}
