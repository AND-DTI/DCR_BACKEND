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
@Table(name = "MATRIPRD", schema = "HD4DCDHH")
@ApiModel
public class Matriprd {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "int", unique = true)
	@Id
	private Integer idmatriz;
	
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
	private String produto;
	

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String modelo;
	
	@TamanhoMaximo(7)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String anomdl;
	
	@TamanhoMaximo(150)
	@Column(columnDefinition = "char(150)")
	private String desccom;
	
	@TamanhoMaximo(80)
	@Column(columnDefinition = "char(80)")
	private String descrfb;
	
	@TamanhoMaximo(4) //j4 - old 2
	@Column(columnDefinition = "char(4)") //j4 - old 2
	private String tpprd;
	
	@TamanhoMaximo(1)
	@Column(columnDefinition = "int")
	private Integer protot;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer special;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String tpdcre;

	@TamanhoMaximo(7)
	@Column(columnDefinition = "char(7)")
	private String origprd;
	
	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
	private String dtneci;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer priourgen;
	
	@TamanhoMaximo(8)
	//@TamanhoMinimo(1) j4
	@Column(columnDefinition = "char(8)")
	private String prevfat;
	
	@TamanhoMaximo(10)
	//@TamanhoMinimo(1) j4
	@Column(columnDefinition = "char(10)")
	private String prioresp;
	
	@TamanhoMaximo(8)
	//@TamanhoMinimo(1) j4
	@Column(columnDefinition = "char(8)")
	private String priodtmnt;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	//@Column(columnDefinition = "char(8)") j4
	private String priohrmnt;
	
	@TamanhoMaximo(1)
	//@TamanhoMinimo(1) J4
	@Column(columnDefinition = "char(1)")
    private String itgarantia;
	
	@TamanhoMaximo(250)
	//@TamanhoMinimo(1) J4
	@Column(columnDefinition = "char(250)")
    private String obsprio;

	@TamanhoMaximo(10)
	@Column(columnDefinition = "bigint")
	private Long flex1flw;	

	@TamanhoMaximo(40)
	@Column(columnDefinition = "char(40)")
	private String flex4flw;	

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

	public Integer getIdmatriz() {		return idmatriz;	}
	public void setIdmatriz(Integer idmatriz) {		this.idmatriz = idmatriz;	}

	public String getProduto() {		return produto;	}
	public void setProduto(String produto) {		this.produto = produto;	}

	public String getModelo() {		return modelo;	}
	public void setModelo(String modelo) {		this.modelo = modelo;	}

	public String getAnomdl() {		return anomdl;	}
	public void setAnomdl(String anomdl) {		this.anomdl = anomdl;	}

	public String getDesccom() {		return desccom;	}
	public void setDesccom(String desccom) {		this.desccom = desccom;	}

	public String getDescrfb() {		return descrfb;	}
	public void setDescrfb(String descrfb) {		this.descrfb = descrfb;	}

	public String getTpprd() {		return tpprd;	}
	public void setTpprd(String tpprd) {		this.tpprd = tpprd;	}

	public Integer getProtot() {		return protot;	}
	public void setProtot(Integer protot) {		this.protot = protot;	}

	public Integer getSpecial() {		return special;	}
	public void setSpecial(Integer special) {		this.special = special;	}

	public String getTpdcre() {		return tpdcre;	}
	public void setTpdcre(String tpdcre) {		this.tpdcre = tpdcre;	}

	public String getOrigprd() {		return origprd;	}
	public void setOrigprd(String origprd) {		this.origprd = origprd;	}

	public String getDtneci() {		return dtneci;	}
	public void setDtneci(String dtneci) {		this.dtneci = dtneci;	}

	public Integer getPriourgen() {		return priourgen;	}
	public void setPriourgen(Integer priourgen) {		this.priourgen = priourgen;	}

	public String getPrevfat() {		return prevfat;	}
	public void setPrevfat(String prevfat) {		this.prevfat = prevfat;	}

	public String getPrioresp() {		return prioresp;	}
	public void setPrioresp(String prioresp) {		this.prioresp = prioresp;	}

	public String getPriodtmnt() {		return priodtmnt;	}
	public void setPriodtmnt(String priodtmnt) {		this.priodtmnt = priodtmnt;	}

	public String getPriohrmnt() {		return priohrmnt;	}
	public void setPriohrmnt(String priohrmnt) {		this.priohrmnt = priohrmnt;	}

	public Long getFlex1flw() {		return flex1flw;	}
	public void setFlex1flw(Long flex1flw) {		
		this.flex1flw = flex1flw==null? 0 : flex1flw;
	}

	public String getFlex4flw() {		return flex4flw;	}
	public void setFlex4flw(String flex4flw) {		
		this.flex4flw = flex4flw==null? "" : flex4flw;
	}

	public String getItaudsys() {		return itaudsys;	}
	public void setItaudsys(String itaudsys) {		this.itaudsys = itaudsys;	}

	public String getItaudusr() {		return itaudusr;	}
	public void setItaudusr(String itaudusr) {		this.itaudusr = itaudusr;	}

	public String getItaudhst() {		return itaudhst;	}
	public void setItaudhst(String itaudhst) {		this.itaudhst = itaudhst;	}

	public String getItauddt() {		return itauddt;	}
	public void setItauddt(String itauddt) {		this.itauddt = itauddt;	}

	public String getItaudhr() {		return itaudhr;	}
	public void setItaudhr(String itaudhr) {		this.itaudhr = itaudhr;	}

	public String getItgarantia() {		return itgarantia;	}
	public void setItgarantia(String itgarantia) {		this.itgarantia = itgarantia;	}

	public String getObsprio() {		return obsprio;	}
	public void setObsprio(String obsprio) {		this.obsprio = obsprio;	}

	
}
