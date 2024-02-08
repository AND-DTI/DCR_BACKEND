package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Matriins;
import com.dcr.api.model.keys.MatriinsKey;

public interface MatriinsRepository extends JpaRepository<Matriins, MatriinsKey>{

	  @Query(value = "SELECT ins.IDMATRIZ, ins.PARTNUM, ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, \r\n"
	  		+ "			ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
	  		+ "			pend.NUMPEND, pend.CDPEND, pend.OBSPEND, pend.STATUS "
	  		+ "FROM HD4DCDHH.MATRIINS AS INS \r\n"
	  		+ "LEFT JOIN HD4DCDHH.PENDPROD AS PEND ON PEND.IDMATRIZ = INS.IDMATRIZ AND PEND.PARTNUM = INS.PARTNUM " +
	           "WHERE INS.IDMATRIZ = :idmatriz AND INS.PARTNUM = :partnum", nativeQuery = true)
		  List<Object[]> consultaPendenciaInsumo(Integer idmatriz, String partnum);
}
