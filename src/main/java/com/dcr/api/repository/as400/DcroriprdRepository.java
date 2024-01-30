package com.dcr.api.repository.as400;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcroriprd;
import com.dcr.api.model.keys.DcroriprdKey;

public interface DcroriprdRepository extends JpaRepository<Dcroriprd, DcroriprdKey>{

	@Query("SELECT p FROM Dcroriprd p WHERE p.stsconfig = 1")
	Optional<Dcroriprd> findAtivo();
}
