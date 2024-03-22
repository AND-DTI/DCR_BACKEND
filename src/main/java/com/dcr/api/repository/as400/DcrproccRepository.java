package com.dcr.api.repository.as400;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.projection.ResumoProjection;

public interface DcrproccRepository extends JpaRepository<Dcrprocc, DcrproccKey>{
	@Query(value = "SELECT \r\n"
			+ "    p.idmatriz,\r\n"
			+ "    TRIM(p.partnumpd) AS partnumpd,\r\n"
			+ "    TRIM(p.tpprd) AS tpprd,\r\n"
			+ "    p.status,\r\n"
			+ "    regra.cnpjemi,\r\n"
			+ "    TRIM(regra.razsoc) AS razsoc,\r\n"
			+ "    taxausd,\r\n"
			+ "    totalnac,\r\n"
			+ "    totalimp,\r\n"
			+ "    custotal,\r\n"
			+ "    coefred,\r\n"
			+ "    iitotal,\r\n"
			+ "    iireduzido,\r\n"
			+ "    dcr.protdcre,  \r\n"
			+ "    dcr.tpenvio,\r\n"
			+ "    dcr.dtenvio,\r\n"
			+ "    dcr.hrenvio,\r\n"
			+ "    dcr.repreenvio,\r\n"
			+ "    reg0.PESO, \r\n"
			+ "    reg0.SALARIOS, \r\n"
			+ "    reg0.UNDCOM, \r\n"
			+ "    reg0.TPDCRE, \r\n" 
			+ "    reg0.NCM, \r\n" 
			+ "    reg0.ENCARGOS, \r\n"
			+ "    dcr.status as protostatus\r\n"
			+ "FROM \r\n"
			+ "    HD4DCDHH.DCRPROCC p\r\n"
			+ "JOIN \r\n"
			+ "    HD4DCDHH.DCRREG0 r0 ON r0.idmatriz = p.idmatriz \r\n"
			+ "                         AND r0.partnumpd = p.partnumpd \r\n"
			+ "                         AND r0.tpprd = p.tpprd\r\n"
			+ "LEFT JOIN \r\n"
			+ "    HD4DCDHH.DCRREGRA regra ON regra.stsconfig = 1\r\n"
			+ "LEFT JOIN \r\n"
			+ "HD4DCDHH.DCRREG0 AS reg0 ON reg0.idmatriz = p.idmatriz AND reg0.partnumpd = p.partnumpd AND reg0.tpprd = p.tpprd \r\n"
			+ "LEFT JOIN \r\n"
			+ "    (SELECT * \r\n"
			+ "     FROM HD4DCDHH.DCRPROTO AS dcr \r\n"
			+ "     WHERE dcr.idmatriz = (SELECT idmatriz FROM HD4DCDHH.DCRPROCC WHERE idmatriz = :idmatriz AND partnumpd = :partnumpd) \r\n"
			+ "       AND dcr.tpprd = (SELECT tpprd FROM HD4DCDHH.DCRPROCC WHERE idmatriz = :idmatriz AND partnumpd = :partnumpd) \r\n"
			+ "       AND dcr.partnumpd = (SELECT partnumpd FROM HD4DCDHH.DCRPROCC WHERE idmatriz = :idmatriz AND partnumpd = :partnumpd)\r\n"
			+ "     ORDER BY TIMESTAMP_FORMAT(dcr.ITAUDDT || ' ' || dcr.ITAUDHR, 'YYYYMMDD HH24:MI:SS') DESC \r\n"
			+ "     FETCH FIRST 1 ROW ONLY) dcr ON p.idmatriz = dcr.idmatriz\r\n"
			+ "WHERE \r\n"
			+ "    p.idmatriz = :idmatriz \r\n"
			+ "    AND p.partnumpd = :partnumpd", nativeQuery = true)
	Optional<ResumoProjection> getResumo(Long idmatriz, String partnumpd);
}
