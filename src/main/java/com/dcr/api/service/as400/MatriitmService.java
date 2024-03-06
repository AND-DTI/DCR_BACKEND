package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matriitm;
import com.dcr.api.model.dto.MatriitmDTO;
import com.dcr.api.repository.as400.MatriitmRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MatriitmService {

	@Autowired
	MatriitmRepository repository;
	
	public List<Matriitm> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Matriitm> getByID(Integer id) {
		
		return repository.findById(id);
	}
	
	public void delete(Matriitm matriz) {
		
		repository.delete(matriz);
	}
	
	public Matriitm create(MatriitmDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Matriitm matriz = new Matriitm();
		
		matriz.setIdmatriz(dto.idmatriz());
		matriz.setCodcor(dto.codcor());
		matriz.setModelo(dto.modelo());
		matriz.setPartdesc(dto.partdesc());
		matriz.setPartnumpd(dto.partnumpd());
		matriz.setUnmed(dto.unmed());
		matriz.setPriocor(dto.priocor());
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
	
	public Matriitm update(Matriitm matriz,  MatriitmDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		matriz.setCodcor(dto.codcor());
		matriz.setModelo(dto.modelo());
		matriz.setPartdesc(dto.partdesc());
		matriz.setPartnumpd(dto.partnumpd());
		matriz.setUnmed(dto.unmed());
		matriz.setPriocor(dto.priocor());
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
}
