package com.dcr.api.repository.as400;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.projection.ResumoProjection;

public interface DcrproccRepository extends JpaRepository<Dcrprocc, DcrproccKey>{
	@Query(value = "select p.idmatriz,  TRIM(p.partnumpd) AS partnumpd, TRIM(p.tpprd) AS tpprd, p.status, " +
	        "regra.cnpjemi, TRIM(regra.razsoc) AS razsoc, " +
	        "taxausd, totalnac, totalimp, custotal, " +
	        "coefred, iitotal, iireduzido " +
	        "from HD4DCDHH.DCRPROCC p " +
	        "JOIN HD4DCDHH.DCRREG0 r0 on r0.idmatriz=p.idmatriz and r0.partnumpd=r0.partnumpd and r0.tpprd=p.tpprd " +
	        "left JOIN HD4DCDHH.DCRREGRA regra on stsconfig = 1 " +
	        "where p.idmatriz=:idmatriz and p.partnumpd=:partnumpd", nativeQuery = true)
	Optional<ResumoProjection> getResumo(Long idmatriz, String partnumpd);
}
