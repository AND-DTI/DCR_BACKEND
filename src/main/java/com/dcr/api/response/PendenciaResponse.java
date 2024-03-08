package com.dcr.api.response;

public class PendenciaResponse {
	private Object partnum;
	private Object numpend;
    private Object cdpend;
    private Object obspend;
    private Object status;
    
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
	public Object getObspend() {
		return obspend;
	}
	public void setObspend(Object obspend) {
		this.obspend = obspend;
	}
	public Object getStatus() {
		return status;
	}
	public void setStatus(Object status) {
		this.status = status;
	}
	public Object getPartnum() {
		return partnum;
	}
	public void setPartnum(Object partnum) {
		this.partnum = partnum;
	}
}
