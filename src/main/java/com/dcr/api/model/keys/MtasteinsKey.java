package com.dcr.api.model.keys;

import java.util.Objects;

public class MtasteinsKey {
	private Integer idmatriz;
	private String partnum;
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
		MtasteinsKey other = (MtasteinsKey) obj;
		return Objects.equals(idmatriz, other.idmatriz) && Objects.equals(partnum, other.partnum);
	}
}
