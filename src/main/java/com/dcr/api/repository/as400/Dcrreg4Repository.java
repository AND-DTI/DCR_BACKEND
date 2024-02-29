package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrreg4;
import com.dcr.api.model.keys.Dcrreg4Key;

public interface Dcrreg4Repository extends JpaRepository<Dcrreg4, Dcrreg4Key>{
	@Query(value = "SELECT * FROM HD4DCDHH.DCRREG4 AS dcr WHERE dcr.idmatriz = :idmatriz AND dcr.partnumpd = :partnumpd AND dcr.tpprd = :tpprd ORDER BY dcr.numcomp", nativeQuery = true)
	  List<Dcrreg4> consultaByIds(Integer idmatriz, String partnumpd, String tpprd);
}
