package com.dcr.api.model.as400;

import com.dcr.api.model.keys.AccoperKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCOPER", schema = "HD4DCDHH")
@ApiModel
public class Accoper {

	@EmbeddedId
	private AccoperKey idoper;
	
	@Column(columnDefinition = "char(150)")
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	private String descoper;

	@Column(columnDefinition = "char(1)")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	private String tpoper;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	private Integer nivel;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Integer idpai;
	
	@Column(columnDefinition = "char(5)")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	private String ativo;
	
	@Column(columnDefinition = "char(450)")
	@TamanhoMaximo(450)
	@TamanhoMinimo(1)
	private String rota;
	
	@Column(columnDefinition = "char(100)")
	@TamanhoMaximo(100)
	@TamanhoMinimo(1)
	private String icon;
	
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

	public AccoperKey getIdoper() {
		return idoper;
	}

	public void setIdoper(AccoperKey idoper) {
		this.idoper = idoper;
	}

	public String getDescoper() {
		return descoper;
	}

	public void setDescoper(String descoper) {
		this.descoper = descoper;
	}

	public String getTpoper() {
		return tpoper;
	}

	public void setTpoper(String tpoper) {
		this.tpoper = tpoper;
	}

	public Integer getNivel() {
		return nivel;
	}

	public void setNivel(Integer nivel) {
		this.nivel = nivel;
	}

	public Integer getIdpai() {
		return idpai;
	}

	public void setIdpai(Integer idpai) {
		this.idpai = idpai;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public String getRota() {
		return rota;
	}

	public void setRota(String rota) {
		this.rota = rota;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
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
