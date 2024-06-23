package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCIDIOMA", schema = "HD4DCDHH")
@ApiModel
public class Accidioma {

	@Id
	@Column(columnDefinition = "char(2)")
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
    private String codidioma;
	
	@Column(columnDefinition = "char(60)")
	@TamanhoMaximo(60)
	@TamanhoMinimo(1)
    private String descidioma;
	
	@Column(columnDefinition = "char(1)")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
    private String local;
	
	@Column(columnDefinition = "char(20)")
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
    private String frmtdtresu;
	
	@Column(columnDefinition = "char(20)")
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
    private String frmtdtext;
	
	@Column(columnDefinition = "char(1)")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
    private String frmtdec;
	
	@Column(columnDefinition = "char(1)")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
    private String frmtmil;
	
	@Column(columnDefinition = "char(2)")
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
    private String country;
	
	@Column(columnDefinition = "char(2)")
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
    private String language;
	
	@Column(columnDefinition = "char(20)")
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
    private String nlslang;
	
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

	public String getCodidioma() {
		return codidioma;
	}

	public void setCodidioma(String codidioma) {
		this.codidioma = codidioma;
	}

	public String getDescidioma() {
		return descidioma;
	}

	public void setDescidioma(String descidioma) {
		this.descidioma = descidioma;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getFrmtdtresu() {
		return frmtdtresu;
	}

	public void setFrmtdtresu(String frmtdtresu) {
		this.frmtdtresu = frmtdtresu;
	}

	public String getFrmtdtext() {
		return frmtdtext;
	}

	public void setFrmtdtext(String frmtdtext) {
		this.frmtdtext = frmtdtext;
	}

	public String getFrmtdec() {
		return frmtdec;
	}

	public void setFrmtdec(String frmtdec) {
		this.frmtdec = frmtdec;
	}

	public String getFrmtmil() {
		return frmtmil;
	}

	public void setFrmtmil(String frmtmil) {
		this.frmtmil = frmtmil;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getNlslang() {
		return nlslang;
	}

	public void setNlslang(String nlslang) {
		this.nlslang = nlslang;
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
