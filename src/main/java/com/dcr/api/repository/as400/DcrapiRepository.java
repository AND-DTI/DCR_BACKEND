package com.dcr.api.repository.as400;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrapi;
import com.dcr.api.model.keys.DcrapiKey;

public interface DcrapiRepository extends JpaRepository<Dcrapi, DcrapiKey>{

	@Query("SELECT p FROM Dcrapi p WHERE p.stsconfig = 1")
	Optional<Dcrapi> findAtivo();
}
