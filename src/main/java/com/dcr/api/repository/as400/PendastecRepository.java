package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.keys.PendastecKey;

public interface PendastecRepository extends JpaRepository<Pendastec, PendastecKey>{
	@Query(value = "SELECT * FROM HD4DCDHH.PENDASTEC a WHERE a.cdpend = :cdpend", nativeQuery = true)
    List<Pendastec> findByCdPend(String cdpend);
}
