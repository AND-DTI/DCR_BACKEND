package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrcoli2;
import com.dcr.api.model.dto.Dcrcoli2DTO;
import com.dcr.api.model.dto.Dcrcoli2LoteDTO;
import com.dcr.api.model.keys.Dcrcoli2Key;
import com.dcr.api.repository.as400.Dcrcoli2Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class Dcrcoli2Service {
	
	
	@Autowired
	Dcrcoli2Repository repository;
	
	
	public Dcrcoli2 create(Dcrcoli2DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrcoli2 dcr = new Dcrcoli2();
		
		Dcrcoli2Key key = new Dcrcoli2Key();
		key.setDcre(dto.dcre());
		key.setNumcomp(dto.numcomp());
		
		dcr.setKey(key);
		
		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setUndcom(dto.undcom());
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIe(dto.ie());
		dcr.setNumnf(dto.numnf());
		dcr.setQtde(dto.qtde());
		dcr.setSernf(dto.sernf());
		dcr.setVlrunit(dto.vlrunit());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public void createLote(List<Dcrcoli2LoteDTO> lista, String dcre, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		for (Dcrcoli2LoteDTO dto : lista) {
			Dcrcoli2 dcr = new Dcrcoli2();
			
			Dcrcoli2Key key = new Dcrcoli2Key();
			key.setDcre(dcre);
			key.setNumcomp(dto.numcomp());
			
			dcr.setKey(key);
			
			Optional<Dcrcoli2> exist = this.getByKey(key);
			if(!exist.isEmpty()) {
				this.delete(exist.get(), request);
			}
			
			dcr.setIdreg(dto.idreg());
			dcr.setNcm(dto.ncm());
			dcr.setUndcom(dto.undcom());
			dcr.setCnpjfor(dto.cnpjfor());
			dcr.setEminf(dto.eminf());
			dcr.setEspec(dto.espec());
			dcr.setIe(dto.ie());
			dcr.setNumnf(dto.numnf());
			dcr.setQtde(dto.qtde());
			dcr.setSernf(dto.sernf());
			dcr.setVlrunit(dto.vlrunit());
			Auxiliar.preencheAuditoria(dcr, request);
		
			repository.save(dcr);
		}
	}

	public void delete(Dcrcoli2 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		repository.delete(dcr);
	}
	
	public Dcrcoli2 update(Dcrcoli2DTO dto, Dcrcoli2 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setUndcom(dto.undcom());
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIe(dto.ie());
		dcr.setNumnf(dto.numnf());
		dcr.setQtde(dto.qtde());
		dcr.setSernf(dto.sernf());
		dcr.setVlrunit(dto.vlrunit());
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrcoli2> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrcoli2> getByKey(Dcrcoli2Key dto) {

		return repository.findById(dto);
	}
}
