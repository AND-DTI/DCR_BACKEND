package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.dto.DcrregraDTO;
import com.dcr.api.model.dto.DcrregraKeyDTO;
import com.dcr.api.model.keys.DcrregraKey;
import com.dcr.api.repository.as400.DcrregraRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class DcrregraService {

	@Autowired
	DcrregraRepository repository;
	
	public Optional<Dcrregra> getAtivo() {
		return repository.findAtivo();
	}
	
	public Dcrregra create(DcrregraDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrregra regra = new Dcrregra();
		
		DcrregraKey key = new DcrregraKey();
		key.setConfvigfim("");
		key.setConfvigini(Auxiliar.getDtFormated());
		regra.setDcrregraKey(key);
		
		regra.setCnpjemi(dto.cnpjemi());
		regra.setRazsoc(dto.razsoc());
		regra.setAlertaprev(dto.alertaprev());
		regra.setCarencia(dto.carencia());
		regra.setDiasprevia(dto.diasprevia());
		regra.setExpiraprev(dto.expiraprev());
		regra.setPeritran(dto.peritran());
		regra.setProccarenc(dto.proccarenc());
		regra.setProcsemppb(dto.procsemppb());
		regra.setSubstfat(dto.substfat());
		regra.setSubstfatn(dto.substfatn());
		regra.setSubstituto(dto.substituto());
		regra.setTaxamanual(dto.taxamanual());
		regra.setTpvalor(dto.tpvalor());
		regra.setStsconfig(1);
		regra.setTrancarenc(dto.trancarenc());
		
		Auxiliar.preencheAuditoria(regra, request);
		
		
		return repository.save(regra);
	}
	
	public void update(DcrregraDTO dto, Dcrregra regra, String dtFim, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		repository.updateStsconfigAndConfvigfim(regra.getDcrregraKey().getConfvigini(), regra.getDcrregraKey().getConfvigfim(), 0, dtFim);
			
	}
	 
	public List<Dcrregra> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Dcrregra> getByDate(DcrregraKeyDTO dto) {
		DcrregraKey key = new DcrregraKey();
		key.setConfvigini(dto.confvigini());
		key.setConfvigfim(dto.confvigfim());
		return repository.findById(key);
	}
	
	
}
