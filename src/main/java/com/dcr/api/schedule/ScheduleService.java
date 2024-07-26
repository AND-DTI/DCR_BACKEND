package com.dcr.api.schedule;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
//import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.repository.as400.MatriprdRepository;

@Service
public class ScheduleService {
	@Autowired
	MatriprdRepository repositoryMartiz;
	@Autowired
	ScheduleRepository repositorySchedule;

	
	public List<Object[]> getMatriprdWithNotInDcrprocc() {
		return repositoryMartiz.getMatriprdWithNotInDcrprocc();
	}
	
	public List<Object[]> getPendenciasCadastro() {
		return repositoryMartiz.getPendenciasCadastro();
	}

	
	//Create update PPB base PRODMOD, PRODFAT, VW_PRODUTOS
	public int getProdutosPendentes() {
		return repositorySchedule.produtosPendentes();
	}


	public int atualizaProdutoAcabado() {
		return repositorySchedule.atualizaProdutoAcabado();
	}


	public int explodeMatrizAvulsa(String TpPrd, String usersys, String idMatriz){

		//complete blank til 10 in idmatriz 
		idMatriz = (idMatriz+"          ").substring(0, 10);		
		usersys = StringUtils.rightPad(usersys, 10);
		return repositorySchedule.explodeMatrizAvulsa(TpPrd, usersys, idMatriz);
		//String cmd = "call HDCR004C('"+TpPrd+"' 'DCRMODELO ' '"+idMatriz+"')";
		//return repositorySchedule.callCL(cmd);
	}

	
	public int reprocessaPendencias(String TpPrd, String idMatriz, String usersys){
		
		idMatriz = StringUtils.rightPad(idMatriz, 10);
		usersys  = StringUtils.rightPad(usersys, 10);
		return repositorySchedule.reprocessaPendencias(TpPrd, idMatriz, usersys, "PEN");
				
	}	

	


	public int reprocessaMatrizAvulsa(String tpprd){
				
		return repositorySchedule.reprocessaAvulsa(tpprd);

	}
}
