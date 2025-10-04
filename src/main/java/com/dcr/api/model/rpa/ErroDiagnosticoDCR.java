package com.dcr.api.model.rpa;

public class ErroDiagnosticoDCR {
    
    private String tipo; //Componente / Subcomponente
    private String origem; //Nacional / Importado    
    private String sequencia;
    private String observacao;
    
    public ErroDiagnosticoDCR(String tipo, String origem, String sequencia, String observacao) {
        this.tipo = tipo;
        this.origem = origem;
        this.sequencia = sequencia;
        this.observacao = observacao;
    }
    
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    
    public String getSequencia() {
        return sequencia;
    }
    public void setSequencia(String sequencia) {
        this.sequencia = sequencia;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
}
