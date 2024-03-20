package com.dcr.api.repository.as400;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Cadtppend;

public interface CadtppendRepository extends JpaRepository<Cadtppend, String>{	
		@Query("SELECT p FROM Cadtppend p LEFT JOIN FETCH p.responsaveis r")
		List<Cadtppend> findPendenciasAndResponsaveis();
		
		@Query("SELECT p FROM Cadtppend p LEFT JOIN FETCH p.responsaveis r where p.cdpend = :cdpend")
		Optional<Cadtppend> findPendenciasAndResponsaveisByCdPend(String cdpend);
}
