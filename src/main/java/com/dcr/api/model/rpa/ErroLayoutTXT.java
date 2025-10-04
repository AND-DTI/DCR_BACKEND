package com.dcr.api.model.rpa;

public class ErroLayoutTXT {

    private String registro; //0, 1, 2, 3, 4 e 9
    private String origem;   //Nacional / Importado / N/A   
    private String sequencia;
    private String observacao;
    
    public ErroLayoutTXT(String registro, String origem, String sequencia, String observacao) {
        this.registro = registro;
        this.origem = origem;
        this.sequencia = sequencia;
        this.observacao = observacao;
    }
    public String getRegistro() {
        return registro;
    }
    public void setRegistro(String registro) {
        this.registro = registro;
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
