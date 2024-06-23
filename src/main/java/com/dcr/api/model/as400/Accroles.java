package com.dcr.api.model.as400;

import com.dcr.api.model.keys.AccrolesKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ACCROLES", schema = "HD4DCDHH")
@ApiModel
public class Accroles {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleid;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
    private String rolename;
	
	@TamanhoMaximo(200)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(200)")
    private String roledesc;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String cdsys;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String rolecad;
	
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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getRoledesc() {
		return roledesc;
	}

	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}

	public String getRolecad() {
		return rolecad;
	}

	public void setRolecad(String rolecad) {
		this.rolecad = rolecad;
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

	

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

}
