package com.dcr.api.model.as400;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "DCRRPA", schema = "HD4DCDHH")
@ApiModel
public class Dcrrpa {
 
	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String username;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
    private String maquina;
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Integer porta;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(40)")
    private String baseurl;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(250)")
    private String rpauri;

	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(250)")
    private String playpath;

	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String playbrowse;

	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
    private String playchanel;


	@TamanhoMaximo(10)
	@Column(columnDefinition = "bigint")
	private Long flex1flw;	

	@TamanhoMaximo(15)
	@Column(columnDefinition = "decimal(15,5)")
	private Long flex2flw;	

	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
	private String flex3flw;

	@TamanhoMaximo(40)
	@Column(columnDefinition = "char(40)")
	private String flex4flw;

	@TamanhoMaximo(40)
	@Column(columnDefinition = "char(1000)")
	private String flex5flw;

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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMaquina() {
		return maquina;
	}
	public void setMaquina(String maquina) {
		this.maquina = maquina;
	}
	public Integer getPorta() {
		return porta;
	}
	public void setPorta(Integer porta) {
		this.porta = porta;
	}
	public String getBaseurl() {
		return baseurl;
	}
	public void setBaseurl(String baseurl) {
		this.baseurl = baseurl;
	}
	public String getRpauri() {
		return rpauri;
	}
	public void setRpauri(String rpauri) {
		this.rpauri = rpauri;
	}
	public String getPlaypath() {
		return playpath;
	}
	public void setPlaypath(String playpath) {
		this.playpath = playpath;
	}
	public String getPlaybrowse() {
		return playbrowse;
	}
	public void setPlaybrowse(String playbrowse) {
		this.playbrowse = playbrowse;
	}
	public String getPlaychanel() {
		return playchanel;
	}
	public void setPlaychanel(String playchanel) {
		this.playchanel = playchanel;
	}
	public Long getFlex1flw() {
		return flex1flw;
	}
	public void setFlex1flw(Long flex1flw) {
		this.flex1flw = flex1flw;
	}
	public Long getFlex2flw() {
		return flex2flw;
	}
	public void setFlex2flw(Long flex2flw) {
		this.flex2flw = flex2flw;
	}
	public String getFlex3flw() {
		return flex3flw;
	}
	public void setFlex3flw(String flex3flw) {
		this.flex3flw = flex3flw;
	}
	public String getFlex4flw() {
		return flex4flw;
	}
	public void setFlex4flw(String flex4flw) {
		this.flex4flw = flex4flw;
	}
	public String getFlex5flw() {
		return flex5flw;
	}
	public void setFlex5flw(String flex5flw) {
		this.flex5flw = flex5flw;
	}
		

}
