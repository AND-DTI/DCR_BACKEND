package com.dcr.api.response;
import java.util.List;




public class ProdutoSemListaAstecResponse {
	

	private Object idMatriz;
	private Object partnumpd;
	private Object tpprd;
    
	private Object desccom;
    private Object descrfb;
	private Object ppbprd; 
    private Object prddest;
	private Object tpdcre;
	private Object origprd; //j4
	private Object dtneci;
    private Object priourgen;
    private Object prevfat;
    private Object prioresp;
    private Object priodtmnt;
    private Object prioHRmnt;
	private Object unmed; //j4 added
	private Object preco; //j4 added
	private Object ncm; //j4 added

	private Object status;
    private Object qtdependencias;
	private Object qtdependenciasEmAberto;  //j4 added 	    
	private List<PendenciaAstecResponse> pendencias; //j4 - old List<PendenciaSemListaAstecResponse> 
	//private List<DocumentosAstecResponse> documentos;
    
  

	public Object getIdMatriz() {		return idMatriz;	}
	public void setIdMatriz(Object idMatriz) {		this.idMatriz = idMatriz;	}

	public Object getPartnumpd() {		return partnumpd;	}
	public void setPartnumpd(Object partnumpd) {		this.partnumpd = partnumpd;	}

	public Object getTpprd() {		return tpprd;	}
	public void setTpprd(Object tpprd) {		this.tpprd = tpprd;	}

	public Object getDesccom() {		return desccom;	}
	public void setDesccom(Object desccom) {		this.desccom = desccom;	}

	public Object getDescrfb() {		return descrfb;	}
	public void setDescrfb(Object descrfb) {		this.descrfb = descrfb;	}

	public Object getPpbprd() {		return ppbprd;	}
	public void setPpbprd(Object ppbprd) {		this.ppbprd = ppbprd;	}
	
	public Object getPrddest() {		return prddest;	}
	public void setPrddest(Object prddest) {		this.prddest = prddest;	}	

	public Object getTpdcre() {		return tpdcre;	}
	public void setTpdcre(Object tpdcre) {		this.tpdcre = tpdcre;	}

	public Object getOrigprd() {		return origprd;	}
	public void setOrigprd(Object origprd) {		this.origprd = origprd;	}

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

	public Object getUnmed() {		return unmed;	}
	public void setUnmed(Object unmed) {		this.unmed = unmed;	}

	public Object getPreco() {		return preco;	}
	public void setPreco(Object preco) {		this.preco = preco;	}
	
	public Object getNcm() {		return ncm;	}
	public void setNcm(Object ncm) {		this.ncm = ncm;	}	

	public Object getStatus() {		return status;	}
	public void setStatus(Object status) {		this.status = status;	}

	public Object getQtdependencias() {		return qtdependencias;	}
	public void setQtdependencias(Object qtdependencias) {		this.qtdependencias = qtdependencias;	}

	public Object getQtdependenciasEmAberto() {		return qtdependenciasEmAberto;	}
	public void setQtdependenciasEmAberto(Object qtdependenciasEmAberto) {		this.qtdependenciasEmAberto = qtdependenciasEmAberto;	}		
	
	public List<PendenciaAstecResponse> getPendencias() {		return pendencias;	}
	public void setPendencias(List<PendenciaAstecResponse> pendencias) {		this.pendencias = pendencias;	}

	
	

}
