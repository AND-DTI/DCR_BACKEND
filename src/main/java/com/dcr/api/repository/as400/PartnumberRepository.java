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
      ebqumc as siglaund, codinc as codinco, modal,
      aduitemusd0, aduitemusd, tx_usd, vucv, pesitn, psliq2,
      f_coeff,                                       /* <== COEFF   [ VLRFRR / PSLIQ2 ]               */
      f_vlrtmr,                                      /* <== VLRTMR  [ PESITN * COEFF ]                */
      f_vlrunn,                                      /* <== VLRUNN  [ VUCV * TAXA ]                   */
      f_coefsr,                                      /* <== COEFSR  [ VLRSER / VLMERREAL ]            */
      f_vlrsgtr,                                     /* <== VLRSGTR [ VLRUNN * COEFSR ]               */      
      vlmern, f_vlmerreal, vlrtfr, vlrcif, vlmedi,   /* mercadoria origem/real/dolar/CIF/vl. merc. DI */
      vlrfrr, vlrser, vlfrdi, vlsgdi,                /* frete/seguro BR; frete/seguro DI              */
      cdmdme, cdmofr, cdmdse, vldimo                 /* moeda di/frete/seguro; vl. DI moeda original  */
    FROM
      HD4DCDHH.VW_DOCU_IMP2      
    WHERE  
      nrodig= :numdoc and itnbr= :partnum
    Order by int(nvl(dtareg,0)) desc
    """, nativeQuery = true)
    List<DocumentoIMP> findDocumentoImpByDocnumAndPartnum(String numdoc, String partnum);


    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      ebqumc as siglaund, codinc as codinco, modal,
      aduitemusd0, aduitemusd, tx_usd, vucv, pesitn, psliq2,
      f_coeff,                                       /* <== COEFF   [ VLRFRR / PSLIQ2 ]               */
      f_vlrtmr,                                      /* <== VLRTMR  [ PESITN * COEFF ]                */
      f_vlrunn,                                      /* <== VLRUNN  [ VUCV * TAXA ]                   */
      f_coefsr,                                      /* <== COEFSR  [ VLRSER / VLMERREAL ]            */
      f_vlrsgtr,                                     /* <== VLRSGTR [ VLRUNN * COEFSR ]               */      
      vlmern, f_vlmerreal, vlrtfr, vlrcif, vlmedi,   /* mercadoria origem/real/dolar/CIF/vl. merc. DI */
      vlrfrr, vlrser, vlfrdi, vlsgdi,                /* frete/seguro BR; frete/seguro DI              */
      cdmdme, cdmofr, cdmdse, vldimo                 /* moeda di/frete/seguro; vl. DI moeda original  */
    FROM
      HD4DCDHH.VW_DOCU_IMP2  
    WHERE 
      nrodig= :numdoc and itnbr like :itemLike
    Order by int(nvl(dtareg,0)) desc
      """, nativeQuery = true)
      List<DocumentoIMP> findDocumentoImpByDocnumAndItemLike(String numdoc, String itemLike);


    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      ebqumc as siglaund, codinc as codinco, modal,
      aduitemusd0, aduitemusd, tx_usd, vucv, pesitn, psliq2,
      f_coeff,                                       /* <== COEFF   [ VLRFRR / PSLIQ2 ]               */
      f_vlrtmr,                                      /* <== VLRTMR  [ PESITN * COEFF ]                */
      f_vlrunn,                                      /* <== VLRUNN  [ VUCV * TAXA ]                   */
      f_coefsr,                                      /* <== COEFSR  [ VLRSER / VLMERREAL ]            */
      f_vlrsgtr,                                     /* <== VLRSGTR [ VLRUNN * COEFSR ]               */      
      vlmern, f_vlmerreal, vlrtfr, vlrcif, vlmedi,   /* mercadoria origem/real/dolar/CIF/vl. merc. DI */
      vlrfrr, vlrser, vlfrdi, vlsgdi,                /* frete/seguro BR; frete/seguro DI              */
      cdmdme, cdmofr, cdmdse, vldimo                 /* moeda di/frete/seguro; vl. DI moeda original  */
    FROM
      HD4DCDHH.VW_DOCU_IMP2  
    WHERE 
      itnbr= :partnum 
    Order by int(nvl(dtareg,0)) desc 
    """, nativeQuery = true)
    List<DocumentoIMP> findDocumentoImpByPartnum(String partnum);
    /* OLD:
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      round(vlradu/qtditn,7) as vlrunit, 'UN' as siglaund, codinc as codinco, modal   
    FROM
      HD4DCDHH.VW_DOCU_IMP_LT        
    */


    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      ebqumc as siglaund, codinc as codinco, modal,
      aduitemusd0, aduitemusd, tx_usd, vucv, pesitn, psliq2,
      f_coeff,                                       /* <== COEFF   [ VLRFRR / PSLIQ2 ]               */
      f_vlrtmr,                                      /* <== VLRTMR  [ PESITN * COEFF ]                */
      f_vlrunn,                                      /* <== VLRUNN  [ VUCV * TAXA ]                   */
      f_coefsr,                                      /* <== COEFSR  [ VLRSER / VLMERREAL ]            */
      f_vlrsgtr,                                     /* <== VLRSGTR [ VLRUNN * COEFSR ]               */      
      vlmern, f_vlmerreal, vlrtfr, vlrcif, vlmedi,   /* mercadoria origem/real/dolar/CIF/vl. merc. DI */
      vlrfrr, vlrser, vlfrdi, vlsgdi,                /* frete/seguro BR; frete/seguro DI              */
      cdmdme, cdmofr, cdmdse, vldimo                 /* moeda di/frete/seguro; vl. DI moeda original  */
    FROM
      HD4DCDHH.VW_DOCU_IMP2      
    WHERE 
      itnbr like :itemLike
    Order by int(nvl(dtareg,0)) desc
    """, nativeQuery = true)
    List<DocumentoIMP> findDocumentoImpByItemLike(String itemLike);


    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      ebqumc as siglaund, codinc as codinco, modal,
      aduitemusd0, aduitemusd, tx_usd, vucv, pesitn, psliq2,
      f_coeff,                                       /* <== COEFF   [ VLRFRR / PSLIQ2 ]               */
      f_vlrtmr,                                      /* <== VLRTMR  [ PESITN * COEFF ]                */
      f_vlrunn,                                      /* <== VLRUNN  [ VUCV * TAXA ]                   */
      f_coefsr,                                      /* <== COEFSR  [ VLRSER / VLMERREAL ]            */
      f_vlrsgtr,                                     /* <== VLRSGTR [ VLRUNN * COEFSR ]               */      
      vlmern, f_vlmerreal, vlrtfr, vlrcif, vlmedi,   /* mercadoria origem/real/dolar/CIF/vl. merc. DI */
      vlrfrr, vlrser, vlfrdi, vlsgdi,                /* frete/seguro BR; frete/seguro DI              */
      cdmdme, cdmofr, cdmdse, vldimo                 /* moeda di/frete/seguro; vl. DI moeda original  */
    FROM
      HD4DCDHH.VW_DOCU_IMP2   
    WHERE  
      nrodig= :numdoc 
    Order by int(nvl(dtareg,0)) desc
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
        nf= :numdoc and fdlitm like :itemLike
      """, nativeQuery = true)
      List<DocumentoNAC> findDocumentoNacByDocnumAndItemLike(String numdoc, String itemLike);
  
  
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
        fdlitm like :itemLike  
      """, nativeQuery = true)
      List<DocumentoNAC> findDocumentoNacByItemLike(String itemLike);
  
  
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
