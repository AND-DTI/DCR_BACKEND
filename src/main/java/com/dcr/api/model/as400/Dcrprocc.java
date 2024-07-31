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
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String tpdcre;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int(5)")
	private Integer status;
	
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
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtregistro;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrregistro;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(8,6)")
	private Double taxausd;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double totalnac;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double totalimp;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double custotal;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(4,2)")
	private Double coefred;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double iitotal;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double iireduzido;
	
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDtregistro() {
		return dtregistro;
	}

	public void setDtregistro(String dtregistro) {
		this.dtregistro = dtregistro;
	}

	public String getHrregistro() {
		return hrregistro;
	}

	public void setHrregistro(String hrregistro) {
		this.hrregistro = hrregistro;
	}

	public Double getTaxausd() {
		return taxausd;
	}

	public void setTaxausd(Double taxausd) {
		this.taxausd = taxausd;
	}

	public Double getTotalnac() {
		return totalnac;
	}

	public void setTotalnac(Double totalnac) {
		this.totalnac = totalnac;
	}

	public Double getTotalimp() {
		return totalimp;
	}

	public void setTotalimp(Double totalimp) {
		this.totalimp = totalimp;
	}

	public Double getCustotal() {
		return custotal;
	}

	public void setCustotal(Double custotal) {
		this.custotal = custotal;
	}

	public Double getCoefred() {
		return coefred;
	}

	public void setCoefred(Double coefred) {
		this.coefred = coefred;
	}

	public Double getIitotal() {
		return iitotal;
	}

	public void setIitotal(Double iitotal) {
		this.iitotal = iitotal;
	}

	public Double getIireduzido() {
		return iireduzido;
	}

	public void setIireduzido(Double iireduzido) {
		this.iireduzido = iireduzido;
	}

	public String getTpdcre() {
		return tpdcre;
	}

	public void setTpdcre(String tpdcre) {
		this.tpdcre = tpdcre;
	}
}
