package com.dcr.api.model.dto;
import java.util.List;

public record ProcPendenciaDTO(
    Integer idmatriz,        
    List<ProcPendenciaStepDTO> steps

    ) {
    

}
