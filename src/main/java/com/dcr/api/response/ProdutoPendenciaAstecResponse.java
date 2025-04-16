package com.dcr.api.response;
import java.util.List;



public class ProdutoPendenciaAstecResponse {

	
	private Integer idMatriz; //j5 - old type - Object to use with "for (Object[] resultado : resultados)"" - changed to interface and to work Auxiliar.formatResponse trim
    private String partnumpd;
    private String desccom;
    private String descrfb;
    private String tpdcre;
	private String origprd;		
    private String dtneci;
    private String priourgen;
    private String prevfat;
    private String prioresp;
    private String priodtmnt;
    private String prioHRmnt; 
	private String unmed;   
	private Double preco; //j4 added
	private String ncm; //j4 added
	private String ppbprd;
    private String prddest;
    private int status;    
    private Integer qtdependencias;
	private Integer qtdependenciasEmAberto;  //j4 added  
    private List<PendenciaAstecResponse2> pendencias; 
	private List<InsumosAstecResponse> insumos; //j4 added //InsumosProdResponse
    private List<DocumentosAstecResponse> documentos;


	public Integer getIdMatriz() {
		return idMatriz;
	}
	public void setIdMatriz(Integer idMatriz) {
		this.idMatriz = idMatriz;
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

	public String getDtneci() {
		return dtneci;
	}
	public void setDtneci(String dtneci) {
		this.dtneci = dtneci;
	}

	public String getPriourgen() {
		return priourgen;
	}
	public void setPriourgen(String priourgen) {
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

	public String getPrioHRmnt() {
		return prioHRmnt;
	}
	public void setPrioHRmnt(String prioHRmnt) {
		this.prioHRmnt = prioHRmnt;
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

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getQtdependencias() {
		return qtdependencias;
	}
	public void setQtdependencias(Integer qtdependencias) {
		this.qtdependencias = qtdependencias;
	}

	public Integer getQtdependenciasEmAberto() {
		return qtdependenciasEmAberto;
	}
	public void setQtdependenciasEmAberto(Integer qtdependenciasEmAberto) {
		this.qtdependenciasEmAberto = qtdependenciasEmAberto;
	}

	public List<PendenciaAstecResponse2> getPendencias() {
		return pendencias;
	}
	public void setPendencias(List<PendenciaAstecResponse2> pendencias) {
		this.pendencias = pendencias;
	}

	public List<InsumosAstecResponse> getInsumos() {
		return insumos;
	}
	public void setInsumos(List<InsumosAstecResponse> insumos) {
		this.insumos = insumos;
	}
	
	public List<DocumentosAstecResponse> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DocumentosAstecResponse> documentos) {
		this.documentos = documentos;
	}

	
	 




}
