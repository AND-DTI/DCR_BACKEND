package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.dto.DcrproccDTO;
import com.dcr.api.model.keys.DcrproccKey;
import com.dcr.api.model.projection.ResumoProjection;
import com.dcr.api.repository.as400.DcrproccRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrproccService {

	@Autowired
	DcrproccRepository repository;
	
	@Autowired
	DcrprocchService historicoService;
	
	public Dcrprocc create(DcrproccDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrprocc dcr = new Dcrprocc();
		
		DcrproccKey key = new DcrproccKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setTpprd(dto.tpprd());
		
		dcr.setKey(key);
		dcr.setStatus(dto.status());
		dcr.setDtstatus(dto.dtstatus());
		dcr.setHrstatus(dto.hrstatus());
		dcr.setRespstaus(dto.respstaus());
		
		dcr.setTpdcre(dto.tpdcre());
		dcr.setCoefred(dto.coefred());
		dcr.setCustotal(dto.custotal());
		dcr.setDtregistro(dto.dtregistro());
		dcr.setHrregistro(dto.hrregistro());
		dcr.setIireduzido(dto.iireduzido());
		dcr.setIitotal(dto.iitotal());
		dcr.setTaxausd(dto.taxausd());
		dcr.setTotalimp(dto.totalimp());
		dcr.setTotalnac(dto.totalnac());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrprocc update(DcrproccDTO dto, Dcrprocc dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setDtstatus(dto.dtstatus());
		dcr.setHrstatus(dto.hrstatus());
		dcr.setRespstaus(dto.respstaus());
	
		dcr.setTpdcre(dto.tpdcre());
		dcr.setCoefred(dto.coefred());
		dcr.setCustotal(dto.custotal());
		dcr.setDtregistro(dto.dtregistro());
		dcr.setHrregistro(dto.hrregistro());
		dcr.setIireduzido(dto.iireduzido());
		dcr.setIitotal(dto.iitotal());
		dcr.setTaxausd(dto.taxausd());
		dcr.setTotalimp(dto.totalimp());
		dcr.setTotalnac(dto.totalnac());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrprocc update(Dcrprocc dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public void delete(Dcrprocc dcr) {
		repository.delete(dcr);
	}
	public Dcrprocc setStatus(Dcrprocc dcr, Integer statusNew, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {				
	
		historicoService.setStatus(dcr, dcr.getStatus(), statusNew, request);
		dcr.setStatus(statusNew);
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrprocc> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrprocc> getByKey(DcrproccKey dto) {

		return repository.findById(dto);
	}
	
	public Optional<ResumoProjection> getResumo(Long idmatriz, String partnumpd) {

		return repository.getResumo(idmatriz, partnumpd);
	}
}
