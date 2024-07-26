package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.keys.PendprodKey;



public interface PendprodRepository extends JpaRepository<Pendprod, PendprodKey>{

	@Query(value = "SELECT * FROM HD4DCDHH.PENDPROD a WHERE a.cdpend = :cdpend", nativeQuery = true)
	List<Pendprod> findByCdPend(String cdpend);	
	
	@Query(value = "SELECT * FROM HD4DCDHH.PENDPROD a WHERE a.idmatriz = :idmatriz and a.partnumpd = :partnumpd and a.status = 0", nativeQuery = true)
	List<Pendprod> findPendenciasZero(Long idmatriz, String partnumpd);

	@Query(value = "SELECT max(numpend) FROM HD4DCDHH.PENDPROD WHERE idmatriz= :idmatriz and partnumpd= :partnumpd", nativeQuery = true)
	Integer getLastPend(Integer idmatriz, String partnumpd);

}
