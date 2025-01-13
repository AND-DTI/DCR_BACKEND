package com.dcr.api.model.as400;
import com.dcr.api.validator.TamanhoMaximo;
import com.dcr.api.validator.TamanhoMinimo;
import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;



@Entity
@Table(name = "DCRVIGEN", schema = "HD4DCDHH")
@ApiModel
public class Dcrvigen {

	@Id
	@TamanhoMaximo(10)
	@TamanhoMinimo(10)
	@Column(columnDefinition = "char(10)")
	private String dcre;

	@TamanhoMaximo(10)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "int(10)")
	private Integer idmatriz;
	
	@TamanhoMaximo(25)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(25)")
	private String partnumpd;
	
	@TamanhoMaximo(4)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(4)")
	private String tpprd;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtregistro;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrregistro;
	
	@TamanhoMaximo(10)	
	@Column(columnDefinition = "char(10) default '' ")
	private String dcrant;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String dtvigini;
	
	@TamanhoMaximo(8)
	@TamanhoMinimo(1)
	@Column(columnDefinition = "char(8)")
	private String hrvigini;
	
	@TamanhoMaximo(8)	
	@Column(columnDefinition = "char(8) default '' ")
	private String dtvigfim;
	
	@TamanhoMaximo(8)	
	@Column(columnDefinition = "char(8) default '' ")
	private String hrvigfim;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(8,6)")
	private Double taxausd;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double totalnac;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double totalimp;

	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double custotal;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(4,2)")
	private Double coefred;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double iitotal;
	
	@TamanhoMinimo(1)
	@Column(columnDefinition = "decimal(15,6)")
	private Double iireduzido;
	
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

	public Dcrvigen() {
    }

    public Dcrvigen(String dcre, Integer idmatriz, String partnumpd, String tpprd, String dtregistro, String hrregistro,
            String dcrant, String dtvigini, String hrvigini, Double taxausd, Double totalnac, Double totalimp,
            Double custotal, Double coefred, Double iitotal, Double iireduzido) {
        this.dcre = dcre;
        this.idmatriz = idmatriz;
        this.partnumpd = partnumpd;
        this.tpprd = tpprd;
        this.dtregistro = dtregistro;
        this.hrregistro = hrregistro;
        this.dcrant = dcrant;
        this.dtvigini = dtvigini;
        this.hrvigini = hrvigini;
        this.taxausd = taxausd;
        this.totalnac = totalnac;
        this.totalimp = totalimp;
        this.custotal = custotal;
        this.coefred = coefred;
        this.iitotal = iitotal;
        this.iireduzido = iireduzido;
    }

    public String getDcre() {		return dcre;	}
	public void setDcre(String dcre) {		this.dcre = dcre;	}

	public Integer getIdmatriz() {		return idmatriz;	}
	public void setIdmatriz(Integer idmatriz) {		this.idmatriz = idmatriz;	}

	public String getPartnumpd() {		return partnumpd;	}
	public void setPartnumpd(String partnumpd) {		this.partnumpd = partnumpd;	}

	public String getTpprd() {		return tpprd;	}
	public void setTpprd(String tpprd) {		this.tpprd = tpprd;	}

	public String getDtregistro() {		return dtregistro;	}
	public void setDtregistro(String dtregistro) {		this.dtregistro = dtregistro;	}

	public String getHrregistro() {		return hrregistro;	}
	public void setHrregistro(String hrregistro) {		this.hrregistro = hrregistro;	}

	public String getDcrant() {		return dcrant;	}
	public void setDcrant(String dcrant) {		this.dcrant = dcrant;	}

	public String getDtvigini() {		return dtvigini;	}
	public void setDtvigini(String dtvigini) {		this.dtvigini = dtvigini;	}

	public String getHrvigini() {		return hrvigini;	}
	public void setHrvigini(String hrvigini) {		this.hrvigini = hrvigini;	}

	public String getDtvigfim() {		return dtvigfim;	}
	public void setDtvigfim(String dtvigfim) {		this.dtvigfim = dtvigfim;	}

	public String getHrvigfim() {		return hrvigfim;	}
	public void setHrvigfim(String hrvigfim) {		this.hrvigfim = hrvigfim;	}

	public Double getTaxausd() {		return taxausd;	}
	public void setTaxausd(Double taxausd) {		this.taxausd = taxausd;	}

	public Double getTotalnac() {		return totalnac;	}
	public void setTotalnac(Double totalnac) {		this.totalnac = totalnac;	}

	public Double getTotalimp() {		return totalimp;	}
	public void setTotalimp(Double totalimp) {		this.totalimp = totalimp;	}

	public Double getCustotal() {		return custotal;	}
	public void setCustotal(Double custotal) {		this.custotal = custotal;	}

	public Double getCoefred() {		return coefred;	}
	public void setCoefred(Double coefred) {		this.coefred = coefred;	}

	public Double getIitotal() {		return iitotal;	}
	public void setIitotal(Double iitotal) {		this.iitotal = iitotal;	}

	public Double getIireduzido() {		return iireduzido;	}
	public void setIireduzido(Double iireduzido) {		this.iireduzido = iireduzido;	}

	

    @PrePersist
        public void prePersist() {
            if (dcrant == null) {
                dcrant = "";
            }
            if (dtvigfim == null) {
                dtvigfim = "";
            }
            if (hrvigfim == null) {
                hrvigfim = "";
            }
        }


}
