package com.dcr.api.repository.as400;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.projection.ResumoProjection;




public interface DcrproccRepository extends JpaRepository<Dcrprocc, DcrproccKey>{


	@Query(value = "SELECT \r\n"
			+ "    p.idmatriz,\r\n"
			+ "    TRIM(p.partnumpd) AS partnumpd,\r\n"
			+ "    TRIM(p.tpprd) AS tpprd,\r\n"
			+ "    p.status,\r\n"
			+ "    regra.cnpjemi,\r\n"
			+ "    TRIM(regra.razsoc) AS razsoc,\r\n"
			+ "    taxausd,\r\n"
			+ "    totalnac,\r\n"
			+ "    totalimp,\r\n"
			+ "    custotal,\r\n"
			+ "    coefred,\r\n"
			+ "    iitotal,\r\n"
			+ "    iireduzido,\r\n"
			+ "    dcr.protdcre,  \r\n"
			+ "    dcr.tpenvio,\r\n"
			+ "    dcr.dtenvio,\r\n"
			+ "    dcr.hrenvio,\r\n"
			+ "    dcr.repreenvio,\r\n"
			+ "    reg0.PESO, \r\n"
			+ "    reg0.SALARIOS, \r\n"
			+ "    reg0.UNDCOM, \r\n"
			+ "    reg0.TPDCRE, \r\n" 
			+ "    reg0.NCM, \r\n" 
			+ "    reg0.ENCARGOS, \r\n"
			+ "    dcr.status as protostatus\r\n"
			+ "FROM \r\n"
			+ "    HD4DCDHH.DCRPROCC p\r\n"
			+ "JOIN \r\n"
			+ "    HD4DCDHH.DCRREG0 r0 ON r0.idmatriz = p.idmatriz \r\n"
			+ "                         AND r0.partnumpd = p.partnumpd \r\n"
			+ "                         AND r0.tpprd = p.tpprd\r\n"
			+ "LEFT JOIN \r\n"
			+ "    HD4DCDHH.DCRREGRA regra ON regra.stsconfig = 1\r\n"
			+ "LEFT JOIN \r\n"
			+ "HD4DCDHH.DCRREG0 AS reg0 ON reg0.idmatriz = p.idmatriz AND reg0.partnumpd = p.partnumpd AND reg0.tpprd = p.tpprd \r\n"
			+ "LEFT JOIN \r\n"
			+ "    (SELECT * \r\n"
			+ "     FROM HD4DCDHH.DCRPROTO AS dcr \r\n"
			+ "     WHERE dcr.idmatriz = (SELECT idmatriz FROM HD4DCDHH.DCRPROCC WHERE idmatriz = :idmatriz AND partnumpd = :partnumpd) \r\n"
			+ "       AND dcr.tpprd = (SELECT tpprd FROM HD4DCDHH.DCRPROCC WHERE idmatriz = :idmatriz AND partnumpd = :partnumpd) \r\n"
			+ "       AND dcr.partnumpd = (SELECT partnumpd FROM HD4DCDHH.DCRPROCC WHERE idmatriz = :idmatriz AND partnumpd = :partnumpd)\r\n"
			+ "     ORDER BY TIMESTAMP_FORMAT(dcr.ITAUDDT || ' ' || dcr.ITAUDHR, 'YYYYMMDD HH24:MI:SS') DESC \r\n"
			+ "     FETCH FIRST 1 ROW ONLY) dcr ON p.idmatriz = dcr.idmatriz\r\n"
			+ "WHERE \r\n"
			+ "    p.idmatriz = :idmatriz \r\n"
			+ "    AND p.partnumpd = :partnumpd", nativeQuery = true)
	Optional<ResumoProjection> getResumo(Long idmatriz, String partnumpd);


	@Transactional
	@Modifying
	@Query(value = "delete from HD4DCDHH.DCRREG0 where IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd"
	, nativeQuery = true)
    int deleteRegistro_0(Integer idmatriz, String partnumpd);


	@Transactional
	@Modifying
	@Query(value = "delete from HD4DCDHH.DCRREG1 where IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd"
	, nativeQuery = true)
    int deleteRegistro_1(Integer idmatriz, String partnumpd);


	@Transactional
	@Modifying
	@Query(value = "delete from HD4DCDHH.DCRREG2 where IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd"
	, nativeQuery = true)
    int deleteRegistro_2(Integer idmatriz, String partnumpd);	


