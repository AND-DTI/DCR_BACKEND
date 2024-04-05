package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Cadtaxa;
import com.dcr.api.model.dto.CadtaxaDTO;
import com.dcr.api.repository.as400.CadtaxaRepository;
import com.dcr.api.response.TaxaResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadtaxaService {

	@Autowired
	CadtaxaRepository repository;
	
	public List<Cadtaxa> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Cadtaxa> getByID(String cmoed) {
		
		return repository.findById(cmoed);
	}
	
	public TaxaResponse getVigente(String cdmoed) {
		TaxaResponse response = new TaxaResponse();
		
		Optional<Cadtaxa> taxa = repository.getVigente(cdmoed);
		Optional<Integer> taxaManual = repository.getTaxaManual();
		
		response.setCdmoed(taxa.get().getCdmoed());
		response.setTaxa(taxa.get().getTaxa());
		response.setVigfim(taxa.get().getVigfim());
		response.setVigini(taxa.get().getVigini());
		response.setTaxamanual(taxaManual.get());
		return response;
	}

	public void delete(Cadtaxa ppb) {
		
		repository.delete(ppb);
	}
	
	public Cadtaxa create(CadtaxaDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Cadtaxa taxa = new Cadtaxa();
		
		taxa.setCdmoed(dto.cdmoed());
		taxa.setVigfim(dto.vigfim());
		taxa.setVigini(dto.vigini());
		taxa.setTaxa(dto.taxa());
		
		Auxiliar.preencheAuditoria(taxa, request);
		return repository.save(taxa);
	}
	
	public Cadtaxa update(Cadtaxa taxa,  CadtaxaDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		taxa.setVigfim(dto.vigfim());
		taxa.setVigini(dto.vigini());
		taxa.setTaxa(dto.taxa());
		Auxiliar.preencheAuditoria(taxa, request);
		return repository.save(taxa);
	}
}
