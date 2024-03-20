package com.dcr.api.model.as400;

import static com.dcr.api.utils.Auxiliar.trimNull;

import com.dcr.api.model.keys.PendenciaKey;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PENDRESP", schema = "HD4DCDHH")
@ApiModel
public class Pendresp {

	@EmbeddedId
    private PendenciaKey key;
	
	@TamanhoMaximo(150)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(150)")
    private String nmresp;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdpend", referencedColumnName = "cdpend", insertable = false, updatable = false)
    private Cadtppend cadtppend;
	
    @Column(columnDefinition = "char(40)")
    private String itaudsys;

    @Column(columnDefinition = "char(10)")
    private String itaudusr;

    @Column(columnDefinition = "char(30)")
    private String itaudhst;

    @Column(columnDefinition = "char(8)")
    private String itauddt;

    @Column(columnDefinition = "char(8)")
    private String itaudhr;

	public PendenciaKey getKey() {
		return key;
	}

	public void setKey(PendenciaKey key) {
		this.key = key;
	}

	public String getNmresp() {
		return trimNull(nmresp);
	}

	public void setNmresp(String nmresp) {
		this.nmresp = nmresp;
	}

	public void setItaudsys(String itaudsys) {
		this.itaudsys = itaudsys;
	}
	
	public void setItaudusr(String itaudusr) {
		this.itaudusr = itaudusr;
	}

	public void setItaudhst(String itaudhst) {
		this.itaudhst = itaudhst;
	}

	public void setItauddt(String itauddt) {
		this.itauddt = itauddt;
	}

	public void setItaudhr(String itaudhr) {
		this.itaudhr = itaudhr;
	}

}
