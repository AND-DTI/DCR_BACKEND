package com.dcr.api.model.as400;

import com.dcr.api.model.keys.MtastecKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MTASTEC", schema = "HD4DCDHH")
@ApiModel
public class Mtastec {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmatriz;

	private String partnumpd;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
    private String desccom;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
    private String descrfb;
	
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
    private String unmed;
	
	@TamanhoMaximo(7)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(7)")
    private String origprd;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String dtneci;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Integer priourgen;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String prevfat;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String prioresp;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String priodtmnt;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(5)")
    private String priohrmnt;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
    private String tpdcre;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
    private String itgarantia;
	
	@TamanhoMaximo(250)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(250)")
    private String obsprio;
	
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

	public String getUnmed() {
		return unmed;
	}

	public void setUnmed(String unmed) {
		this.unmed = unmed;
	}

	public String getOrigprd() {
		return origprd;
	}

	public void setOrigprd(String origprd) {
		this.origprd = origprd;
	}

	public String getDtneci() {
		return dtneci;
	}

	public void setDtneci(String dtneci) {
		this.dtneci = dtneci;
	}

	public Integer getPriourgen() {
		return priourgen;
	}

	public void setPriourgen(Integer priourgen) {
		this.priourgen = priourgen;
	}

	public String getPrevfat() {
		return prevfat;
	}

	public void setPrevfat(String prevfat) {
		this.prevfat = prevfat;
	}

	public String getPrioresp() {
		return prioresp;
	}

	public void setPrioresp(String prioresp) {
		this.prioresp = prioresp;
	}

	public String getPriodtmnt() {
		return priodtmnt;
	}

	public void setPriodtmnt(String priodtmnt) {
		this.priodtmnt = priodtmnt;
	}

	public String getPriohrmnt() {
		return priohrmnt;
	}

	public void setPriohrmnt(String priohrmnt) {
		this.priohrmnt = priohrmnt;
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

	public Integer getIdmatriz() {
		return idmatriz;
	}

	public void setIdmatriz(Integer idmatriz) {
		this.idmatriz = idmatriz;
	}

	public String getPartnumpd() {
		return partnumpd;
	}

	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}

	public String getTpdcre() {
		return tpdcre;
	}

	public void setTpdcre(String tpdcre) {
		this.tpdcre = tpdcre;
	}

	public String getItgarantia() {
		return itgarantia;
	}

	public void setItgarantia(String itgarantia) {
		this.itgarantia = itgarantia;
	}

	public String getObsprio() {
		return obsprio;
	}

	public void setObsprio(String obsprio) {
		this.obsprio = obsprio;
	}
}
