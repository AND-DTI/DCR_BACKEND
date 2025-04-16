package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.response.Interface.PendenciaINT;
import com.dcr.api.response.Interface.PendenciaProdINT;



public interface MatriprdRepository  extends JpaRepository<Matriprd, Integer>{

	String sqlbase_listProd = """
	SELECT 
		prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, ppb.PPBPRD, prd.TPPRD, prd.PROTOT, prd.SPECIAL,                      /*0  ~ 9 */
		prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,                                      /*10 ~ 17*/
		itm.PARTNUMPD, itm.CODCOR, cor.CORPT, itm.PARTDESC, itm.UNMED, itm.PRECO, itm.NCM ncmprd, itm.PRIOCOR, prc.status statusproc, cor.CDBEJ, cor.CORENG, cor.TPPIN,     /*18 ~ 29*/
		pen.NUMPEND, pen.CDPEND, pen.OBSRESOL, pen.STATUS, tpe.descpend, tpe.obspend, pen.flex5flw as obsdetail, tpe.tpreg,                               /*30 ~ 37*/
		pen.PARTNUM, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,                                                                         /*38 ~ 42*/
		ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.ESPEC, ins.UNDCOM, ins.NCM ncmins, ins.VLRUNIT,        /*43 ~ 54*/     
		doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.CNPJFOR, doc.IE, doc.ADICAO, doc.ITADICAO, doc.VLRUNIT, doc.SIGLAUND, doc.CODINCO, doc.MODAL,  /*55 ~ 66*/   
		doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.CNPJFOR2, doc.IE2, doc.ADICAO2, doc.ITADICAO2, doc.VLRUNIT2, doc.SIGLAUND2, doc.CODINCO2, doc.MODAL2,  /*67 ~ 77*/
		doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3,  doc.CNPJFOR3, doc.IE3, doc.ADICAO3, doc.ITADICAO3, doc.VLRUNIT3, doc.SIGLAUND3, doc.CODINCO3, doc.MODAL3  /*78 ~ 88*/  
	FROM 
		HD4DCDHH.MATRIPRD as PRD join   
		HD4DCDHH.MATRIITM as ITM on itm.IDMATRIZ= prd.IDMATRIZ join 
		HD4DCDHH.DCRPROCC as PRC on prc.IDMATRIZ= prd.IDMATRIZ and prc.PARTNUMPD= itm.PARTNUMPD left join
		HD4DCDHH.CADCOR   as COR on cor.CODCOR= itm.CODCOR left join
		HD4DCDHH.CADPPB   as PPB on ppb.PARTNUMPD= itm.PARTNUMPD 
		-- pendencias da cor:
		left join
		HD4DCDHH.PENDPROD as PEN on pen.IDMATRIZ= prd.IDMATRIZ and pen.PARTNUMPD= itm.PARTNUMPD left join
		HD4DCDHH.CADTPPEND as TPE on tpe.CDPEND = pen.CDPEND left join 
		HD4DCDHH.MATRIINS as INS on ins.IDMATRIZ= pen.IDMATRIZ and ins.PARTNUMPD= pen.PARTNUMPD and ins.partnum= pen.partnum left join  
		HD4DCDHH.MATRIDOC as DOC on doc.IDMATRIZ= prd.IDMATRIZ and doc.PARTNUMPD= pen.PARTNUMPD and doc.partnum= pen.partnum 	
	""";

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
	  
	  
	@Query(value = """						
	SELECT  
	  prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, 
	  prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT, 
	  itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.NCM, itm.PRIOCOR, 
	  cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN, 
	  tpprd.DSCPOR, tpprd.DSCING, usr.NAME 
	FROM  
	  HD4DCDHH.MATRIPRD prd join 
	  HD4DCDHH.MATRIITM itm ON prd.IDMATRIZ = itm.IDMATRIZ 
	  LEFT JOIN HD4DCDHH.ACCUSER usr ON prd.PRIORESP = usr.USERNAME 
	  LEFT JOIN HD4DCDHH.CADCOR cor ON itm.CODCOR = cor.CODCOR 
	  LEFT JOIN HD4DCDHH.CADTPPRD tpprd ON prd.TPPRD = tpprd.TPPRD 
	WHERE 
	  prd.TPPRD IN :tpprdList --and prd.IDMATRIZ in(4, 24)
	order by prd.IDMATRIZ
	""", nativeQuery = true)
	List<Object[]> consultaByTpprd(List<String> tpprdList);
	  
	  
	@Query(value = 
	sqlbase_listProd + """
	WHERE 
		prd.IDMATRIZ= :idmatriz and itm.PARTNUMPD= :partnumpd	  
	""", nativeQuery = true)
    List<PendenciaProdINT> consultaProdutoPendencia(Integer idmatriz, String partnumpd);
	//List<Object[]> consultaProdutoPendencia(Integer idmatriz, String partnumpd);
		  

