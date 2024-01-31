package com.dcr.api.model.as400;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CADCOR", schema = "HD4DCDHH")
public class Cadcor {

	@Id
	@Column(columnDefinition = "char(4)")
    private String codcor;
	
	@Column(columnDefinition = "char(4)")
    private String cdbej;
	
	@Column(columnDefinition = "char(10)")
    private String corpt;
	
	@Column(columnDefinition = "char(15)")
    private String coreng;
	
	@Column(columnDefinition = "char(1)")
    private String tppin;

    @Column(columnDefinition = "char(40)")
    private String itaudsys;
	   
    @Column(columnDefinition = "char(10)")
    private String itaudusr;

    @Column(columnDefinition = "char(30)")
    private String itaudhst;

    @Column(columnDefinition = "char(8)")
    private String itauddt;

    @Column(columnDefinition = "char(8)")
    private String itaudhr;

	public String getCodcor() {
		return codcor;
	}

	public void setCodcor(String codcor) {
		this.codcor = codcor;
	}

	public String getCdbej() {
		return cdbej;
	}

	public void setCdbej(String cdbej) {
		this.cdbej = cdbej;
	}

	public String getCorpt() {
		return corpt;
	}

	public void setCorpt(String corpt) {
		this.corpt = corpt;
	}

	public String getTppin() {
		return tppin;
	}

	public void setTppin(String tppin) {
		this.tppin = tppin;
	}

	public String getItaudusr() {
		return itaudusr;
	}

	public void setItaudusr(String itaudusr) {
		this.itaudusr = itaudusr;
	}

	public String getItaudhst() {
		return itaudhst;
	}

	public void setItaudhst(String itaudhst) {
		this.itaudhst = itaudhst;
	}

	public String getItauddt() {
		return itauddt;
	}

	public void setItauddt(String itauddt) {
		this.itauddt = itauddt;
	}

	public String getItaudhr() {
		return itaudhr;
	}

	public void setItaudhr(String itaudhr) {
		this.itaudhr = itaudhr;
	}

	public String getCoreng() {
		return coreng;
	}

	public void setCoreng(String coreng) {
		this.coreng = coreng;
	}

	public String getItaudsys() {
		return itaudsys;
	}

	public void setItaudsys(String itaudsys) {
		this.itaudsys = itaudsys;
	}
}
