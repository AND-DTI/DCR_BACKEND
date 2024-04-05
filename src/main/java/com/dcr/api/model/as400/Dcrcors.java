package com.dcr.api.model.as400;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "DCRCORS", schema = "HD4DCDHH")
@ApiModel
public class Dcrcors {

	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Id
	private String cnpjext;
	
	@TamanhoMaximo(200)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(200)")
	private String razsoc;
	
	@TamanhoMaximo(200)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(200)")
	private String accobj;
	
	@TamanhoMaximo(100)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(100)")
	private String accresp;
	
	@TamanhoMaximo(100)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(100)")
	private String accemail;
	
	@TamanhoMaximo(100)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(100)")
	private String accfone;
	
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

	public String getCnpjext() {
		return cnpjext;
	}

	public void setCnpjext(String cnpjext) {
		this.cnpjext = cnpjext;
	}

	public String getRazsoc() {
		return razsoc;
	}

	public void setRazsoc(String razsoc) {
		this.razsoc = razsoc;
	}

	public String getAccobj() {
		return accobj;
	}

	public void setAccobj(String accobj) {
		this.accobj = accobj;
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

	public String getAccresp() {
		return accresp;
	}

	public void setAccresp(String accresp) {
		this.accresp = accresp;
	}

	public String getAccemail() {
		return accemail;
	}

	public void setAccemail(String accemail) {
		this.accemail = accemail;
	}

	public String getAccfone() {
		return accfone;
	}

	public void setAccfone(String accfone) {
		this.accfone = accfone;
	}
}
