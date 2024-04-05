package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrcorsip;
import com.dcr.api.model.keys.DcrcorsipKey;
import com.dcr.api.repository.as400.DcrcorsipRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrcorsipService {
	
	@Autowired
	DcrcorsipRepository repository;
	
	public List<Dcrcorsip> getAll(){
		return repository.findAll();
	}
	
	public List<Dcrcorsip> getByCnpj(String cnpjext){
		return repository.getByCnpj(cnpjext);
	}
	
	public Optional<Dcrcorsip> getByID(DcrcorsipKey key){
		return repository.findById(key);
	}
	
	public Dcrcorsip create(DcrcorsipKey key, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		Dcrcorsip dcr = new Dcrcorsip();
		
		dcr.setKey(key);
		
		Auxiliar.preencheAuditoria(dcr, request);
		return repository.save(dcr);
	}
	
	public Dcrcorsip update(DcrcorsipKey key, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		Dcrcorsip dcr = new Dcrcorsip();
		
		dcr.setKey(key);
		
		Auxiliar.preencheAuditoria(dcr, request);
		return repository.save(dcr);
	}
	
	public void delete(Dcrcorsip dcr) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		repository.delete(dcr);
	}
}
