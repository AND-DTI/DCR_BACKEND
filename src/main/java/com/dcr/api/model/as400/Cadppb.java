package com.dcr.api.model.as400;

import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "CADPPB", schema = "HD4DCDHH")
@ApiModel
public class Cadppb {

	@EmbeddedId
	private ProdutoKey key;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
	private String desccom;
	
	@TamanhoMaximo(80)
	@Column(columnDefinition = "char(80)")
	private String descrfb;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String prddest;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
	private String ppbprd;
	 
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

	public ProdutoKey getKey() {
		return key;
	}

	public void setKey(ProdutoKey key) {
		this.key = key;
	}

	public String getDesccom() {
		return desccom;
	}

	public void setDesccom(String desccom) {
		this.desccom = desccom;
	}

	public String getDescrfb() {
		return descrfb;
	}

	public void setDescrfb(String descrfb) {
		this.descrfb = descrfb;
	}

	public String getPrddest() {
		return prddest;
	}

	public void setPrddest(String prddest) {
		this.prddest = prddest;
	}

	public String getPpbprd() {
		return ppbprd;
	}

	public void setPpbprd(String ppbprd) {
		this.ppbprd = ppbprd;
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
