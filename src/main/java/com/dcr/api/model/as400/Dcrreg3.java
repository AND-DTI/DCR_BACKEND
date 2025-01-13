package com.dcr.api.model.as400;
import com.dcr.api.model.keys.Dcrreg3Key;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;



@Entity
@Table(name = "DCRREG3"/* schema = "HD4DCDHH"*/)
@ApiModel
public class Dcrreg3 {

	@EmbeddedId
	private Dcrreg3Key key;

	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	private String partnum;	
	
	@Column(columnDefinition = "char(1)")
	private String idreg;
	
	@Column(columnDefinition = "char(1)")
	private String iibasecalc;
	
	@Column(columnDefinition = "char(1)")
	private String impdireta;
	
	@Column(columnDefinition = "char(1)")
	private String suspens;
	
	@Column(columnDefinition = "int")
	private Long di;
	
	@Column(columnDefinition = "char(3)")
	private String adicao;
	
	@Column(columnDefinition = "int")
	private Integer itemadicao;
	
	@Column(columnDefinition = "int")
	private Long numnf;
	
	@Column(columnDefinition = "char(5)")
	private String sernf;
	
	@Column(columnDefinition = "int")
	private Long cnpjfor;
	
	@Column(columnDefinition = "char(15)")
	private String ie;
	
	@Column(columnDefinition = "char(8)")
	private String eminf;
	
	@Column(columnDefinition = "char(80)")
	private String espec;
	
	@Column(columnDefinition = "char(80)")
	private String undcom;
	
	@Column(columnDefinition = "char(8)")
	private String ncm;
	
	@Column(columnDefinition = "decimal(15,7)")
	private Double qtde;
	
	@Column(columnDefinition = "char(1)")
	private String indreducii;
	
	@Column(columnDefinition = "decimal(15,6)")
	private Double vlrunit;
	
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

	public Dcrreg3Key getKey() {
		return key;
	}

	public void setKey(Dcrreg3Key key) {
		this.key = key;
	}

	public String getPartnum() {		return partnum;	}
	public void setPartnum(String partnum) {		this.partnum = partnum;	}

	public String getIdreg() {
		return idreg;
	}

	public void setIdreg(String idreg) {
		this.idreg = idreg;
	}

	public String getIibasecalc() {
		return iibasecalc;
	}

	public void setIibasecalc(String iibasecalc) {
		this.iibasecalc = iibasecalc;
	}

	public String getImpdireta() {
		return impdireta;
	}

	public void setImpdireta(String impdireta) {
		this.impdireta = impdireta;
	}

	public String getSuspens() {
		return suspens;
	}

	public void setSuspens(String suspens) {
		this.suspens = suspens;
	}

	public Long getDi() {
		return di;
	}

	public void setDi(Long di) {
		this.di = di;
	}

	public String getAdicao() {
		return adicao;
	}

	public void setAdicao(String adicao) {
		this.adicao = adicao;
	}

	public Integer getItemadicao() {
		return itemadicao;
	}

	public void setItemadicao(Integer itemadicao) {
		this.itemadicao = itemadicao;
	}

	public String getSernf() {
		return sernf;
	}

	public void setSernf(String sernf) {
		this.sernf = sernf;
	}

	public Long getCnpjfor() {
		return cnpjfor;
	}

	public void setCnpjfor(Long cnpjfor) {
		this.cnpjfor = cnpjfor;
	}

	public String getIe() {
		return ie;
	}

	public void setIe(String ie) {
		this.ie = ie;
	}

	public String getEminf() {
		return eminf;
	}

	public void setEminf(String eminf) {
		this.eminf = eminf;
	}

	public String getEspec() {
		return espec;
	}

	public void setEspec(String espec) {
		this.espec = espec;
	}

	public String getUndcom() {
		return undcom;
	}

	public void setUndcom(String undcom) {
		this.undcom = undcom;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public Double getQtde() {
		return qtde;
	}

	public void setQtde(Double qtde) {
		this.qtde = qtde;
	}

	public String getIndreducii() {
		return indreducii;
	}

	public void setIndreducii(String indreducii) {
		this.indreducii = indreducii;
	}

	public Double getVlrunit() {
		return vlrunit;
	}

	public void setVlrunit(Double vlrunit) {
		this.vlrunit = vlrunit;
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

	public Long getNumnf() {
		return numnf;
	}

	public void setNumnf(Long numnf) {
		this.numnf = numnf;
	}
}
