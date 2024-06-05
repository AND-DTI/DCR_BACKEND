package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.projection.AstecProjection;
//import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.model.projection.ProdsProjection;
//public interface CadppbRepository extends JpaRepository<Cadppb, ProdutoKey>{ //j4
public interface CadppbRepository extends JpaRepository<Cadppb, String>{


	@Query(value = 
	          "Select \r\n"
			+ "  -- nivel 1: \r\n"
			+ "  nvl(fat.CDPRD, '-') cdprd, fat.UNCOME, fat.ANOMDL as ANOFAT, fat.FRSTANOFAB, fat.LASTANOFAB, \r\n"
			+ "  ppb.TPPRD, tp.DSCPOR, ppb.DESCCOM, ppb.DESCRFB, ppb.PRDDEST, ppb.PPBPRD, \r\n"
			+ "  case nvl(mdl.MODELO, '') \r\n"
			+ "    when '' then mdl.MDSUGEST else mdl.MODELO \r\n"
			+ "  end as modelo, mdl.ANOMDL, \r\n"			
			+ "  -- nivel 2: \r\n"
			+ "  ppb.PARTNUMPD, mdl.DESCPOR, mdl.DESCING, mdl.UENGNO, mdl.CODCOR, cor.CORPT  \r\n"										
			+ "From \r\n"
			+ "  HD4DCDHH.CADPPB ppb left join \r\n"
			+ "  HD4DCDHH.PRODFAT fat on fat.PARTNUMPD = ppb.PARTNUMPD left join \r\n"
			+ "  HD4DCDHH.PRODMOD mdl on mdl.PARTNUMPD = ppb.PARTNUMPD left join \r\n"
			+ "  HD4DCDHH.CADTPPRD tp on tp.TPPRD=ppb.TPPRD left join \r\n"
			+ "  HD4DCDHH.CADCOR cor on cor.CODCOR = mdl.CODCOR \r\n"
			+ "Where --cdprd in ('JXE', 'KEG') \r\n"
			+ "  ppb.tpprd in :tpprdList", nativeQuery = true)
	List<ProdsProjection> consultaByTpprd(List<String> tpprdList);
	
	  
	@Query(value = 
			  "Select \r\n"
			+ "  ppb.*,   \r\n"
			+ "  tp.dscpor \r\n"
			+ "From \r\n"
			+ "  HD4DCDHH.CADPPB ppb left join \r\n"
			+ "  HD4DCDHH.CADTPPRD tp on tp.TPPRD=ppb.TPPRD  \r\n"
			+ "Where  \r\n"
			+ "  ppb.tpprd = 'PC'", nativeQuery = true)
	List<AstecProjection> findAllASTEC();
	//List<AstecDTO> findAllASTEC();


}


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