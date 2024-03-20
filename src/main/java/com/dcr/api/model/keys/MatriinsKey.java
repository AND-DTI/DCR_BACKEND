package com.dcr.api.model.keys;

import java.util.Objects;

import com.dcr.api.validator.TamanhoMaximo;

public class MatriinsKey {
	@TamanhoMaximo(9)
	private Integer idmatriz;
	
	@TamanhoMaximo(25)
	private String partnum;

	@TamanhoMaximo(25)
	private String partnumpd;
	
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
		return Objects.hash(idmatriz, partnum);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatriinsKey other = (MatriinsKey) obj;
		return Objects.equals(idmatriz, other.idmatriz) && Objects.equals(partnum, other.partnum);
	}

	public String getPartnumpd() {
		return partnumpd;
	}

	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}
}
