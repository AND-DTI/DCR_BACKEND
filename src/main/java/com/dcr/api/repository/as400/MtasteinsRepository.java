package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Mtasteins;
import com.dcr.api.model.keys.MtasteinsKey;

public interface MtasteinsRepository extends JpaRepository<Mtasteins, MtasteinsKey>{

	 @Query(value = "SELECT ins.IDMATRIZ, ins.PARTNUM, ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, \r\n"
	 		+ "			ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC, \r\n"
	 		+ "			pend.NUMPEND, pend.CDPEND, pend.STATUS \r\n"
	 		+ "FROM HD4DCDHH.MTASTEINS AS INS \r\n"
	 		+ "LEFT JOIN HD4DCDHH.PENDASTEC AS PEND ON PEND.IDMATRIZ = INS.IDMATRIZ AND PEND.PARTNUM = INS.PARTNUM " +
		           "WHERE INS.IDMATRIZ = :idmatriz AND INS.PARTNUM = :partnum", nativeQuery = true)
			  List<Object[]> consultaPendenciaInsumo(Integer idmatriz, String partnum);
}
