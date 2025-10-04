package com.dcr.api.model.rpa;
import java.util.List;

public class DiagnosticoLayout {
    
    private String resultado;
    private Integer qtdeErros;    
    private List<ErroLayoutTXT> erros;
    
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
    public List<ErroLayoutTXT> getErros() {
        return erros;
    }
    public void setErros(List<ErroLayoutTXT> erros) {
        this.erros = erros;
    }

    @Override
    public String toString() {
        return "DiagnosticoLayout{resultado="+resultado+", qtdeErros="+qtdeErros+'}';
    }

}
