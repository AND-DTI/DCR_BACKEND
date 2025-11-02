package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Dcrreg4;
import com.dcr.api.model.dto.INT.Dcrreg4INT;
import com.dcr.api.model.keys.Dcrreg4Key;



public interface Dcrreg4Repository extends JpaRepository<Dcrreg4, Dcrreg4Key>{
	
	@Query(value = """
    SELECT dcr.*
	FROM   HD4DCDHH.DCRREG4 AS dcr 
	WHERE  dcr.idmatriz= :idmatriz 
	       and dcr.partnumpd= :partnumpd 
		   and dcr.tpprd= :tpprd 
	ORDER  By dcr.numcomp
	""", nativeQuery = true)
	List<Dcrreg4> consultaByIds(Integer idmatriz, String partnumpd, String tpprd);


	@Query(value = """
    SELECT dcr.*, case when itmcoli='S' then 'COLIGADA' else '' end as obscoligada
	FROM   HD4DCDHH.DCRREG4 AS dcr 
	WHERE  dcr.idmatriz= :idmatriz 
	       and dcr.partnumpd= :partnumpd 
		   and dcr.tpprd= :tpprd 
	ORDER  By dcr.numcomp
	""", nativeQuery = true)
	List<Dcrreg4INT> consultaByIds2(Integer idmatriz, String partnumpd, String tpprd);


}
