package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.as400.Accoperol;
import com.dcr.api.model.dto.AccoperDTO;
import com.dcr.api.model.dto.AccoperolDTO;
import com.dcr.api.model.keys.AccoperolKey;
import com.dcr.api.repository.as400.AccoperRepository;
import com.dcr.api.repository.as400.AccoperolRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccoperolService {

	@Autowired
	AccoperolRepository repository;
	
	public List<Accoperol> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Accoperol> getByID(AccoperolKey key) {
		
		return repository.findById(key);
	}
	
	public void delete(Accoperol ppb) {
		
		repository.delete(ppb);
	}
	
	public Accoperol create(AccoperolDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accoperol oper = new Accoperol();
		
		AccoperolKey key = new AccoperolKey();
		key.setIdoper(dto.idoper());
		key.setRoleid(dto.roleid());
		key.setCdsys("NEW_DCR");
		oper.setKey(key);
		oper.setAction(dto.action());
		oper.setCreate(dto.create());
		oper.setDelete(dto.delete());
		oper.setRead(dto.read());
		oper.setUpdate(dto.update());
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
	
	public Accoperol update(Accoperol oper,  AccoperolDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		oper.setAction(dto.action());
		oper.setCreate(dto.create());
		oper.setDelete(dto.delete());
		oper.setRead(dto.read());
		oper.setUpdate(dto.update());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
}
