package com.dcr.api.response;
import java.util.List;



public class ProdutoPendenciaResponse {

	private Object idMatriz;
    private Object produto;
    private Object modelo;
    private Object anomdl;
    private Object desccom;
    private Object descrfb;
    private Object tpprd;
    private Object protot;
    private Object special;
    private Object tpdcre;
    private Object orig;
    private Object dtneci;
    private Object priourgen;
    private Object prevfat;
    private Object prioresp;
    private Object priodtmnt;
    private Object prioHRmnt;
    //private Object status; j4 - status é por cor
    private Object qtdependencias;
	private Object qtdependenciasEmAberto;  //j4 added  
	private List<CoresResponse> cores;
    private List<PendenciaResponse> pendencias;
	private List<InsumosProdResponse> insumos; //j4 added
	private List<DocumentosResponse> documentos;
	//private List<ProdutoPendenciaResponseList> itens; //j4-comented - verificar se usa no front
    

	public Object getIdMatriz() {		return idMatriz;	}
	public void setIdMatriz(Object idMatriz) {		this.idMatriz = idMatriz;	}

	public Object getProduto() {		return produto;	}
	public void setProduto(Object produto) {		this.produto = produto;	}

	public Object getModelo() {		return modelo;	}
	public void setModelo(Object modelo) {		this.modelo = modelo;	}

	public Object getAnomdl() {		return anomdl;	}
	public void setAnomdl(Object anomdl) {		this.anomdl = anomdl;	}

	public Object getDesccom() {		return desccom;	}
	public void setDesccom(Object desccom) {		this.desccom = desccom;	}

	public Object getDescrfb() {		return descrfb;	}
	public void setDescrfb(Object descrfb) {		this.descrfb = descrfb;	}

	public Object getTpprd() {		return tpprd;	}
	public void setTpprd(Object tpprd) {		this.tpprd = tpprd;	}

	public Object getProtot() {		return protot;	}
	public void setProtot(Object protot) {		this.protot = protot;	}

	public Object getSpecial() {		return special;	}
	public void setSpecial(Object special) {		this.special = special;	}

	public Object getTpdcre() {		return tpdcre;	}
	public void setTpdcre(Object tpdcre) {		this.tpdcre = tpdcre;	}

	public Object getOrig() {		return orig;	}
	public void setOrig(Object orig) {		this.orig = orig;	}

	public Object getDtneci() {		return dtneci;	}
	public void setDtneci(Object dtneci) {		this.dtneci = dtneci;	}

	public Object getPriourgen() {		return priourgen;	}
	public void setPriourgen(Object priourgen) {		this.priourgen = priourgen;	}

	public Object getPrevfat() {		return prevfat;	}
	public void setPrevfat(Object prevfat) {		this.prevfat = prevfat;	}

	public Object getPrioresp() {		return prioresp;	}
	public void setPrioresp(Object prioresp) {		this.prioresp = prioresp;	}

	public Object getPriodtmnt() {		return priodtmnt;	}
	public void setPriodtmnt(Object priodtmnt) {		this.priodtmnt = priodtmnt;	}

	public Object getPrioHRmnt() {		return prioHRmnt;	}
	public void setPrioHRmnt(Object prioHRmnt) {		this.prioHRmnt = prioHRmnt;	}
	
	//public List<ProdutoPendenciaResponseList> getItens() {		return itens;	} //ja estava comentado antes do j4
	//public void setItens(List<ProdutoPendenciaResponseList> itens) {		this.itens = itens;	} //j4 commented
	
	public List<PendenciaResponse> getPendencias() {		return pendencias;	}
	public void setPendencias(List<PendenciaResponse> pendencias) {		this.pendencias = pendencias;	}

	public List<InsumosProdResponse> getInsumos() {		return insumos;	}
	public void setInsumos(List<InsumosProdResponse> insumos) {		this.insumos = insumos;	}	

	public List<DocumentosResponse> getDocumentos() {		return documentos;	}
	public void setDocumentos(List<DocumentosResponse> documentos) {		this.documentos = documentos;	}

	public List<CoresResponse> getCores() {		return cores;	}
	public void setCores(List<CoresResponse> cores) {		this.cores = cores;	}
	
	//public Object getStatus() {		return status;	}
	//public void setStatus(Object status) {		this.status = status;	}
		
	public Object getQtdependencias() {		return qtdependencias;	}
	public void setQtdependencias(Object qtdependencias) {		this.qtdependencias = qtdependencias;	}

	public Object getQtdependenciasEmAberto() {		return qtdependenciasEmAberto;	}
	public void setQtdependenciasEmAberto(Object qtdependenciasEmAberto) {		this.qtdependenciasEmAberto = qtdependenciasEmAberto;	}
	
	
}
