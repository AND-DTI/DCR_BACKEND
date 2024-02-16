package com.dcr.api.model.keys;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


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
}
