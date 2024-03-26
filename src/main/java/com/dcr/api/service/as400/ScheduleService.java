package com.dcr.api.service.as400;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.repository.as400.MatriprdRepository;

public class ScheduleService {
	@Autowired
	MatriprdRepository repository;
	
	public void gerarArquivo() {
		List<Matriprd> lista = repository.getMatriprdWithNotInDcrprocc();
		
		//gerarArquivo
	}
}
