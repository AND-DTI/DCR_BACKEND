package com.dcr.api.model.dto;

import java.util.List;

public record MatriprdComCorDTO(Integer idmatriz,String produto,String modelo,String anomdl,String desccom,String descrfb,String tpprd,Integer protot,Integer special,String tpdcre,String origprd,String dtneci,Integer priourgen,String prevfat,String prioresp,String priodtmnt,String priohrmnt,List<MatriitmDTO> cores) {

}
