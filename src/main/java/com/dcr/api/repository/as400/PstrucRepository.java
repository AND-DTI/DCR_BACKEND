package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Pstruc;




public interface PstrucRepository extends JpaRepository<Pstruc, String>{

	@Query(value = 
	          "Select \r\n"
			+ "  pinbr, usrs1, cinbr, edatm, edato, mdate \r\n"			
			+ "From \r\n"
			+ "  AMFLIBD.PSTRUC \r\n"			
			+ "Where \r\n"
			+ "  substring(pinbr,1,5) = '00000' and pinbr= :partnumber", nativeQuery = true)
	List<Pstruc> pegaProdutoAcabado(String partnumber/*, String tpprd*/);
	
	  
	@Query(value = 
			  "Select \r\n"
			+ "  pinbr, usrs1, cinbr, edatm, edato, mdate \r\n"			
			+ "From \r\n"
			+ "  AMFLIBD.PSTRUC \r\n"			
			+ "Where \r\n"
			+ "  substring(pinbr,1,5) <> '00000' and pinbr= :partnumber", nativeQuery = true)
	List<Pstruc> pegaASTEC(String partnumber/*, String tpprd*/);


	//List<ProdsProjection> pegaASTEC(List<String> tpprdList);
}