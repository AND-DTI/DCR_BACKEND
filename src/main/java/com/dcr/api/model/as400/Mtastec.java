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
@Table(name = "MTASTEC", schema = "HD4DCDHH")
@ApiModel
public class Mtastec { 

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idmatriz;

	private String partnumpd;
	
	@TamanhoMaximo(150)
	//@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
    private String desccom;
	
	@TamanhoMaximo(80)
	//@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
    private String descrfb;
	
	@TamanhoMaximo(3)
	//@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
    private String unmed;

	@TamanhoMaximo(15)
	@Column(columnDefinition = "decimal(15,2)")
	private Double preco; //added j5

	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(3)")
	private String ncm; //added j5

	@TamanhoMaximo(80)
	@Column(columnDefinition = "char(80)")
	private String undcom; //added j5

	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
    private String tpdcre;
	
	@TamanhoMaximo(7)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(7)")
    private String origprd;

	@TamanhoMaximo(1)	
	@Column(columnDefinition = "char(1)")
    private String itgarantia;

	@TamanhoMaximo(250)	
	@Column(columnDefinition = "char(250)")
    private String obsprio;
	
	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
    private String dtneci;
	
	@TamanhoMaximo(1)	
	@Column(columnDefinition = "int")
    private Integer priourgen;
	
	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
    private String prevfat;
	
	@TamanhoMaximo(10)	
	@Column(columnDefinition = "char(10)")
    private String prioresp;
	
	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
    private String priodtmnt;
	
	@TamanhoMaximo(5)
	@Column(columnDefinition = "char(5)")
    private String priohrmnt;
	
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

	@TamanhoMaximo(10)
	@Column(columnDefinition = "bigint")
	private Long flex1flw;	

	@TamanhoMaximo(40)
	@Column(columnDefinition = "char(40)")
	private String flex4flw;

	
	public Integer getIdmatriz() {
		return idmatriz;
	}

	public void setIdmatriz(Integer idmatriz) {
		this.idmatriz = idmatriz;
	}

	public String getPartnumpd() {
		return partnumpd;
	}

	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}

	public String getDesccom() {
		return desccom;
	}

	public void setDesccom(String desccom) {
		this.desccom = desccom;
	}

	public String getDescrfb() {
		return descrfb;
	}

	public void setDescrfb(String descrfb) {
		this.descrfb = descrfb;
	}

	public String getUnmed() {
		return unmed;
	}

	public void setUnmed(String unmed) {
		this.unmed = unmed;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getUndcom() {
		return undcom;
	}

	public void setUndcom(String undcom) {
		this.undcom = undcom;
	}

	public String getTpdcre() {
		return tpdcre;
	}

	public void setTpdcre(String tpdcre) {
		this.tpdcre = tpdcre;
	}

	public String getOrigprd() {
		return origprd;
	}

	public void setOrigprd(String origprd) {
		this.origprd = origprd;
	}

	public String getItgarantia() {
		return itgarantia;
	}

	public void setItgarantia(String itgarantia) {
		this.itgarantia = itgarantia;
	}

	public String getObsprio() {
		return obsprio;
	}

	public void setObsprio(String obsprio) {
		this.obsprio = obsprio;
	}

	public String getDtneci() {
		return dtneci;
	}

	public void setDtneci(String dtneci) {
		this.dtneci = dtneci;
	}

	public Integer getPriourgen() {
		return priourgen;
	}

	public void setPriourgen(Integer priourgen) {
		this.priourgen = priourgen;
	}

	public String getPrevfat() {
		return prevfat;
	}

	public void setPrevfat(String prevfat) {
		this.prevfat = prevfat;
	}

	public String getPrioresp() {
		return prioresp;
	}

	public void setPrioresp(String prioresp) {
		this.prioresp = prioresp;
	}

	public String getPriodtmnt() {
		return priodtmnt;
	}

	public void setPriodtmnt(String priodtmnt) {
		this.priodtmnt = priodtmnt;
	}

	public String getPriohrmnt() {
		return priohrmnt;
	}

	public void setPriohrmnt(String priohrmnt) {
		this.priohrmnt = priohrmnt;
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

	public Long getFlex1flw() {
		return flex1flw;
	}

	public void setFlex1flw(Long flex1flw) {
		this.flex1flw = flex1flw;
	}

	public String getFlex4flw() {
		return flex4flw;
	}

	public void setFlex4flw(String flex4flw) {
		this.flex4flw = flex4flw;
	}

}
