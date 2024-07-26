package com.dcr.api.model.dto;
import java.util.List;



public record CadppbComCorDTO(
    String tpprd, String desccom, String descrfb, String prddest, String ppbprd,
    List<CorDTO> cores
    //List<MatriitmDTO> cores
    ) 
{

}
