package com.dcr.api.model.dto;


public record PendprodDTO(

    Integer idmatriz,
    String partnumpd,
    String partnum,     
    Integer numpend,
    
    String cdpend,
    String obsresol,
    Integer status,

    Long flex1flw,		
	Long flex2flw,		
	String flex3flw,	
	String flex4flw,
	String flex5flw

    /*old bf J5:
    Integer idmatriz,
    String partnum, 
    String partnumpd,
    Integer numpend,
    String cdpend,
    String obsresol,
    Integer status*/
    ) {

}
