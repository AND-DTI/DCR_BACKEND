package com.dcr.api.repository.as400;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.keys.DcrregraKey;

public interface DcrregraRepository extends JpaRepository<Dcrregra, DcrregraKey>{

	@Query("SELECT p FROM Dcrregra p WHERE p.stsconfig = 1")
	Optional<Dcrregra> findAtivo();
}
