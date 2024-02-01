package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CADTAXA", schema = "HD4DCDHH")
@ApiModel
public class Cadtaxa {

	@Id
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
    private String cdmoed;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String vigini;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String vigfim;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(8)")
    private Double taxa;
    
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

	public String getCdmoed() {
		return cdmoed;
	}

	public void setCdmoed(String cdmoed) {
		this.cdmoed = cdmoed;
	}

	public String getVigini() {
		return vigini;
	}

	public void setVigini(String vigini) {
		this.vigini = vigini;
	}

	public String getVigfim() {
		return vigfim;
	}

	public void setVigfim(String vigfim) {
		this.vigfim = vigfim;
	}

	public Double getTaxa() {
		return taxa;
	}

	public void setTaxa(Double taxa) {
		this.taxa = taxa;
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
