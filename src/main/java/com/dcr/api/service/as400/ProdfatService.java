package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Prodfat;
import com.dcr.api.model.dto.ProdfatDTO;
import com.dcr.api.model.keys.ProdfatKey;
import com.dcr.api.repository.as400.ProdfatRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProdfatService {
	@Autowired
	ProdfatRepository repository;
	
	public List<Prodfat> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Prodfat> getByID(ProdfatKey key) {
		
		return repository.findById(key);
	}
	
	public void delete(Prodfat pend) {
		
		repository.delete(pend);
	}
	
	public Prodfat create(ProdfatDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Prodfat prod = new Prodfat();
		ProdfatKey key = new ProdfatKey();
		
		key.setCdprd(dto.cdprd());
		key.setPartnumpd(dto.partnumpd());
		
		prod.setKey(key);
		prod.setAnomdl(dto.anomdl());
		prod.setDesccom(dto.desccom());
		prod.setFrstanofab(dto.frstanofab());
		prod.setLastanofab(dto.lastanofab());
		prod.setTpprd(dto.tpprd());
		prod.setUncome(dto.uncome());
		Auxiliar.preencheAuditoria(prod, request);
		
		return repository.save(prod);
	}
	
	public Prodfat update(Prodfat prod, ProdfatDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		prod.setAnomdl(dto.anomdl());
		prod.setDesccom(dto.desccom());
		prod.setFrstanofab(dto.frstanofab());
		prod.setLastanofab(dto.lastanofab());
		prod.setTpprd(dto.tpprd());
		prod.setUncome(dto.uncome());
		Auxiliar.preencheAuditoria(prod, request);
		
		return repository.save(prod);
	}
}
