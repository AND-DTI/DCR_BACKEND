package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRPROTO", schema = "HD4DCDHH")
@ApiModel
public class Dcrproto {

	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String protdcre;
	
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int(9)")
	private Long idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
	private String tpprd;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String tpenvio;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtenvio;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrenvio;
	
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(14)")
	private String repreenvio;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(2)")
	private String status;
	
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

	public String getProtdcre() {
		return protdcre;
	}

	public void setProtdcre(String protdcre) {
		this.protdcre = protdcre;
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

	public String getTpenvio() {
		return tpenvio;
	}

	public void setTpenvio(String tpenvio) {
		this.tpenvio = tpenvio;
	}

	public String getDtenvio() {
		return dtenvio;
	}

	public void setDtenvio(String dtenvio) {
		this.dtenvio = dtenvio;
	}

	public String getHrenvio() {
		return hrenvio;
	}

	public void setHrenvio(String hrenvio) {
		this.hrenvio = hrenvio;
	}

	public String getRepreenvio() {
		return repreenvio;
	}

	public void setRepreenvio(String repreenvio) {
		this.repreenvio = repreenvio;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
