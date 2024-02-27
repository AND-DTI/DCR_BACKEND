package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.model.projection.ProdsProjection;
import com.dcr.api.model.projection.TpprdProjection;

public interface CadppbRepository extends JpaRepository<Cadppb, ProdutoKey>{

	@Query(value = "select \r\n"
			+ "  a.CDPRD, a.TPPRD, tp.DSCPOR, a.DESCCOM, a.DESCRFB, a.PRDDEST, a.PPBPRD,  \r\n"
			+ "  case nvl(c.MODELO, '')\r\n"
			+ "    when '' then c.MDSUGEST \r\n"
			+ "    else c.MODELO\r\n"
			+ "  end as modelo,\r\n"
			+ "  b.ANOMDL,\r\n"
			+ "  b.PARTNUMPD, c.DESCPOR, c.DESCING, c.UENGNO, c.CODCOR, cor.CORPT\r\n"
			+ " from\r\n"
			+ "  HD4DCDHH.CADPPB a join\r\n"
			+ "  HD4DCDHH.CADTPPRD tp on tp.TPPRD=a.TPPRD left join \r\n"
			+ "  HD4DCDHH.PRODFAT b on b.CDPRD = a.CDPRD left join \r\n"
			+ "  HD4DCDHH.PRODMOD c on c.PARTNUMPD = b.PARTNUMPD left join\r\n"
			+ "  HD4DCDHH.CADCOR cor on cor.CODCOR = c.CODCOR"
			+ " WHERE a.tpprd IN :tpprdList", nativeQuery = true)
	List<ProdsProjection> consultaByTpprd(List<String> tpprdList);
	
	  
}
