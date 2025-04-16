package com.dcr.api.model.as400;
import java.util.Objects;
import com.dcr.api.model.keys.MtastedocKey;
import com.dcr.api.validator.TamanhoMaximo;
//import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;



@Entity
@Table(name = "MTASTEDOC"/*, schema = "HD4DCDHH"*/)
@ApiModel
public class Mtastedoc {

	@EmbeddedId
	private MtastedocKey key;
	
	@TamanhoMaximo(20)
	//@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
    private String numdoc;
	
	@TamanhoMaximo(10)
	//@TamanhoMinimo(1) - j4 removidos de todos os nao obrigatorios
	@Column(columnDefinition = "char(10)")
	private String serdoc;
	
	@TamanhoMaximo(8)
	//@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String emidoc;
	
	@TamanhoMaximo(14)
	@Column(columnDefinition = "int")
	private Long cnpjfor;
	
	@TamanhoMaximo(15)
	@Column(columnDefinition = "char(15)")
	private String ie;
	
	@TamanhoMaximo(3)
	@Column(columnDefinition = "char(3)")
	private String adicao;
	
	@TamanhoMaximo(2)
	@Column(columnDefinition = "smallint")
	private int itadicao;

	@TamanhoMaximo(15) 
	//@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double vlrunit;

	@TamanhoMaximo(2)
	@Column(columnDefinition = "char(2)")
	private String siglaund;

	@TamanhoMaximo(3)
	@Column(columnDefinition = "char(3)")
	private String codinco;
	
	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
	private String modal;	


	@TamanhoMaximo(20)
	@Column(columnDefinition = "char(20)")
	private String numdoc2;

	@TamanhoMaximo(10)
	@Column(columnDefinition = "char(10)")
	private String serdoc2;

	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
	private String emidoc2;

	@TamanhoMaximo(14)
	@Column(columnDefinition = "int")
	private Long cnpjfor2;

	@TamanhoMaximo(15)
	@Column(columnDefinition = "char(15)")
	private String ie2;
	
	@TamanhoMaximo(3)
	@Column(columnDefinition = "char(3)")
	private String adicao2;
	
	@TamanhoMaximo(2)
	@Column(columnDefinition = "smallint")
	private int itadicao2;

	@TamanhoMaximo(15) 
	@Column(columnDefinition = "decimal(15,6)")
	private Double vlrunit2;

	@TamanhoMaximo(2)
	@Column(columnDefinition = "char(2)")
	private String siglaund2;

	@TamanhoMaximo(3)
	@Column(columnDefinition = "char(3)")
	private String codinco2;
	
	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
	private String modal2;	
	
	
	@TamanhoMaximo(20)
	@Column(columnDefinition = "char(20)")
	private String numdoc3;

	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
	private String emidoc3;
	
	@TamanhoMaximo(10)
	@Column(columnDefinition = "char(10)")
	private String serdoc3;
	
	@TamanhoMaximo(14)
	@Column(columnDefinition = "int")
	private Long cnpjfor3;
	
	@TamanhoMaximo(15)
	@Column(columnDefinition = "char(15)")
	private String ie3;
	
	@TamanhoMaximo(3)
	@Column(columnDefinition = "char(3)")
	private String adicao3;
	
	@TamanhoMaximo(2)
	@Column(columnDefinition = "smallint")
	private int itadicao3;

	@TamanhoMaximo(15) 
	@Column(columnDefinition = "decimal(15,6)")
	private Double vlrunit3;

	@TamanhoMaximo(2)
	@Column(columnDefinition = "char(2)")
	private String siglaund3;

	@TamanhoMaximo(3)
	@Column(columnDefinition = "char(3)")
	private String codinco3;
	
	@TamanhoMaximo(8)
	@Column(columnDefinition = "char(8)")
	private String modal3;	

	
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


