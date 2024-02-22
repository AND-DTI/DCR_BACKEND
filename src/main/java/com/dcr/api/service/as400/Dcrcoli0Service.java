package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrcoli0;
import com.dcr.api.model.dto.Dcrcoli0DTO;
import com.dcr.api.model.keys.Dcrcoli0Key;
import com.dcr.api.repository.as400.Dcrcoli0Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class Dcrcoli0Service {
	@Autowired
	Dcrcoli0Repository repository;
	
	public Dcrcoli0 create(Dcrcoli0DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrcoli0 dcr = new Dcrcoli0();
		
		Dcrcoli0Key key = new Dcrcoli0Key();
		key.setDcre(dto.dcre());
		key.setDenom(dto.denom());
	
		
		dcr.setKey(key);
		dcr.setCdclient(dto.cdclient());
		dcr.setDtdcre(dto.dtdcre());
		dcr.setCnpj(dto.cnpj());
		dcr.setCpfrl(dto.cpfrl());
		dcr.setDcrant(dto.dcrant());
		dcr.setEncargos(dto.encargos());
		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setOrigdcr(dto.origdcr());
		dcr.setPeso(dto.peso());
		dcr.setPpb(dto.ppb());
		dcr.setProcretif(dto.procretif());
		dcr.setSalarios(dto.salarios());
		dcr.setTpcoef(dto.tpcoef());
		dcr.setTpdcre(dto.tpdcre());
		dcr.setUndcom(dto.undcom());
		dcr.setVrspgd(dto.vrspgd());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrcoli0 update(Dcrcoli0DTO dto, Dcrcoli0 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setCdclient(dto.cdclient());
		dcr.setDtdcre(dto.dtdcre());
		dcr.setCnpj(dto.cnpj());
		dcr.setCpfrl(dto.cpfrl());
		dcr.setDcrant(dto.dcrant());
		dcr.setEncargos(dto.encargos());
		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setOrigdcr(dto.origdcr());
		dcr.setPeso(dto.peso());
		dcr.setPpb(dto.ppb());
		dcr.setProcretif(dto.procretif());
		dcr.setSalarios(dto.salarios());
		dcr.setTpcoef(dto.tpcoef());
		dcr.setTpdcre(dto.tpdcre());
		dcr.setUndcom(dto.undcom());
		dcr.setVrspgd(dto.vrspgd());
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrcoli0> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrcoli0> getByKey(Dcrcoli0Key dto) {

		return repository.findById(dto);
	}
}
