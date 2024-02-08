package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.as400.Cadcor;
import com.dcr.api.model.dto.AccoperDTO;
import com.dcr.api.model.dto.CadcorDTO;
import com.dcr.api.repository.as400.AccoperRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccoperService {

	@Autowired
	AccoperRepository repository;
	
	public List<Accoper> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Accoper> getByID(Integer id) {
		
		return repository.findById(id);
	}
	
	public void delete(Accoper ppb) {
		
		repository.delete(ppb);
	}
	
	public Accoper create(AccoperDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accoper oper = new Accoper();
		
		oper.setIdoper(dto.idoper());
		oper.setAtivo(dto.ativo());
		oper.setTpoper(dto.tpoper());
		oper.setCdmodule(dto.cdmodule());
		oper.setDescoper(dto.descoper());
		oper.setIdpai(dto.idpai());
		oper.setNivel(dto.nivel());
		oper.setRota(dto.rota());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
	
	public Accoper update(Accoper oper,  AccoperDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		oper.setAtivo(dto.ativo());
		oper.setTpoper(dto.tpoper());
		oper.setCdmodule(dto.cdmodule());
		oper.setDescoper(dto.descoper());
		oper.setIdpai(dto.idpai());
		oper.setNivel(dto.nivel());
		oper.setRota(dto.rota());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
}