	//Create for pendency resolution - create doc3 (no original doc, no sugest doc2)
	public Mtastedoc(MtastedocKey key, String numdoc3, String emidoc3, String serdoc3, Long cnpjfor3, String ie3, 
				    String adicao3, int itadicao3, Double vlrunit3, String siglaund3, String codinco3, String modal3) {
		this.key = key;
		this.numdoc3 = numdoc3;
		this.emidoc3 = emidoc3;
		this.serdoc3 = serdoc3 == null? "": serdoc3;
		this.cnpjfor3 = cnpjfor3 == null? 0L: cnpjfor3;
		this.ie3 = ie3 == null? "": ie3;
		this.adicao3 = adicao3 == null? "": adicao3;
		this.itadicao3 =  Objects.equals(itadicao3, null)? 0: itadicao3;
		this.vlrunit3 = vlrunit3 == null? 0.: vlrunit3;
		this.siglaund3 = siglaund3 == null? "": serdoc3;
		this.codinco3 = codinco3 == null? "": serdoc3;
		this.modal3 = modal3 == null? "": serdoc3;

		this.numdoc = "";
		this.serdoc = "";
		this.emidoc = "";
		this.cnpjfor = 0L;
		this.ie = "";
		this.adicao = "";
		this.itadicao = 0;
		this.vlrunit = 0.;
		this.siglaund = "";
		this.codinco = "";
		this.modal = "";
		this.numdoc2 = "";
		this.serdoc2 = "";
		this.emidoc2 = "";
		this.cnpjfor2 = 0L;
		this.ie2 = "";
		this.adicao2 = "";
		this.itadicao2 = 0;
		this.vlrunit2 = 0.;
		this.siglaund2 = "";
		this.codinco2 = "";
		this.modal2 = "";
	}


	public Mtastedoc(MtastedocKey key, String numdoc, String serdoc, String emidoc, Long cnpjfor, String ie,
			String adicao, int itadicao, Double vlrunit, String siglaund, String codinco, String modal,
			String numdoc2, String serdoc2, String emidoc2, Long cnpjfor2, String ie2, String adicao2, int itadicao2,
			Double vlrunit2, String siglaund2, String codinco2, String modal2, String numdoc3, String emidoc3,
			String serdoc3, Long cnpjfor3, String ie3, String adicao3, int itadicao3, Double vlrunit3,
			String siglaund3, String codinco3, String modal3) {
		this.key = key;
		this.numdoc = numdoc;
		this.serdoc = serdoc;
		this.emidoc = emidoc;
		this.cnpjfor = cnpjfor;
		this.ie = ie;
		this.adicao = adicao;
		this.itadicao = itadicao;
		this.vlrunit = vlrunit;
		this.siglaund = siglaund;
		this.codinco = codinco;
		this.modal = modal;
		this.numdoc2 = numdoc2;
		this.serdoc2 = serdoc2;
		this.emidoc2 = emidoc2;
		this.cnpjfor2 = cnpjfor2;
		this.ie2 = ie2;
		this.adicao2 = adicao2;
		this.itadicao2 = itadicao2;
		this.vlrunit2 = vlrunit2;
		this.siglaund2 = siglaund2;
		this.codinco2 = codinco2;
		this.modal2 = modal2;
		this.numdoc3 = numdoc3;
		this.emidoc3 = emidoc3;
		this.serdoc3 = serdoc3;
		this.cnpjfor3 = cnpjfor3;
		this.ie3 = ie3;
		this.adicao3 = adicao3;
		this.itadicao3 = itadicao3;
		this.vlrunit3 = vlrunit3;
		this.siglaund3 = siglaund3;
		this.codinco3 = codinco3;
		this.modal3 = modal3;
	}

	public Mtastedoc(){

	}


	public MtastedocKey getKey() {		return key;	}
	public void setKey(MtastedocKey key) {		this.key = key;	}

	public String getNumdoc() {		return numdoc;	}
	public void setNumdoc(String numdoc) {		this.numdoc = numdoc;	}

	public String getSerdoc() {		return serdoc;	}
	public void setSerdoc(String serdoc) {		this.serdoc = serdoc==null? "" : serdoc;	}

	public String getEmidoc() {		return emidoc;	}
	public void setEmidoc(String emidoc) {		this.emidoc = emidoc;	}

	public Long getCnpjfor() {		return cnpjfor;	}
	public void setCnpjfor(Long cnpjfor) {		this.cnpjfor = cnpjfor==null? 0 : cnpjfor;	}

	public String getIe() {		return ie;	}
	public void setIe(String ie) {		this.ie = ie==null? "" : ie;	}

	public String getAdicao() {		return adicao;	}
	public void setAdicao(String adicao) {		this.adicao = adicao==null? "" : adicao;	}

	public int getItadicao() {		return itadicao;	}
	public void setItadicao(int itadicao) {		this.itadicao = itadicao;	}

