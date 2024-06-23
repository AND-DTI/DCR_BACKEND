package com.dcr.api.model.as400;

import com.dcr.api.model.keys.Dcrreg9Key;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRREG9", schema = "HD4DCDHH")
@ApiModel
public class Dcrreg9 {

	@EmbeddedId
	private Dcrreg9Key key;
	
	@Column(columnDefinition = "char(1)")
	private String idreg;
	
	@Column(columnDefinition = "int")
	private Long qtdred;
	
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

	public Dcrreg9Key getKey() {
		return key;
	}

	public void setKey(Dcrreg9Key key) {
		this.key = key;
	}

	public String getIdreg() {
		return idreg;
	}

	public void setIdreg(String idreg) {
		this.idreg = idreg;
	}

	public Long getQtdred() {
		return qtdred;
	}

	public void setQtdred(Long qtdred) {
		this.qtdred = qtdred;
	}

	public String getItaudsys() {
		return itaudsys;
	}

	public void setItaudsys(String itaudsys) {
		this.itaudsys = itaudsys;
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
}
