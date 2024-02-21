package com.dcr.api.repository.as400;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.keys.DcrregraKey;

import jakarta.transaction.Transactional;

public interface DcrregraRepository extends JpaRepository<Dcrregra, DcrregraKey>{

	@Query("SELECT p FROM Dcrregra p WHERE p.stsconfig = 1")
	Optional<Dcrregra> findAtivo();
			
	@Modifying
    @Transactional
    @Query(value = "UPDATE HD4DCDHH.DCRREGRA SET stsconfig = :stsconfig, confvigfim = :confvigfimNew WHERE confvigini = :confvigini AND confvigfim = :confvigfim", nativeQuery = true)
    void updateStsconfigAndConfvigfim(@Param("confvigini") String confvigini, @Param("confvigfim") String confvigfim, @Param("stsconfig") int stsconfig, @Param("confvigfimNew") String confvigfimNew);
}
