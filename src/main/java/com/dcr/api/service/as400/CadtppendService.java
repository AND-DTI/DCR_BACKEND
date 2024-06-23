package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Cadtppend;
import com.dcr.api.model.dto.CadtppendDTO;
import com.dcr.api.repository.as400.CadtppendRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadtppendService {

	@Autowired
	CadtppendRepository repository;
	
	public List<Cadtppend> getAll() {
		
		return repository.findPendenciasAndResponsaveis();
	}
	
	public Optional<Cadtppend> getByID(String id) {
		
		return repository.findPendenciasAndResponsaveisByCdPend(id);
	}
	
	public Cadtppend create(CadtppendDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Cadtppend pend = new Cadtppend();
		
		pend.setCdpend(dto.cdpend());
		pend.setDescpend(dto.descpend());
		pend.setObspend(dto.obspend());
		pend.setTpreg(dto.tpreg());
		
		Auxiliar.preencheAuditoria(pend, request);
		
		return repository.save(pend);
	}
	
	public Cadtppend update(Cadtppend pend, CadtppendDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		pend.setDescpend(dto.descpend());
		pend.setObspend(dto.obspend());
		pend.setTpreg(dto.tpreg());
		Auxiliar.preencheAuditoria(pend, request);
		
		return repository.save(pend);
	}
	
	public void delete(Cadtppend pendencia) {
		
		repository.delete(pendencia);
	}
}
