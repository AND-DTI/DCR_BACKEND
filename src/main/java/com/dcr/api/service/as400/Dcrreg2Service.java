package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrreg2;
import com.dcr.api.model.dto.Dcrreg2DTO;
import com.dcr.api.model.keys.Dcrreg2Key;
import com.dcr.api.repository.as400.Dcrreg2Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class Dcrreg2Service {
	
	@Autowired
	Dcrreg2Repository repository;
	
	public Dcrreg2 create(Dcrreg2DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrreg2 dcr = new Dcrreg2();
		
		Dcrreg2Key key = new Dcrreg2Key();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setNumcomp(dto.numcomp());
		key.setTpprd(dto.tpprd());
		
		dcr.setKey(key);
		
		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIdreg(dto.idreg());
		dcr.setIe(dto.ie());
		dcr.setNcm(dto.ncm());
		dcr.setNumnf(dto.numnf());
		dcr.setQtde(dto.qtde());
		dcr.setSernf(dto.sernf());
		dcr.setUndcom(dto.sernf());
		dcr.setVlrunit(dto.vlrunit());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrreg2 update(Dcrreg2DTO dto, Dcrreg2 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setCnpjfor(dto.cnpjfor());
		dcr.setEminf(dto.eminf());
		dcr.setEspec(dto.espec());
		dcr.setIdreg(dto.idreg());
		dcr.setIe(dto.ie());
		dcr.setNcm(dto.ncm());
		dcr.setNumnf(dto.numnf());
		dcr.setQtde(dto.qtde());
		dcr.setSernf(dto.sernf());
		dcr.setUndcom(dto.sernf());
		dcr.setVlrunit(dto.vlrunit());
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrreg2> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrreg2> getByKey(Dcrreg2Key dto) {

		return repository.findById(dto);
	}
	
	public List<Dcrreg2> getByIds(Integer idmatriz, String partnumpd, String tpprd) {		

		List<Dcrreg2> lista = repository.consultaByIds(idmatriz, partnumpd, tpprd);
		Auxiliar.formatResponse(lista);

		return lista;
	}
}
