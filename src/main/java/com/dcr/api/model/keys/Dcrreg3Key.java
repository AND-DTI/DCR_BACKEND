package com.dcr.api.model.keys;
//import io.swagger.annotations.ApiModel;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Table;


public class Dcrreg3Key {
	private Long idmatriz;
	private String partnumpd;
	private String tpprd;
	private Integer numsubcomp;
	private Integer numcomp;
	
	public Long getIdmatriz() {
		return idmatriz;
	}
	public void setIdmatriz(Long idmatriz) {
		this.idmatriz = idmatriz;
	}
	public String getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}
	public String getTpprd() {
		return tpprd;
	}
	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}
	public Integer getNumsubcomp() {
		return numsubcomp;
	}
	public void setNumsubcomp(Integer numsubcomp) {
		this.numsubcomp = numsubcomp;
	}
	public Integer getNumcomp() {
		return numcomp;
	}
	public void setNumcomp(Integer numcomp) {
		this.numcomp = numcomp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idmatriz == null) ? 0 : idmatriz.hashCode());
		result = prime * result + ((partnumpd == null) ? 0 : partnumpd.hashCode());
		result = prime * result + ((tpprd == null) ? 0 : tpprd.hashCode());
		result = prime * result + ((numsubcomp == null) ? 0 : numsubcomp.hashCode());
		result = prime * result + ((numcomp == null) ? 0 : numcomp.hashCode());
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
		Dcrreg3Key other = (Dcrreg3Key) obj;
		if (idmatriz == null) {
			if (other.idmatriz != null)
				return false;
		} else if (!idmatriz.equals(other.idmatriz))
			return false;
		if (partnumpd == null) {
			if (other.partnumpd != null)
				return false;
		} else if (!partnumpd.equals(other.partnumpd))
			return false;
		if (tpprd == null) {
			if (other.tpprd != null)
				return false;
		} else if (!tpprd.equals(other.tpprd))
			return false;
		if (numsubcomp == null) {
			if (other.numsubcomp != null)
				return false;
		} else if (!numsubcomp.equals(other.numsubcomp))
			return false;
		if (numcomp == null) {
			if (other.numcomp != null)
				return false;
		} else if (!numcomp.equals(other.numcomp))
			return false;
		return true;
	}
}
