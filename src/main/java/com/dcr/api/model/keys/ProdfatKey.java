package com.dcr.api.model.keys;


public class ProdfatKey {

	private String cdprd;
	private String partnumpd;
	public String getCdprd() {
		return cdprd;
	}
	public void setCdprd(String cdprd) {
		this.cdprd = cdprd;
	}
	public String getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdprd == null) ? 0 : cdprd.hashCode());
		result = prime * result + ((partnumpd == null) ? 0 : partnumpd.hashCode());
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
		ProdfatKey other = (ProdfatKey) obj;
		if (cdprd == null) {
			if (other.cdprd != null)
				return false;
		} else if (!cdprd.equals(other.cdprd))
			return false;
		if (partnumpd == null) {
			if (other.partnumpd != null)
				return false;
		} else if (!partnumpd.equals(other.partnumpd))
			return false;
		return true;
	}
	
}
