package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrvigen;
import com.dcr.api.model.dto.DcrvigenDTO;
import com.dcr.api.repository.as400.DcrvigenRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrvigenService {

	@Autowired
	DcrvigenRepository repository;
	
	public Dcrvigen create(DcrvigenDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrvigen dcr = new Dcrvigen();
		
		dcr.setCoefred(dto.coefred());
		dcr.setCustotal(dto.custotal());
		dcr.setDcrant(dto.dcrant());
		dcr.setDcre(dto.dcre());
		dcr.setDtregistro(dto.dtregistro());
		dcr.setDtvigfim(dto.dtvigfim());
		dcr.setDtvigini(dto.dtvigini());
		dcr.setHrregistro(dto.hrregistro());
		dcr.setHrvigfim(dto.hrvigfim());
		dcr.setHrvigini(dto.hrvigini());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setIireduzido(dto.iireduzido());
		dcr.setIitotal(dto.iitotal());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setTaxausd(dto.taxausd());
		dcr.setTotalimp(dto.totalimp());
		dcr.setTotalnac(dto.totalnac());
		dcr.setTpprd(dto.tpprd());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrvigen create(Dcrvigen dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrvigen update(Dcrvigen dcr, DcrvigenDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setCoefred(dto.coefred());
		dcr.setCustotal(dto.custotal());
		dcr.setDcrant(dto.dcrant());
		dcr.setDtregistro(dto.dtregistro());
		dcr.setDtvigfim(dto.dtvigfim());
		dcr.setDtvigini(dto.dtvigini());
		dcr.setHrregistro(dto.hrregistro());
		dcr.setHrvigfim(dto.hrvigfim());
		dcr.setHrvigini(dto.hrvigini());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setIireduzido(dto.iireduzido());
		dcr.setIitotal(dto.iitotal());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setTaxausd(dto.taxausd());
		dcr.setTotalimp(dto.totalimp());
		dcr.setTotalnac(dto.totalnac());
		dcr.setTpprd(dto.tpprd());
	
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrvigen update(Dcrvigen dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrvigen> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrvigen> getByKey(String id) {
		return repository.findById(id);
	}
	
	public void delete(Dcrvigen dcr) {
		repository.delete(dcr);
	}
}
