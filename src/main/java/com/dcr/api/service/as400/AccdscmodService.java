package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accdscmod;
import com.dcr.api.model.dto.AccdscmodDTO;
import com.dcr.api.model.keys.AccdscmodKey;
import com.dcr.api.repository.as400.AccdscmodRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccdscmodService {

	@Autowired
	AccdscmodRepository repository;
	
	public List<Accdscmod> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Accdscmod> getByID(AccdscmodKey key) {
		
		return repository.findById(key);
	}
	
	public void delete(Accdscmod ppb) {
		
		repository.delete(ppb);
	}
	
	public Accdscmod create(AccdscmodDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accdscmod oper = new Accdscmod();
		AccdscmodKey key = new AccdscmodKey();
		
		key.setCdmodule(dto.cdmodule());
		key.setCodidioma(dto.codidioma());
		key.setCdsys("NEW_DCR");
		oper.setKey(key);
		oper.setDescmod(dto.descmod());
		oper.setShorttitle(dto.shorttitle());
		oper.setSubtitle(dto.subtitle());
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
	
	public Accdscmod update(Accdscmod oper,  AccdscmodDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		oper.setDescmod(dto.descmod());
		oper.setShorttitle(dto.shorttitle());
		oper.setSubtitle(dto.subtitle());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
}
