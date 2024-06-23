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
@Table(name = "DCRPROCCH", schema = "HD4DCDHH")
@ApiModel
public class Dcrprocch {

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "int", unique = true)
	@Id
	private Long idhist;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int(10)")
	private Long idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
	private String tpprd;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer stsold;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer stsnew;
	
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

	public Long getIdhist() {
		return idhist;
	}

	public void setIdhist(Long idhist) {
		this.idhist = idhist;
	}

	public Long getIdmatriz() {
		return idmatriz;
	}

	public void setIdmatriz(Long idmatriz) {
		this.idmatriz = idmatriz;
	}

	public String getPartnumpd() {
		return partnumpd;
	}

	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}

	public String getTpprd() {
		return tpprd;
	}

	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}

	public Integer getStsold() {
		return stsold;
	}

	public void setStsold(Integer stsold) {
		this.stsold = stsold;
	}

	public Integer getStsnew() {
		return stsnew;
	}

	public void setStsnew(Integer stsnew) {
		this.stsnew = stsnew;
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

	public String getRespstatus() {
		return respstaus;
	}

	public void setRespstatus(String respstatus) {
		this.respstaus = respstatus;
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
