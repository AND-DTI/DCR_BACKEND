package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Partnumber;
import com.dcr.api.response.Interface.DocumentoIMP;
import com.dcr.api.response.Interface.DocumentoNAC;



public interface PartnumberRepository extends JpaRepository<Partnumber, String>{




    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      round(vlradu/qtditn,7) as vlrunit, 'UN' as siglaund, codinc as codinco, modal 
    FROM
      HD4DCDHH.VW_DOCU_IMP_LT  
    WHERE  
      nrodig= :numdoc and itnbr= :partnum
    """, nativeQuery = true)
    List<DocumentoIMP> findDocumentoImpByDocnumAndPartnum(String numdoc, String partnum);


    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      round(vlradu/qtditn,7) as vlrunit, 'UN' as siglaund, codinc as codinco, modal   
    FROM
      HD4DCDHH.VW_DOCU_IMP_LT  
    WHERE 
      itnbr= :partnum 
    """, nativeQuery = true)
    List<DocumentoIMP> findDocumentoImpByPartnum(String partnum);


    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      round(vlradu/qtditn,7) as vlrunit, 'UN' as siglaund, codinc as codinco, modal   
    FROM
      HD4DCDHH.VW_DOCU_IMP_LT  
    WHERE  
      nrodig= :numdoc 
    """, nativeQuery = true)
    List<DocumentoIMP> findDocumentoImpByNumdoc(String numdoc);





    @Query(value = """
    SELECT 
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      TEMLIVRO            
    FROM
      HD4DCDHH.VW_DOCU_NAC 
    WHERE  
      nf= :numdoc and fdlitm= :partnum
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByDocnumAndPartnum(String numdoc, String partnum);
  
  
    @Query(value = """
    SELECT 
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      TEMLIVRO            
    FROM
      HD4DCDHH.VW_DOCU_NAC  
    WHERE 
      fdlitm= :partnum  
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByPartnum(String partnum);
  
  
    @Query(value = """
    SELECT 
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      TEMLIVRO            
    FROM
      HD4DCDHH.VW_DOCU_NAC 
    WHERE  
      nf= :numdoc 
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByNumdoc(String numdoc);


}
