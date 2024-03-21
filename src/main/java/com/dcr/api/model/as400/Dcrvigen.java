package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRVIGEN", schema = "HD4DCDHH")
@ApiModel
public class Dcrvigen {

	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String dcre;

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int(10)")
	private Integer idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
	private String tpprd;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtregistro;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrregistro;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String dcrant;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtvigini;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrvigini;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtvigfim;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrvigfim;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(8,6)")
	private String taxausd;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private String totalnac;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private String totalimp;

	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private String custotal;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(4,2)")
	private String coefred;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private String iitotal;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private String iireduzido;
	
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

	public String getDcre() {
		return dcre;
	}

	public void setDcre(String dcre) {
		this.dcre = dcre;
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

	public String getTpprd() {
		return tpprd;
	}

	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}

	public String getDtregistro() {
		return dtregistro;
	}

	public void setDtregistro(String dtregistro) {
		this.dtregistro = dtregistro;
	}

	public String getHrregistro() {
		return hrregistro;
	}

	public void setHrregistro(String hrregistro) {
		this.hrregistro = hrregistro;
	}

	public String getDcrant() {
		return dcrant;
	}

	public void setDcrant(String dcrant) {
		this.dcrant = dcrant;
	}

	public String getDtvigini() {
		return dtvigini;
	}

	public void setDtvigini(String dtvigini) {
		this.dtvigini = dtvigini;
	}

	public String getHrvigini() {
		return hrvigini;
	}

	public void setHrvigini(String hrvigini) {
		this.hrvigini = hrvigini;
	}

	public String getDtvigfim() {
		return dtvigfim;
	}

	public void setDtvigfim(String dtvigfim) {
		this.dtvigfim = dtvigfim;
	}

	public String getHrvigfim() {
		return hrvigfim;
	}

	public void setHrvigfim(String hrvigfim) {
		this.hrvigfim = hrvigfim;
	}

	public String getTaxausd() {
		return taxausd;
	}

	public void setTaxausd(String taxausd) {
		this.taxausd = taxausd;
	}

	public String getTotalnac() {
		return totalnac;
	}

	public void setTotalnac(String totalnac) {
		this.totalnac = totalnac;
	}

	public String getTotalimp() {
		return totalimp;
	}

	public void setTotalimp(String totalimp) {
		this.totalimp = totalimp;
	}

	public String getCustotal() {
		return custotal;
	}

	public void setCustotal(String custotal) {
		this.custotal = custotal;
	}

	public String getCoefred() {
		return coefred;
	}

	public void setCoefred(String coefred) {
		this.coefred = coefred;
	}

	public String getIitotal() {
		return iitotal;
	}

	public void setIitotal(String iitotal) {
		this.iitotal = iitotal;
	}

	public String getIireduzido() {
		return iireduzido;
	}

	public void setIireduzido(String iireduzido) {
		this.iireduzido = iireduzido;
	}
}
