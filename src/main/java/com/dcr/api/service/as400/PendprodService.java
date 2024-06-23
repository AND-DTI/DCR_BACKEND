package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.dto.PendprodDTO;
import com.dcr.api.model.dto.resolverPendenciaDTO;
import com.dcr.api.model.keys.PendprodKey;
import com.dcr.api.repository.as400.PendprodRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class PendprodService {

	@Autowired
	PendprodRepository repository;
	
	public List<Pendprod> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Pendprod> getByID(PendprodKey id) {
		
		return repository.findById(id);
	}
	
	public List<Pendprod> findPendenciasZero(Long idmatriz, String partnumpd) {
		
		return repository.findPendenciasZero(idmatriz, partnumpd);
	}

	public List<Pendprod> getByCdPend(String cdpend) {
		
		return repository.findByCdPend(cdpend);
	}

	public void delete(Pendprod matriz) {
		
		repository.delete(matriz);
	}
	
	public Pendprod create(PendprodDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Pendprod pend = new Pendprod();
		
		PendprodKey key = new PendprodKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnum(dto.partnum());
		key.setNumpend(dto.numpend());
		key.setPartnumpd(dto.partnumpd());
		pend.setKey(key);

		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}
	
	public Pendprod update(Pendprod pend,  PendprodDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}
	
public Pendprod resolverPendencia(Pendprod pend,  resolverPendenciaDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}
}
