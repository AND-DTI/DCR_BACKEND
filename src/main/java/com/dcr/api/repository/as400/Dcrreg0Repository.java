package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrreg0;
import com.dcr.api.model.keys.Dcrreg0Key;

public interface Dcrreg0Repository  extends JpaRepository<Dcrreg0, Dcrreg0Key>{

			  @Query(value = "SELECT * FROM HD4DCDHH.DCRREG0 AS dcr WHERE dcr.idmatriz = :idmatriz AND dcr.partnumpd = :partnumpd AND dcr.tpprd = :tpprd", nativeQuery = true)
			  List<Dcrreg0> consultaByIds(Integer idmatriz, String partnumpd, String tpprd);
}
