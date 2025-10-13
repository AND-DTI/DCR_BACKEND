package com.dcr.api.repository.as400;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Cadppbtp;



public interface CadppbtpRepository extends JpaRepository<Cadppbtp, String>{



	@Query(value = """
	Select * from HD4DCDHH.CADPPBTP where tpprd = :tpprd
	order by int(viginippb) desc
	fetch first 1 row only 
	""", nativeQuery = true)
	List<Cadppbtp> findPPBVigente(String tpprd);


	@Transactional
	@Modifying
	@Query(value = """
	INSERT into HD4DCDHH.CADPPB (
	  partnumpd, tpprd, desccom, descrfb, prddest, ppbprd,
	  itaudsys, itaudusr, itaudhst, itauddt, itaudhr
	)
	SELECT  
	  produto, tpprd, desc1, desc2, ppb.prddest, ppb.ppbprd,
	  CAST(:sysname as char(40)), CAST(:usrint as char(10)), CAST(:host as char(30)),
	  VARCHAR_FORMAT(CURRENT TIMESTAMP, 'YYYYMMDD') dtatual,
	  CHAR(TIME(CURRENT TIMESTAMP),JIS) hratual  
	FROM 
	  (select 
	     CAST(:partnumpd as char(25)) as produto, x.tpprd, 
		 CAST(:desccom as char(150)) as desc1, CAST(:descrfb as char(80)) as desc2, 		 		 
	     x.ppbprd, x.descppb, x.prddest, viginippb,
	     rownumber() over(partition by x.tpprd order by int(viginippb) desc) as ln_ppb  
	   from   
	     HD4DCDHH.CADPPBTP x where x.tpprd= :tpprd
	  )ppb   
	WHERE 
	  ln_ppb = 1  
	  and not exists(
		select * from HD4DCDHH.CADPPB x
		where x.tpprd= ppb.tpprd and x.partnumpd= ppb.produto
	  )
	""", nativeQuery = true)
	int associaProdutoPPB(String partnumpd, String tpprd, String desccom, String descrfb, String sysname, String usrint, String host);


}
