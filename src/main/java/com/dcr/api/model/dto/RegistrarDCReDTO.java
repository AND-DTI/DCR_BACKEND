package com.dcr.api.model.dto;

public record RegistrarDCReDTO(
    Integer idmatriz,
    String partnumpd,
    String tpprd,
    String dcre,
    String dtregistro,
    String hrregistro, 
    String tpdcre,  
    String dcrant,
    //dtvigini: '', -> cálculo back
    //hrvigini: '', -> cálculo back
    //dtvigfim: '', -> used for dcr replace - implement
    //hrvigfim: '', -> used for dcr replace - implement
    Double taxausd,
    Double totalnac,
    Double totalimp,
    Double custotal,
    Double coefred,
    Double iitotal,
    Double iireduzido
    ) {

}
