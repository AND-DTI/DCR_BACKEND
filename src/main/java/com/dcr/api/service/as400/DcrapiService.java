package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrapi;
import com.dcr.api.model.dto.DcrapiDTO;
import com.dcr.api.model.dto.DcrapiKeyDTO;
import com.dcr.api.model.keys.DcrapiKey;
import com.dcr.api.repository.as400.DcrapiRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrapiService {

	@Autowired
	DcrapiRepository repository;
	
	public List<Dcrapi> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Dcrapi> getAtivo() {
		return repository.findAtivo();
	}
	
	public Optional<Dcrapi> getByDate(DcrapiKeyDTO dto) {
		DcrapiKey key = new DcrapiKey();
		key.setConfvigini(dto.confvigini());
		key.setConfvigfim(dto.confvigfim());
		return repository.findById(key);
	}
	
	public Dcrapi create(DcrapiDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrapi dcr = new Dcrapi();
		
		DcrapiKey key = new DcrapiKey();
		key.setConfvigfim("");
		key.setConfvigini(Auxiliar.getDtFormated());
		dcr.setDcrapiKey(key);
		
		dcr.setAlertbody(dto.alertbody());
		dcr.setAlerthead(dto.alerthead());
		dcr.setAlerturl(dto.alerturl());
		dcr.setColigcust(dto.coligcust());
		dcr.setColigemai1(dto.coligemai1());
		dcr.setColigemai2(dto.coligemai2());
		dcr.setColigexpir(dto.coligexpir());
		dcr.setColignac(dto.colignac());
		dcr.setColigproc(dto.coligproc());
		dcr.setStsconfig(1);
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public void update(DcrapiDTO dto, Dcrapi dcr, HttpServletRequest request, String dtFim) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {	
		
		repository.updateStsconfigAndConfvigfim(dcr.getDcrapiKey().getConfvigini(), dcr.getDcrapiKey().getConfvigfim(), 0, dtFim);
	}
}
