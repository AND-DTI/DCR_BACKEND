package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "ACCSYS", schema = "HD4DCDHH")
@ApiModel
public class Accsys {
	
	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String cdsys;
	
	@TamanhoMaximo(60)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(60)")
	private String namesys;
	
	@TamanhoMaximo(200)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(200)")
	private String descsys;
	
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

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}

	public String getNamesys() {
		return namesys;
	}

	public void setNamesys(String namesys) {
		this.namesys = namesys;
	}

	public String getDescsys() {
		return descsys;
	}

	public void setDescsys(String descsys) {
		this.descsys = descsys;
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