	@Transactional
	@Modifying
	@Query(value = "delete from HD4DCDHH.DCRREG3 where IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd"
	, nativeQuery = true)
    int deleteRegistro_3(Integer idmatriz, String partnumpd);	
	

	@Transactional
	@Modifying
	@Query(value = "delete from HD4DCDHH.DCRREG4 where IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd"
	, nativeQuery = true)
    int deleteRegistro_4(Integer idmatriz, String partnumpd);		


	@Transactional
	@Modifying
	@Query(value = "delete from HD4DCDHH.DCRREG9 where IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd"
	, nativeQuery = true)
    int deleteRegistro_9(Integer idmatriz, String partnumpd);	


	@Transactional
	@Modifying
	@Query(value = """
	insert into HD4DCDHH.DCRREG0
	(IDMATRIZ, PARTNUMPD, TPPRD, IDREG, CNPJ, /*CPFRL,*/ PPB, DENOM, NCM, UNDCOM, PESO, 
		SALARIOS, ENCARGOS, TPDCRE, DCRANT, PROCRETIF, VRSPGD, ORIGDCR, TPCOEF, 
		ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR
	)
	select 
		prd.idmatriz, itm.partnumpd, prd.tpprd, '0', conf.cnpjemi, ppb.ppbprd, prd.descrfb, itm.ncm, itm.undcom, 
		(select sum(WEGHT) from HD4DCDHH.MATRIINS x where x.idmatriz= prd.idmatriz), /* --> criar campo e salvar quando explodir estrutura */
		0, 0, prd.tpdcre, '', '', '', 2, 'F',
		'DCRBACKEND', CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)), 
		VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
		CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual  
	from 
		HD4DCDHH.MATRIPRD as PRD join
		HD4DCDHH.MATRIITM as ITM on itm.IDMATRIZ= prd.IDMATRIZ               
		left join
		HD4DCDHH.DCRREGRA conf on conf.stsconfig= 1 left join
		HD4DCDHH.CADPPB ppb on ppb.partnumpd = itm.partnumpd
	where 
		prd.IDMATRIZ= :idmatriz and itm.PARTNUMPD= :partnumpd 
	""", nativeQuery = true)
	int geraRegistro_0(@Param("idmatriz") Integer idmatriz, @Param("partnumpd") String partnumpd, 
	                   @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst);//, @Param("tpprd") String tpprd);


	@Transactional
	@Modifying
	@Query(value = """		
	insert into HD4DCDHH.DCRREG1
	(IDMATRIZ, PARTNUMPD, TPPRD, IDREG, MODELO, DESCRICAO, PRECO, CODINT, 
	 ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR
	)
	select 
		prd.idmatriz, itm.partnumpd, prd.tpprd, '1', 1, prd.descrfb, itm.preco, 
		substr(itm.partnumpd,6,15) codint,  
		'DCRBACKEND', CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)),
		VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
		CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual  
	from 
		HD4DCDHH.MATRIPRD as PRD join
		HD4DCDHH.MATRIITM as ITM on itm.IDMATRIZ= prd.IDMATRIZ  		
	where 
		prd.IDMATRIZ= :idmatriz and itm.PARTNUMPD= :partnumpd
	""", nativeQuery = true)
	int geraRegistro_1(@Param("idmatriz") Integer idmatriz, @Param("partnumpd") String partnumpd,
					   @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst);


    // CREATE REG-2  
	// Obs.: recuperar CNPJ, IE e ESPEC do doc3 ao resolver pendência e converter valor unitário base unidade do doc3
	@Transactional
	@Modifying
	@Query(value = """		
	insert into HD4DCDHH.DCRREG2
	(IDMATRIZ, PARTNUMPD, TPPRD, PARTNUM, IDREG, NUMCOMP, 
		NUMNF, SERNF, CNPJFOR, IE, EMINF, ESPEC, UNDCOM, NCM, QTDE, VLRUNIT,
		ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR
	)
	select 
		prd.idmatriz, itm.partnumpd, prd.tpprd, ins.partnum, '2' reg,
		Rownumber() Over(Partition by doc.partnumpd) as numcomp,
		case when NUMDOC3='' then NUMDOC else NUMDOC3 end as NUMNF,
		case when NUMDOC3='' then SERDOC else SERDOC3 end as SERNF,
		case when NUMDOC3='' then CNPJFOR else CNPJFOR3 end as CNPJ, 
		case when NUMDOC3='' then IE else IE3 end as IE,
		case when NUMDOC3='' then EMIDOC else EMIDOC3 end as EMINF,
		ins.ESPEC, /* --> update with doc - DI*/
		ins.UNDCOM, ins.NCM, ins.NECFIL, ins.VLRUNIT, /*vlrunit - update when set NUMDOC3*/  
		'DCRBACKEND',  CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)), 
		VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
		CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual 
	from 
		HD4DCDHH.MATRIPRD as PRD join
		HD4DCDHH.MATRIITM as ITM on itm.IDMATRIZ= prd.IDMATRIZ join 
		HD4DCDHH.MATRIINS as INS on ins.IDMATRIZ= prd.IDMATRIZ and ins.PARTNUMPD= itm.PARTNUMPD 
		join /*join - ver se permite exclusão de item sem doc - INS ficará maior que DOC*/
		HD4DCDHH.MATRIDOC as DOC on doc.IDMATRIZ= prd.IDMATRIZ and doc.PARTNUMPD= itm.PARTNUMPD and doc.partnum= ins.partnum 	
	where 
		prd.IDMATRIZ= :idmatriz and itm.PARTNUMPD= :partnumpd and 
		ins.ITMORG in('1', '4')   /* <> '3' */
	""", nativeQuery = true)
	int geraRegistro_2(@Param("idmatriz") Integer idmatriz, @Param("partnumpd") String partnumpd,
	                   @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst);
			


