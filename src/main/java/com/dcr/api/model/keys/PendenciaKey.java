package com.dcr.api.model.keys;

import java.util.Objects;

public class PendenciaKey {
	private String cdpend;
	private String cdresp;
	
	public String getCdpend() {
		return cdpend;
	}
	public void setCdpend(String cdpend) {
		this.cdpend = cdpend;
	}
	public String getCdresp() {
		return cdresp;
	}
	public void setCdresp(String cdresp) {
		this.cdresp = cdresp;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cdpend, cdresp);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PendenciaKey other = (PendenciaKey) obj;
		return Objects.equals(cdpend, other.cdpend) && Objects.equals(cdresp, other.cdresp);
	}
}
