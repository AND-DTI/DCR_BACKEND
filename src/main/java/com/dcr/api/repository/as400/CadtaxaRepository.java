package com.dcr.api.repository.as400;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Cadtaxa;



public interface CadtaxaRepository extends JpaRepository<Cadtaxa, String> {


	@Query(value = "SELECT * FROM HD4DCDHH.CADTAXA AS cad WHERE cad.CDMOED = :cdmoed ORDER BY TO_DATE(VIGFIM, 'YYYYMMDD') DESC FETCH FIRST 1 ROW ONLY", nativeQuery = true)
	Optional<Cadtaxa> getVigente(String cdmoed);
	

	@Query(value = "SELECT dcr.TAXAMANUAL FROM HD4DCDHH.DCRREGRA as dcr WHERE STSCONFIG = 1 FETCH FIRST 1 ROW ONLY", nativeQuery = true)
	Optional<Integer> getTaxaManual();

	
	@Query(value = """
	SELECT *  
	FROM   HD4DCDHH.CADTAXA AS cad 
	WHERE  cad.CDMOED = 'USD' and
		   vigini = year(CURRENT TIMESTAMP)||right('00'||month(CURRENT TIMESTAMP),2)||right('00'||day(CURRENT TIMESTAMP),2)
	Fetch first 1 row only 			
	""", nativeQuery = true)
	Optional<Cadtaxa> getTaxaDoDia(String cdmoed);




}
