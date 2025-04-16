package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.keys.PendprodKey;



public interface PendprodRepository extends JpaRepository<Pendprod, PendprodKey>{

	@Query(value = "SELECT * FROM HD4DCDHH.PENDPROD a WHERE a.cdpend = :cdpend", nativeQuery = true)
	List<Pendprod> findByCdPend(String cdpend);	
	
	@Query(value = "SELECT * FROM HD4DCDHH.PENDPROD a WHERE a.idmatriz = :idmatriz and a.partnumpd = :partnumpd and a.status = 0", nativeQuery = true)
	List<Pendprod> findPendenciasZero(Long idmatriz, String partnumpd);

	@Query(value = "SELECT nvl(max(numpend),0) FROM HD4DCDHH.PENDPROD WHERE idmatriz= :idmatriz and partnumpd= :partnumpd", nativeQuery = true)
	Integer getLastPend(Integer idmatriz, String partnumpd);

	@Transactional
	@Modifying
	@Query(value = """
	delete from HD4DCDHH.PENDPROD where idmatriz= :idmatriz and partnumpd= :partnumpd and cdpend='END'
	""", nativeQuery = true)
	int removePend_END(Integer idmatriz, String partnumpd);

	@Transactional
	@Modifying
	@Query(value = """
	update HD4DCDHH.PENDPROD set flex4flw= :protocolo 
	where idmatriz= :idmatriz and partnumpd= :partnumpd and flex3flw= 'DIAG' and status= 0
	""", nativeQuery = true)
	int vinculaProtocolo(Integer idmatriz, String partnumpd, String protocolo);


}
