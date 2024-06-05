package com.dcr.api.model.keys;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import jakarta.persistence.Column;


public class AccdscopeKey {
	
	@TamanhoMaximo(9)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int")
    private Integer idoper;
	
	@TamanhoMaximo(2)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(2)")
    private String codidioma;

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
    private String cdsys;
	
	public Integer getIdoper() {
		return idoper;
	}

	public void setIdoper(Integer idoper) {
		this.idoper = idoper;
	}

	public String getCodidioma() {
		return codidioma;
	}

	public void setCodidioma(String codidioma) {
		this.codidioma = codidioma;
	}

	public String getCdsys() {
		return cdsys;
	}

	public void setCdsys(String cdsys) {
		this.cdsys = cdsys;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idoper == null) ? 0 : idoper.hashCode());
		result = prime * result + ((codidioma == null) ? 0 : codidioma.hashCode());
		result = prime * result + ((cdsys == null) ? 0 : cdsys.hashCode());
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
		AccdscopeKey other = (AccdscopeKey) obj;
		if (idoper == null) {
			if (other.idoper != null)
				return false;
		} else if (!idoper.equals(other.idoper))
			return false;
		if (codidioma == null) {
			if (other.codidioma != null)
				return false;
		} else if (!codidioma.equals(other.codidioma))
			return false;
		if (cdsys == null) {
			if (other.cdsys != null)
				return false;
		} else if (!cdsys.equals(other.cdsys))
			return false;
		return true;
	}
}
