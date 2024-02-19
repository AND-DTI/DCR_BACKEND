package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrlayout;
import com.dcr.api.model.dto.DcrlayoutDTO;
import com.dcr.api.model.keys.DcrlayoutKey;
import com.dcr.api.repository.as400.DcrlayoutRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrlayoutService {
	@Autowired
	DcrlayoutRepository repository;
	
	public Optional<Dcrlayout> getById(DcrlayoutKey key) {
		return repository.findById(key);
	}
	
	
	public Dcrlayout create(DcrlayoutDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrlayout dcr = new Dcrlayout();
		
		DcrlayoutKey key = new DcrlayoutKey();
		key.setCampo(dto.campo());
		key.setIdreg(dto.idreg());
		
		dcr.setKey(key);
		
		dcr.setCampodesc(dto.campodesc());
		dcr.setCampotam(dto.campotam());
		dcr.setDescreg(dto.descreg());
		dcr.setObrig(dto.obrig());
		dcr.setPosfim(dto.posfim());
		dcr.setPosini(dto.posini());
		dcr.setRegra(dto.regra());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrlayout update(DcrlayoutDTO dto, Dcrlayout dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		
		
		dcr.setCampodesc(dto.campodesc());
		dcr.setCampotam(dto.campotam());
		dcr.setDescreg(dto.descreg());
		dcr.setObrig(dto.obrig());
		dcr.setPosfim(dto.posfim());
		dcr.setPosini(dto.posini());
		dcr.setRegra(dto.regra());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrlayout> getAll() {
		
		return repository.findAll();
	}

}
