package com.dcr.api.model.dto;

import java.util.List;

public record MatriprdComCorIdDTO(Integer idmatriz,String produto,String modelo,String anomdl,String desccom,String descrfb,String tpprd,Integer protot,Integer special,String tpdcre,String origprd,String dtneci,Integer priourgen,String prevfat,List<MatriitmDTO> itens) {

}
