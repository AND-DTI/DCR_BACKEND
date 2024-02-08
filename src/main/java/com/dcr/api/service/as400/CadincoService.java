package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Cadinco;
import com.dcr.api.model.dto.CadincoDTO;
import com.dcr.api.repository.as400.CadincoRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadincoService {
 
	@Autowired
	CadincoRepository repository;
	
public List<Cadinco> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Cadinco> getByID(String cmoed) {
		
		return repository.findById(cmoed);
	}
	
	public void delete(Cadinco ppb) {
		
		repository.delete(ppb);
	}
	
	public Cadinco create(CadincoDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Cadinco inco = new Cadinco();
		
		inco.setCodinco(dto.codinco());
		inco.setDscinco(dto.dscinco());
		inco.setFrtembut(dto.frtembut());
		
		Auxiliar.preencheAuditoria(inco, request);
		return repository.save(inco);
	}
	
	public Cadinco update(Cadinco inco,  CadincoDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		inco.setDscinco(dto.dscinco());
		inco.setFrtembut(dto.frtembut());
		Auxiliar.preencheAuditoria(inco, request);
		return repository.save(inco);
	}
}
