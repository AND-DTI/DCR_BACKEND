package com.dcr.api.model.as400;

import com.dcr.api.model.keys.MtastedocKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "MTASTEDOC", schema = "HD4DCDHH")
@ApiModel
public class Mtastedoc {

	@EmbeddedId
	private MtastedocKey key;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
    private String numdoc;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String serdoc;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String emidoc;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
    private String numdoc2;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String serdoc2;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String emidoc2;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
    private String numdocnew;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String serdocnew;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String emidocnew;
	
	@Column(columnDefinition = "char(40)")
    private String itaudsys;	
	
	@Column(columnDefinition = "char(10)")
    private String itaudusr;
	
	@Column(columnDefinition = "char(20)")
    private String itaudhst;
	
	@Column(columnDefinition = "char(8)")
    private String itauddt;
	
	@Column(columnDefinition = "char(8)")
    private String itaudhr;

	public MtastedocKey getKey() {
		return key;
	}

	public void setKey(MtastedocKey key) {
		this.key = key;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getSerdoc() {
		return serdoc;
	}

	public void setSerdoc(String serdoc) {
		this.serdoc = serdoc;
	}

	public String getEmidoc() {
		return emidoc;
	}

	public void setEmidoc(String emidoc) {
		this.emidoc = emidoc;
	}

	public String getNumdoc2() {
		return numdoc2;
	}

	public void setNumdoc2(String numdoc2) {
		this.numdoc2 = numdoc2;
	}

	public String getSerdoc2() {
		return serdoc2;
	}

	public void setSerdoc2(String serdoc2) {
		this.serdoc2 = serdoc2;
	}

	public String getEmidoc2() {
		return emidoc2;
	}

	public void setEmidoc2(String emidoc2) {
		this.emidoc2 = emidoc2;
	}

	public String getNumdocnew() {
		return numdocnew;
	}

	public void setNumdocnew(String numdocnew) {
		this.numdocnew = numdocnew;
	}

	public String getSerdocnew() {
		return serdocnew;
	}

	public void setSerdocnew(String serdocnew) {
		this.serdocnew = serdocnew;
	}

	public String getEmidocnew() {
		return emidocnew;
	}

	public void setEmidocnew(String emidocnew) {
		this.emidocnew = emidocnew;
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
