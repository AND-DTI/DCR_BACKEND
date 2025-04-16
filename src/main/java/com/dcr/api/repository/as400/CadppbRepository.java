package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.projection.AstecProjection;
import com.dcr.api.model.projection.ProdsProjection;
//import com.dcr.api.model.keys.ProdutoKey;
//public interface CadppbRepository extends JpaRepository<Cadppb, ProdutoKey>{ //j4



public interface CadppbRepository extends JpaRepository<Cadppb, String>{


	@Query(value =  """
	Select 
		/* nivel 1: */
		nvl(fat.CDPRD, '-') cdprd, fat.UNCOME, fat.ANOMDL as ANOFAT, fat.FRSTANOFAB, fat.LASTANOFAB, 
		ppb.TPPRD, tp.DSCPOR, ppb.DESCCOM, ppb.DESCRFB, ppb.PRDDEST, ppb.PPBPRD, 
		case nvl(mdl.MODELO, '') 
		  when '' then mdl.MDSUGEST else mdl.MODELO 
		end as modelo, mdl.ANOMDL, 			
		/* nivel 2: */
		ppb.PARTNUMPD, mdl.DESCPOR, mdl.DESCING, mdl.UENGNO, mdl.CODCOR, cor.CORPT  										
	From 
		HD4DCDHH.CADPPB ppb left join 
		HD4DCDHH.PRODFAT fat on fat.PARTNUMPD= ppb.PARTNUMPD left join 
		HD4DCDHH.PRODMOD mdl on mdl.PARTNUMPD= ppb.PARTNUMPD left join 
		HD4DCDHH.CADTPPRD tp on tp.TPPRD= ppb.TPPRD left join 
		HD4DCDHH.CADCOR cor on cor.CODCOR= mdl.CODCOR 
	Where 
		ppb.tpprd in :tpprdList  
	--Order by nvl(fat.CDPRD, '-')
	""", nativeQuery = true)
	List<ProdsProjection> consultaByTpprd(List<String> tpprdList);
	//--cdprd in ('JXE', 'KEG') 
	  

	@Query(value = """
	Select 
		ppb.*,
		tp.dscpor 
	From 
		HD4DCDHH.CADPPB ppb left join 
		HD4DCDHH.CADTPPRD tp on tp.TPPRD=ppb.TPPRD 
	Where  
		ppb.tpprd = 'PC'
	""", nativeQuery = true)
	List<AstecProjection> findAllASTEC();
	//List<AstecDTO> findAllASTEC();


	@Transactional
	@Modifying
	@Query(value = """
	insert into HD4DCDHH.PPBEXCEPT (PARTNUMPD, TPPRD, DESCCOM, PRDDEST, ITAUDSYS, ITAUDUSR, ITAUDHST, ITAUDDT, ITAUDHR)
	select CAST(:partnumpd as char(25)), CAST(:tpprd as char(4)), CAST(:desccom as char(150)), CAST(:prddest as char(1)), 
		   CAST(:itaudsys as char(40)), CAST(:itaudusr as char(10)), CAST(:itaudhst as char(30)),
		   VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dt, CHAR(TIME(CURRENT TIMESTAMP),JIS) hr
	from   SYSIBM.SYSDUMMY1
	""", nativeQuery = true)
    int desobrigaPPB(@Param("partnumpd") String partnumpd, @Param("tpprd") String tpprd, 
	                 @Param("desccom") String desccom, @Param("prddest") String prddest,
					 @Param("itaudsys") String itaudsys, @Param("itaudusr") String itaudusr, @Param("itaudhst") String itaudhst
					);

	@Transactional
	@Modifying
	@Query(value = """
	delete from HD4DCDHH.PRODFAT where partnumpd= :partnumpd
	""", nativeQuery = true)
	int deletePRODFAT(@Param("partnumpd") String partnumpd);
						
	@Transactional
	@Modifying
	@Query(value = """
	delete from HD4DCDHH.PRODMOD where partnumpd= :partnumpd
	""", nativeQuery = true)
	int deletePRODMOD(@Param("partnumpd") String partnumpd);

}


//"call HDCR004C( CAST(:TPPRD as char(3)), CAST(:USERSYS as char(10)), CAST(:IDMATRIZ as char(10)) )"

/*old:
	@Query(value = "select \r\n"
			+ "  a.CDPRD, a.TPPRD, tp.DSCPOR, a.DESCCOM, a.DESCRFB, a.PRDDEST, a.PPBPRD,  \r\n"
			+ "  case nvl(c.MODELO, '')\r\n"
			+ "    when '' then c.MDSUGEST \r\n"
			+ "    else c.MODELO\r\n"
			+ "  end as modelo,\r\n"
			+ "  b.ANOMDL,\r\n"
			+ "  b.PARTNUMPD, c.DESCPOR, c.DESCING, c.UENGNO, c.CODCOR, cor.CORPT, b.UNCOME\r\n"
			+ " from\r\n"
			+ "  HD4DCDHH.CADPPB a join\r\n"
			+ "  HD4DCDHH.CADTPPRD tp on tp.TPPRD=a.TPPRD left join \r\n"
			+ "  HD4DCDHH.PRODFAT b on b.CDPRD = a.CDPRD left join \r\n"
			+ "  HD4DCDHH.PRODMOD c on c.PARTNUMPD = b.PARTNUMPD left join\r\n"
			+ "  HD4DCDHH.CADCOR cor on cor.CODCOR = c.CODCOR"
			+ " WHERE a.tpprd IN :tpprdList", nativeQuery = true)
 */