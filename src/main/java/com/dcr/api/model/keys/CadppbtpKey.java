package com.dcr.api.model.keys;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import jakarta.persistence.Column;


public class CadppbtpKey {

	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
    private String tpprd;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(8)
	@Column(columnDefinition = "char(8)")
    private String viginippb;

	public String getTpprd() {
		return tpprd;
	}

	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}

	public String getViginippb() {
		return viginippb;
	}

	public void setViginippb(String viginippb) {
		this.viginippb = viginippb;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tpprd == null) ? 0 : tpprd.hashCode());
		result = prime * result + ((viginippb == null) ? 0 : viginippb.hashCode());
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
		CadppbtpKey other = (CadppbtpKey) obj;
		if (tpprd == null) {
			if (other.tpprd != null)
				return false;
		} else if (!tpprd.equals(other.tpprd))
			return false;
		if (viginippb == null) {
			if (other.viginippb != null)
				return false;
		} else if (!viginippb.equals(other.viginippb))
			return false;
		return true;
	}

}
