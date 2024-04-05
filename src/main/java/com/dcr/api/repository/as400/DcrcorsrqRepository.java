package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrcorsrq;

public interface DcrcorsrqRepository  extends JpaRepository<Dcrcorsrq, Long>{

	@Query(value = "SELECT * FROM HD4DCDHH.DCRCORSRQ AS RQ WHERE RQ.CNPJEXT = :cnpjext", nativeQuery = true)
	List<Dcrcorsrq> getByCnpj(String cnpjext);
	
	@Query(value = "SELECT a.* \r\n"
			+ "					FROM   HD4DCDHH.DCRCORSRQ AS a LEFT JOIN\r\n"
			+ "					       HD4DCDHH.DCRCORSIP AS b on b.cnpjext = a.cnpjext\r\n"
			+ "					WHERE  a.rotareq = :rota AND b.numip = :ip", nativeQuery = true)
	List<Dcrcorsrq> getByRota(String rota, String ip);
}
