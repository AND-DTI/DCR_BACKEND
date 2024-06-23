package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRCORSRQ", schema = "HD4DCDHH")
@ApiModel
public class Dcrcorsrq {

	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idreq;
	
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(14)")
	private String cnpjext;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String tpreq;
	
	@TamanhoMaximo(60)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(60)")
	private String objreq;
	
	@TamanhoMaximo(100)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(100)")
	private String rotareq;
	
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

	public Long getIdreq() {
		return idreq;
	}

	public void setIdreq(Long idreq) {
		this.idreq = idreq;
	}

	public String getCnpjext() {
		return cnpjext;
	}

	public void setCnpjext(String cnpjext) {
		this.cnpjext = cnpjext;
	}

	public String getTpreq() {
		return tpreq;
	}

	public void setTpreq(String tpreq) {
		this.tpreq = tpreq;
	}

	public String getObjreq() {
		return objreq;
	}

	public void setObjreq(String objreq) {
		this.objreq = objreq;
	}

	public void setItaudsys(String itaudsys) {
		this.itaudsys = itaudsys;
	}

	public void setItaudusr(String itaudusr) {
		this.itaudusr = itaudusr;
	}

	public void setItaudhst(String itaudhst) {
		this.itaudhst = itaudhst;
	}

	public void setItauddt(String itauddt) {
		this.itauddt = itauddt;
	}

	public void setItaudhr(String itaudhr) {
		this.itaudhr = itaudhr;
	}

	public String getRotareq() {
		return rotareq;
	}

	public void setRotareq(String rotareq) {
		this.rotareq = rotareq;
	}
}
