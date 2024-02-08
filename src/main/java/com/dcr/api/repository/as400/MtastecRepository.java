package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Mtastec;

public interface MtastecRepository extends JpaRepository<Mtastec, Integer>{

	Mtastec findByIdmatrizAndPartnumpd(Integer idmatriz, String partnumpd);
	
	@Query(value = "SELECT mta.IDMATRIZ, mta.PARTNUMPD, mta.DESCCOM, mta.DESCRFB, mta.UNMED, mta.ORIGPRD, mta.DTNECI, mta.PRIOURGEN, mta.PREVFAT, mta.PRIORESP, mta.PRIODTMNT, mta.PRIOHRMNT,\r\n"
			+ "		 ins.PARTNUM, ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.PARTSUGEST, ins.PARTSUGDSC, ins.PARTNEW, ins.PARTNEWDSC,\r\n"
			+ "		 pend.NUMPEND, pend.CDPEND, pend.STATUS \r\n"
			+ "FROM HD4DCDHH.MTASTEC AS MTA \r\n"
			+ "LEFT JOIN HD4DCDHH.MTASTEINS AS INS ON MTA.IDMATRIZ = INS.IDMATRIZ \r\n"
			+ "LEFT JOIN HD4DCDHH.PENDASTEC AS PEND ON PEND.IDMATRIZ = INS.IDMATRIZ " + 
              "WHERE mta.IDMATRIZ = :idmatriz", nativeQuery = true)
	  List<Object[]> consultaPendencia(Integer idmatriz);
}
