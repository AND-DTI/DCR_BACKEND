package com.dcr.api.model.as400;

import com.dcr.api.model.keys.Dcrreg2Key;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRREG2", schema = "HD4DCDHH")
@ApiModel
public class Dcrreg2 {

	@EmbeddedId
	private Dcrreg2Key key;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String idreg;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Long numnf;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(5)")
	private String sernf;
	
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Long cnpjfor;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(15)")
	private String ie;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String eminf;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
	private String espec;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
	private String undcom;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String ncm;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,7)")
	private Double qtde;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double vlrunit;
	
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

	public Dcrreg2Key getKey() {
		return key;
	}

	public void setKey(Dcrreg2Key key) {
		this.key = key;
	}

	public String getIdreg() {
		return idreg;
	}

	public void setIdreg(String idreg) {
		this.idreg = idreg;
	}


	public String getSernf() {
		return sernf;
	}

	public void setSernf(String sernf) {
		this.sernf = sernf;
	}

	public Long getCnpjfor() {
		return cnpjfor;
	}

	public void setCnpjfor(Long cnpjfor) {
		this.cnpjfor = cnpjfor;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getEminf() {
		return eminf;
	}

	public void setEminf(String eminf) {
		this.eminf = eminf;
	}

	public String getEspec() {
		return espec;
	}

	public void setEspec(String espec) {
		this.espec = espec;
	}

	public String getUndcom() {
		return undcom;
	}

	public void setUndcom(String undcom) {
		this.undcom = undcom;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
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

	public Long getNumnf() {
		return numnf;
	}

	public void setNumnf(Long numnf) {
		this.numnf = numnf;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public Double getVlrunit() {
		return vlrunit;
	}

	public void setVlrunit(Double vlrunit) {
		this.vlrunit = vlrunit;
	}
}
