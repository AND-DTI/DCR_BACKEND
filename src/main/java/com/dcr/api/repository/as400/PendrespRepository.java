package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.as400.Pendresp;
import com.dcr.api.model.keys.PendenciaKey;

public interface PendrespRepository extends JpaRepository<Pendresp, PendenciaKey>{
	@Query(value = "SELECT * FROM HD4DCDHH.PENDRESP a WHERE a.cdpend = :cdpend", nativeQuery = true)
    List<Pendresp> findByCdPend(String cdpend);
}
