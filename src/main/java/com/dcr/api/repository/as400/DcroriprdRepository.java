package com.dcr.api.repository.as400;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dcr.api.model.as400.Dcroriprd;
import com.dcr.api.model.keys.DcroriprdKey;

import jakarta.transaction.Transactional;

public interface DcroriprdRepository extends JpaRepository<Dcroriprd, DcroriprdKey>{

	@Query("SELECT p FROM Dcroriprd p WHERE p.stsconfig = 1")
	Optional<Dcroriprd> findAtivo();
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE HD4DCDHH.Dcroriprd SET stsconfig = :stsconfig, confvigfim = :confvigfimNew WHERE confvigini = :confvigini AND confvigfim = :confvigfim", nativeQuery = true)
	void updateStsconfigAndConfvigfim(@Param("confvigini") String confvigini, @Param("confvigfim") String confvigfim, @Param("stsconfig") int stsconfig, @Param("confvigfimNew") String confvigfimNew);
}
