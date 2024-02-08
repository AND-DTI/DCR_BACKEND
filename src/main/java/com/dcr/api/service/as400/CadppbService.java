package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.dto.CadppbDTO;
import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.repository.as400.CadppbRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadppbService {
	
	@Autowired
	CadppbRepository repository;
	
	public List<Cadppb> getAll() {
	
		return repository.findAll();
	}
	
	public Optional<Cadppb> getByID(ProdutoKey key) {
		
		return repository.findById(key);
	}
	
	public void delete(Cadppb ppb) {
		
		repository.delete(ppb);
	}
	
	public Cadppb create(CadppbDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Cadppb ppb = new Cadppb();
		
		ProdutoKey key = new ProdutoKey();
		
		key.setCdprd(dto.cdprd());
		key.setTpprd(dto.tpprd());
		
		ppb.setKey(key);
		ppb.setDesccom(dto.desccom());
		ppb.setDescrfb(dto.descrfb());
		ppb.setPpbprd(dto.ppbprd());
		ppb.setPrddest(dto.prddest());
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
	
	public Cadppb update(Cadppb ppb,  CadppbDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		ppb.setDesccom(dto.desccom());
		ppb.setDescrfb(dto.descrfb());
		ppb.setPpbprd(dto.ppbprd());
		ppb.setPrddest(dto.prddest());
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
}
