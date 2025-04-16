package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.response.Interface.PendenciaASTEC;




public interface MtastecRepository extends JpaRepository<Mtastec, Integer>{


	Mtastec findByIdmatrizAndPartnumpd(Integer idmatriz, String partnumpd);


	String sqlbase_listProd = """
	SELECT 
		prd.IDMATRIZ, prd.PARTNUMPD, prd.DESCCOM, prd.DESCRFB, ppb.PRDDEST, ppb.PPBPRD, 
		prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT, prd.UNMED, prd.PRECO, prd.NCM as ncm_prd, prc.status as status_prc, /*6 ~ 17*/  
		pen.NUMPEND, pen.CDPEND, pen.OBSRESOL, pen.STATUS, tpe.descpend, tpe.obspend, pen.flex5flw as obsdetail, tpe.tpreg, /*18 ~ 25*/
		pen.PARTNUM, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC, /*26 ~ 30*/
		ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.ESPEC, ins.UNDCOM, ins.NCM as ncm_ins, ins.VLRUNIT as vlrUnit_ins, /*31 ~ 42*/     
		doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.CNPJFOR, doc.IE, doc.ADICAO, doc.ITADICAO, doc.VLRUNIT, doc.SIGLAUND, doc.CODINCO, doc.MODAL, /*43 ~ 54*/   
		doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.CNPJFOR2, doc.IE2, doc.ADICAO2, doc.ITADICAO2, doc.VLRUNIT2, doc.SIGLAUND2, doc.CODINCO2, doc.MODAL2, /*55 ~ 65*/
		doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3,  doc.CNPJFOR3, doc.IE3, doc.ADICAO3, doc.ITADICAO3, doc.VLRUNIT3, doc.SIGLAUND3, doc.CODINCO3, doc.MODAL3  /*66 ~ 76*/
	FROM 
		HD4DCDHH.MTASTEC  as PRD join 
		HD4DCDHH.DCRPROCC as PRC on prc.idmatriz= prd.idmatriz and prc.PARTNUMPD= prd.PARTNUMPD left join 
		HD4DCDHH.CADPPB   as PPB on ppb.partnumpd= prd.partnumpd and ppb.tpprd= 'PC' left join
		-- pendências:		
		HD4DCDHH.PENDASTEC as PEN on pen.idmatriz= prd.idmatriz left join
		HD4DCDHH.CADTPPEND as TPE on tpe.CDPEND = pen.CDPEND left join 
		HD4DCDHH.MTASTEINS as INS on ins.idmatriz= pen.idmatriz and ins.partnum= pen.partnum left join  
		HD4DCDHH.MTASTEDOC as DOC on doc.idmatriz= prd.idmatriz and doc.partnum= pen.partnum 
	""";
	

	@Query(value = "SELECT mta.IDMATRIZ, mta.PARTNUMPD, mta.DESCCOM, mta.DESCRFB, mta.UNMED, mta.ORIGPRD, mta.DTNECI, mta.PRIOURGEN, mta.PREVFAT, mta.PRIORESP, mta.PRIODTMNT, mta.PRIOHRMNT,\r\n"
		+ "		 ins.PARTNUM, ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
		+ "		 pend.NUMPEND, pend.CDPEND, pend.STATUS, mta.tpdcre \r\n"
		+ "FROM HD4DCDHH.MTASTEC AS MTA \r\n"
		+ "LEFT JOIN HD4DCDHH.MTASTEINS AS INS ON MTA.IDMATRIZ = INS.IDMATRIZ \r\n"
		+ "LEFT JOIN HD4DCDHH.PENDASTEC AS PEND ON PEND.IDMATRIZ = INS.IDMATRIZ " + 
			"WHERE mta.IDMATRIZ = :idmatriz", nativeQuery = true)
	List<Object[]> consultaPendencia(Integer idmatriz);
	  

