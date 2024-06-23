package com.dcr.api.model.keys;

import java.util.Objects;

public class MatridocKey {
	private Integer idmatriz;
	private String partnum;
	private String partnumpd;
	private String tpdoc;
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
	public String getTpdoc() {
		return tpdoc;
	}
	public void setTpdoc(String tpdoc) {
		this.tpdoc = tpdoc;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idmatriz, partnum, tpdoc);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatridocKey other = (MatridocKey) obj;
		return Objects.equals(idmatriz, other.idmatriz) && Objects.equals(partnum, other.partnum)
				&& Objects.equals(tpdoc, other.tpdoc);
	}
	public String getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}
}
