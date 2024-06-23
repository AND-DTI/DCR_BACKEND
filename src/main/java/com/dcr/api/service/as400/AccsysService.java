package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accsys;
import com.dcr.api.model.dto.AccsysDTO;
import com.dcr.api.repository.as400.AccsysRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccsysService {

	@Autowired
	AccsysRepository repository;
	
	public List<Accsys> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Accsys> getByID(String cmoed) {
		
		return repository.findById(cmoed);
	}
	
	public void delete(Accsys ppb) {
		
		repository.delete(ppb);
	}
	
	public Accsys create(AccsysDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accsys sys = new Accsys();
		
		sys.setCdsys(dto.cdsys());
		sys.setDescsys(dto.descsys());
		sys.setNamesys(dto.descsys());
		Auxiliar.preencheAuditoria(sys, request);
		return repository.save(sys);
	}
	
	public Accsys update(Accsys sys,  AccsysDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
	
		sys.setDescsys(dto.descsys());
		sys.setNamesys(dto.descsys());
		Auxiliar.preencheAuditoria(sys, request);
		return repository.save(sys);
	}
}
