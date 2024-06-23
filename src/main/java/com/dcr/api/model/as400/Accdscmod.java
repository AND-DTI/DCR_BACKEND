package com.dcr.api.model.as400;

import com.dcr.api.model.keys.AccdscmodKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCDSCMOD", schema = "HD4DCDHH")
@ApiModel
public class Accdscmod {

	@EmbeddedId
	private AccdscmodKey key;
	
	@Column(columnDefinition = "char(45)")
	@TamanhoMaximo(45)
	@TamanhoMinimo(1)
	private String shorttitle;
	
	@Column(columnDefinition = "char(60)")
	@TamanhoMaximo(60)
	@TamanhoMinimo(1)
	private String subtitle;
	
	@Column(columnDefinition = "char(150)")
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	private String descmod;
	
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

	public AccdscmodKey getKey() {
		return key;
	}

	public void setKey(AccdscmodKey key) {
		this.key = key;
	}

	public String getShorttitle() {
		return shorttitle;
	}

	public void setShorttitle(String shorttitle) {
		this.shorttitle = shorttitle;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getDescmod() {
		return descmod;
	}

	public void setDescmod(String descmod) {
		this.descmod = descmod;
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
