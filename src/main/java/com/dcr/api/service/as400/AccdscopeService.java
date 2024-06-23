package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accdscope;
import com.dcr.api.model.dto.AccdscopeDTO;
import com.dcr.api.model.keys.AccdscopeKey;
import com.dcr.api.repository.as400.AccdscopeRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccdscopeService {

	@Autowired
	AccdscopeRepository repository;
	
	public List<Accdscope> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Accdscope> getByID(AccdscopeKey key) {
		
		return repository.findById(key);
	}
	
	public void delete(Accdscope ppb) {
		
		repository.delete(ppb);
	}
	
	public Accdscope create(AccdscopeDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accdscope oper = new Accdscope();
		
		AccdscopeKey key = new AccdscopeKey();
		key.setCodidioma(dto.codidioma());
		key.setIdoper(dto.idoper());
		key.setCdsys("NEW_DCR");
		oper.setKey(key);
		oper.setDescoper(dto.descoper());
		oper.setShorttitle(dto.shorttitle());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
	
	public Accdscope update(Accdscope oper,  AccdscopeDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		oper.setDescoper(dto.descoper());
		oper.setShorttitle(dto.shorttitle());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
}
