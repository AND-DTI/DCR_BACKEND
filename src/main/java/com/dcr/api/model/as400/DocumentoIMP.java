package com.dcr.api.model.as400;
import com.dcr.api.model.keys.DocumentoIMPKey;

import io.swagger.annotations.ApiModel;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;




@Entity
@Table(name = "VW_DOCU_IMP")
@ApiModel
public class DocumentoIMP {

    
    @EmbeddedId
    private DocumentoIMPKey key;

    @Column(columnDefinition = "int")
    private int codcia;

    @Column(columnDefinition = "char(14)")
    private String fatnro;

    @Column(columnDefinition = "int")
    private int anofat;

    @Column(columnDefinition = "char(6)")
    private String vndnr;

    @Column(columnDefinition = "char(8)")
    private String emissao;

    @Column(columnDefinition = "char(1)")
    private String temlivro;

    @Column(columnDefinition = "char(3)")
    private String codinc;

    @Column(columnDefinition = "char(30)")
    private String modal;

    @Column(columnDefinition = "char(8)")
    private String dtareg;

    @Column(columnDefinition = "char(400)")
    private String espec;

    @Column(columnDefinition = "decimal(18,7)")
    private Double vlradu;

    @Column(columnDefinition = "decimal(18,7)")
    private Double qtditn;

    

    public DocumentoIMPKey getKey() {
        return key;
    }

    public void setKey(DocumentoIMPKey key) {
        this.key = key;
    }

    public int getCodcia() {
        return codcia;
    }

    public void setCodcia(int codcia) {
        this.codcia = codcia;
    }

    public String getFatnro() {
        return fatnro;
    }

    public void setFatnro(String fatnro) {
        this.fatnro = fatnro;
    }

    public int getAnofat() {
        return anofat;
    }

    public void setAnofat(int anofat) {
        this.anofat = anofat;
    }

    public String getVndnr() {
        return vndnr;
    }

    public void setVndnr(String vndnr) {
        this.vndnr = vndnr;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public String getTemlivro() {
        return temlivro;
    }

    public void setTemlivro(String temlivro) {
        this.temlivro = temlivro;
    }

    public String getCodinc() {
        return codinc;
    }

    public void setCodinc(String codinc) {
        this.codinc = codinc;
    }

    public String getModal() {
        return modal;
    }

    public void setModal(String modal) {
        this.modal = modal;
    }

    public String getDtareg() {
        return dtareg;
    }

    public void setDtareg(String dtareg) {
        this.dtareg = dtareg;
    }

    public String getEspec() {
        return espec;
    }

    public void setEspec(String espec) {
        this.espec = espec;
    }

    public Double getVlradu() {
        return vlradu;
    }

    public void setVlradu(Double vlradu) {
        this.vlradu = vlradu;
    }

    public Double getQtditn() {
        return qtditn;
    }

    public void setQtditn(Double qtditn) {
        this.qtditn = qtditn;
    }


    
    
}
