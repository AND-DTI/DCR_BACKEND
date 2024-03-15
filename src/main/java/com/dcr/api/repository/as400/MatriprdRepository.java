package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Matriprd;

public interface MatriprdRepository  extends JpaRepository<Matriprd, Integer>{

	  
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
	  
	  @Query(value = "SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
	  		+ "	  		 							 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
	  		+ "	  		 				itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"
	  		+ "	  		 				cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN,\r\n"
	  		+ "	  		 				tpprd.DSCPOR, tpprd.DSCING, usr.NAME\r\n"
	  		+ "	  		          FROM HD4DCDHH.MATRIPRD prd \r\n"
	  		+ "					  LEFT JOIN HD4DCDHH.ACCUSER usr ON prd.PRIORESP = usr.USERNAME \r\n"
	  		+ "	  		          LEFT JOIN HD4DCDHH.MATRIITM itm ON prd.IDMATRIZ = itm.IDMATRIZ\r\n"
	  		+ "	  		          LEFT JOIN HD4DCDHH.CADCOR cor ON itm.CODCOR = cor.CODCOR \r\n"
	  		+ "	  		          LEFT JOIN HD4DCDHH.CADTPPRD tpprd ON prd.TPPRD = tpprd.TPPRD \r\n"
	  		+ "	  		          WHERE prd.TPPRD IN :tpprdList", nativeQuery = true)
	  List<Object[]> consultaByTpprd(List<String> tpprdList);
	  
	  @Query(value = "SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
	  		+ "					 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
	  		+ "		itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"
	  		+ "		ins.PARTNUM, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
	  		+ "		pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS, \r\n"
	  		+ "		doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3, itm.IDMATRIZ, pend.PARTNUM, pend.IDMATRIZ, DOC.PARTNUM,  \r\n"
	  		+ "		DOC.CNPJFOR, DOC.IE, DOC.ADICAO, DOC.ITADICAO, DOC.CNPJFOR2, DOC.IE2, DOC.ADICAO2, DOC.ITADICAO2, DOC.CNPJFOR3, DOC.IE3, DOC.ADICAO3, DOC.ITADICAO3, INS.PARTNUMPD, PEND.PARTNUMPD, cor.CORPT, tppend.OBSPEND, pend.OBSRESOL, doc.partnumpd  \r\n"
	  		+ "FROM HD4DCDHH.MATRIPRD AS PRD\r\n"
	  		+ "LEFT JOIN HD4DCDHH.MATRIITM AS ITM ON PRD.IDMATRIZ = ITM.IDMATRIZ \r\n"
	  		+ "LEFT JOIN HD4DCDHH.MATRIINS AS INS ON ITM.IDMATRIZ = INS.IDMATRIZ \r\n"
	  		+ "LEFT JOIN HD4DCDHH.PENDPROD AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ  \r\n" 
	  		+ "LEFT JOIN HD4DCDHH.CADCOR AS cor ON ITM.CODCOR = cor.CODCOR \r\n"
	  		+ "LEFT JOIN HD4DCDHH.CADTPPEND AS tppend ON PEND.CDPEND = tppend.CDPEND \r\n"
	  		+ "LEFT JOIN HD4DCDHH.MATRIDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ " +
	              "WHERE prd.IDMATRIZ = :idmatriz", nativeQuery = true)
		  List<Object[]> consultaProdutoPendencia(Integer idmatriz);
		  
		  @Query(value = "SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
			  		+ "					 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
			  		+ "		itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"
			  		+ "		ins.PARTNUM, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
			  		+ "		pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS, \r\n"
			  		+ "		doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3, proc.status, \r\n"
			  		+ "		cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN, pend.PARTNUM, pend.IDMATRIZ, ITM.IDMATRIZ, DOC.PARTNUM,  \r\n"
			  		+ "		DOC.CNPJFOR, DOC.IE, DOC.ADICAO, DOC.ITADICAO, DOC.CNPJFOR2, DOC.IE2, DOC.ADICAO2, DOC.ITADICAO2, DOC.CNPJFOR3, DOC.IE3, DOC.ADICAO3, DOC.ITADICAO3 \r\n"
			  		+ "     FROM HD4DCDHH.MATRIPRD AS PRD\r\n"
			  		+ "LEFT JOIN HD4DCDHH.MATRIITM AS ITM ON PRD.IDMATRIZ = ITM.IDMATRIZ \r\n"
			  		+ "LEFT JOIN HD4DCDHH.MATRIINS AS INS ON ITM.IDMATRIZ = INS.IDMATRIZ \r\n"
			  		+ "LEFT JOIN HD4DCDHH.PENDPROD AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ  \r\n" 
			  		+ "LEFT JOIN HD4DCDHH.MATRIDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ \r\n" 
			  		+ "LEFT JOIN HD4DCDHH.CADCOR AS cor ON ITM.CODCOR = cor.CODCOR \r\n"
			  		+ "LEFT JOIN HD4DCDHH.DCRPROCC AS PROC ON PRD.IDMATRIZ = PROC.IDMATRIZ " +
			              "WHERE PROC.STATUS IN :status", nativeQuery = true)
		  List<Object[]> consultaTodasAsPendencias(List<Integer> status);
		  
		  
		  @Query(value = "SELECT COUNT(IDMATRIZ) FROM HD4DCDHH.PENDPROD WHERE IDMATRIZ = :idmatriz AND STATUS = 0", nativeQuery = true)
		  Integer countPendencias(String idmatriz);
		  
		  @Query(value = "SELECT NUMDOC, SERDOC FROM HD4DCDHH.MATRIDOC WHERE IDMATRIZ = :idmatriz AND PARTNUM = :partnum AND TPDOC = :tpdoc", nativeQuery = true)
		  List<Object[]> complementaPendenciaDoc(String idmatriz, String partnum, String tpdoc);
		  
		  @Query(value = "SELECT DESCPEND, TPREG FROM HD4DCDHH.CADTPPEND WHERE CDPEND = :cdpend", nativeQuery = true)
		  List<Object[]> complementaPendenciaDesc(String cdpend);
}