	@Query(value = 
	sqlbase_listProd + """
	WHERE 
		prd.IDMATRIZ= :idmatriz and itm.PARTNUMPD= :partnumpd and pen.flex3flw= 'DIAG' and pen.status = 0/*add field SUBTIPO*/
	""", nativeQuery = true)
	List<Object[]> consultaProdutoPendenciaDiagnostico(Integer idmatriz, String partnumpd);


	@Query(value = 
	sqlbase_listProd + """
	WHERE 
		prd.IDMATRIZ= :idmatriz and itm.partnumpd= :partnumpd and pen.flex3flw= :subtype and pen.status= :status /*add field SUBTIPO*/
	""", nativeQuery = true)
	List<PendenciaINT> consultaProdutoPendenciaBySubtype(Integer idmatriz, String partnumpd, String subtype, int status);

	

	@Query(value = 
	sqlbase_listProd + """
	WHERE 
	  prc.STATUS IN :status        /*@@teste --> or prd.idmatriz=94 */
	Order by prd.IDMATRIZ desc
	""", nativeQuery = true)
	List<Object[]> consultaTodasAsPendencias(List<Integer> status);
		  
		  
	@Query(value = 
	"SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
	+ "					 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
	+ "		itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"

	+ "		pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS, \r\n"
	+ "		doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3, proc.status, \r\n"
	+ "		cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN, pend.PARTNUM, pend.IDMATRIZ, ITM.IDMATRIZ, DOC.PARTNUM,  \r\n"
	+ "		DOC.CNPJFOR, DOC.IE, DOC.ADICAO, DOC.ITADICAO, DOC.CNPJFOR2, DOC.IE2, DOC.ADICAO2, DOC.ITADICAO2, DOC.CNPJFOR3, DOC.IE3, DOC.ADICAO3, DOC.ITADICAO3, PROC.STATUS \r\n"
	+ "     FROM HD4DCDHH.MATRIPRD AS PRD\r\n"
	+ "LEFT JOIN HD4DCDHH.MATRIITM AS ITM ON PRD.IDMATRIZ = ITM.IDMATRIZ \r\n"
	
	+ "LEFT JOIN HD4DCDHH.PENDPROD AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ  AND ITM.PARTNUMPD = PEND.PARTNUMPD  \r\n" 
	+ "LEFT JOIN HD4DCDHH.MATRIDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ AND ITM.PARTNUMPD = DOC.PARTNUMPD  \r\n" 
	+ "LEFT JOIN HD4DCDHH.CADCOR AS cor ON ITM.CODCOR = cor.CODCOR \r\n"
	+ "LEFT JOIN HD4DCDHH.DCRPROCC AS PROC ON PRD.IDMATRIZ = PROC.IDMATRIZ AND ITM.PARTNUMPD = PROC.PARTNUMPD " +
			"WHERE PROC.STATUS IN :status", nativeQuery = true)
	List<Object[]> consultaTodasAsPendenciasSemLista(List<Integer> status);
	
	
	@Query(value = "SELECT COUNT(IDMATRIZ) FROM HD4DCDHH.PENDPROD WHERE IDMATRIZ = :idmatriz AND STATUS = 0 AND PARTNUMPD = :partnumpd", nativeQuery = true)
	Integer countPendencias(String idmatriz, String partnumpd);
	
