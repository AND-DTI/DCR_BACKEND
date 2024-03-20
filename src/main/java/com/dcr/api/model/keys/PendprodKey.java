package com.dcr.api.model.keys;

import java.util.Objects;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

public class PendprodKey {
	
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Integer idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String partnum;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String partnumpd;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	private Integer numpend;
	
	public Integer getIdmatriz() {
		return idmatriz;
	}
	public void setIdmatriz(Integer idmatriz) {
		this.idmatriz = idmatriz;
	}
	public String getPartnum() {
		return partnum;
	}
	public void setPartnum(String partnum) {
		this.partnum = partnum;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmatriz, numpend, partnum);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PendprodKey other = (PendprodKey) obj;
		return Objects.equals(idmatriz, other.idmatriz) && Objects.equals(numpend, other.numpend)
				&& Objects.equals(partnum, other.partnum);
	}
	public Integer getNumpend() {
		return numpend;
	}
	public void setNumpend(Integer numpend) {
		this.numpend = numpend;
	}
	public String getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}
	
}
