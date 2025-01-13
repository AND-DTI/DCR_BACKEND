package com.dcr.api.model.dto;
import java.util.List;


public record PendrespDTO2(
    
    List<PendrespDTO> responsaveis,
    List<String> subtipos
    
    ) {

}
