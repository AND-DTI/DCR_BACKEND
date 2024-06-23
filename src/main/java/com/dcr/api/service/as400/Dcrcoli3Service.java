package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrcoli1;
import com.dcr.api.model.as400.Dcrcoli3;
import com.dcr.api.model.dto.Dcrcoli1DTO;
import com.dcr.api.model.dto.Dcrcoli3DTO;
import com.dcr.api.model.dto.Dcrcoli3LoteDTO;
import com.dcr.api.model.keys.Dcrcoli3Key;
import com.dcr.api.repository.as400.Dcrcoli3Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class Dcrcoli3Service {
	@Autowired
	Dcrcoli3Repository repository;
	
	public Dcrcoli3 create(Dcrcoli3DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrcoli3 dcr = new Dcrcoli3();
		
		Dcrcoli3Key key = new Dcrcoli3Key();
		key.setDcre(dto.dcre());
		key.setNumcomp(dto.numcomp());
		key.setNumsubcomp(dto.numsubcomp());
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
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public void createLote(List<Dcrcoli3LoteDTO> lista, String dcre, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		for (Dcrcoli3LoteDTO dto : lista) {
			Dcrcoli3 dcr = new Dcrcoli3();
			
			Dcrcoli3Key key = new Dcrcoli3Key();
			key.setDcre(dcre);
			key.setNumcomp(dto.numcomp());
			key.setNumsubcomp(dto.numsubcomp());
			dcr.setKey(key);
			
			Optional<Dcrcoli3> exist = this.getByKey(key);
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
			Auxiliar.preencheAuditoria(dcr, request);
			
			repository.save(dcr);
		}
	}
	
	public void delete(Dcrcoli3 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		repository.delete(dcr);
	}
	
	public Dcrcoli3 update(Dcrcoli3DTO dto, Dcrcoli3 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setUndcom(dto.undcom());
		dcr.setAdicao(dto.adicao());
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setDi(dto.di());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
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
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrcoli3> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrcoli3> getByKey(Dcrcoli3Key dto) {

		return repository.findById(dto);
	}
}
