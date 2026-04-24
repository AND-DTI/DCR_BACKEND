package com.dcr.api.response.Interface;



public interface ProdutoINT {
    
    /* OBS: 
       Primitive int not work if value is null - use Integer 
    */

    Integer getIdMatriz(); 
    String getPartnumpd();
    String getTpprd();
    String getDesccom();
    String getDescrfb();
    String getTpdcre();
    String getProduto();
    String getModelo();
    int getAnomdl();
    int getProtot();
    int getSpecial();
	String getOrigprd();	
    String getDtneci();
    String getPriourgen();
    String getPrevfat();
    String getPrioresp();
    String getPriodtmnt();
    String getPrioHRmnt(); 
	String getUnmed();   
	Double getPreco(); 
	String getNcmprd(); 
    String getPpbprd();
    String getPrddest();
    int getStatusproc();
    String getCodcor();
    String getPartdesc();        
    //int priocor;
    String getCdbej();
    String getCorpt();
    String getCoreng();
    String getTppin();
    
    //DCR-E SUGERIDO:
    String getSugest();
    String getPrdSugest();  
    Integer getFatSugest();
    String getMdlSugest(); 
    Integer getAnoSugest(); 
    String getDescSugest();
    String getDcrSugest(); 
    String getRegSugest();    
    Double getPrcSugest(); 
    Double getIiSugest();

    //CÓPIA DE DCR-E:
    String getPrtncopy(); 
    String getPrdcopy(); 
    Integer getFatcopy(); 
    String getMdlcopy(); 
    Integer getAnocopy(); 
    String getDesccopy();
    String getDcrecopy(); 
    String getRegcopy();
    Double getPrccopy(); 
    Double getIicopy();
    String getRespcopy(); 
    String getDtcopy(); 
    String getHrcopy(); 	  

}
