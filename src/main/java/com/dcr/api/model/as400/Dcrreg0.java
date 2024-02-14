package com.dcr.api.model.as400;

import com.dcr.api.model.keys.Dcrreg0Key;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRREG0", schema = "HD4DCDHH")
@ApiModel
public class Dcrreg0 {

	@EmbeddedId
	private Dcrreg0Key key;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(2)")
	private String idreg;
	
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(14)")
	private String cnpj;
	
	@TamanhoMaximo(11)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(11)")
	private String cpfrl;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
	private String ppb;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String ncm;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
	private String undcom;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(14,5)")
	private Double peso;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,2)")
	private Double salarios;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,2)")
	private Double encargos;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String tpdcre;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String dcrant;
	
	@TamanhoMaximo(17)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(17)")
	private String procretif;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
	private String vrspgd;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String origdcr;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String tpcoef;
	
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

	public Dcrreg0Key getKey() {
		return key;
	}

	public void setKey(Dcrreg0Key key) {
		this.key = key;
	}

	public String getIdreg() {
		return idreg;
	}

	public void setIdreg(String idreg) {
		this.idreg = idreg;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpfrl() {
		return cpfrl;
	}

	public void setCpfrl(String cpfrl) {
		this.cpfrl = cpfrl;
	}

	public String getPpb() {
		return ppb;
	}

	public void setPpb(String ppb) {
		this.ppb = ppb;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getUndcom() {
		return undcom;
	}

	public void setUndcom(String undcom) {
		this.undcom = undcom;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public Double getSalarios() {
		return salarios;
	}

	public void setSalarios(Double salarios) {
		this.salarios = salarios;
	}

	public Double getEncargos() {
		return encargos;
	}

	public void setEncargos(Double encargos) {
		this.encargos = encargos;
	}

	public String getTpdcre() {
		return tpdcre;
	}

	public void setTpdcre(String tpdcre) {
		this.tpdcre = tpdcre;
	}

	public String getDcrant() {
		return dcrant;
	}

	public void setDcrant(String dcrant) {
		this.dcrant = dcrant;
	}

	public String getProcretif() {
		return procretif;
	}

	public void setProcretif(String procretif) {
		this.procretif = procretif;
	}

	public String getVrspgd() {
		return vrspgd;
	}

	public void setVrspgd(String vrspgd) {
		this.vrspgd = vrspgd;
	}

	public String getOrigdcr() {
		return origdcr;
	}

	public void setOrigdcr(String origdcr) {
		this.origdcr = origdcr;
	}

	public String getTpcoef() {
		return tpcoef;
	}

	public void setTpcoef(String tpcoef) {
		this.tpcoef = tpcoef;
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
