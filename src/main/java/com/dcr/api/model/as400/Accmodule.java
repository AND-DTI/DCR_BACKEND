package com.dcr.api.model.as400;

import com.dcr.api.model.keys.AccModuleKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCMODULE", schema = "HD4DCDHH")
@ApiModel
public class Accmodule {

	@EmbeddedId
	private AccModuleKey key;
	
	@Column(columnDefinition = "char(100)")
	@TamanhoMaximo(100)
	@TamanhoMinimo(1)	
	private String namemodule;
	
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

	public String getNamemodule() {
		return namemodule;
	}

	public void setNamemodule(String namemodule) {
		this.namemodule = namemodule;
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

	public String getItaudsys() {
		return itaudsys;
	}

	public void setItaudsys(String itaudsys) {
		this.itaudsys = itaudsys;
	}

	public AccModuleKey getKey() {
		return key;
	}

	public void setKey(AccModuleKey key) {
		this.key = key;
	}

}