	@Query(value = "SELECT prd.IDMATRIZ, prd.DESCCOM, prd.DESCRFB,\r\n"
		+ "	  							  prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
		+ "	  				ins.itmorg, ins.CDSPN, ins.EMCOMP, ins.ITTYP, ins.PARTDESC, ins.PARTNEW, ins.PARTNEWDSC, ins.PARTSUGDSC, ins.PARTSUGEST, ins.UNMSR	\r\n"
		+ "            FROM HD4DCDHH.MTASTEC as prd \r\n"
		+ "            LEFT JOIN HD4DCDHH.MTASTEINS ins ON prd.IDMATRIZ = ins.IDMATRIZ \r\n"
		+ "            WHERE prd.IDMATRIZ = :idmatriz", nativeQuery = true)
	List<Object[]> consultaDetalhe(Integer idmatriz);
	  

	@Query(value = "SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
			+ "	  							 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
			+ "	  				itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"
			+ "	  				cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN,\r\n"
			+ "	  				tpprd.DSCPOR, tpprd.DSCING,\r\n"
			+ "	  				ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC, ins.PARTDESC\r\n"
			+ "            FROM HD4DCDHH.MATRIPRD prd \r\n"
			+ "            LEFT JOIN HD4DCDHH.MATRIITM itm ON prd.IDMATRIZ = itm.IDMATRIZ\r\n"
			+ "            LEFT JOIN HD4DCDHH.CADCOR cor ON itm.CODCOR = cor.CODCOR \r\n"
			+ "            LEFT JOIN HD4DCDHH.MATRIINS AS INS ON ITM.IDMATRIZ = INS.IDMATRIZ\r\n"
			+ "            LEFT JOIN HD4DCDHH.CADTPPRD tpprd ON prd.TPPRD = tpprd.TPPRD \r\n"
			+ "            WHERE prd.IDMATRIZ = :idmatriz", nativeQuery = true)
	List<Object[]> consultaJoin(Integer idmatriz);
		
	
	@Query(value = "SELECT NUMDOC, SERDOC FROM HD4DCDHH.MATRIDOC WHERE IDMATRIZ = :idmatriz AND PARTNUM = :partnum AND TPDOC = :tpdoc", nativeQuery = true)
	List<Object[]> complementaPendenciaDoc(String idmatriz, String partnum, String tpdoc);
	  

	@Query(value = "SELECT DESCPEND, TPREG FROM HD4DCDHH.CADTPPEND WHERE CDPEND = :cdpend", nativeQuery = true)
	List<Object[]> complementaPendenciaDesc(String cdpend);
	
		
	@Query(value = 
	sqlbase_listProd + """
	WHERE 			
		prd.IDMATRIZ= :idmatriz and ( :partnum = '-' or pen.PARTNUM = :partnum)  
	Order by pen.numpend
	""", nativeQuery = true)
	//List<Object[]> consultaProdutoPendencia(Integer idmatriz, String partnumpd);
	List<PendenciaASTEC> consultaProdutoPendencia(Integer idmatriz, String partnum);

	@Query(value = 
	sqlbase_listProd + """
	WHERE 
		prd.IDMATRIZ= :idmatriz and pen.flex3flw= 'DIAG' and pen.status = 0 /*add field SUBTIPO*/
	""", nativeQuery = true)
	List<PendenciaASTEC> consultaProdutoPendenciaDiagnostico(Integer idmatriz);
	

	@Query(value = 
	sqlbase_listProd + """
	WHERE 
		prd.IDMATRIZ= :idmatriz and pen.flex3flw= :subtype and pen.status= :status /*add field SUBTIPO*/
	""", nativeQuery = true)
	List<PendenciaASTEC> consultaProdutoPendenciaBySubtype(Integer idmatriz, String subtype, int status);


	@Query(value = 
	sqlbase_listProd + """
	WHERE 
		prc.status in :status 
	Order by prd.idmatriz, pen.numpend
	""", nativeQuery = true)
	List<Object[]> consultaTodasAsPendencias(List<Integer> status);


	@Query(value = "SELECT COUNT(IDMATRIZ) FROM HD4DCDHH.PENDASTEC WHERE IDMATRIZ = :idmatriz AND STATUS = 0", nativeQuery = true)
	Integer countPendenciasNoPartnum(String idmatriz);
	

