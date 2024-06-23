package com.dcr.api.model.as400;

import com.dcr.api.model.keys.DcrlayoutKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRLAYOUT", schema = "HD4DCDHH")
@ApiModel
public class Dcrlayout {
	
	@EmbeddedId
	private DcrlayoutKey key;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
	private String descreg;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer posini;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer posfim;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer campotam;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
	private String campodesc;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
	private String regra;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String condfield;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String condvalue;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String fillzero;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String fillblank;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String obrig;
	
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

	public DcrlayoutKey getKey() {
		return key;
	}

	public void setKey(DcrlayoutKey key) {
		this.key = key;
	}

	public String getDescreg() {
		return descreg;
	}

	public void setDescreg(String descreg) {
		this.descreg = descreg;
	}

	public Integer getPosini() {
		return posini;
	}

	public void setPosini(Integer posini) {
		this.posini = posini;
	}

	public Integer getPosfim() {
		return posfim;
	}

	public void setPosfim(Integer posfim) {
		this.posfim = posfim;
	}

	public Integer getCampotam() {
		return campotam;
	}

	public void setCampotam(Integer campotam) {
		this.campotam = campotam;
	}

	public String getCampodesc() {
		return campodesc;
	}

	public void setCampodesc(String campodesc) {
		this.campodesc = campodesc;
	}

	public String getRegra() {
		return regra;
	}

	public void setRegra(String regra) {
		this.regra = regra;
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

	public String getObrig() {
		return obrig;
	}

	public void setObrig(String obrig) {
		this.obrig = obrig;
	}

	public String getCondfield() {
		return condfield;
	}

	public void setCondfield(String condfield) {
		this.condfield = condfield;
	}

	public String getCondvalue() {
		return condvalue;
	}

	public void setCondvalue(String condvalue) {
		this.condvalue = condvalue;
	}

	public String getFillzero() {
		return fillzero;
	}

	public void setFillzero(String fillzero) {
		this.fillzero = fillzero;
	}

	public String getFillblank() {
		return fillblank;
	}

	public void setFillblank(String fillblank) {
		this.fillblank = fillblank;
	}
}
