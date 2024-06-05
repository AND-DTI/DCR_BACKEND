package com.dcr.api.model.as400;
import com.dcr.api.model.keys.MatriitmKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;



@Entity
@Table(name = "MATRIITM", schema = "HD4DCDHH")
@ApiModel
public class Matriitm {

	@EmbeddedId
	private MatriitmKey key;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
	private String codcor;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
	private String partdesc;
	
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
	private String unmed;

	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(3)")
	private String ncm; //added j4
	
	@TamanhoMaximo(5)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Integer priocor;
	
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

	public String getCodcor() {
		return codcor;
	}

	public void setCodcor(String codcor) {
		this.codcor = codcor;
	}

	public String getPartdesc() {
		return partdesc;
	}

	public void setPartdesc(String partdesc) {
		this.partdesc = partdesc;
	}

	public String getUnmed() {
		return unmed;
	}

	public void setUnmed(String unmed) {
		this.unmed = unmed;
	}

	public String getNcm() {		return ncm;	}
	public void setNcm(String ncm) {
		this.ncm = ncm==null? "" : ncm;
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

	public Integer getPriocor() {		return priocor;	}
	public void setPriocor(Integer priocor) {		this.priocor = priocor;	}

	public MatriitmKey getKey() {		return key;	}
	public void setKey(MatriitmKey key) {		this.key = key;	}
}
