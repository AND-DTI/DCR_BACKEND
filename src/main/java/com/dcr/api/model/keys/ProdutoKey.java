package com.dcr.api.model.keys;

import java.util.Objects;

public class ProdutoKey {
	private String cdprd;
	private String tpprd;
	public String getCdprd() {
		return cdprd;
	}
	public void setCdprd(String cdprd) {
		this.cdprd = cdprd;
	}
	public String getTpprd() {
		return tpprd;
	}
	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cdprd, tpprd);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProdutoKey other = (ProdutoKey) obj;
		return Objects.equals(cdprd, other.cdprd) && Objects.equals(tpprd, other.tpprd);
	}
}
