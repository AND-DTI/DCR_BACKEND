package com.dcr.api.model.keys;

import java.util.Objects;

public class PendastecKey {
	private Integer idmatriz;
	private String partnum;
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
	public Integer getNumpend() {
		return numpend;
	}
	public void setNumpend(Integer numpend) {
		this.numpend = numpend;
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
		PendastecKey other = (PendastecKey) obj;
		return Objects.equals(idmatriz, other.idmatriz) && Objects.equals(numpend, other.numpend)
				&& Objects.equals(partnum, other.partnum);
	}
}
