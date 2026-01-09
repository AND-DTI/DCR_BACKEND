package com.dcr.api.repository.as400;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dcr.api.model.as400.Dcrrpa;
//import java.util.Optional;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import jakarta.transaction.Transactional;


public interface DcrrpaRepository extends JpaRepository<Dcrrpa, String>{

	//@Query("SELECT p FROM Dcrapi p WHERE p.stsconfig = 1")
	//Optional<Dcrrpa> findAtivo();
	
	
	/*@Modifying
	@Transactional
	@Query(value = """
			
	""";
	, nativeQuery = true)
	void updateStsconfigAndConfvigfim(@Param("confvigini") String confvigini, @Param("confvigfim") String confvigfim, @Param("stsconfig") int stsconfig, @Param("confvigfimNew") String confvigfimNew);*/

}
