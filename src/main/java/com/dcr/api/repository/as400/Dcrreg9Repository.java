package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrreg9;
import com.dcr.api.model.keys.Dcrreg9Key;

public interface Dcrreg9Repository extends JpaRepository<Dcrreg9, Dcrreg9Key>{
	@Query(value = "SELECT * FROM HD4DCDHH.DCRREG9 AS dcr WHERE dcr.idmatriz = :idmatriz AND dcr.partnumpd = :partnumpd AND dcr.tpprd = :tpprd", nativeQuery = true)
	  List<Dcrreg9> consultaByIds(Integer idmatriz, String partnumpd, String tpprd);
}
