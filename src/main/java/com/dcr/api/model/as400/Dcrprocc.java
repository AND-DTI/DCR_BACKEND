package com.dcr.api.model.as400;

import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRPROCC", schema = "HD4DCDHH")
@ApiModel
public class Dcrprocc {

	@EmbeddedId
	private DcrproccKey key;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtstatus;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrstatus;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String respstaus;
	
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

	public DcrproccKey getKey() {
		return key;
	}

	public void setKey(DcrproccKey key) {
		this.key = key;
	}

	public String getDtstatus() {
		return dtstatus;
	}

	public void setDtstatus(String dtstatus) {
		this.dtstatus = dtstatus;
	}

	public String getHrstatus() {
		return hrstatus;
	}

	public void setHrstatus(String hrstatus) {
		this.hrstatus = hrstatus;
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

	public String getRespstaus() {
		return respstaus;
	}

	public void setRespstaus(String respstaus) {
		this.respstaus = respstaus;
	}
}
