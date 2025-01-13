package com.dcr.api.model.dto;

public record DcrregraDTO(
    Integer peritran, 
    String cnpjemi, 
    String razsoc, 
    Integer proccarenc, 
    Integer trancarenc, 
    Integer procsemppb, 
    Integer expiraprev, 
    Integer diasprevia, 
    Integer alertaprev, 
    Integer tpvalor, 
    Integer taxamanual, 
    Integer carencia, 
    Integer substituto,
    Integer substfat, 
    Integer substfatn,
    Double coefredu,
    Double aliqiipad     
    ) {

}
