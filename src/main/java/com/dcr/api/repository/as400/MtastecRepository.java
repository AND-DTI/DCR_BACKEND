package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.as400.Pendprod;

public interface MtastecRepository extends JpaRepository<Mtastec, Integer>{

	Mtastec findByIdmatrizAndPartnumpd(Integer idmatriz, String partnumpd);
	
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
			  
			  
	  @Query(value = "SELECT prd.IDMATRIZ, prd.PARTNUMPD, prd.DESCCOM, prd.DESCRFB, prd.UNMED, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
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
	  List<Object[]> consultaTodasAsPendencias(List<Integer> status);
	  
	  @Query(value = "SELECT COUNT(IDMATRIZ) FROM HD4DCDHH.PENDASTEC WHERE IDMATRIZ = :idmatriz AND STATUS = 0", nativeQuery = true)
	  Integer countPendenciasNoPartnum(String idmatriz);
	  
	  @Query(value = "SELECT COUNT(IDMATRIZ) FROM HD4DCDHH.PENDASTEC WHERE IDMATRIZ = :idmatriz AND STATUS = 0 AND PARTNUM = :partnum", nativeQuery = true)
	  Integer countPendencias(String idmatriz, String partnum);
	  
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
