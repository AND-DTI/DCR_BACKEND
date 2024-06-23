package com.dcr.api.model.as400;

import com.dcr.api.model.keys.PendprodKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "PENDPROD", schema = "HD4DCDHH")
@ApiModel
public class Pendprod {

	@EmbeddedId
	private PendprodKey key;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String cdpend;	
	
	@TamanhoMaximo(250)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(250)")
    private String obsresol;	
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Integer status;	
	
	@Column(columnDefinition = "char(40)")
    private String itaudsys;	
	
	@Column(columnDefinition = "char(10)")
    private String itaudusr;
	
	@Column(columnDefinition = "char(20)")
    private String itaudhst;
	
	@Column(columnDefinition = "char(8)")
    private String itauddt;
	
	@Column(columnDefinition = "char(8)")
    private String itaudhr;

	public PendprodKey getKey() {
		return key;
	}

	public void setKey(PendprodKey key) {
		this.key = key;
	}

	public String getCdpend() {
		return cdpend;
	}

	public void setCdpend(String cdpend) {
		this.cdpend = cdpend;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getObsresol() {
		return obsresol;
	}

	public void setObsresol(String obsresol) {
		this.obsresol = obsresol;
	}
}
