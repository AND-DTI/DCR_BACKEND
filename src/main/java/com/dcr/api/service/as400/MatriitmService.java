package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matriitm;
import com.dcr.api.model.dto.MatriitmDTO;
import com.dcr.api.model.keys.MatriitmKey;
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
	
	public Optional<Matriitm> getByID(MatriitmKey id) {
		
		return repository.findById(id);
	}
	
	public void delete(Matriitm matriz) {
		
		repository.delete(matriz);
	}
	
	public Matriitm create(MatriitmDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Matriitm matriz = new Matriitm();
		MatriitmKey key = new MatriitmKey();
		key.setIdmatriz(dto.idmatriz());
		key.setModelo(dto.modelo());
		key.setPartnumpd(dto.partnumpd());
		
		matriz.setKey(key);
		matriz.setCodcor(dto.codcor());
		matriz.setPartdesc(dto.partdesc());
		matriz.setUnmed(dto.unmed());
		matriz.setPriocor(dto.priocor());
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
	
	public Matriitm createComCor(MatriitmDTO dto, HttpServletRequest request, Integer idMatriz) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Matriitm matriz = new Matriitm();
		MatriitmKey key = new MatriitmKey();
		key.setIdmatriz(idMatriz);
		key.setModelo(dto.modelo());
		key.setPartnumpd(dto.partnumpd());
		
		matriz.setKey(key);
		matriz.setCodcor(dto.codcor());
		matriz.setPartdesc(dto.partdesc());
		matriz.setUnmed(dto.unmed());
		matriz.setPriocor(dto.priocor());
		matriz.setNcm(dto.ncm()); //j4 - null value controled by entity
		matriz.setPreco(0.);
		matriz.setUndcom("");				
		Auxiliar.preencheAuditoria(matriz, request);
		
		return repository.save(matriz);
	}
	
	public Matriitm update(Matriitm matriz,  MatriitmDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		matriz.setCodcor(dto.codcor());
		matriz.setPartdesc(dto.partdesc());
		matriz.setUnmed(dto.unmed());
		matriz.setPriocor(dto.priocor());
		matriz.setNcm(dto.ncm()); //j4 - null value controled by entity - update only by replication routines
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}

	public Matriitm save(Matriitm matriz){

		return repository.save(matriz);

	}
}
