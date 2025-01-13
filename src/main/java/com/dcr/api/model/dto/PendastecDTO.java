package com.dcr.api.model.dto; 

public record PendastecDTO (
        
    Integer idmatriz,    
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
    
    ){

}
