package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PRODMOD", schema = "HD4DCDHH")
@ApiModel
public class Prodmod {

	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	@Id
    private String partnumpd;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
    private String descpor;
	
	@TamanhoMaximo(60)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
    private String descing;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(15)")
    private String uengno;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String modelo;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private String anomdl;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
    private String codcor;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String mdsugest;
	
	@TamanhoMaximo(7)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Long psliq;
	
	@TamanhoMaximo(7)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Long psbrt;
	
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

	public String getPartnumpd() {
		return partnumpd;
	}

	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}

	public String getDescpor() {
		return descpor;
	}

	public void setDescpor(String descpor) {
		this.descpor = descpor;
	}

	public String getDescing() {
		return descing;
	}

	public void setDescing(String descing) {
		this.descing = descing;
	}

	public String getUengno() {
		return uengno;
	}

	public void setUengno(String uengno) {
		this.uengno = uengno;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getAnomdl() {
		return anomdl;
	}

	public void setAnomdl(String anomdl) {
		this.anomdl = anomdl;
	}

	public String getCodcor() {
		return codcor;
	}

	public void setCodcor(String codcor) {
		this.codcor = codcor;
	}

	public String getMdsugest() {
		return mdsugest;
	}

	public void setMdsugest(String mdsugest) {
		this.mdsugest = mdsugest;
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

	public Long getPsliq() {
		return psliq;
	}

	public void setPsliq(Long psliq) {
		this.psliq = psliq;
	}

	public Long getPsbrt() {
		return psbrt;
	}

	public void setPsbrt(Long psbrt) {
		this.psbrt = psbrt;
	}
}
