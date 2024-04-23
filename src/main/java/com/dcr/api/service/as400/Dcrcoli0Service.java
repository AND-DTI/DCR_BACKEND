package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrcoli0;
import com.dcr.api.model.dto.ColigadoLoteDTO;
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
	
	public void delete(Dcrcoli0 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		repository.delete(dcr);
	}
	
	public Dcrcoli0 createLote(ColigadoLoteDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrcoli0 dcr = new Dcrcoli0();
		
		Dcrcoli0Key key = new Dcrcoli0Key();
		key.setDcre(dto.getDcre());
		key.setDenom(dto.getDenom());
	
		
		dcr.setKey(key);
		dcr.setCdclient(dto.getCdclient());
		dcr.setDtdcre(dto.getDtdcre());
		dcr.setCnpj(dto.getCnpj());
		dcr.setCpfrl(dto.getCpfrl());
		dcr.setDcrant(dto.getDcrant());
		dcr.setEncargos(dto.getEncargos());
		dcr.setIdreg(dto.getIdreg());
		dcr.setNcm(dto.getNcm());
		dcr.setOrigdcr(dto.getOrigdcr());
		dcr.setPeso(dto.getPeso());
		dcr.setPpb(dto.getPpb());
		dcr.setProcretif(dto.getProcretif());
		dcr.setSalarios(dto.getSalarios());
		dcr.setTpcoef(dto.getTpcoef());
		dcr.setTpdcre(dto.getTpdcre());
		dcr.setUndcom(dto.getUndcom());
		dcr.setVrspgd(dto.getVrspgd());
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
