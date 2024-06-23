package com.dcr.api.model.as400;

import com.dcr.api.model.keys.AccdscopeKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCDSCOPE", schema = "HD4DCDHH")
@ApiModel
public class Accdscope {

	@EmbeddedId
    private AccdscopeKey key;
	
	@TamanhoMaximo(45)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(45)")
    private String shorttitle;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
    private String descoper;
	
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

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getDescoper() {
		return descoper;
	}

	public void setDescoper(String descoper) {
		this.descoper = descoper;
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

	public AccdscopeKey getKey() {
		return key;
	}

	public void setKey(AccdscopeKey key) {
		this.key = key;
	}
}
