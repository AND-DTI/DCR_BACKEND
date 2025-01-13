package com.dcr.api.model.keys;
//import java.util.Objects;
import com.dcr.api.validator.TamanhoMaximo;



public class DocumentoIMPKey {
	
	@TamanhoMaximo(25)
	private String itnbr;
	
	@TamanhoMaximo(15)
	private String nrodig;

	@TamanhoMaximo(3)
	private String nroadi;

	public String getItnbr() {
		return itnbr;
	}

	public void setItnbr(String itnbr) {
		this.itnbr = itnbr;
	}

	public String getNrodig() {
		return nrodig;
	}

	public void setNrodig(String nrodig) {
		this.nrodig = nrodig;
	}

	public String getNroadi() {
		return nroadi;
	}

	public void setNroadi(String nroadi) {
		this.nroadi = nroadi;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itnbr == null) ? 0 : itnbr.hashCode());
		result = prime * result + ((nrodig == null) ? 0 : nrodig.hashCode());
		result = prime * result + ((nroadi == null) ? 0 : nroadi.hashCode());
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
		DocumentoIMPKey other = (DocumentoIMPKey) obj;
		if (itnbr == null) {
			if (other.itnbr != null)
				return false;
		} else if (!itnbr.equals(other.itnbr))
			return false;
		if (nrodig == null) {
			if (other.nrodig != null)
				return false;
		} else if (!nrodig.equals(other.nrodig))
			return false;
		if (nroadi == null) {
			if (other.nroadi != null)
				return false;
		} else if (!nroadi.equals(other.nroadi))
			return false;
		return true;
	}
	


}
