package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.dto.MtastecDTO;
import com.dcr.api.model.keys.MtastecKey;
import com.dcr.api.repository.as400.MtastecRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MtastecService {

	@Autowired
	MtastecRepository repository;
	
	public List<Mtastec> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Mtastec> getByID(MtastecKey id) {
		
		return repository.findById(id);
	}
	
	public void delete(Mtastec matriz) {
		
		repository.delete(matriz);
	}
	
	public Mtastec create(MtastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Mtastec astec = new Mtastec();
		
		MtastecKey mtastecKey = new MtastecKey();
		mtastecKey.setPartnumpd("SeuValor");
		astec.setKey(mtastecKey);

		astec.setDesccom(dto.desccom());
		astec.setDescrfb(dto.descrfb());
		astec.setDtneci(dto.dtneci());
		astec.setOrigprd(dto.origprd());
		astec.setPrevfat(dto.prevfat());
		astec.setPriodtmnt(dto.priodtmnt());
		astec.setPriohrmnt(dto.priohrmnt());
		astec.setPrioresp(dto.prioresp());
		astec.setPriourgen(dto.priourgen());
		astec.setUnmed(dto.unmed());
		
		Auxiliar.preencheAuditoria(astec, request);
		return repository.save(astec);
	}
	
	public Mtastec update(Mtastec astec,  MtastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		astec.setDesccom(dto.desccom());
		astec.setDescrfb(dto.descrfb());
		astec.setDtneci(dto.dtneci());
		astec.setOrigprd(dto.origprd());
		astec.setPrevfat(dto.prevfat());
		astec.setPriodtmnt(dto.priodtmnt());
		astec.setPriohrmnt(dto.priohrmnt());
		astec.setPrioresp(dto.prioresp());
		astec.setPriourgen(dto.priourgen());
		astec.setUnmed(dto.unmed());
		
		Auxiliar.preencheAuditoria(astec, request);
		return repository.save(astec);
	}
}
