package com.dcr.api.model.as400;

import static com.dcr.api.utils.Auxiliar.trimNull;

import java.util.List;

import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CADTPPEND", schema = "HD4DCDHH")
@ApiModel
public class Cadtppend {

	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(10)", unique = true)
    private String cdpend;
	
	@TamanhoMaximo(80)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(80)")
    private String descpend;
	
	@TamanhoMaximo(256)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(256)")
    private String obspend;
	
	@TamanhoMaximo(1)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(1)")
    private String tpreg;
	
	@OneToMany(mappedBy = "cadtppend", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pendresp> responsaveis;
	
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
    
	public String getCdpend() {
		return trimNull(cdpend);
	}

	public void setCdpend(String cdpend) {
		this.cdpend = cdpend;
	}

	public void setDescpend(String descpend) {
		this.descpend = descpend;
	}

	public void setObspend(String obspend) {
		this.obspend = obspend;
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

	public List<Pendresp> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Pendresp> responsaveis) {
		this.responsaveis = responsaveis;
	}

	public String getDescpend() {
		return trimNull(descpend);
	}

	public String getObspend() {
		return trimNull(obspend);
	}

	public String getTpreg() {
		return tpreg;
	}

	public void setTpreg(String tpreg) {
		this.tpreg = tpreg;
	}
}
