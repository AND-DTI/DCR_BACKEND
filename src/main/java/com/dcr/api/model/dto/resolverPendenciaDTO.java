package com.dcr.api.model.dto;

public record resolverPendenciaDTO(

    Integer idmatriz, String partnumpd, String modelo, String tpprd, Double preco, String tpreg,
    Integer numpend, String cdpend, String obsresol, Integer status, 
    String partnum, String partnew, String partnewdsc,
    String tpdoc, String numdoc3, String serdoc3, String emidoc3, Long cnpjfor3, String ie3,
    String adicao3, int itadicao3, Double vlrunit3, String siglaund3, String codinco3, String modal3
    
    ) {

}
