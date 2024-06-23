package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.dto.PendastecDTO;
import com.dcr.api.model.dto.PendprodDTO;
import com.dcr.api.model.keys.PendastecKey;
import com.dcr.api.model.keys.PendprodKey;
import com.dcr.api.repository.as400.PendastecRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PendastecService {

	@Autowired
	PendastecRepository repository;
	
	public List<Pendastec> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Pendastec> getByID(PendastecKey id) {
		
		return repository.findById(id);
	}
	
	public List<Pendastec> getByCdPend(String cdpend) {
		
		return repository.findByCdPend(cdpend);
	}
	
	public void delete(Pendastec matriz) {
		
		repository.delete(matriz);
	}
	
	public Pendastec create(PendastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Pendastec pend = new Pendastec();
		
		PendastecKey key = new PendastecKey();
		key.setIdmatriz(dto.idmatriz());
		key.setNumpend(dto.numpend());
		key.setPartnum(dto.partnum());
		
		pend.setKey(key);
		pend.setCdpend(dto.cdpend());
		pend.setStatus(dto.status());
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}
	
	public Pendastec update(Pendastec pend,  PendastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		pend.setCdpend(dto.cdpend());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}
}
