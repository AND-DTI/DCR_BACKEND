package com.dcr.api.model.dto;

import java.util.List;

public class ColigadoLoteDTO {
	private String dcre;
	private String denom;
	private String cdclient;
	private String dtdcre;
	private String idreg;
	private String cnpj;
	private String cpfrl;
	private String ppb;
	private String ncm;
	private String undcom;
	private Double peso;
	private Double salarios;
	private Double encargos;
	private String tpdcre;
	private String dcrant;
	private String procretif;
	private String vrspgd;
	private String origdcr;
	private String tpcoef;
	
	private List<Dcrcoli1LoteDTO> coliUm;
	private List<Dcrcoli2LoteDTO> coliDois;
	private List<Dcrcoli3LoteDTO> coliTres;
	private List<Dcrcoli4LoteDTO> coliQuatro;
	
	public String getDcre() {
		return dcre;
	}
	public void setDcre(String dcre) {
		this.dcre = dcre;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
	public String getCdclient() {
		return cdclient;
	}
	public void setCdclient(String cdclient) {
		this.cdclient = cdclient;
	}
	public String getDtdcre() {
		return dtdcre;
	}
	public void setDtdcre(String dtdcre) {
		this.dtdcre = dtdcre;
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
	public List<Dcrcoli1LoteDTO> getColiUm() {
		return coliUm;
	}
	public void setColiUm(List<Dcrcoli1LoteDTO> coliUm) {
		this.coliUm = coliUm;
	}
	public List<Dcrcoli2LoteDTO> getColiDois() {
		return coliDois;
	}
	public void setColiDois(List<Dcrcoli2LoteDTO> coliDois) {
		this.coliDois = coliDois;
	}
	public List<Dcrcoli3LoteDTO> getColiTres() {
		return coliTres;
	}
	public void setColiTres(List<Dcrcoli3LoteDTO> coliTres) {
		this.coliTres = coliTres;
	}
	public List<Dcrcoli4LoteDTO> getColiQuatro() {
		return coliQuatro;
	}
	public void setColiQuatro(List<Dcrcoli4LoteDTO> coliQuatro) {
		this.coliQuatro = coliQuatro;
	}

}
