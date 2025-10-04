package com.dcr.api.repository.as400;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dcr.api.model.as400.Cadppbtp;





public interface CadppbtpRepository extends JpaRepository<Cadppbtp, String>{




	/*@Query(value = """
	Select * from HD4DCDHH.CADPPBTP 
	""", nativeQuery = true)
	List<Cadppbtp> findAllCategorias();*/

}
