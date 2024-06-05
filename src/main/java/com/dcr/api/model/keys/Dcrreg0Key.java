package com.dcr.api.model.keys;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import jakarta.persistence.Embeddable;

@Embeddable
public class Dcrreg0Key {

	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Long idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private String tpprd;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	private String denom;
	
	public Long getIdmatriz() {
		return idmatriz;
	}
	public void setIdmatriz(Long idmatriz) {
		this.idmatriz = idmatriz;
	}
	public String getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}
	public String getTpprd() {
		return tpprd;
	}
	public void setTpprd(String tpprd) {
		this.tpprd = tpprd;
	}
	public String getDenom() {
		return denom;
	}
	public void setDenom(String denom) {
		this.denom = denom;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idmatriz == null) ? 0 : idmatriz.hashCode());
		result = prime * result + ((partnumpd == null) ? 0 : partnumpd.hashCode());
		result = prime * result + ((tpprd == null) ? 0 : tpprd.hashCode());
		result = prime * result + ((denom == null) ? 0 : denom.hashCode());
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
		Dcrreg0Key other = (Dcrreg0Key) obj;
		if (idmatriz == null) {
			if (other.idmatriz != null)
				return false;
		} else if (!idmatriz.equals(other.idmatriz))
			return false;
		if (partnumpd == null) {
			if (other.partnumpd != null)
				return false;
		} else if (!partnumpd.equals(other.partnumpd))
			return false;
		if (tpprd == null) {
			if (other.tpprd != null)
				return false;
		} else if (!tpprd.equals(other.tpprd))
			return false;
		if (denom == null) {
			if (other.denom != null)
				return false;
		} else if (!denom.equals(other.denom))
			return false;
		return true;
	}
}
