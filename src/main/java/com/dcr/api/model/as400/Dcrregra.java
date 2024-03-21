package com.dcr.api.model.as400;

import com.dcr.api.model.keys.DcrregraKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRREGRA", schema = "HD4DCDHH")
@ApiModel
public class Dcrregra {

	@EmbeddedId
    private DcrregraKey dcrregraKey;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(5)")
	private Integer stsconfig;
	  
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(14)")
	private String cnpjemi;
	
	@TamanhoMaximo(600)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(600)")
	private String razsoc;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer peritran;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer proccarenc;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer trancarenc;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer procsemppb;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer expiraprev;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer diasprevia;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer alertaprev;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer tpvalor;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer taxamanual;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer carencia;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer substituto;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer substfat;
    
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    @Column(columnDefinition = "int")
    private Integer substfatn;

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

	public Integer getPeritran() {
		return peritran;
	}

	public void setPeritran(Integer peritran) {
		this.peritran = peritran;
	}

	public Integer getProccarenc() {
		return proccarenc;
	}

	public void setProccarenc(Integer proccarenc) {
		this.proccarenc = proccarenc;
	}

	public Integer getTrancarenc() {
		return trancarenc;
	}

	public void setTrancarenc(Integer trancarenc) {
		this.trancarenc = trancarenc;
	}

	public Integer getProcsemppb() {
		return procsemppb;
	}

	public void setProcsemppb(Integer procsemppb) {
		this.procsemppb = procsemppb;
	}

	public Integer getExpiraprev() {
		return expiraprev;
	}

	public void setExpiraprev(Integer expiraprev) {
		this.expiraprev = expiraprev;
	}

	public Integer getDiasprevia() {
		return diasprevia;
	}

	public void setDiasprevia(Integer diasprevia) {
		this.diasprevia = diasprevia;
	}

	public Integer getAlertaprev() {
		return alertaprev;
	}

	public void setAlertaprev(Integer alertaprev) {
		this.alertaprev = alertaprev;
	}

	public Integer getTpvalor() {
		return tpvalor;
	}

	public void setTpvalor(Integer tpvalor) {
		this.tpvalor = tpvalor;
	}

	public Integer getTaxamanual() {
		return taxamanual;
	}

	public void setTaxamanual(Integer taxamanual) {
		this.taxamanual = taxamanual;
	}

	public Integer getCarencia() {
		return carencia;
	}

	public void setCarencia(Integer carencia) {
		this.carencia = carencia;
	}

	public Integer getSubstituto() {
		return substituto;
	}

	public void setSubstituto(Integer substituto) {
		this.substituto = substituto;
	}

	public Integer getSubstfat() {
		return substfat;
	}

	public void setSubstfat(Integer substfat) {
		this.substfat = substfat;
	}

	public Integer getSubstfatn() {
		return substfatn;
	}

	public void setSubstfatn(Integer substfatn) {
		this.substfatn = substfatn;
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

	public DcrregraKey getDcrregraKey() {
		return dcrregraKey;
	}

	public void setDcrregraKey(DcrregraKey dcrregraKey) {
		this.dcrregraKey = dcrregraKey;
	}

	public Integer getStsconfig() {
		return stsconfig;
	}

	public void setStsconfig(Integer stsconfig) {
		this.stsconfig = stsconfig;
	}

	public String getCnpjemi() {
		return cnpjemi;
	}

	public void setCnpjemi(String cnpjemi) {
		this.cnpjemi = cnpjemi;
	}

	public String getRazsoc() {
		return razsoc;
	}

	public void setRazsoc(String razsoc) {
		this.razsoc = razsoc;
	}
    
}
