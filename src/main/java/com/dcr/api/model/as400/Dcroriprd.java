package com.dcr.api.model.as400;

import com.dcr.api.model.keys.DcroriprdKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRORIPRD", schema = "HD4DCDHH")
@ApiModel
public class Dcroriprd {

	@EmbeddedId
    private DcroriprdKey dcroriprdKey;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(5)")
	private Integer stsconfig;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer planopd;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer estoq;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Integer astecped;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer astecopen;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer astecppa;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer garantia;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer planon0;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer planon1;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer astecpedn0;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer astecpedn1;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer astecppan0;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer astecppan1;

	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer reprocstru;
	
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
    
	public Integer getPlanopd() {
		return planopd;
	}

	public void setPlanopd(Integer planopd) {
		this.planopd = planopd;
	}

	public Integer getEstoq() {
		return estoq;
	}

	public void setEstoq(Integer estoq) {
		this.estoq = estoq;
	}

	public Integer getAstecped() {
		return astecped;
	}

	public void setAstecped(Integer astecped) {
		this.astecped = astecped;
	}

	public Integer getAstecopen() {
		return astecopen;
	}

	public void setAstecopen(Integer astecopen) {
		this.astecopen = astecopen;
	}

	public Integer getAstecppa() {
		return astecppa;
	}

	public void setAstecppa(Integer astecppa) {
		this.astecppa = astecppa;
	}

	public Integer getGarantia() {
		return garantia;
	}

	public void setGarantia(Integer garantia) {
		this.garantia = garantia;
	}

	public Integer getPlanon0() {
		return planon0;
	}

	public void setPlanon0(Integer planon0) {
		this.planon0 = planon0;
	}

	public Integer getPlanon1() {
		return planon1;
	}

	public void setPlanon1(Integer planon1) {
		this.planon1 = planon1;
	}

	public Integer getAstecpedn0() {
		return astecpedn0;
	}

	public void setAstecpedn0(Integer astecpedn0) {
		this.astecpedn0 = astecpedn0;
	}

	public Integer getAstecpedn1() {
		return astecpedn1;
	}

	public void setAstecpedn1(Integer astecpedn1) {
		this.astecpedn1 = astecpedn1;
	}

	public DcroriprdKey getDcroriprdKey() {
		return dcroriprdKey;
	}

	public void setDcroriprdKey(DcroriprdKey dcroriprdKey) {
		this.dcroriprdKey = dcroriprdKey;
	}

	public Integer getAstecppan0() {
		return astecppan0;
	}

	public void setAstecppan0(Integer astecppan0) {
		this.astecppan0 = astecppan0;
	}

	public Integer getAstecppan1() {
		return astecppan1;
	}

	public void setAstecppan1(Integer astecppan1) {
		this.astecppan1 = astecppan1;
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

	public Integer getStsconfig() {
		return stsconfig;
	}

	public void setStsconfig(Integer stsconfig) {
		this.stsconfig = stsconfig;
	}

	public Integer getReprocstru() {
		return reprocstru;
	}

	public void setReprocstru(Integer reprocstru) {
		this.reprocstru = reprocstru;
	}

}
