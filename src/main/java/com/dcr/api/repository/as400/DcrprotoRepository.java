package com.dcr.api.repository.as400;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Dcrproto;



public interface DcrprotoRepository extends JpaRepository<Dcrproto, String>{


	@Query(value = "SELECT * FROM HD4DCDHH.DCRPROTO AS dcr WHERE dcr.idmatriz = :idmatriz AND dcr.tpprd = :tpprd AND dcr.partnumpd = :partnumpd "
	  		+ "ORDER BY TIMESTAMP_FORMAT(dcr.ITAUDDT || ' ' || dcr.ITAUDHR, 'YYYYMMDD HH24:MI:SS') DESC\r\n"
	  		+ "FETCH FIRST 1 ROW ONLY", nativeQuery = true)
	Optional<Dcrproto> consultaByProduto(Long idmatriz, String tpprd, String partnumpd);


	@Transactional
	@Modifying
	@Query(value = """
	update HD4DCDHH.DCRPROTO set TPENVIO = 'H'
	where idmatriz= :idmatriz and partnumpd= :partnumpd /*and tpprd= :tpprd*/
	""", nativeQuery = true)
	int setHistoricoProtocolo(Integer idmatriz, String partnumpd);
}
