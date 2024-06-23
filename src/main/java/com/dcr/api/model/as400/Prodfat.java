package com.dcr.api.model.as400;

import com.dcr.api.model.keys.ProdfatKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODFAT", schema = "HD4DCDHH")
@ApiModel
public class Prodfat {

	@EmbeddedId
	private ProdfatKey key;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
	private String desccom;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
	private String tpprd;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(4)
	@Column(columnDefinition = "int")
	private Integer anomdl;
	
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
	private String uncome;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(4)
	@Column(columnDefinition = "int")
	private Integer frstanofab;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(4)
	@Column(columnDefinition = "int")
	private Integer lastanofab;
	
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

	public ProdfatKey getKey() {
		return key;
	}

	public void setKey(ProdfatKey key) {
		this.key = key;
	}

	public String getDesccom() {
		return desccom;
	}

	public void setDesccom(String desccom) {
		this.desccom = desccom;
	}

	public String getTpprd() {
		return tpprd;
	}

	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}

	public Integer getAnomdl() {
		return anomdl;
	}

	public void setAnomdl(Integer anomdl) {
		this.anomdl = anomdl;
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

	public String getUncome() {
		return uncome;
	}

	public void setUncome(String uncome) {
		this.uncome = uncome;
	}

	public Integer getFrstanofab() {
		return frstanofab;
	}

	public void setFrstanofab(Integer frstanofab) {
		this.frstanofab = frstanofab;
	}

	public Integer getLastanofab() {
		return lastanofab;
	}

	public void setLastanofab(Integer lastanofab) {
		this.lastanofab = lastanofab;
	}
}
