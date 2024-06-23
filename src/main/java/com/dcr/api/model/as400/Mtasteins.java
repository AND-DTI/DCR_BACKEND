package com.dcr.api.model.as400;

import com.dcr.api.model.keys.MtasteinsKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "MTASTEINS", schema = "HD4DCDHH")
@ApiModel
public class Mtasteins {

	@EmbeddedId
	private MtasteinsKey key;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
    private String partdesc;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
    private String itmorg;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
    private String ittyp;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(2)")
    private String unmsr;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(10,3)")
    private Double necfil;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
    private String cdspn;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(7,3)")
    private Double weght;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
    private String emcomp;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
    private String partsugest;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
    private String partsugdsc;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
    private String partnew;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
    private String espec;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
    private String undcom;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
    private String ncm;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
    private Double vlrunit;
	
	@TamanhoMaximo(30)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(30)")
    private String partnewdsc;
	
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

	public MtasteinsKey getKey() {
		return key;
	}

	public void setKey(MtasteinsKey key) {
		this.key = key;
	}

	public String getPartdesc() {
		return partdesc;
	}

	public void setPartdesc(String partdesc) {
		this.partdesc = partdesc;
	}

	public String getItmorg() {
		return itmorg;
	}

	public void setItmorg(String itmorg) {
		this.itmorg = itmorg;
	}

	public String getIttyp() {
		return ittyp;
	}

	public void setIttyp(String ittyp) {
		this.ittyp = ittyp;
	}

	public String getUnmsr() {
		return unmsr;
	}

	public void setUnmsr(String unmsr) {
		this.unmsr = unmsr;
	}

	public Double getNecfil() {
		return necfil;
	}

	public void setNecfil(Double necfil) {
		this.necfil = necfil;
	}

	public String getCdspn() {
		return cdspn;
	}

	public void setCdspn(String cdspn) {
		this.cdspn = cdspn;
	}

	public Double getWeght() {
		return weght;
	}

	public void setWeght(Double weght) {
		this.weght = weght;
	}

	public String getEmcomp() {
		return emcomp;
	}

	public void setEmcomp(String emcomp) {
		this.emcomp = emcomp;
	}

	public String getPartsugest() {
		return partsugest;
	}

	public void setPartsugest(String partsugest) {
		this.partsugest = partsugest;
	}

	public String getPartsugdsc() {
		return partsugdsc;
	}

	public void setPartsugdsc(String partsugdsc) {
		this.partsugdsc = partsugdsc;
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

	public String getPartnew() {
		return partnew;
	}

	public void setPartnew(String partnew) {
		this.partnew = partnew;
	}

	public String getPartnewdsc() {
		return partnewdsc;
	}

	public void setPartnewdsc(String partnewdsc) {
		this.partnewdsc = partnewdsc;
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

	public Double getVlrunit() {
		return vlrunit;
	}

	public void setVlrunit(Double vlrunit) {
		this.vlrunit = vlrunit;
	}
}
