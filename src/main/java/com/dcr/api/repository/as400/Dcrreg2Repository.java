package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.as400.Dcrreg2;
import com.dcr.api.model.keys.Dcrreg2Key;

public interface Dcrreg2Repository extends JpaRepository<Dcrreg2, Dcrreg2Key>{

	@Query(value = "SELECT * FROM HD4DCDHH.DCRREG2 AS dcr WHERE dcr.idmatriz = :idmatriz AND dcr.partnumpd = :partnumpd AND dcr.tpprd = :tpprd ORDER BY dcr.numcomp", nativeQuery = true)
	  List<Dcrreg2> consultaByIds(Integer idmatriz, String partnumpd, String tpprd);
}
