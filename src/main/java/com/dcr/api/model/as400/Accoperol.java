package com.dcr.api.model.as400;

import com.dcr.api.model.keys.AccoperolKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCOPEROL", schema = "HD4DCDHH")
@ApiModel
public class Accoperol {
	
	@EmbeddedId
	private AccoperolKey key;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	private Integer create;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	private Integer read;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	private Integer update;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	private Integer delete;
	
	@Column(columnDefinition = "int")
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	private Integer action;
	
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

	public AccoperolKey getKey() {
		return key;
	}

	public void setKey(AccoperolKey key) {
		this.key = key;
	}

	public Integer getCreate() {
		return create;
	}

	public void setCreate(Integer create) {
		this.create = create;
	}

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	public Integer getUpdate() {
		return update;
	}

	public void setUpdate(Integer update) {
		this.update = update;
	}

	public Integer getDelete() {
		return delete;
	}

	public void setDelete(Integer delete) {
		this.delete = delete;
	}

	public Integer getAction() {
		return action;
	}

	public void setAction(Integer action) {
		this.action = action;
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
