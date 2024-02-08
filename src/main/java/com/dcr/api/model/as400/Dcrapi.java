package com.dcr.api.model.as400;

import com.dcr.api.model.keys.DcrapiKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRAPI", schema = "HD4DCDHH")
@ApiModel
public class Dcrapi {
	
	@EmbeddedId
    private DcrapiKey dcrapiKey;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	private Integer stsconfig;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String coligproc;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String coligcust;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String colignac;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String coligexpir;
	
	@TamanhoMaximo(60)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(60)")
	private String coligemai1;
	
	@TamanhoMaximo(60)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(60)")
	private String coligemai2;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
	private String alerturl;
	
	@TamanhoMaximo(250)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(250)")
	private String alerthead;
	
	@TamanhoMaximo(800)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(800)")
	private String alertbody;
	
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

	public DcrapiKey getDcrapiKey() {
		return dcrapiKey;
	}

	public void setDcrapiKey(DcrapiKey dcrapiKey) {
		this.dcrapiKey = dcrapiKey;
	}

	public String getColigproc() {
		return coligproc;
	}

	public void setColigproc(String coligproc) {
		this.coligproc = coligproc;
	}

	public String getColigcust() {
		return coligcust;
	}

	public void setColigcust(String coligcust) {
		this.coligcust = coligcust;
	}

	public String getColignac() {
		return colignac;
	}

	public void setColignac(String colignac) {
		this.colignac = colignac;
	}

	public String getColigexpir() {
		return coligexpir;
	}

	public void setColigexpir(String coligexpir) {
		this.coligexpir = coligexpir;
	}

	public String getColigemai1() {
		return coligemai1;
	}

	public void setColigemai1(String coligemai1) {
		this.coligemai1 = coligemai1;
	}

	public String getColigemai2() {
		return coligemai2;
	}

	public void setColigemai2(String coligemai2) {
		this.coligemai2 = coligemai2;
	}

	public String getAlerturl() {
		return alerturl;
	}

	public void setAlerturl(String alerturl) {
		this.alerturl = alerturl;
	}

	public String getAlerthead() {
		return alerthead;
	}

	public void setAlerthead(String alerthead) {
		this.alerthead = alerthead;
	}

	public String getAlertbody() {
		return alertbody;
	}

	public void setAlertbody(String alertbody) {
		this.alertbody = alertbody;
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
}
