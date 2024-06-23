package com.dcr.api.model.keys;

import java.util.Objects;

import com.dcr.api.validator.TamanhoMaximo;

public class DcrapiKey {
	
	@TamanhoMaximo(8)
	private String confvigini;
	
	@TamanhoMaximo(10)
	private String confvigfim;
	
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
		DcrapiKey other = (DcrapiKey) obj;
		return Objects.equals(confvigfim, other.confvigfim) && Objects.equals(confvigini, other.confvigini);
	}
}
