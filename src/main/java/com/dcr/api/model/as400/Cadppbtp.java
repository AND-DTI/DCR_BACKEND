package com.dcr.api.model.as400;
import com.dcr.api.model.keys.CadppbtpKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity
@Table(name = "CADPPBTP", schema = "HD4DCDHH")
@ApiModel
public class Cadppbtp {
	
	
	@EmbeddedId
	private CadppbtpKey key;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
    private String descppb;
	
	@TamanhoMaximo(80)	
	@Column(columnDefinition = "char(80)")
    private String ppbprd;

	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
    private String prddest;

	@Column(columnDefinition = "bigint")
    private Long flex1flw;

	@Column(columnDefinition = "decimal(15,5)")
    private Double flex2flw;

	@Column(columnDefinition = "char(8)")
    private String flex3flw;

	@Column(columnDefinition = "char(40)")
    private String flex4flw;

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

	
	public Cadppbtp(CadppbtpKey key, String descppb, String ppbprd, String prddest, Long flex1flw, Double flex2flw,
			String flex3flw, String flex4flw, String flex5flw, String itaudsys, String itaudusr, String itaudhst,
			String itauddt, String itaudhr) {
		this.key = key;
		this.descppb = descppb;
		this.ppbprd = ppbprd;
		this.prddest = prddest;
		this.flex1flw = flex1flw;
		this.flex2flw = flex2flw;
		this.flex3flw = flex3flw;
		this.flex4flw = flex4flw;
		this.flex5flw = flex5flw;
		this.itaudsys = itaudsys;
		this.itaudusr = itaudusr;
		this.itaudhst = itaudhst;
		this.itauddt = itauddt;
		this.itaudhr = itaudhr;
	}

	public Cadppbtp() {
	}

	public String getDescppb() {
		return descppb;
	}

	public void setDescppb(String descppb) {
		this.descppb = descppb;
	}

	public String getPpbprd() {
		return ppbprd;
	}

	public void setPpbprd(String ppbprd) {
		this.ppbprd = ppbprd;
	}

	public String getPrddest() {
		return prddest;
	}

	public void setPrddest(String prddest) {
		this.prddest = prddest;
	}

	public Long getFlex1flw() {
		return flex1flw;
	}

	public void setFlex1flw(Long flex1flw) {
		this.flex1flw = flex1flw;
	}

	public Double getFlex2flw() {
		return flex2flw;
	}

	public void setFlex2flw(Double flex2flw) {
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

	public CadppbtpKey getKey() {
		return key;
	}

	public void setKey(CadppbtpKey key) {
		this.key = key;
	}

	
}
