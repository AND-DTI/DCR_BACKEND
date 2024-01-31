package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.as400.Cadtpprd;
import com.dcr.api.model.dto.CadppbDTO;
import com.dcr.api.model.dto.CadtpprdDTO;
import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.repository.as400.CadtpprdRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadtpprdService {

	@Autowired
	CadtpprdRepository repository;
	

	public List<Cadtpprd> getAll() {
	
		return repository.findAll();
	}
	
	public Optional<Cadtpprd> getByID(String tpprd) {
		
		return repository.findById(tpprd);
	}
	
	public void delete(Cadtpprd ppb) {
		
		repository.delete(ppb);
	}
	
	public Cadtpprd create(CadtpprdDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Cadtpprd ppb = new Cadtpprd();
		
		ppb.setDscing(dto.dscing());
		ppb.setDscpor(dto.dscpor());
		ppb.setTpprd(dto.tpprd());
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
	
	public Cadtpprd update(Cadtpprd ppb,  CadtpprdDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		ppb.setDscing(dto.dscing());
		ppb.setDscpor(dto.dscpor());
		ppb.setTpprd(dto.tpprd());
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
}
