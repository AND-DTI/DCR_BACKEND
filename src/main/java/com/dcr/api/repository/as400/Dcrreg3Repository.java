package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrreg3;
import com.dcr.api.model.keys.Dcrreg3Key;

public interface Dcrreg3Repository extends JpaRepository<Dcrreg3, Dcrreg3Key>{
	@Query(value = "SELECT * FROM HD4DCDHH.DCRREG3 AS dcr WHERE dcr.idmatriz = :idmatriz AND dcr.partnumpd = :partnumpd AND dcr.tpprd = :tpprd ORDER BY dcr.numsubcomp", nativeQuery = true)
	  List<Dcrreg3> consultaByIds(Integer idmatriz, String partnumpd, String tpprd);
}
