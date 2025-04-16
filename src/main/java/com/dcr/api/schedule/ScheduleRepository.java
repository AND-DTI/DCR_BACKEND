package com.dcr.api.schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.dcr.api.model.as400.Cadppb;




public interface ScheduleRepository extends JpaRepository<Cadppb, String>{



	@Query(value = 
	        " Select count(*) from HD4DCDHH.CADPPB \r\n"			
		   +" Where   flex4flw = 'PENDENTE CHECK MODELO'"
		   , nativeQuery = true)
	int produtosPendentes();


	@Transactional
	@Modifying
	@Query(value = "call HDCR006C('DCRBACKEND')", nativeQuery = true)
	int atualizaProdutoAcabado();
	
	 
	//Para cada matriz pendente de inserção PROCC
	//--> Recalcula matriz avulso 
	//   --> checar se nao tem processamento geral em anadmento (DCRMODELO, DCRASTEC)
	//   --> Checar se usuario que cadastrou avulso não tem JOB em andamento (HDCR004C->5Q já checa)
	@Transactional
	@Modifying
	@Query(value = 
	       "call HDCR004C( CAST(:TPPRD as char(3)), CAST(:USERSYS as char(10)), CAST(:IDMATRIZ as char(10)) )"
	, nativeQuery = true)
    int explodeMatrizAvulsa(@Param("TPPRD") String tpprd, @Param("USERSYS") String usersys, @Param("IDMATRIZ") String idmatriz);


	@Transactional
	@Modifying
	@Query(value = 
	       "call HDCR003C( CAST(:TPPRD as char(3)), CAST(:IDMATRIZ as char(10)), CAST(:USERSYS as char(10)), CAST(:TPPROC as char(3)))"
	, nativeQuery = true)
    int reprocessaPendencias(@Param("TPPRD") String tpprd, @Param("IDMATRIZ") String idmatriz, @Param("USERSYS") String usersys, @Param("TPPROC") String procStep);
	//CALL PGM(LPDPGICE/HDCR003C) PARM('PRD' '94        ' 'SB037635  ' 'PEN')


	//--> Reprocessa Matrizes avulsas de 5 em 5 Min (se explosao finalizou - CL já faz checagem):
	//obs.: CL processa todas com FLEX4FLW = 'MATRIZ PENDENTE (AVULSA)'
	@Transactional
	@Modifying
	@Query(value = 
	       "call HDCR005C( CAST(:TPPRD as char(3)) )"
	, nativeQuery = true)
    int reprocessaAvulsa(@Param("TPPRD") String tpprd);
    


	/*@Transactional
	@Modifying	
	@Query(value = cmd, nativeQuery = true)
	int callCL(String cmd);/* */

	


}



//teste procedure: didn't work
//@Procedure(value = "select HDCR004C(:TpPrd, 'DCRMODELO ', :idMatriz)
//@Query(value = "select HDCR004C(:TpPrd, 'DCRMODELO ', :idMatriz) from sysibm.sysdummy1", nativeQuery = true)
//@Query(value = "select HDCR004C(cast(tp as char(3)), 'DCRMODELO ', cast(id as char(10)) ) from "+
//	           "(select :TpPrd as tp, :idMatriz as id from sysibm.sysdummy1)", nativeQuery = true)
//int explodeMatrizAvulsa(String TpPrd, String idMatriz);
//int explodeMatrizAvulsa(@Param("TpPrd") String TpPrd, @Param("idMatriz") String idMatriz);	
//@Procedure(name = "HDCR004C")

/*@Query(value = 
			"Update HD4DCDHH.CADPPB set \r\n"
		+ "  flex4flw = 'PENDENTE CHECK MODELO-' \r\n"			
		+ "Where  \r\n"
		+ "  flex4flw = 'PENDENTE CHECK MODELO' ", nativeQuery = true)*/

//int explodeMatrizAvulsa(@Param("tpprd") String tpprd);

//@Query(value = "update HD4DCDHH.MATRIPRD set flex5flw= 'call HDCR004C(#:TpPrd# #DCRMODELO # #:idMatriz#)' where idmatriz=92", nativeQuery = true)
//@Query(value = "call HDCR004C('PRD', 'DCRMODELO ', '999')", nativeQuery = true)