	public Double getVlrunit() {		return vlrunit;	}
	public void setVlrunit(Double vlrunit) {		this.vlrunit = vlrunit;	}

	public String getSiglaund() {		return siglaund;	}
	public void setSiglaund(String siglaund) {		this.siglaund = siglaund;	}

	public String getCodinco() {		return codinco;	}
	public void setCodinco(String codinco) {		this.codinco = codinco==null? "" : codinco;	}

	public String getModal() {		return modal;	}
	public void setModal(String modal) {		this.modal = modal==null? "" : modal;	}


	public String getNumdoc2() {		return numdoc2;	}
	public void setNumdoc2(String numdoc2) {		this.numdoc2 = numdoc2==null? "" : numdoc2;	}

	public String getSerdoc2() {		return serdoc2;	}
	public void setSerdoc2(String serdoc2) {		this.serdoc2 = serdoc2==null? "" : serdoc2;	}

	public String getEmidoc2() {		return emidoc2;	}
	public void setEmidoc2(String emidoc2) {		this.emidoc2 = emidoc2==null? "" : emidoc2;	}

	public Long getCnpjfor2() {		return cnpjfor2;	}
	public void setCnpjfor2(Long cnpjfor2) {		this.cnpjfor2 = cnpjfor2==null? 0 : cnpjfor2;	}

	public String getIe2() {		return ie2;	}
	public void setIe2(String ie2) {		this.ie2 = ie2==null? "" : ie2;	}

	public String getAdicao2() {		return adicao2;	}
	public void setAdicao2(String adicao2) {		this.adicao2 = adicao2==null? "" : adicao2;	}

	public int getItadicao2() {		return itadicao2;	}
	public void setItadicao2(int itadicao2) {		this.itadicao2 = itadicao2;	}

	public Double getVlrunit2() {		return vlrunit2;	}
	public void setVlrunit2(Double vlrunit2) {		this.vlrunit2 = vlrunit2==null? 0 : vlrunit2;	}

	public String getSiglaund2() {		return siglaund2;	}
	public void setSiglaund2(String siglaund2) {		this.siglaund2 = siglaund2==null? "" : siglaund2;	}

	public String getCodinco2() {		return codinco2;	}
	public void setCodinco2(String codinco2) {		this.codinco2 = codinco2==null? "" : codinco2;	}

	public String getModal2() {		return modal2;	}
	public void setModal2(String modal2) {		this.modal2 = modal2==null? "" : modal2;	}


	public String getNumdoc3() {		return numdoc3;	}
	public void setNumdoc3(String numdoc3) {		this.numdoc3 = numdoc3==null? "" : numdoc3;	}

	public String getEmidoc3() {		return emidoc3;	}
	public void setEmidoc3(String emidoc3) {		this.emidoc3 = emidoc3==null? "" : emidoc3;	}

	public String getSerdoc3() {		return serdoc3;	}
	public void setSerdoc3(String serdoc3) {		this.serdoc3 = serdoc3==null? "" : serdoc3;	}

	public Long getCnpjfor3() {		return cnpjfor3;	}
	public void setCnpjfor3(Long cnpjfor3) {		this.cnpjfor3 = cnpjfor3==null? 0 : cnpjfor3;	}

	public String getIe3() {		return ie3;	}
	public void setIe3(String ie3) {		this.ie3 = ie3==null? "" : ie3;	}

	public String getAdicao3() {		return adicao3;	}
	public void setAdicao3(String adicao3) {		this.adicao3 = adicao3==null? "" : adicao3;	}

	public int getItadicao3() {		return itadicao3;	}
	public void setItadicao3(int itadicao3) {		this.itadicao3 = itadicao3;	}

	public Double getVlrunit3() {		return vlrunit3;	}
	public void setVlrunit3(Double vlrunit3) {		this.vlrunit3 = vlrunit3==null? 0 : vlrunit3;	}

	public String getSiglaund3() {		return siglaund3;	}
	public void setSiglaund3(String siglaund3) {		this.siglaund3 = siglaund3==null? "" : siglaund3;	}

	public String getCodinco3() {		return codinco3;	}
	public void setCodinco3(String codinco3) {		this.codinco3 = codinco3==null? "" : codinco3;	}

	public String getModal3() {		return modal3;	}
	public void setModal3(String modal3) {		this.modal3 = modal3==null? "" : modal3;	}	


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
