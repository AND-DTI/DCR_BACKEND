package com.dcr.api.schedule;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import com.dcr.api.utils.Auxiliar;
import jakarta.annotation.PostConstruct;



@Component
@EnableScheduling
public class ScheduleController {


	@Autowired
	ScheduleService service;
	

	@PostConstruct
	public void startSchedule() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		//scheduler.scheduleAtFixedRate(this::atualizaProduto, 0, 10, TimeUnit.MINUTES);
        //scheduler.scheduleAtFixedRate(this::explodeMatrizAvulsa, 0, 10, TimeUnit.MINUTES);
		//scheduler.scheduleAtFixedRate(this::reprocessaMatrizAvulsa, 0, 10, TimeUnit.MINUTES);
        //scheduler.scheduleAtFixedRate(this::verificarPendencias, 0, 30, TimeUnit.MINUTES);
	}


	public void atualizaProduto() { //add call online - when create new ppb - set this to 1 hour to correct the ones not updated/called
		
		try {

			
			FileWriter fws = new FileWriter("logs/atualizacaoProduto.txt");
	        BufferedWriter bws = new BufferedWriter(fws); 
	        StringBuffer sbs = new StringBuffer();
			sbs.append(" Arquivo gerado em " + Auxiliar.getDtHrFormated() );

			int pendentes = service.getProdutosPendentes();				        
	        sbs.append("\n Produtos pendentes de atualizacao (HDCR006C) --> " + pendentes); 

			if (pendentes > 0){
				sbs.append("\n Calling HDCR006C...");
				service.atualizaProdutoAcabado();				
			}
				    
	        bws.write(sbs.toString());
	        bws.close();
	        System.out.println("Schedule Atualização Produto processado com sucesso!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}



	public void explodeMatrizAvulsa() { //add call online - when create new ppb - set this to 1 hour to correct the ones not updated/called
		
		try {

			
			FileWriter fws = new FileWriter("logs/explosaoMatrizAvulsa.txt");
	        BufferedWriter bws = new BufferedWriter(fws); 
	        StringBuffer sb = new StringBuffer();
			sb.append(" Arquivo gerado em " + Auxiliar.getDtHrFormated() );
			
			List<Object[]> lista = service.getMatriprdWithNotInDcrprocc();			
			sb.append("\n Matrizes avulsas pendentes de explosao: " + lista.size() );

			String idMatriz = "";
			for (Object[] objects : lista) {
				idMatriz = objects[0].toString();
				String tpprd = objects[6].toString();
				tpprd = tpprd.equals("PC")? "AST" : "PRD";
				String user = objects[13].toString();
				sb.append("\n Calling HDCR004C('"+tpprd+"' 'DCRMODELO '"+idMatriz+"')...");
				//PGM(LPDPGICE/HDCR004C) PARM('PRD' 'DCRMODELO ' '88        ')
				service.explodeMatrizAvulsa(tpprd, user, idMatriz);
				break; //envia apenas uma matriz
			}
	        sb.append("\n Matriz enviada p/ explosao (HDCR0064) --> " + idMatriz); 
   
	        bws.write(sb.toString());
	        bws.close();
	        System.out.println("Schedule Explosão Matriz Avulsa processado com sucesso!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


	//recalcula avulsas apos explosao:
	//MATRIPRD.FLEX4FLW = 'MATRIZ PENDENTE (AVULSA)'
	public void reprocessaMatrizAvulsa() { 
		
		try {

			
			FileWriter fws = new FileWriter("logs/processamentoMatrizAvulsa.txt");
	        BufferedWriter bws = new BufferedWriter(fws); 
	        StringBuffer sbs = new StringBuffer();
			sbs.append(" Arquivo gerado em " + Auxiliar.getDtHrFormated() );

			
			sbs.append("\n Calling HDCR005C...");
			service.reprocessaMatrizAvulsa("DCRMODELO"); //pegar do usuário que gerou a avulsa			
							    
	        bws.write(sbs.toString());
	        bws.close();
	        System.out.println("Schedule Reprocessamento Matriz Avulsa processado com sucesso!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	




	public void verificarPendencias() {
		
		try {

			List<Object[]> lista = service.getPendenciasCadastro();
			FileWriter fws;			 
			fws = new FileWriter("logs/pendencias.txt");
			
	        BufferedWriter bws = new BufferedWriter(fws); 
	        StringBuffer sbs = new StringBuffer();
	        sbs.append(" Arquivo gerado em " + Auxiliar.getDtHrFormated() );
	        sbs.append("\n Quantidade de registros: " + lista.size() );
	        for (Object[] objects : lista) {
	        	sbs.append("\n####################################################################");
	        	sbs.append("\n IDMATRIZ: " + objects[0]);
	        	sbs.append("\n PARTNUMPD: " + objects[1]);
	        	sbs.append("\n PARTNUM: " + objects[2]);
	        	sbs.append("\n NUMPEND: " + objects[3]);
	        	sbs.append("\n CDPEND: " + objects[4]);
	        	sbs.append("\n OBSRESOL: " + objects[5]);
	        	sbs.append("\n STATUS: " + objects[6]);
	        	sbs.append("\n ITAUDSYS: " + objects[12]);
	        	sbs.append("\n ITAUDUSR: " + objects[13]);
	        	sbs.append("\n ITAUDHST: " + objects[14]);
	        	sbs.append("\n ITAUDDT: " + objects[15]);
	        	sbs.append("\n ITAUDHR: " + objects[16]);
	        	sbs.append("\n####################################################################");					
			}
	        
	        bws.write(sbs.toString());
	        bws.close();
	        System.out.println("Arquivo Pendencias gerado com sucesso!");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}


	/*public void explodeMatrizAvulsa() {

		try {
				List<Object[]> lista = service.getMatriprdWithNotInDcrprocc();
				FileWriter fw;				
				fw = new FileWriter("logs/schedule.txt");
				
		        BufferedWriter bw = new BufferedWriter(fw); 
		        StringBuffer sb = new StringBuffer();
		        sb.append(" Arquivo gerado em " + Auxiliar.getDtHrFormated() );
		        sb.append("\n Quantidade de registros: " + lista.size() );
		        for (Object[] objects : lista) {
		        	sb.append("\n####################################################################");
		        	sb.append("\n IDMATRIZ: " + objects[0]);
		        	sb.append("\n PRODUTO: " + objects[1]);
		        	sb.append("\n MODELO: " + objects[2]);
		        	sb.append("\n ANOMDL: " + objects[3]);
		        	sb.append("\n DESCCOM: " + objects[4]);
		        	sb.append("\n DESCRFB: " + objects[5]);
		        	sb.append("\n TPPRD: " + objects[6]);
		        	sb.append("\n PROTOT: " + objects[7]);
		        	sb.append("\n SPECIAL: " + objects[8]);
		        	sb.append("\n TPDCRE: " + objects[9]);
		        	sb.append("\n ORIGPRD: " + objects[10]);
		        	sb.append("\n DTNECI: " + objects[11]);
		        	sb.append("\n PRIOURGEN: " + objects[12]);
		        	sb.append("\n PREVFAT: " + objects[13]);
		        	sb.append("\n PRIORESP: " + objects[14]);
		        	sb.append("\n PRIODTMNT: " + objects[15]);
		        	sb.append("\n PRIOHRMNT: " + objects[16]);
		        	sb.append("\n ITAUDSYS: " + objects[17]);
		        	sb.append("\n ITAUDUSR: " + objects[18]);
		        	sb.append("\n ITAUDHST: " + objects[19]);
		        	sb.append("\n ITAUDDT: " + objects[20]);
		        	sb.append("\n ITAUDHR: " + objects[21]);
		        	sb.append("\n####################################################################");					
				}
		        
		        bw.write(sb.toString());
		        bw.close();
		        System.out.println("Arquivo gerado com sucesso!");
				
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
				
	}*/
}
