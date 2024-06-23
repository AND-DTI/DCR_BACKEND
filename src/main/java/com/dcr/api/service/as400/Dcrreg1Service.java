package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.dto.Dcrreg1DTO;
import com.dcr.api.model.keys.Dcrreg1Key;
import com.dcr.api.repository.as400.Dcrreg1Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class Dcrreg1Service {

	@Autowired
	Dcrreg1Repository repository;
	
	public Dcrreg1 create(Dcrreg1DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrreg1 dcr = new Dcrreg1();
		
		Dcrreg1Key key = new Dcrreg1Key();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setModelo(dto.modelo());
		key.setTpprd(dto.tpprd());
		
		dcr.setKey(key);
		
		dcr.setCodint(dto.codint());
		dcr.setDescricao(dto.descricao());
		dcr.setIdreg(dto.idreg());
		dcr.setPreco(dto.preco());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrreg1 update(Dcrreg1DTO dto, Dcrreg1 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setCodint(dto.codint());
		dcr.setDescricao(dto.descricao());
		dcr.setIdreg(dto.idreg());
		dcr.setPreco(dto.preco());
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrreg1> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrreg1> getByKey(Dcrreg1Key dto) {

		return repository.findById(dto);
	}
	
	public List<Dcrreg1> getByIds(Integer idmatriz, String partnumpd, String tpprd) {

		return repository.consultaByIds(idmatriz, partnumpd, tpprd);
	}
	
}
