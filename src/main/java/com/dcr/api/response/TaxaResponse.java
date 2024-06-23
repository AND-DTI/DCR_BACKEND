package com.dcr.api.response;

public class TaxaResponse {
	private String cdmoed;
    private String vigini;
    private String vigfim;
    private Double taxa;
    private Integer taxamanual;
	public String getCdmoed() {
		return cdmoed;
	}
	public void setCdmoed(String cdmoed) {
		this.cdmoed = cdmoed;
	}
	public String getVigini() {
		return vigini;
	}
	public void setVigini(String vigini) {
		this.vigini = vigini;
	}
	public String getVigfim() {
		return vigfim;
	}
	public void setVigfim(String vigfim) {
		this.vigfim = vigfim;
	}
	public Double getTaxa() {
		return taxa;
	}
	public void setTaxa(Double taxa) {
		this.taxa = taxa;
	}
	public Integer getTaxamanual() {
		return taxamanual;
	}
	public void setTaxamanual(Integer taxamanual) {
		this.taxamanual = taxamanual;
	}
}
