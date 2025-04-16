package com.dcr.api.repository.as400;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.dto.INT.JobExplosaoINT;
import com.dcr.api.model.dto.INT.ProcessamentoMatrizINT;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.projection.ResumoProjection;




public interface DcrproccRepository extends JpaRepository<Dcrprocc, DcrproccKey>{


    @Query(value = """
    select * from (   
        select 
            statusMatriz, st.dscsts, idusr, /*descStatus,*/ mt.idmatriz, 
            p.partnumpd, mt.produto, mt.modelo, mt.desccom, mt.tpprd, mt.origprd,
            j.cnrojob, j.cdtinicio, j.cdtfinal, j.cstsjob, p.status,     
            case when h.stsold is null 
              then p.dtstatus else h.dtstatus
            end as dtMatriz,			
			substring(trim(flex4), 1, 19) dtProcessamento /*old substring(trim(flex5), 1, 16)*/
        from 
            (select idmatriz, flex1flw statusMatriz, itaudusr idusr, --trim('flex4flw') descStatus, 
                    produto, modelo, desccom, tpprd, origprd, flex5flw flex5, trim(flex4flw) flex4
             from   HD4DCDHH.MATRIPRD 
			 union
             select idmatriz, flex1flw statusMatriz, itaudusr idusr, --trim(flex4flw) descStatus, 
                    '' produto, '' modelo, desccom, 'PC' tpprd, origprd, flex5flw flex5, trim(flex4flw) flex4
             from   HD4DCDHH.MTASTEC
            )mt left join
            HD4DCDHH.DCRPROCC p on p.idmatriz= mt.idmatriz and p.tpprd= mt.tpprd left join
            HD4DCDHH.DCRPROCCH h on h.idmatriz= mt.idmatriz and h.tpprd= mt.tpprd and stsold=0 left join
            HD4EMDHD.EMACJOB j on j.cuserid= mt.idusr
            left join
            (select 0 sts, 'Disponivel' dscsts from SYSIBM.SYSDUMMY1 s union 
            select 1 sts, 'Explodindo Estrutura' dsc from SYSIBM.SYSDUMMY1 s union
            select 2 sts, 'Processando Matriz' dsc from SYSIBM.SYSDUMMY1 s union
            select 3 sts, 'Processando Documentos' dsc from SYSIBM.SYSDUMMY1 s union
            select 4 sts, 'rocessando Pendências' dsc from SYSIBM.SYSDUMMY1 s union
            select 5 sts, 'Acoplando Coligada' dsc from SYSIBM.SYSDUMMY1 s 
            )st on st.sts = mt.statusMatriz
        )vw
    where 
        statusMatriz <> 0 
    """, nativeQuery = true)
    //Optional<ResumoProjection> getProcessando();
    List<ProcessamentoMatrizINT> getProcessando();


    @Query(value = """
    select distinct * from HD4EMDHD.EMACJOB j
    where cstsjob <> 'F' and (
        exists(
            select * from  HD4DCDHH.MATRIPRD mt
            where j.cuserid= mt.itaudusr
        ) 
        or exists(
            select * from  HD4DCDHH.MTASTEC mt
            where j.cuserid= mt.itaudusr
        ) 
    )
    """, nativeQuery = true)
    //Optional<ResumoProjection> getProcessando();
    List<JobExplosaoINT> getJobExplosao();


    @Transactional
	@Modifying
	@Query(value = """
    Update HD4EMDHD.EMACJOB set 
      cstsjob= 'F', cdtfinal= :dtfinal
    Where 
      cuserid= :userid and cnrojob= :nrojob
	""", nativeQuery = true)
    int liberaJobExplosao(String userid, Integer nrojob, String dtfinal);


    @Transactional
	@Modifying
	@Query(value = """
    Update MATRIPRD set 
    flex1flw= 0, flex4flw= ''
    Where 
      idmatriz= :idmatriz
	""", nativeQuery = true)
    int liberaMatriz(Long idmatriz);


