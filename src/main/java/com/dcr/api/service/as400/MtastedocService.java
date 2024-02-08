package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Mtastedoc;
import com.dcr.api.model.dto.MtastedocDTO;
import com.dcr.api.model.keys.MtastedocKey;
import com.dcr.api.repository.as400.MtastedocRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MtastedocService {

	@Autowired
	MtastedocRepository repository;
	
	public List<Mtastedoc> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Mtastedoc> getByID(MtastedocKey id) {
		
		return repository.findById(id);
	}
	
	public void delete(Mtastedoc matriz) {
		
		repository.delete(matriz);
	}
	
	public Mtastedoc create(MtastedocDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Mtastedoc matriz = new Mtastedoc();
		
		MtastedocKey key = new MtastedocKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnum(dto.partnum());
		key.setTpdoc(dto.tpdoc());
		
		matriz.setKey(key);
		matriz.setEmidoc(dto.emidoc());
		matriz.setEmidoc2(dto.emidoc2());
		matriz.setEmidocnew(dto.emidocnew());
		matriz.setNumdoc(dto.numdoc());
		matriz.setNumdoc2(dto.numdoc2());
		matriz.setNumdocnew(dto.numdocnew());
		matriz.setSerdoc(dto.serdoc());
		matriz.setSerdoc2(dto.serdoc2());
		matriz.setSerdocnew(dto.serdocnew());
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
	
	public Mtastedoc update(Mtastedoc matriz,  MtastedocDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		matriz.setEmidoc(dto.emidoc());
		matriz.setEmidoc2(dto.emidoc2());
		matriz.setEmidocnew(dto.emidocnew());
		matriz.setNumdoc(dto.numdoc());
		matriz.setNumdoc2(dto.numdoc2());
		matriz.setNumdocnew(dto.numdocnew());
		matriz.setSerdoc(dto.serdoc());
		matriz.setSerdoc2(dto.serdoc2());
		matriz.setSerdocnew(dto.serdocnew());
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
}
