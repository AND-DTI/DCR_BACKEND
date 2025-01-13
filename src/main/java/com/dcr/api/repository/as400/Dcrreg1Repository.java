package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.keys.Dcrreg1Key;


public interface Dcrreg1Repository extends JpaRepository<Dcrreg1, Dcrreg1Key>{

	 @Query(value = "SELECT * FROM HD4DCDHH.DCRREG1 AS dcr WHERE dcr.idmatriz = :idmatriz AND dcr.partnumpd = :partnumpd AND dcr.tpprd = :tpprd", nativeQuery = true)
	  List<Dcrreg1> consultaByIds(Integer idmatriz, String partnumpd, String tpprd);

}
