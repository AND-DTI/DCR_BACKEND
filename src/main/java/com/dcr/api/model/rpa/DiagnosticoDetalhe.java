package com.dcr.api.model.rpa;
import java.util.List;


public class DiagnosticoDetalhe {
    
    private String resultado;
    private Integer qtdeErros;
    private Integer qtdeErrosNac;
    private Integer qtdeErrosImp;
    private List<ErroDiagnosticoDCR> erros;

    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }
    public Integer getQtdeErros() {
        return qtdeErros;
    }
    public void setQtdeErros(Integer qtdeErros) {
        this.qtdeErros = qtdeErros;
    }
    public List<ErroDiagnosticoDCR> getErros() {
        return erros;
    }
    public void setErros(List<ErroDiagnosticoDCR> erros) {
        this.erros = erros;
    }
    public Integer getQtdeErrosNac() {
        return qtdeErrosNac;
    }
    public void setQtdeErrosNac(Integer qtdeErrosNac) {
        this.qtdeErrosNac = qtdeErrosNac;
    }
    public Integer getQtdeErrosImp() {
        return qtdeErrosImp;
    }
    public void setQtdeErrosImp(Integer qtdeErrosImp) {
        this.qtdeErrosImp = qtdeErrosImp;
    }
    
    
}
