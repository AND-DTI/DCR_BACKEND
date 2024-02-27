package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accidioma;
import com.dcr.api.model.dto.AccidiomaDTO;
import com.dcr.api.repository.as400.AccidiomaRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccidiomaService {

	@Autowired
	AccidiomaRepository repository;
	
	public List<Accidioma> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Accidioma> getByID(String id) {
		
		return repository.findById(id);
	}
	
	public void delete(Accidioma ppb) {
		
		repository.delete(ppb);
	}
	
	public Accidioma create(AccidiomaDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accidioma oper = new Accidioma();
		
		oper.setCodidioma(dto.codidioma());
		oper.setCountry(dto.country());
		oper.setDescidioma(dto.descidioma());
		oper.setFrmtdec(dto.frmtdec());
		oper.setFrmtdtext(dto.frmtdtext());
		oper.setFrmtdtresu(dto.frmtdtresu());
		oper.setFrmtmil(dto.frmtmil());
		oper.setLanguage(dto.language());
		oper.setLocal(dto.local());
		oper.setNlslang(dto.nlslang());
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
	
	public Accidioma update(Accidioma oper,  AccidiomaDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		oper.setCountry(dto.country());
		oper.setDescidioma(dto.descidioma());
		oper.setFrmtdec(dto.frmtdec());
		oper.setFrmtdtext(dto.frmtdtext());
		oper.setFrmtdtresu(dto.frmtdtresu());
		oper.setFrmtmil(dto.frmtmil());
		oper.setLanguage(dto.language());
		oper.setLocal(dto.local());
		oper.setNlslang(dto.nlslang());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
}
