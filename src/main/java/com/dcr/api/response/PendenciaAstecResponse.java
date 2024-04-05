package com.dcr.api.response;

public class PendenciaAstecResponse {
	private Object idmatriz;
	private Object partnum;
	private Object numpend;
    private Object cdpend;
    private Object obsresol;
    private Object status;
	public Object getPartnum() {
		return partnum;
	}
	public void setPartnum(Object partnum) {
		this.partnum = partnum;
	}
	public Object getNumpend() {
		return numpend;
	}
	public void setNumpend(Object numpend) {
		this.numpend = numpend;
	}
	public Object getCdpend() {
		return cdpend;
	}
	public void setCdpend(Object cdpend) {
		this.cdpend = cdpend;
	}
	public Object getObsresol() {
		return obsresol;
	}
	public void setObsresol(Object obsresol) {
		this.obsresol = obsresol;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public Object getIdmatriz() {
		return idmatriz;
	}
	public void setIdmatriz(Object idmatriz) {
		this.idmatriz = idmatriz;
	}
}