	@Query(value = "SELECT COUNT(IDMATRIZ) FROM HD4DCDHH.PENDASTEC WHERE IDMATRIZ = :idmatriz AND STATUS = 0 AND PARTNUM = :partnum", nativeQuery = true)
	Integer countPendencias(String idmatriz, String partnum);


	//j4 - added:
	@Query(value = "Select count(*) FROM HD4DCDHH.PENDASTEC WHERE IDMATRIZ= :idmatriz ", nativeQuery = true)
	Integer countPendenciasMatriz(String idmatriz);

	//j4 - added:
	@Query(value = "Select count(*) FROM HD4DCDHH.PENDASTEC WHERE IDMATRIZ= :idmatriz and STATUS= 0 and (PARTNUM= :partnum or :partnum= '-')", nativeQuery = true)
	Integer countPendenciasEmAberto(String idmatriz, String partnum);
	  

	@Query(value = "SELECT prd.IDMATRIZ, prd.PARTNUMPD, prd.DESCCOM, prd.DESCRFB, prd.UNMED, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
		+ "	        	pend.PARTNUM, pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS,\r\n"
		+ "	        	ins.PARTNUM, ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.NCM, ins.VLRUNIT, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
		+ "	        	doc.PARTNUM, doc.ADICAO, doc.ADICAO2, doc.ADICAO3, doc.CNPJFOR, doc.CNPJFOR2, doc.CNPJFOR3, doc.EMIDOC, doc.EMIDOC2, doc.EMIDOC3, doc.IE, doc.IE2, doc.IE3, doc.ITADICAO, doc.ITADICAO2, doc.ITADICAO3,\r\n"
		+ "	        	doc.NUMDOC, doc.NUMDOC2, doc.NUMDOC3, doc.PARTNUM, doc.SERDOC, doc.SERDOC2, doc.SERDOC3, doc.TPDOC, pend.idmatriz, proc.status, PPB.PPBPRD, PPB.PRDDEST, prd.tpdcre\r\n"
		+ "		  		     FROM HD4DCDHH.MTASTEC AS PRD \r\n"
		+ "		  		LEFT JOIN HD4DCDHH.MTASTEINS AS INS ON PRD.IDMATRIZ = INS.IDMATRIZ\r\n"
		+ "		  		LEFT JOIN HD4DCDHH.PENDASTEC AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ  AND INS.PARTNUM = PEND.PARTNUM  \r\n"
		+ "		  		LEFT JOIN HD4DCDHH.MTASTEDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ AND INS.PARTNUM = DOC.PARTNUM \r\n"
		+ "		  		LEFT JOIN HD4DCDHH.DCRPROCC AS PROC ON PRD.IDMATRIZ = PROC.IDMATRIZ AND PRD.PARTNUMPD = PROC.PARTNUMPD" 
		+ "	  		    LEFT JOIN HD4DCDHH.CADPPB AS PPB ON PPB.CDPRD = PRD.PARTNUMPD AND PPB.TPPRD = 'PC' " +
					" WHERE PROC.STATUS IN :status  AND EXISTS (SELECT pprod.* FROM HD4DCDHH.PENDPROD AS pprod WHERE PPROD.IDMATRIZ = prd.idmatriz AND PPROD.STATUS = 0 )", nativeQuery = true)
	List<Object[]> consultaTodasAsPendenciasSemLista(List<Integer> status);
	  
	  
	@Query(value = "SELECT * FROM HD4DCDHH.PENDASTEC a WHERE a.idmatriz = :idmatriz and a.partnumpd = :partnumpd and a.status = 0", nativeQuery = true)
	List<Pendastec> findPendenciasZero(Long idmatriz, String partnumpd);

}


