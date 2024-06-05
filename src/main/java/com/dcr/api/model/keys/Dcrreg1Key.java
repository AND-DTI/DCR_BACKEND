package com.dcr.api.model.keys;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;


public class Dcrreg1Key {

	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	private Long idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	private String tpprd;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	private Integer modelo;
	
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
	public Integer getModelo() {
		return modelo;
	}
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idmatriz == null) ? 0 : idmatriz.hashCode());
		result = prime * result + ((partnumpd == null) ? 0 : partnumpd.hashCode());
		result = prime * result + ((tpprd == null) ? 0 : tpprd.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
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
		Dcrreg1Key other = (Dcrreg1Key) obj;
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
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		return true;
	}
}
