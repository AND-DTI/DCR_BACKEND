package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matridoc;
import com.dcr.api.model.dto.MatridocDTO;
import com.dcr.api.model.keys.MatridocKey;
import com.dcr.api.repository.as400.MatridocRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MatridocService {
	
	@Autowired
	MatridocRepository repository;
	
	public List<Matridoc> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Matridoc> getByID(MatridocKey id) {
		
		return repository.findById(id);
	}
	
	public void delete(Matridoc matriz) {
		
		repository.delete(matriz);
	}
	
	public Matridoc create(MatridocDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Matridoc matriz = new Matridoc();
		
		MatridocKey key = new MatridocKey();
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
	
	public Matridoc update(Matridoc matriz,  MatridocDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
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
