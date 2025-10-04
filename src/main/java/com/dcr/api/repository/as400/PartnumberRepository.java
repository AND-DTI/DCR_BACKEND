package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Partnumber;
import com.dcr.api.response.Interface.DCRModeloBase;
import com.dcr.api.response.Interface.DocumentoIMP;
import com.dcr.api.response.Interface.DocumentoNAC;



public interface PartnumberRepository extends JpaRepository<Partnumber, String>{




    @Query(value = """
    SELECT        
      itnbr as partnum, nrodig as numdoc, dtareg as emidoc,  
      nroadi as adicao, seqadi as itadicao, 
      ebqumc as siglaund, codinc as codinco, modal,
      aduitemusd0, aduitemusd, tx_usd, vucv, pesitn, psliq2,
      imdsc1 as partdesc, emp as empdoc, codncm as ncm,
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
      imdsc1 as partdesc, emp as empdoc, codncm as ncm,
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
      imdsc1 as partdesc, emp as empdoc, codncm as ncm,
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
      imdsc1 as partdesc, emp as empdoc, codncm as ncm,
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
      imdsc1 as partdesc, emp as empdoc, codncm as ncm,
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
    SELECT distinct 
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      fddsc1 partdesc, cia, emp empdoc, fdbclf as ncm
    FROM
      HD4DCDHH.VW_DOCU_NAC2 
    WHERE        
      nf= :numdoc and fdlitm= :partnum
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByDocnumAndPartnum(String numdoc, String partnum);


    @Query(value = """
    SELECT distinct
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      fddsc1 partdesc, cia, emp empdoc, fdbclf as ncm            
    FROM
      HD4DCDHH.VW_DOCU_NAC2 
    WHERE          
      nf= :numdoc and fdlitm like :itemLike
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByDocnumAndItemLike(String numdoc, String itemLike);
  
      
    @Query(value = """
    SELECT distinct
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      fddsc1 partdesc, cia, emp empdoc, fdbclf as ncm            
    FROM
      HD4DCDHH.VW_DOCU_NAC2 
    WHERE  
      nf= :numdoc 
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByNumdoc(String numdoc);


    @Query(value = """
    select * from (
    SELECT distinct
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      fddsc1 partdesc, cia, emp empdoc, fdbclf as ncm,
      ROW_NUMBER() Over(partition by nf, fdlitm) ln_nf_item, emijul          
    FROM
      HD4DCDHH.VW_DOCU_NAC2  
    WHERE 
      emijul >= (select dtajul from qryusers.datas 
                where grgcar = VARCHAR_FORMAT(CURRENT TIMESTAMP-:y years, 'YYYYMMDD'))
      and fdlitm= :partnum  
    )vw
    where ln_nf_item = 1
    order by emijul desc
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByPartnum(String partnum, Integer y);


    @Query(value = """
    select * from (
    SELECT 
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      fddsc1 partdesc, cia, emp empdoc, fdbclf as ncm,
      ROW_NUMBER() Over(partition by nf, fdlitm) ln_nf_item, emijul           
    FROM
      HD4DCDHH.VW_DOCU_NAC2  
    WHERE 
      emijul >= (select dtajul from qryusers.datas 
                where grgcar = VARCHAR_FORMAT(CURRENT TIMESTAMP-:y years, 'YYYYMMDD'))
      and fdlitm like :itemLike  
    )vw
    where ln_nf_item = 1
    order by emijul desc
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByItemLike(String itemLike, Integer y);


    @Query(value = """
    select * from (
    SELECT 
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      fddsc1 partdesc, cia, emp empdoc, fdbclf as ncm,
      ROW_NUMBER() Over(partition by nf, fdlitm) ln_nf_item, emijul           
    FROM
      HD4DCDHH.VW_DOCU_NAC2  
    WHERE 
      emijul <= (select dtajul from qryusers.datas 
                where grgcar = VARCHAR_FORMAT(CURRENT TIMESTAMP-:y years, 'YYYYMMDD'))
      and fdlitm= :partnum  
    )vw
    where ln_nf_item = 1
    order by emijul desc
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByPartnumElder(String partnum, Integer y);


    @Query(value = """
    select * from (
    SELECT 
      fdlitm PARTNUM, nf NUMDOC, ser SERDOC, emissao EMIDOC,  
      cnpjnf CNPJFOR, ienf IE, nf_prcunit VLRUNIT, nf_um SIGLAUND, 
      fddsc1 partdesc, cia, emp empdoc, fdbclf as ncm,
      ROW_NUMBER() Over(partition by nf, fdlitm) ln_nf_item, emijul            
    FROM
      HD4DCDHH.VW_DOCU_NAC2  
    WHERE 
      emijul <= (select dtajul from qryusers.datas 
                where grgcar = VARCHAR_FORMAT(CURRENT TIMESTAMP-:y years, 'YYYYMMDD'))
      and fdlitm like :itemLike  
    )vw
    where ln_nf_item = 1
    order by emijul desc
    """, nativeQuery = true)
    List<DocumentoNAC> findDocumentoNacByItemLikeElder(String itemLike, Integer y);





    @Query(value = """
    Select *	from  
    (SELECT substring(partnumpd,6,3) mdl_base, substring(partnumpd, 6, 9) as modelo, /*get ano in vw_produtos*/ 
            partnumpd prd_registro, tpprd, idmatriz, dcre, dtregistro, taxausd, 
            totalnac, totalimp, custotal, iireduzido, round((totalnac+totalimp)*taxausd,2) as vl_produto,
            ROWNUMBER() OVER(Partition by substring(partnumpd,6,3) order by totalimp desc) as ln_vlmax
      FROM   HD4DCDHH.DCRVIGEN x
      WHERE  tpprd <> 'PC'
    )dcr
    Where 
      mdl_base = :modeloBase
      and ln_vlmax = 1
    """, nativeQuery = true)
    DCRModeloBase findDCRModeloBase(String modeloBase);


    @Query(value = """
    Select *	from  
    (SELECT substring(partnumpd,6,3) mdl_base, substring(partnumpd, 6, 9) as modelo, /*get ano in vw_produtos*/ 
            partnumpd prd_registro, tpprd, idmatriz, dcre, dtregistro, taxausd, 
            totalnac, totalimp, custotal, iireduzido, round((totalnac+totalimp)*taxausd,2) as vl_produto,
            ROWNUMBER() OVER(Partition by substring(partnumpd,6,3) order by totalimp desc) as ln_vlmax
      FROM   HD4DCDHH.DCRVIGEN x
      WHERE  tpprd <> 'PC'
    )dcr
    Where 
      mdl_base = :modeloBase or
      dcre = :dcre or
      prd_registro = :partnumber or
      modelo like :modeloLike
    """, nativeQuery = true)
    List<DCRModeloBase> findDCRsModelo(String modeloBase, String dcre, String modeloLike, String partnumber);


    @Query(value = """
    Select *	from  
    (SELECT substring(partnumpd,6,3) mdl_base, substring(partnumpd, 6, 9) as modelo, /*get ano in vw_produtos*/ 
            partnumpd prd_registro, tpprd, idmatriz, dcre, dtregistro, taxausd, 
            totalnac, totalimp, custotal, iireduzido, round((totalnac+totalimp)*taxausd,2) as vl_produto,
            ROWNUMBER() OVER(Partition by substring(partnumpd,6,3) order by totalimp desc) as ln_vlmax
      FROM   HD4DCDHH.DCRVIGEN x
      WHERE  tpprd <> 'PC'
    )dcr
    Where 
      dcre = :numDcre        
    """, nativeQuery = true)
    List<DCRModeloBase> findDCRNum(String numDcre);


    @Query(value = """
    Select *	from  
    (SELECT substring(partnumpd,6,3) mdl_base, substring(partnumpd, 6, 9) as modelo, /*get ano in vw_produtos*/ 
            partnumpd prd_registro, tpprd, idmatriz, dcre, dtregistro, taxausd, 
            totalnac, totalimp, custotal, iireduzido, round((totalnac+totalimp)*taxausd,2) as vl_produto,
            ROWNUMBER() OVER(Partition by substring(partnumpd,6,3) order by totalimp desc) as ln_vlmax
      FROM   HD4DCDHH.DCRVIGEN x
      WHERE  tpprd <> 'PC'
    )dcr
    Where 
      prd_registro = :partnumber        
    """, nativeQuery = true)
    List<DCRModeloBase> findDCRPartnumber(String partnumber);


}
