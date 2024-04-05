package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrcors;
import com.dcr.api.model.dto.DcrcorsDTO;
import com.dcr.api.repository.as400.DcrcorsRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrcorsService {

	@Autowired
	DcrcorsRepository repository;
	
	public List<Dcrcors> getAll(){
		return repository.findAll();
	}
	
	public Optional<Dcrcors> getByID(String cnpjext){
		return repository.findById(cnpjext);
	}
	
	public Dcrcors create(DcrcorsDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		Dcrcors dcr = new Dcrcors();
		
		dcr.setCnpjext(dto.cnpjext());
		dcr.setRazsoc(dto.razsoc());
		dcr.setAccobj(dto.accobj());
		dcr.setAccemail(dto.accemail());
		dcr.setAccresp(dto.accresp());
		dcr.setAccfone(dto.accfone());
		
		Auxiliar.preencheAuditoria(dcr, request);
		return repository.save(dcr);
	}
	
	public Dcrcors update(Dcrcors dcr, DcrcorsDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		dcr.setRazsoc(dto.razsoc());
		dcr.setAccobj(dto.accobj());
		dcr.setAccemail(dto.accemail());
		dcr.setAccresp(dto.accresp());
		dcr.setAccfone(dto.accfone());
		
		Auxiliar.preencheAuditoria(dcr, request);
		return repository.save(dcr);
	}
	
	public void delete(Dcrcors dcr) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		repository.delete(dcr);
	}
}
