package com.dcr.api.model.as400;
import com.dcr.api.model.keys.PendastecKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;



@Entity
@Table(name = "PENDASTEC"/*, schema = "HD4DCDHH"*/)
@ApiModel
public class Pendastec {

	@EmbeddedId
	private PendastecKey key;
	
	@Column(columnDefinition = "char(10)")
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	private String cdpend;

	@TamanhoMaximo(250)
	@Column(columnDefinition = "char(250)")
    private String obsresol;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer status; 


	@TamanhoMaximo(10)
	@Column(columnDefinition = "bigint")
	private Long flex1flw;	

	@TamanhoMaximo(15)
	@Column(columnDefinition = "decimal(15,5)")
	private Long flex2flw;	

	@TamanhoMaximo(40)
	@Column(columnDefinition = "char(8)")
	private String flex3flw;	

	@TamanhoMaximo(40)
	@Column(columnDefinition = "char(40)")
	private String flex4flw;

	@TamanhoMaximo(1000)
	@Column(columnDefinition = "char(1000)")
	private String flex5flw;

	
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
	

	public PendastecKey getKey() {		return key;	}
	public void setKey(PendastecKey key) {
		this.key = key;
	}

	public String getCdpend() {		return cdpend;	}
	public void setCdpend(String cdpend) {
		this.cdpend = cdpend;
	}

	public String getObsresol() {		return obsresol;	}
	public void setObsresol(String obsresol) {
		this.obsresol = obsresol == null? "" : obsresol;
	}

	public Integer getStatus() {		return status;	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getFlex1flw() {		return flex1flw;	}
	public void setFlex1flw(Long flex1flw) {
		this.flex1flw = flex1flw  == null? 0 : flex1flw;
	}

	public Long getFlex2flw() {		return flex2flw;	}
	public void setFlex2flw(Long flex2flw) {
		this.flex2flw = flex2flw == null? 0 : flex2flw;
	}

	public String getFlex3flw() {		return flex3flw;	}
	public void setFlex3flw(String flex3flw) {
		this.flex3flw = flex3flw == null? "" : flex3flw;
	}

	public String getFlex4flw() {		return flex4flw;	}
	public void setFlex4flw(String flex4flw) {
		this.flex4flw = flex4flw == null? "" : flex4flw;
	}

	public String getFlex5flw() {		return flex5flw;	}
	public void setFlex5flw(String flex5flw) {
		this.flex5flw = flex5flw == null? "" : flex5flw;
	}	

	public String getItaudsys() {		return itaudsys;	}
	public void setItaudsys(String itaudsys) {
		this.itaudsys = itaudsys;
	}

	public String getItaudusr() {		return itaudusr;	}
	public void setItaudusr(String itaudusr) {
		this.itaudusr = itaudusr;
	}

	public String getItaudhst() {		return itaudhst;	}
	public void setItaudhst(String itaudhst) {
		this.itaudhst = itaudhst;
	}

	public String getItauddt() {		return itauddt;	}
	public void setItauddt(String itauddt) {
		this.itauddt = itauddt;
	}

	public String getItaudhr() {		return itaudhr;	}
	public void setItaudhr(String itaudhr) {
		this.itaudhr = itaudhr;
	}

}
