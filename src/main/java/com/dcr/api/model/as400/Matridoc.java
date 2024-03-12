package com.dcr.api.model.as400;

import com.dcr.api.model.keys.MatridocKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "MATRIDOC", schema = "HD4DCDHH")
@ApiModel
public class Matridoc {

	@EmbeddedId
	private MatridocKey key;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
	private String numdoc;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String serdoc;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String emidoc;
	
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Long cnpjfor;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(15)")
	private String ie;
	
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
	private String adicao;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String itadicao;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
	private String numdoc2;

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String serdoc2;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String emidoc3;
	
	@TamanhoMaximo(20)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(20)")
	private String numdoc3;
	
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)")
	private String serdoc3;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String emidoc2;
	
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Long cnpjfor2;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(15)")
	private String ie2;
	
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
	private String adicao2;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String itadicao2;
	
	@TamanhoMaximo(14)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private Long cnpjfor3;
	
	@TamanhoMaximo(15)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(15)")
	private String ie3;
	
	@TamanhoMaximo(3)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(3)")
	private String adicao3;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
	private String itadicao3;
	
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

	public MatridocKey getKey() {
		return key;
	}

	public void setKey(MatridocKey key) {
		this.key = key;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getSerdoc() {
		return serdoc;
	}

	public void setSerdoc(String serdoc) {
		this.serdoc = serdoc;
	}

	public String getEmidoc() {
		return emidoc;
	}

	public void setEmidoc(String emidoc) {
		this.emidoc = emidoc;
	}

	public String getNumdoc2() {
		return numdoc2;
	}

	public void setNumdoc2(String numdoc2) {
		this.numdoc2 = numdoc2;
	}

	public String getSerdoc2() {
		return serdoc2;
	}

	public void setSerdoc2(String serdoc2) {
		this.serdoc2 = serdoc2;
	}

	public String getEmidoc2() {
		return emidoc2;
	}

	public void setEmidoc2(String emidoc2) {
		this.emidoc2 = emidoc2;
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

	public String getAdicao() {
		return adicao;
	}

	public void setAdicao(String adicao) {
		this.adicao = adicao;
	}

	public String getItadicao() {
		return itadicao;
	}

	public void setItadicao(String itadicao) {
		this.itadicao = itadicao;
	}

	public String getEmidoc3() {
		return emidoc3;
	}

	public void setEmidoc3(String emidoc3) {
		this.emidoc3 = emidoc3;
	}

	public String getNumdoc3() {
		return numdoc3;
	}

	public void setNumdoc3(String numdoc3) {
		this.numdoc3 = numdoc3;
	}

	public String getSerdoc3() {
		return serdoc3;
	}

	public void setSerdoc3(String serdoc3) {
		this.serdoc3 = serdoc3;
	}

	public Long getCnpjfor2() {
		return cnpjfor2;
	}

	public void setCnpjfor2(Long cnpjfor2) {
		this.cnpjfor2 = cnpjfor2;
	}

	public String getIe2() {
		return ie2;
	}

	public void setIe2(String ie2) {
		this.ie2 = ie2;
	}

	public String getAdicao2() {
		return adicao2;
	}

	public void setAdicao2(String adicao2) {
		this.adicao2 = adicao2;
	}

	public String getItadicao2() {
		return itadicao2;
	}

	public void setItadicao2(String itadicao2) {
		this.itadicao2 = itadicao2;
	}

	public Long getCnpjfor3() {
		return cnpjfor3;
	}

	public void setCnpjfor3(Long cnpjfor3) {
		this.cnpjfor3 = cnpjfor3;
	}

	public String getIe3() {
		return ie3;
	}

	public void setIe3(String ie3) {
		this.ie3 = ie3;
	}

	public String getAdicao3() {
		return adicao3;
	}

	public void setAdicao3(String adicao3) {
		this.adicao3 = adicao3;
	}

	public String getItadicao3() {
		return itadicao3;
	}

	public void setItadicao3(String itadicao3) {
		this.itadicao3 = itadicao3;
	}
}
