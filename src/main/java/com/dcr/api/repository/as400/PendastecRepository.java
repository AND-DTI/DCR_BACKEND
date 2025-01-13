package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.keys.PendastecKey;



public interface PendastecRepository extends JpaRepository<Pendastec, PendastecKey>{


	@Query(value = "SELECT * FROM HD4DCDHH.PENDASTEC a WHERE a.cdpend = :cdpend", nativeQuery = true)
    List<Pendastec> findByCdPend(String cdpend);

	@Query(value = "SELECT * FROM HD4DCDHH.PENDASTEC a WHERE a.idmatriz = :idmatriz and a.status = 0", nativeQuery = true)
	List<Pendastec> findPendenciasZero(Long idmatriz);

	@Query(value = "SELECT nvl(max(numpend),0) FROM HD4DCDHH.PENDASTEC WHERE idmatriz= :idmatriz", nativeQuery = true)
	Integer getLastPend(Integer idmatriz);

	@Transactional
	@Modifying
	@Query(value = """
	delete from HD4DCDHH.PENDASTEC where idmatriz= :idmatriz and cdpend='END'
	""", nativeQuery = true)
	int removePend_END(Integer idmatriz);

	@Transactional
	@Modifying
	@Query(value = """
	update HD4DCDHH.PENDASTEC set flex4flw= :protocolo 
	where idmatriz= :idmatriz and flex3flw= 'DIAG' and status= 0
	""", nativeQuery = true)
	int vinculaProtocolo(Integer idmatriz, String protocolo);

}