    @Transactional
	@Modifying
	@Query(value = """
    Update MATASTEC set 
    flex1flw= 0, flex4flw= ''
    Where 
        idmatriz= :idmatriz
	""", nativeQuery = true)
    int liberaMatrizASTEC(Long idmatriz);
    

	@Query(value = """
	SELECT 
		prc.idmatriz, prc.partnumpd, prc.tpprd, prc.status, cfg.cnpjemi, cfg.razsoc,
		prc.taxausd, prc.totalnac, prc.totalimp, prc.custotal, -- <- updated by diagnostic (DCRPROTO)
		prc.coefred, prc.iitotal, prc.iireduzido, pro.protdcre, 
		pro.tpenvio, pro.dtenvio, pro.hrenvio, pro.repreenvio, pro.status as protostatus,
		rg0.ppb, rg0.denom, rg0.ncm, rg0.undcom, rg0.salarios, rg0.encargos, rg0.peso,
		rg1.preco as preco_brl, case when nvl(taxa,0)=0 then 0 else round(rg1.preco/taxa,2) end as preco_usd,
		rg0.tpdcre, rg0.dcrant, rg0.procretif, taxa,
		case when nvl(taxa,0)=0 then 0 else rg2.totalnac/taxa end as pre_totalnac,
		rg3e4.totalimp as pre_totalimp, cfg.coefredu, cfg.aliqiipad,   
		(case when nvl(taxa,0)=0 then 0 else rg2.totalnac/taxa end) + rg3e4.totalimp as pre_custotal,
		(rg3e4.totalimp * cfg.aliqiipad) as pre_iitotal, 
		(rg3e4.totalimp * cfg.aliqiipad) - ((rg3e4.totalimp * cfg.aliqiipad)*cfg.coefredu) as pre_iireduzido  
	  FROM
		HD4DCDHH.DCRPROCC as PRC join
		HD4DCDHH.DCRREG0  as RG0 on rg0.idmatriz= prc.idmatriz and rg0.partnumpd= prc.partnumpd and rg0.tpprd= prc.tpprd left join			
		HD4DCDHH.DCRREG1  as RG1 on rg1.idmatriz= prc.idmatriz and rg1.partnumpd= prc.partnumpd and rg1.tpprd= prc.tpprd left join
		HD4DCDHH.DCRREGRA as CFG on cfg.stsconfig = 1 left join
		--> PRÉ-CÁLCULO   
		(select idmatriz, partnumpd, sum(qtde * vlrunit) as totalnac 
		 from HD4DCDHH.DCRREG2 group by idmatriz, partnumpd 
		) as RG2 on rg2.idmatriz= prc.idmatriz and rg2.partnumpd= prc.partnumpd  left join
		--Importado:
		(select idmatriz, partnumpd, sum(totalimp) as totalimp from 
		 ( select idmatriz, partnumpd, sum(qtde * vlrunit) as totalimp 
		   from   HD4DCDHH.DCRREG3 group by idmatriz, partnumpd  union
		   select idmatriz, partnumpd, sum(qtde * vlrunit) as totalimp 
		   from   HD4DCDHH.DCRREG4 group by idmatriz, partnumpd )
		 group by idmatriz, partnumpd
		) as RG3e4 on rg3e4.idmatriz= prc.idmatriz and rg3e4.partnumpd= prc.partnumpd left join 		
		--> TAXA   
		(select * from HD4DCDHH.CADTAXA  
		 where  CDMOED = 'USD' and
				vigini = year(CURRENT TIMESTAMP)||right('00'||month(CURRENT TIMESTAMP),2)||right('00'||day(CURRENT TIMESTAMP),2)
		 Fetch first 1 row only
		)tax on tax.taxa > 0   left join     
		--> PROTOCOLO		
		(select pro.* from 
		 HD4DCDHH.DCRPROTO pro join
		 HD4DCDHH.DCRPROCC prc on pro.idmatriz= prc.idmatriz and pro.partnumpd= prc.partnumpd and pro.tpprd= prc.tpprd
		 where pro.idmatriz = :idmatriz and pro.partnumpd= :partnumpd and pro.tpenvio <> 'H'    
		 order by TIMESTAMP_FORMAT(pro.ITAUDDT || ' ' || pro.ITAUDHR, 'YYYYMMDD HH24:MI:SS') desc
		 fetch first 1 row only
		) as PRO on pro.idmatriz = prc.idmatriz        
	  WHERE
		prc.idmatriz = :idmatriz and prc.partnumpd= :partnumpd		
	""", nativeQuery = true)
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
	(IDMATRIZ, PARTNUMPD, TPPRD, IDREG, CNPJ, CPFRL, PPB, DENOM, NCM, UNDCOM, PESO, 
		SALARIOS, ENCARGOS, TPDCRE, DCRANT, PROCRETIF, VRSPGD, ORIGDCR, TPCOEF, 
		ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR
	)
	select 
		prd.idmatriz, itm.partnumpd, prd.tpprd, '0', conf.cnpjemi, '***********', ppb.ppbprd, prd.descrfb, itm.ncm, itm.undcom, 
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
		substr(ins.partnum ||
		case replace(ins.espec, trim(ins.partnum)||' - ', '') 
		  when '' then ins.partdesc 
		  else replace(ins.espec, trim(ins.partnum)||' - ', '') 
		end, 1, 80) as ESPEC, 
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
		case when NUMDOC3='' then ITADICAO else ITADICAO3 end as ITADICAO, 		 
		substr(ins.partnum ||
		case ins.ESPEC  /* --> update with doc - DI*/
		  when '' then ins.partdesc 
		  else replace(ins.espec, trim(ins.partnum)||' - ', '') 
		end, 1, 80) as ESPEC, 
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
		Rownumber() Over(Partition by doc.partnumpd) + nvl(lastcomp,0) as numcomp,
		'S', 'S', 'N' as indii,
		case when NUMDOC3='' then NUMDOC else NUMDOC3 end as DI,
		case when NUMDOC3='' then ADICAO else ADICAO3 end as ADICAO,
		case when NUMDOC3='' then ITADICAO else ITADICAO3 end as ITADICAO, 
		substr(ins.partnum ||
		case ins.ESPEC  /* --> update with doc - DI*/
		  when '' then ins.partdesc 
		  else replace(ins.espec, trim(ins.partnum)||' - ', '') 
		end, 1, 80) as ESPEC,  
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
		left join
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
		idmatriz, partnumpd, tpprd, '9', sum(qt) + 1, /*+ 9*/ 
		'DCRBACKEND',  CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)), 
		VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
		CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual 
	FROM 
	    (select idmatriz, 'reg'||idreg as reg, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG0 group by idmatriz, idreg, partnumpd, tpprd union 
		 select idmatriz, 'reg'||idreg as reg, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG1 group by idmatriz, idreg, partnumpd, tpprd union
		 select idmatriz, 'reg'||idreg as reg, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG2 group by idmatriz, idreg, partnumpd, tpprd union
		 select idmatriz, 'reg'||idreg as reg, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG3 group by idmatriz, idreg, partnumpd, tpprd union
		 select idmatriz, 'reg'||idreg as reg, partnumpd, tpprd, count(*) qt from HD4DCDHH.DCRREG4 group by idmatriz, idreg, partnumpd, tpprd 
		)regs  
	WHERE 
		IDMATRIZ= :idmatriz and PARTNUMPD= :partnumpd
	group by idmatriz, tpprd, partnumpd	
	""", nativeQuery = true)
	int geraRegistro_9(@Param("idmatriz") Integer idmatriz, @Param("partnumpd") String partnumpd,
					   @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst);	

	


}


//OLD getResumo:
/*"SELECT \r\n"
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
			+ "    AND p.partnumpd = :partnumpd"
*/