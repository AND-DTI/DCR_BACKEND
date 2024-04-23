package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrcoli1;
import com.dcr.api.model.as400.Dcrcoli4;
import com.dcr.api.model.dto.Dcrcoli1DTO;
import com.dcr.api.model.dto.Dcrcoli4DTO;
import com.dcr.api.model.dto.Dcrcoli4LoteDTO;
import com.dcr.api.model.keys.Dcrcoli4Key;
import com.dcr.api.repository.as400.Dcrcoli4Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class Dcrcoli4Service {
	
	@Autowired
	Dcrcoli4Repository repository;
	
	public Dcrcoli4 create(Dcrcoli4DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrcoli4 dcr = new Dcrcoli4();
		
		Dcrcoli4Key key = new Dcrcoli4Key();
		key.setDcre(dto.dcre());
		key.setNumcomp(dto.numcomp());

		dcr.setKey(key);

		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setUndcom(dto.undcom());
		dcr.setAdicao(dto.adicao());
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setDi(dto.di());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIe(dto.ie());
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
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public void createLote(List<Dcrcoli4LoteDTO> lista, String dcre, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		for (Dcrcoli4LoteDTO dto : lista) {
			Dcrcoli4 dcr = new Dcrcoli4();
			
			Dcrcoli4Key key = new Dcrcoli4Key();
			key.setDcre(dcre);
			key.setNumcomp(dto.numcomp());
	
			dcr.setKey(key);
	
			Optional<Dcrcoli4> exist = this.getByKey(key);
			if(!exist.isEmpty()) {
				this.delete(exist.get(), request);
			}
			
			dcr.setIdreg(dto.idreg());
			dcr.setNcm(dto.ncm());
			dcr.setUndcom(dto.undcom());
			dcr.setAdicao(dto.adicao());
			dcr.setCnpjfor(dto.cnpjfor());
			dcr.setDi(dto.di());
			dcr.setEminf(dto.eminf());
			dcr.setEspec(dto.espec());
			dcr.setIe(dto.ie());
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
			Auxiliar.preencheAuditoria(dcr, request);
			
			repository.save(dcr);
		}
	}
	
	public void delete(Dcrcoli4 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		repository.delete(dcr);
	}
	
	
	public Dcrcoli4 update(Dcrcoli4DTO dto, Dcrcoli4 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setUndcom(dto.undcom());
		dcr.setAdicao(dto.adicao());
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setDi(dto.di());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIe(dto.ie());
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
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrcoli4> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrcoli4> getByKey(Dcrcoli4Key dto) {

		return repository.findById(dto);
	}
}
