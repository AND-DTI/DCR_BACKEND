package com.dcr.api.model.rpa;


public class DiagnosticoResumo {

    private Double totalImportado;
    private Double totalNacional;
    private Double custoTotal;
    private Double iiSemReducao;
    private Double iiReduzido;
    private Double pesoBruto;
    
    public DiagnosticoResumo() {
    }

    public DiagnosticoResumo(Double totalImportado, Double totalNacional, Double custoTotal, Double iiSemReducao, Double iiReduzido, Double pesoBruto) {
        this.totalImportado = totalImportado;
        this.totalNacional = totalNacional;
        this.custoTotal = custoTotal;
        this.iiSemReducao = iiSemReducao;
        this.iiReduzido = iiReduzido;
        this.pesoBruto = pesoBruto;
    }

    @Override
    public String toString() {
        return "DiagnosticoResumo{totalImportado="+totalImportado+", totalNacional="+totalNacional+", custoTotal="+custoTotal+", iiSemReducao="+iiSemReducao+", iiReduzido="+iiReduzido+", pesoBruto="+pesoBruto+'}';
    }

    public Double getTotalImportado() {
        return totalImportado;
    }

    public void setTotalImportado(Double totalImportado) {
        this.totalImportado = totalImportado;
    }

    public Double getTotalNacional() {
        return totalNacional;
    }

    public void setTotalNacional(Double totalNacional) {
        this.totalNacional = totalNacional;
    }

    public Double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(Double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public Double getIiSemReducao() {
        return iiSemReducao;
    }

    public void setIiSemReducao(Double iiSemReducao) {
        this.iiSemReducao = iiSemReducao;
    }

    public Double getIiReduzido() {
        return iiReduzido;
    }

    public void setIiReduzido(Double iiReduzido) {
        this.iiReduzido = iiReduzido;
    }

    public Double getPesoBruto() {
        return pesoBruto;
    }

    public void setPesoBruto(Double pesoBruto) {
        this.pesoBruto = pesoBruto;
    }

   
}
