package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Cadcor;
import com.dcr.api.model.dto.CadcorDTO;
import com.dcr.api.repository.as400.CadcorRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadcorService {

	@Autowired
	CadcorRepository repository;
	
	public List<Cadcor> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Cadcor> getByID(String cmoed) {
		
		return repository.findById(cmoed);
	}
	
	public void delete(Cadcor ppb) {
		
		repository.delete(ppb);
	}
	
	public Cadcor create(CadcorDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Cadcor cor = new Cadcor();
		
		cor.setCdbej(dto.cdbej());
		cor.setCodcor(dto.codcor());
		cor.setCoreng(dto.coreng());
		cor.setCorpt(dto.corpt());
		cor.setTppin(dto.tppin());
		
		Auxiliar.preencheAuditoria(cor, request);
		return repository.save(cor);
	}
	
	public Cadcor update(Cadcor cor,  CadcorDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		cor.setCdbej(dto.cdbej());
		cor.setCodcor(dto.codcor());
		cor.setCoreng(dto.coreng());
		cor.setCorpt(dto.corpt());
		cor.setTppin(dto.tppin());
		Auxiliar.preencheAuditoria(cor, request);
		return repository.save(cor);
	}
}
