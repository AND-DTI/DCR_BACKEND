package com.dcr.api.response;

import java.util.List;

public class AstecDetailResponse {
	private Object idmatriz;
	private Object desccom;
	private Object descrfb;
	private Object origprd;
	private Object dtneci;
	private Object priourgen;
	private Object prevfat;
	private Object prioresp;
	private Object priodtmnt;
	private Object priohrmnt;
	private List<InsumosResponse> insumos;
	
	public Object getIdmatriz() {
		return idmatriz;
	}
	public void setIdmatriz(Object idmatriz) {
		this.idmatriz = idmatriz;
	}
	public Object getDesccom() {
		return desccom;
	}
	public void setDesccom(Object desccom) {
		this.desccom = desccom;
	}
	public Object getDescrfb() {
		return descrfb;
	}
	public void setDescrfb(Object descrfb) {
		this.descrfb = descrfb;
	}
	public Object getOrigprd() {
		return origprd;
	}
	public void setOrigprd(Object origprd) {
		this.origprd = origprd;
	}
	public Object getDtneci() {
		return dtneci;
	}
	public void setDtneci(Object dtneci) {
		this.dtneci = dtneci;
	}
	public Object getPriourgen() {
		return priourgen;
	}
	public void setPriourgen(Object priourgen) {
		this.priourgen = priourgen;
	}
	public Object getPrevfat() {
		return prevfat;
	}
	public void setPrevfat(Object prevfat) {
		this.prevfat = prevfat;
	}
	public Object getPrioresp() {
		return prioresp;
	}
	public void setPrioresp(Object prioresp) {
		this.prioresp = prioresp;
	}
	public Object getPriodtmnt() {
		return priodtmnt;
	}
	public void setPriodtmnt(Object priodtmnt) {
		this.priodtmnt = priodtmnt;
	}
	public Object getPriohrmnt() {
		return priohrmnt;
	}
	public void setPriohrmnt(Object priohrmnt) {
		this.priohrmnt = priohrmnt;
	}
	public List<InsumosResponse> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<InsumosResponse> insumos) {
		this.insumos = insumos;
	}
}
