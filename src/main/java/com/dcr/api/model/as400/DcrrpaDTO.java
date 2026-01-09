package com.dcr.api.model.as400;


public record DcrrpaDTO(
    String username,
    String maquina,		
    Integer porta,			
    String baseurl,
    String rpauri,	
    String playpath,
    String playbrowse,
    String playchanel
) {
    
}
