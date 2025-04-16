package com.dcr.api.model.keys;
import java.util.Objects;
import jakarta.persistence.Embeddable;




@Embeddable
public class MtastecKey {
	
	private Integer idmatriz;

	private String partnumpd;
	
	public String getPartnumpd() {
		return partnumpd;
	}
	public void setPartnumpd(String partnumpd) {
		this.partnumpd = partnumpd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idmatriz, partnumpd);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MtastecKey other = (MtastecKey) obj;
		return Objects.equals(idmatriz, other.idmatriz) && Objects.equals(partnumpd, other.partnumpd);
	}
}
