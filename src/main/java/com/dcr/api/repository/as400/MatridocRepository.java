package com.dcr.api.repository.as400;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Matridoc;
import com.dcr.api.model.keys.MatridocKey;



public interface MatridocRepository extends JpaRepository<Matridoc, MatridocKey> {

	  @Query(value = "SELECT * FROM HD4DCDHH.MATRIDOC WHERE IDMATRIZ = :idmatriz AND PARTNUM = :partnum AND PARTNUMPD = :partnumpd", nativeQuery = true)
	  Optional<Matridoc> buscaDoc(Integer idmatriz, String partnum, String partnumpd);
}
