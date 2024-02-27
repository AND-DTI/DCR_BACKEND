package com.dcr.api.repository.as400;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dcr.api.model.as400.Dcrapi;
import com.dcr.api.model.keys.DcrapiKey;

import jakarta.transaction.Transactional;

public interface DcrapiRepository extends JpaRepository<Dcrapi, DcrapiKey>{

	@Query("SELECT p FROM Dcrapi p WHERE p.stsconfig = 1")
	Optional<Dcrapi> findAtivo();
	
	
	@Modifying
	@Transactional
	@Query(value = "UPDATE HD4DCDHH.DCRAPI SET stsconfig = :stsconfig, confvigfim = :confvigfimNew WHERE confvigini = :confvigini AND confvigfim = :confvigfim", nativeQuery = true)
	void updateStsconfigAndConfvigfim(@Param("confvigini") String confvigini, @Param("confvigfim") String confvigfim, @Param("stsconfig") int stsconfig, @Param("confvigfimNew") String confvigfimNew);
}
