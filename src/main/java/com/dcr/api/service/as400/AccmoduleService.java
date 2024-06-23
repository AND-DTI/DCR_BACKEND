package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accmodule;
import com.dcr.api.model.dto.AccmoduleDTO;
import com.dcr.api.model.keys.AccModuleKey;
import com.dcr.api.repository.as400.AccmoduleRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccmoduleService {

	@Autowired
	AccmoduleRepository repository;
	
	public List<Accmodule> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Accmodule> getByID(AccModuleKey key) {
		
		return repository.findById(key);
	}
	
	public void delete(Accmodule ppb) {
		
		repository.delete(ppb);
	}
	
	public Accmodule create(AccmoduleDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accmodule oper = new Accmodule();
		AccModuleKey key = new AccModuleKey();
		key.setCdsys("NEW_DCR");
		key.setCdmodule(dto.cdmodule());
		
		oper.setKey(key);
		oper.setNamemodule(dto.namemodule());
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
	
	public Accmodule update(Accmodule oper,  AccmoduleDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		oper.setNamemodule(dto.namemodule());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
}
