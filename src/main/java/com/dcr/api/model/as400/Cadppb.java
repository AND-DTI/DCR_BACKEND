package com.dcr.api.model.as400;

//import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
//import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;

@Entity
@Table(name = "CADPPB"/*, schema = "HD4DCDHH"*/)
@ApiModel
@NamedStoredProcedureQuery(
  name = "LPGPGICE.HDCR004C",
  procedureName = "HDCR004C",
  parameters = {
	@StoredProcedureParameter(name = "TPPRD", mode = ParameterMode.IN, type = String.class),
	@StoredProcedureParameter(name = "USERSYS", mode = ParameterMode.IN, type = String.class),
	@StoredProcedureParameter(name = "IDMATRIZ", mode = ParameterMode.IN, type = String.class)	
  }
)
public class Cadppb {

	//@EmbeddedId
	//private ProdutoKey key;

	@Id
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	private String partnumpd;

	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	private String tpprd;
	
	@TamanhoMaximo(150)	
	@Column(columnDefinition = "char(150)")
	private String desccom;
	
	@TamanhoMaximo(80)
	@Column(columnDefinition = "char(80)")
	private String descrfb;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
	private String prddest;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
	private String ppbprd;

	@TamanhoMaximo(40)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(40)")
	private String flex4flw;	
	     
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

	//public ProdutoKey getKey() {		return key;	}
	//public void setKey(ProdutoKey key) {		this.key = key;	}

	public String getPartnumpd() {		return partnumpd;	}
	public void setPartnumpd(String partnumpd) {		this.partnumpd = partnumpd;	}

	public String getTpprd() {		return tpprd;	}
	public void setTpprd(String tpprd) {		this.tpprd = tpprd;	}

	public String getDesccom() {		return desccom;	}
	public void setDesccom(String desccom) {		this.desccom = desccom==null? "" : desccom;	}

	public String getDescrfb() {		return descrfb;	}
	public void setDescrfb(String descrfb) {		this.descrfb = descrfb==null? "" : descrfb;	}

	public String getPrddest() {		return prddest;	}
	public void setPrddest(String prddest) {		this.prddest = prddest;	}

	public String getPpbprd() {		return ppbprd;	}
	public void setPpbprd(String ppbprd) {		this.ppbprd = ppbprd;	}

	public String getFlex4flw() {		return flex4flw;	}
	public void setFlex4flw(String flex4flw) {		this.flex4flw = flex4flw;	}	

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
}