//old consultaTodasAsPendencias:
/*@Query(value = "SELECT prd.IDMATRIZ, prd.PARTNUMPD, prd.DESCCOM, prd.DESCRFB, prd.UNMED, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
		+ "	        	pend.PARTNUM, pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS,\r\n"
		+ "	        	ins.PARTNUM, ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.NCM, ins.VLRUNIT, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
		+ "	        	doc.PARTNUM, doc.ADICAO, doc.ADICAO2, doc.ADICAO3, doc.CNPJFOR, doc.CNPJFOR2, doc.CNPJFOR3, doc.EMIDOC, doc.EMIDOC2, doc.EMIDOC3, doc.IE, doc.IE2, doc.IE3, doc.ITADICAO, doc.ITADICAO2, doc.ITADICAO3,\r\n"
		+ "	        	doc.NUMDOC, doc.NUMDOC2, doc.NUMDOC3, doc.PARTNUM, doc.SERDOC, doc.SERDOC2, doc.SERDOC3, doc.TPDOC, pend.idmatriz, proc.status, PPB.PPBPRD, PPB.PRDDEST, prd.tpdcre\r\n"
		+ "		  		FROM HD4DCDHH.MTASTEC AS PRD \r\n"
		+ "		  		 \r\n"
		+ "		  		LEFT JOIN HD4DCDHH.MTASTEINS AS INS ON PRD.IDMATRIZ = INS.IDMATRIZ  \r\n"
		+ "		  		LEFT JOIN HD4DCDHH.PENDASTEC AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ    \r\n"
		+ "		  		LEFT JOIN HD4DCDHH.MTASTEDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ   \r\n"
		+ "		  		LEFT JOIN HD4DCDHH.DCRPROCC AS PROC ON PRD.IDMATRIZ = PROC.IDMATRIZ"  
		+ "	  		    LEFT JOIN HD4DCDHH.CADPPB AS PPB ON PPB.CDPRD = PRD.PARTNUMPD AND PPB.TPPRD = 'PC' " +
					" WHERE PROC.STATUS IN :status", nativeQuery = true)
	List<Object[]> consultaTodasAsPendencias(List<Integer> status); */

/* old: consultaProdutoPendencia
 @Query(value = " SELECT \r\n"
	+ "	        	prd.IDMATRIZ, prd.PARTNUMPD, prd.DESCCOM, prd.DESCRFB, prd.UNMED, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
	+ "	        	pend.PARTNUM, pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS,\r\n"
	+ "	        	ins.PARTNUM, ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.NCM, ins.VLRUNIT, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
	+ "	        	doc.PARTNUM, doc.ADICAO, doc.ADICAO2, doc.ADICAO3, doc.CNPJFOR, doc.CNPJFOR2, doc.CNPJFOR3, doc.EMIDOC, doc.EMIDOC2, doc.EMIDOC3, doc.IE, doc.IE2, doc.IE3, doc.ITADICAO, doc.ITADICAO2, doc.ITADICAO3,\r\n"
	+ "	        	doc.NUMDOC, doc.NUMDOC2, doc.NUMDOC3, doc.PARTNUM, doc.SERDOC, doc.SERDOC2, doc.SERDOC3, doc.TPDOC, pend.idmatriz, PPB.PPBPRD, PPB.PRDDEST, prd.tpdcre \r\n"
	+ "	  		FROM HD4DCDHH.MTASTEC AS PRD \r\n"
	+ "	  		LEFT JOIN HD4DCDHH.PENDASTEC AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ   \r\n"
	+ "	  		LEFT JOIN HD4DCDHH.MTASTEINS AS INS ON PRD.IDMATRIZ = INS.IDMATRIZ AND PEND.PARTNUM = INS.PARTNUM  \r\n"
	+ "	  		LEFT JOIN HD4DCDHH.MTASTEDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ AND PEND.PARTNUM = DOC.PARTNUM" 
	+ "	  		LEFT JOIN HD4DCDHH.CADPPB AS PPB ON PPB.CDPRD = PRD.PARTNUMPD AND PPB.TPPRD = 'PC' " +
				" WHERE prd.IDMATRIZ = :idmatriz AND ( :partnum = '' OR PEND.PARTNUM = :partnum)", nativeQuery = true)
	List<Object[]> consultaProdutoPendencia(Integer idmatriz, String partnum);
 */