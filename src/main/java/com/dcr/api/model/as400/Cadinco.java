package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "CADINCO", schema = "HD4DCDHH")
@ApiModel
public class Cadinco {
	
	@Id
	@Column(columnDefinition = "char(3)")
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
    private String codinco;
	
	@Column(columnDefinition = "char(100)")
	@TamanhoMaximo(100)
	@TamanhoMinimo(1)
	private String dscinco;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
    private Integer frtembut;
	
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

	public String getCodinco() {
		return codinco;
	}

	public void setCodinco(String codinco) {
		this.codinco = codinco;
	}

	public String getDscinco() {
		return dscinco;
	}

	public void setDscinco(String dscinco) {
		this.dscinco = dscinco;
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

	public Integer getFrtembut() {
		return frtembut;
	}

	public void setFrtembut(Integer frtembut) {
		this.frtembut = frtembut;
	}
}
