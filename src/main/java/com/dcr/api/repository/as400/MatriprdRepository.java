package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Matriprd;

public interface MatriprdRepository  extends JpaRepository<Matriprd, Integer>{

	  
	  @Query(value = "SELECT prd.IDMATRIZ, prd.PRODUTO, prd.MODELO, prd.ANOMDL, prd.DESCCOM, prd.DESCRFB, prd.TPPRD, prd.PROTOT, prd.SPECIAL, \r\n"
	  		+ "					 prd.TPDCRE, prd.ORIGPRD, prd.DTNECI, prd.PRIOURGEN, prd.PREVFAT, prd.PRIORESP, prd.PRIODTMNT, prd.PRIOHRMNT,\r\n"
	  		+ "		itm.PARTNUMPD, itm.MODELO, itm.CODCOR, itm.PARTDESC, itm.UNMED, itm.PRIOCOR,\r\n"
	  		+ "		cor.CODCOR, cor.CDBEJ, cor.CORPT, cor.CORENG, cor.TPPIN,\r\n"
	  		+ "		tpprd.TPPRD, tpprd.DSCPOR, tpprd.DSCING " +
              "FROM HD4DCDHH.MATRIPRD prd " +
              "LEFT JOIN HD4DCDHH.MATRIITM itm ON prd.IDMATRIZ = itm.IDMATRIZ " +
              "LEFT JOIN HD4DCDHH.CADCOR cor ON itm.CODCOR = cor.CODCOR " +
              "LEFT JOIN HD4DCDHH.CADTPPRD tpprd ON prd.TPPRD = tpprd.TPPRD " +
              "WHERE prd.IDMATRIZ = :idmatriz", nativeQuery = true)
	  List<Object[]> consultaJoin(Integer idmatriz);
	  
	 
}