	@Query(value = "SELECT COUNT(IDMATRIZ) FROM HD4DCDHH.PENDPROD WHERE IDMATRIZ = :idmatriz AND STATUS = 0", nativeQuery = true)
	Integer countPendenciasNoPartnum(String idmatriz);

	//j4 - added:
	@Query(value = "Select count(*) FROM HD4DCDHH.PENDPROD WHERE IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd", nativeQuery = true)
	Integer countPendenciasCor(String idmatriz, String partnumpd);

	//j4 - added:
	@Query(value = "Select count(*) FROM HD4DCDHH.PENDPROD WHERE IDMATRIZ= :idmatriz and STATUS= 0 and (PARTNUMPD= :partnumpd or :partnumpd= '-')", nativeQuery = true)
	Integer countPendenciasCorEmAberto(String idmatriz, String partnumpd);
	
	@Query(value = "SELECT NUMDOC, SERDOC FROM HD4DCDHH.MATRIDOC WHERE IDMATRIZ = :idmatriz AND PARTNUM = :partnum AND TPDOC = :tpdoc", nativeQuery = true)
	List<Object[]> complementaPendenciaDoc(String idmatriz, String partnum, String tpdoc);
	
	@Query(value = "SELECT DESCPEND, TPREG FROM HD4DCDHH.CADTPPEND WHERE CDPEND = :cdpend", nativeQuery = true)
	List<Object[]> complementaPendenciaDesc(String cdpend);
	
	@Query(value = "SELECT prd.* FROM HD4DCDHH.MATRIPRD AS prd \r\n"
	+ "    LEFT JOIN  HD4DCDHH.DCRPROCC AS procc ON prd.IDMATRIZ = procc.IDMATRIZ \r\n"
	+ "    WHERE prd.ORIGPRD = 'MANUAL' AND procc.IDMATRIZ IS NULL", nativeQuery = true)
	List<Object[]> getMatriprdWithNotInDcrprocc();
		
	@Query(value = "SELECT pend.* FROM HD4DCDHH.PENDPROD as pend\r\n"
	+ "    LEFT JOIN HD4DCDHH.CADTPPEND AS cad ON pend.CDPEND = cad.CDPEND \r\n"
	+ "    WHERE pend.status = 0 AND cad.TPREG = 'C'", nativeQuery = true)
	List<Object[]> getPendenciasCadastro();

}