    // CREATE REG-3  
	// Obs.: recuperar ADICAO, ITADICAO e ESPEC do doc3 ao resolver pendência e converter valor unitário base unidade do doc3
	@Transactional
	@Modifying
	@Query(value = """			
	insert into HD4DCDHH.DCRREG3(
		IDMATRIZ, PARTNUMPD, TPPRD, PARTNUM, IDREG, NUMSUBCOMP, NUMCOMP,
		IIBASECALC, IMPDIRETA, SUSPENS, INDREDUCII, DI, ADICAO, ITEMADICAO, ESPEC, UNDCOM, NCM, QTDE, VLRUNIT,
		ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR
	)
	SELECT 
		prd.idmatriz, itm.partnumpd, prd.tpprd, ins.partnum, '3' reg,
		reg2.numcomp as sub, Rownumber() Over(Partition by doc.partnumpd) as numcomp, 
		'S', 'S', 'S', 'N' as indii,
		case when NUMDOC3='' then NUMDOC else NUMDOC3 end as DI,
		case when NUMDOC3='' then ADICAO else ADICAO3 end as ADICAO,
		case when NUMDOC3='' then ITADICAO else ITADICAO3 end as ITADICAO, ins.ESPEC, /* --> update with doc - DI*/   
		ins.UNDCOM, ins.NCM, ins.NECFIL, ins.VLRUNIT, /*vlrunit - update when set NUMDOC3*/  
		'DCRBACKEND',  CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)), 
		VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
		CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual
		--conjunto.uitmpai, conjunto.descpai
	FROM 
		HD4DCDHH.MATRIPRD as PRD join
		HD4DCDHH.MATRIITM as ITM on itm.IDMATRIZ= prd.IDMATRIZ join 
		HD4DCDHH.MATRIINS as INS on ins.IDMATRIZ= prd.IDMATRIZ and ins.PARTNUMPD= itm.PARTNUMPD 
		join /*join - ver se permite exclusão de item sem doc - INS ficará maior que DOC*/
		HD4DCDHH.MATRIDOC as DOC on doc.IDMATRIZ= prd.IDMATRIZ and doc.PARTNUMPD= itm.PARTNUMPD and doc.partnum= ins.partnum 
		join --> itens pai com filho importado
		(select UITMPAI, nac.partdesc descpai, nac.itmorg orig_pai, nac.emcomp emp_pai, nac.partnew, 
				uidmaq, uempfil, uitmfil, uitdsc, uittyp, uitmorg, uunmsr, unecfil, uweght, 
				uemcomp as emcomp_fil, uempin82 as in82_fil, unseq, useqc, univel, ucutin
		from   HD4DCDHH.MATRIINS nac join
				HD4DCDHH.DCRSTRU s on s.umodelo= nac.PARTNUMPD and nac.PARTNUM = s.UITMPAI 
		where  IDMATRIZ= :idmatriz and ITMORG in('1', '4') and s.UITMORG= '3' 
		) conjunto on conjunto.uitmfil = ins.partnum
		join --> num. componente nacional .. obs.: no teste há nacionais sem doc
		HD4DCDHH.DCRREG2 reg2 on reg2.IDMATRIZ= prd.IDMATRIZ and reg2.PARTNUMPD= itm.PARTNUMPD and reg2.partnum= conjunto.UITMPAI 
	WHERE 
		prd.IDMATRIZ= :idmatriz and itm.PARTNUMPD= :partnumpd and
		ins.ITMORG = '3'
	""", nativeQuery = true)
	int geraRegistro_3(@Param("idmatriz") Integer idmatriz, @Param("partnumpd") String partnumpd,
					   @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst);	
				
		
    // CREATE REG-4  
	// Obs.: recuperar ADICAO, ITADICAO e ESPEC do doc3 ao resolver pendência e converter valor unitário base unidade do doc3
	@Transactional
	@Modifying
	@Query(value = """				
	insert into HD4DCDHH.DCRREG4(
		IDMATRIZ, PARTNUMPD, TPPRD, PARTNUM, IDREG, NUMCOMP, 
		IMPDIRETA, SUSPENS, INDREDUCII, DI, ADICAO, ITEMADICAO, ESPEC, UNDCOM, NCM, QTDE, VLRUNIT,
		ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR
	)
	SELECT 
		prd.idmatriz, itm.partnumpd, prd.tpprd, ins.partnum, '4' reg,
		Rownumber() Over(Partition by doc.partnumpd) + lastcomp as numcomp,
		'S', 'S', 'N' as indii,
		case when NUMDOC3='' then NUMDOC else NUMDOC3 end as DI,
		case when NUMDOC3='' then ADICAO else ADICAO3 end as ADICAO,
		case when NUMDOC3='' then ITADICAO else ITADICAO3 end as ITADICAO, ins.ESPEC, /* --> update with doc - DI*/   
		ins.UNDCOM, ins.NCM, ins.NECFIL, ins.VLRUNIT, /*vlrunit - update when set NUMDOC3*/  
		'DCRBACKEND',  CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)), 
		VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
		CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual 
	FROM 
		HD4DCDHH.MATRIPRD as PRD join
		HD4DCDHH.MATRIITM as ITM on itm.IDMATRIZ= prd.IDMATRIZ join 
		HD4DCDHH.MATRIINS as INS on ins.IDMATRIZ= prd.IDMATRIZ and ins.PARTNUMPD= itm.PARTNUMPD 
		join /*join - ver se permite exclusão de item sem doc - INS ficará maior que DOC*/
		HD4DCDHH.MATRIDOC as DOC on doc.IDMATRIZ= prd.IDMATRIZ and doc.PARTNUMPD= itm.PARTNUMPD and doc.partnum= ins.partnum 
		join
		(select idmatriz, partnumpd, max(numcomp) lastcomp 
		from HD4DCDHH.DCRREG3 group by idmatriz, partnumpd
		) as R3 on r3.IDMATRIZ= prd.IDMATRIZ and r3.PARTNUMPD= itm.PARTNUMPD
	WHERE 
		prd.IDMATRIZ= :idmatriz and itm.PARTNUMPD= :partnumpd and 
		ins.ITMORG = '3' and
		--> importados não filhos dos subcompenentes do REG3
		not exists(
		  select * from HD4DCDHH.DCRREG2 reg3 
		  where reg3.IDMATRIZ= prd.IDMATRIZ and reg3.PARTNUMPD= itm.PARTNUMPD and reg3.partnum= ins.partnum
		)		
	""", nativeQuery = true)
	int geraRegistro_4(@Param("idmatriz") Integer idmatriz, @Param("partnumpd") String partnumpd,
					   @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst);	
		
		
	@Transactional
	@Modifying
	@Query(value = """		
	insert into HD4DCDHH.DCRREG9 (
		IDMATRIZ, PARTNUMPD, TPPRD, IDREG, QTDRED, ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR
		)
	SELECT  
		idmatriz, partnumpd, tpprd, '9', sum(qt),   
		'DCRBACKEND',  CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)), 
		VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
		CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual 
	FROM 
	    (select idmatriz, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG0 group by idmatriz, partnumpd, tpprd union 
		 select idmatriz, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG1 group by idmatriz, partnumpd, tpprd union
		 select idmatriz, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG2 group by idmatriz, partnumpd, tpprd union
		 select idmatriz, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG3 group by idmatriz, partnumpd, tpprd union
		 select idmatriz, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG4 group by idmatriz, partnumpd, tpprd 
		)vw 
	WHERE 
		IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd
	group by idmatriz, tpprd, partnumpd	
	""", nativeQuery = true)
	int geraRegistro_9(@Param("idmatriz") Integer idmatriz, @Param("partnumpd") String partnumpd,
					   @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst);	

	


}
