package com.dcr.api.model.keys;

import java.util.Objects;

import com.dcr.api.validator.TamanhoMaximo;

public class DcroriprdKey {

	@TamanhoMaximo(8)
    private String confvigini;
	
	@TamanhoMaximo(8)
    private String confvigfim;

	@Override
	public int hashCode() {
		return Objects.hash(confvigfim, confvigini);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DcroriprdKey other = (DcroriprdKey) obj;
		return Objects.equals(confvigfim, other.confvigfim) && Objects.equals(confvigini, other.confvigini);
	}

	public String getConfvigini() {
		return confvigini;
	}

	public void setConfvigini(String confvigini) {
		this.confvigini = confvigini;
	}

	public String getConfvigfim() {
		return confvigfim;
	}

	public void setConfvigfim(String confvigfim) {
		this.confvigfim = confvigfim;
	}
	
	
}
