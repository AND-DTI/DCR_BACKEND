package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Accoper;

public interface AccoperRepository extends JpaRepository<Accoper, Integer>{

	   @Query("SELECT a FROM Accoper a WHERE a.cdmodule = :cdmodule AND a.ativo = 'S'")
	   List<Accoper> findByCdmoduleAndAtivo(String cdmodule);
	  
}
