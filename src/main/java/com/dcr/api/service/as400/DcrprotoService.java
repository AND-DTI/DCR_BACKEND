package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrproto;
import com.dcr.api.model.dto.DcrprotoDTO;
import com.dcr.api.model.dto.GeraDiagnosticoDTO;
import com.dcr.api.model.dto.GeraRegistroDTO;
import com.dcr.api.repository.as400.DcrprotoRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrprotoService {

	@Autowired
	DcrprotoRepository repository;
	
	public Dcrproto create(DcrprotoDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrproto dcr = new Dcrproto();
		
		dcr.setDtenvio(dto.dtenvio());
		dcr.setHrenvio(dto.hrenvio());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setProtdcre(dto.protdcre());
		dcr.setRepreenvio(dto.repreenvio());
		dcr.setTpenvio(dto.tpenvio());
		dcr.setTpprd(dto.tpprd());
		dcr.setStatus(dto.status());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrproto create(GeraDiagnosticoDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrproto dcr = new Dcrproto();
		
		dcr.setDtenvio(dto.dtenvio());
		dcr.setHrenvio(dto.hrenvio());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setProtdcre(dto.protdcre());
		dcr.setRepreenvio(dto.repreenvio());
		dcr.setTpenvio(dto.tpenvio());
		dcr.setTpprd(dto.tpprd());
		dcr.setStatus(dto.status());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrproto create(GeraRegistroDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrproto dcr = new Dcrproto();
		
		dcr.setDtenvio(dto.dtenvio());
		dcr.setHrenvio(dto.hrenvio());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setProtdcre(dto.protdcre());
		dcr.setRepreenvio(dto.repreenvio());
		dcr.setTpenvio(dto.tpenvio());
		dcr.setTpprd(dto.tpprd());
		dcr.setStatus(dto.status());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrproto update(Dcrproto dcr, DcrprotoDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setDtenvio(dto.dtenvio());
		dcr.setHrenvio(dto.hrenvio());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setRepreenvio(dto.repreenvio());
		dcr.setTpenvio(dto.tpenvio());
		dcr.setTpprd(dto.tpprd());
		dcr.setStatus(dto.status());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrproto update(Dcrproto dcr, GeraDiagnosticoDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setDtenvio(dto.dtenvio());
		dcr.setHrenvio(dto.hrenvio());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setRepreenvio(dto.repreenvio());
		dcr.setTpenvio(dto.tpenvio());
		dcr.setTpprd(dto.tpprd());
		dcr.setStatus(dto.status());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrproto> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrproto> getByKey(String id) {
		return repository.findById(id);
	}
	
	public Optional<Dcrproto> getByProduto(Long idmatriz, String tpprd, String partnumpd) {
		return repository.consultaByProduto(idmatriz, tpprd, partnumpd);
	}
	
	public void delete(Dcrproto dcr) {
		repository.delete(dcr);
	}
}
