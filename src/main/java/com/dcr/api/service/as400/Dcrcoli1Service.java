package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrcoli1;
import com.dcr.api.model.dto.Dcrcoli1DTO;
import com.dcr.api.model.dto.Dcrcoli1LoteDTO;
import com.dcr.api.model.keys.Dcrcoli1Key;
import com.dcr.api.repository.as400.Dcrcoli1Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class Dcrcoli1Service {
	@Autowired
	Dcrcoli1Repository repository;
	
	public void createLote(List<Dcrcoli1LoteDTO> lista, String dcre, String cdclient, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		for (Dcrcoli1LoteDTO dto : lista) {
			Dcrcoli1 dcr = new Dcrcoli1();
			
			Dcrcoli1Key key = new Dcrcoli1Key();
			key.setDcre(dcre);
			key.setCdclient(cdclient);
			key.setModelo(dto.modelo());
			dcr.setKey(key);
			
			Optional<Dcrcoli1> exist = this.getByKey(key);
			if(!exist.isEmpty()) {
				this.delete(exist.get(), request);
			}
				
			dcr.setIdreg(dto.idreg());
			dcr.setDescricao(dto.descricao());
			dcr.setPreco(dto.preco());
			
			Auxiliar.preencheAuditoria(dcr, request);
			repository.save(dcr);
		}
		
	}
	
	public Dcrcoli1 create(Dcrcoli1DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrcoli1 dcr = new Dcrcoli1();
		
		Dcrcoli1Key key = new Dcrcoli1Key();
		key.setDcre(dto.dcre());
		key.setCdclient(dto.cdclient());
		key.setModelo(dto.modelo());
		dcr.setKey(key);
		
		dcr.setIdreg(dto.idreg());
		dcr.setDescricao(dto.descricao());
		dcr.setPreco(dto.preco());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public void delete(Dcrcoli1 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		repository.delete(dcr);
	}
	
	public Dcrcoli1 update(Dcrcoli1DTO dto, Dcrcoli1 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setIdreg(dto.idreg());
		dcr.setDescricao(dto.descricao());
		dcr.setPreco(dto.preco());

			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrcoli1> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrcoli1> getByKey(Dcrcoli1Key dto) {

		return repository.findById(dto);
	}
}