/*

OLD sql consultaTodasAsPendencias - pesado com itens - estoura memória - trazer só itens com pendencia:
SELECT 
  prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, 
  prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,
  itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,
  ins.PARTNUM, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,
  pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS, 
  doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3, proc.status, 
  cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN, pend.PARTNUM, pend.IDMATRIZ, ITM.IDMATRIZ, DOC.PARTNUM,  
  DOC.CNPJFOR, DOC.IE, DOC.ADICAO, DOC.ITADICAO, DOC.CNPJFOR2, DOC.IE2, DOC.ADICAO2, DOC.ITADICAO2, DOC.CNPJFOR3, DOC.IE3, DOC.ADICAO3, DOC.ITADICAO3 
FROM 
  HD4DCDHH.MATRIPRD AS PRD JOIN 
  HD4DCDHH.MATRIITM AS ITM ON PRD.IDMATRIZ = ITM.IDMATRIZ JOIN 
  HD4DCDHH.MATRIINS AS INS ON ITM.IDMATRIZ = INS.IDMATRIZ and itm.PARTNUMPD= ins.PARTNUMPD LEFT JOIN 
  HD4DCDHH.PENDPROD AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ LEFT JOIN 
  HD4DCDHH.MATRIDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ LEFT JOIN 
  HD4DCDHH.CADCOR AS cor ON ITM.CODCOR = cor.CODCOR LEFT JOIN 
  HD4DCDHH.DCRPROCC AS PROC ON PRD.IDMATRIZ = PROC.IDMATRIZ 
WHERE 
  PROC.STATUS IN (0, 1)


OLD sql consultaProdutoPendencia:
"SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
	  		+ "					 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
	  		+ "		itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"
	  		+ "		ins.PARTNUM, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
	  		+ "		pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS, \r\n"
	  		+ "		doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3, itm.IDMATRIZ, pend.PARTNUM, pend.IDMATRIZ, DOC.PARTNUM,  \r\n"
	  		+ "		DOC.CNPJFOR, DOC.IE, DOC.ADICAO, DOC.ITADICAO, DOC.CNPJFOR2, DOC.IE2, DOC.ADICAO2, DOC.ITADICAO2, DOC.CNPJFOR3, DOC.IE3, DOC.ADICAO3, DOC.ITADICAO3, INS.PARTNUMPD, PEND.PARTNUMPD, cor.CORPT, tppend.OBSPEND, pend.OBSRESOL, doc.partnumpd  \r\n"
	  		+ "FROM HD4DCDHH.MATRIPRD AS PRD\r\n"
	  		+ "LEFT JOIN HD4DCDHH.PENDPROD AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ  \r\n"
	  		+ "LEFT JOIN HD4DCDHH.MATRIITM AS ITM ON PRD.IDMATRIZ = ITM.IDMATRIZ AND PEND.PARTNUMPD = ITM.PARTNUMPD \r\n"
	  		+ "LEFT JOIN HD4DCDHH.MATRIINS AS INS ON ITM.IDMATRIZ = INS.IDMATRIZ AND PEND.PARTNUMPD = INS.PARTNUMPD \r\n"
	  		+ "LEFT JOIN HD4DCDHH.CADCOR AS cor ON ITM.CODCOR = cor.CODCOR \r\n"
	  		+ "LEFT JOIN HD4DCDHH.CADTPPEND AS tppend ON PEND.CDPEND = tppend.CDPEND \r\n"
	  		+ "LEFT JOIN HD4DCDHH.MATRIDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ AND PEND.PARTNUMPD = DOC.PARTNUMPD " +
	              "WHERE prd.IDMATRIZ = :idmatriz AND ( :partnumpd = '' OR PEND.PARTNUMPD = :partnumpd)"
		  
 
OLD sql consultaTodasAsPendenciasSemLista:
"SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
+ "					 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
+ "		itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"

+ "		pend.NUMPEND, pend.CDPEND, pend.OBSRESOL, pend.STATUS, \r\n"
+ "		doc.TPDOC, doc.NUMDOC, doc.SERDOC, doc.EMIDOC, doc.NUMDOC2, doc.SERDOC2, doc.EMIDOC2, doc.NUMDOC3, doc.SERDOC3, doc.EMIDOC3, proc.status, \r\n"
+ "		cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN, pend.PARTNUM, pend.IDMATRIZ, ITM.IDMATRIZ, DOC.PARTNUM,  \r\n"
+ "		DOC.CNPJFOR, DOC.IE, DOC.ADICAO, DOC.ITADICAO, DOC.CNPJFOR2, DOC.IE2, DOC.ADICAO2, DOC.ITADICAO2, DOC.CNPJFOR3, DOC.IE3, DOC.ADICAO3, DOC.ITADICAO3, PROC.STATUS \r\n"
+ "     FROM HD4DCDHH.MATRIPRD AS PRD\r\n"
+ "LEFT JOIN HD4DCDHH.MATRIITM AS ITM ON PRD.IDMATRIZ = ITM.IDMATRIZ \r\n"

+ "LEFT JOIN HD4DCDHH.PENDPROD AS PEND ON PRD.IDMATRIZ = PEND.IDMATRIZ  AND ITM.PARTNUMPD = PEND.PARTNUMPD  \r\n" 
+ "LEFT JOIN HD4DCDHH.MATRIDOC AS DOC ON PRD.IDMATRIZ = DOC.IDMATRIZ AND ITM.PARTNUMPD = DOC.PARTNUMPD  \r\n" 
+ "LEFT JOIN HD4DCDHH.CADCOR AS cor ON ITM.CODCOR = cor.CODCOR \r\n"
+ "LEFT JOIN HD4DCDHH.DCRPROCC AS PROC ON PRD.IDMATRIZ = PROC.IDMATRIZ AND ITM.PARTNUMPD = PROC.PARTNUMPD " +
		"WHERE PROC.STATUS IN :status"
	
	
*/