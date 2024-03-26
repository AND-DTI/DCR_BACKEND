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

import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.service.as400.ScheduleService;
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
        scheduler.scheduleAtFixedRate(this::gerarArquivo, 0, 30, TimeUnit.MINUTES);
	}
	 
	public void gerarArquivo() {
		try {
				List<Object[]> lista = service.getMatriprdWithNotInDcrprocc();
				FileWriter fw;
				 
				fw = new FileWriter("schedule.txt");
				
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
				
	}
}
