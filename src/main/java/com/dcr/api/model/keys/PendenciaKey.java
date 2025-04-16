package com.dcr.api.model.keys;
import static com.dcr.api.utils.Auxiliar.trimNull;
//import java.util.Objects;
import com.dcr.api.validator.TamanhoMaximo;


public class PendenciaKey {

	@TamanhoMaximo(10)
	private String cdpend;

    @TamanhoMaximo(8)
	private String subtipo;
	
	@TamanhoMaximo(10)
	private String cdresp;


	
	public String getCdpend() {		return trimNull(cdpend);	}
	public void setCdpend(String cdpend) {		this.cdpend = cdpend;	}
	
    public String getCdresp() {		return trimNull(cdresp);	}
	public void setCdresp(String cdresp) {		this.cdresp = cdresp;	}
    
    public String getSubtipo() {        return subtipo;    }
    public void setSubtipo(String subtipo) {        this.subtipo = subtipo;    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cdpend == null) ? 0 : cdpend.hashCode());
        result = prime * result + ((subtipo == null) ? 0 : subtipo.hashCode());
        result = prime * result + ((cdresp == null) ? 0 : cdresp.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PendenciaKey other = (PendenciaKey) obj;
        if (cdpend == null) {
            if (other.cdpend != null)
                return false;
        } else if (!cdpend.equals(other.cdpend))
            return false;
        if (subtipo == null) {
            if (other.subtipo != null)
                return false;
        } else if (!subtipo.equals(other.subtipo))
            return false;
        if (cdresp == null) {
            if (other.cdresp != null)
                return false;
        } else if (!cdresp.equals(other.cdresp))
            return false;
        return true;
    }
	
    
    /*@Override
	public int hashCode() {
		return Objects.hash(cdpend, cdresp);
	}*/

	/*@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PendenciaKey other = (PendenciaKey) obj;
		return Objects.equals(cdpend, other.cdpend) && Objects.equals(cdresp, other.cdresp);
	}*/
}
