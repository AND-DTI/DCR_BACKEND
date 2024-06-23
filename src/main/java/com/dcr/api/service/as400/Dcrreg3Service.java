package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.as400.Dcrreg3;
import com.dcr.api.model.dto.Dcrreg3DTO;
import com.dcr.api.model.keys.Dcrreg3Key;
import com.dcr.api.repository.as400.Dcrreg3Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class Dcrreg3Service {
	@Autowired
	Dcrreg3Repository repository;
	
	public Dcrreg3 create(Dcrreg3DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrreg3 dcr = new Dcrreg3();
		
		Dcrreg3Key key = new Dcrreg3Key();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setNumcomp(dto.numcomp());
		key.setTpprd(dto.tpprd());
		key.setNumsubcomp(dto.numsubcomp());
		
		dcr.setKey(key);
		
		dcr.setAdicao(dto.adicao());
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setDi(dto.di());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIdreg(dto.idreg());
		dcr.setIe(dto.ie());
		dcr.setIibasecalc(dto.iibasecalc());
		dcr.setImpdireta(dto.impdireta());
		dcr.setIndreducii(dto.indreducii());
		dcr.setItemadicao(dto.itemadicao());
		dcr.setNcm(dto.ncm());
		dcr.setNumnf(dto.numnf());
		dcr.setQtde(dto.qtde());
		dcr.setSernf(dto.sernf());
		dcr.setSuspens(dto.suspens());
		dcr.setUndcom(dto.undcom());
		dcr.setVlrunit(dto.vlrunit());
		dcr.setIibasecalc(dto.iibasecalc());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrreg3 update(Dcrreg3DTO dto, Dcrreg3 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		
		dcr.setAdicao(dto.adicao());
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setDi(dto.di());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIdreg(dto.idreg());
		dcr.setIe(dto.ie());
		dcr.setIibasecalc(dto.iibasecalc());
		dcr.setImpdireta(dto.impdireta());
		dcr.setIndreducii(dto.indreducii());
		dcr.setItemadicao(dto.itemadicao());
		dcr.setNcm(dto.ncm());
		dcr.setNumnf(dto.numnf());
		dcr.setQtde(dto.qtde());
		dcr.setSernf(dto.sernf());
		dcr.setSuspens(dto.suspens());
		dcr.setUndcom(dto.undcom());
		dcr.setVlrunit(dto.vlrunit());
		dcr.setIibasecalc(dto.iibasecalc());
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrreg3> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrreg3> getByKey(Dcrreg3Key dto) {

		return repository.findById(dto);
	}
	
	public List<Dcrreg3> getByIds(Integer idmatriz, String partnumpd, String tpprd) {

		return repository.consultaByIds(idmatriz, partnumpd, tpprd);
	}
}
