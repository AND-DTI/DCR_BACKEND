package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrreg4;
import com.dcr.api.model.dto.Dcrreg4DTO;
import com.dcr.api.model.keys.Dcrreg4Key;
import com.dcr.api.repository.as400.Dcrreg4Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class Dcrreg4Service {


	@Autowired
	Dcrreg4Repository repository;
	
	
	public Dcrreg4 create(Dcrreg4DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrreg4 dcr = new Dcrreg4();
		
		Dcrreg4Key key = new Dcrreg4Key();
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
		dcr.setDi(dto.di());
		dcr.setAdicao(dto.adicao());
		dcr.setItemadicao(dto.itemadicao());
		dcr.setIndreducii(dto.indreducii());
		dcr.setImpdireta(dto.impdireta());
		dcr.setSuspens(dto.suspens());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrreg4 update(Dcrreg4DTO dto, Dcrreg4 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

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
		dcr.setDi(dto.di());
		dcr.setAdicao(dto.adicao());
		dcr.setItemadicao(dto.itemadicao());
		dcr.setIndreducii(dto.indreducii());
		dcr.setImpdireta(dto.impdireta());
		dcr.setSuspens(dto.suspens());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrreg4> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrreg4> getByKey(Dcrreg4Key dto) {

		return repository.findById(dto);
	}
	
	public List<Dcrreg4> getByIds(Integer idmatriz, String partnumpd, String tpprd) {
				
		List<Dcrreg4> lista = repository.consultaByIds(idmatriz, partnumpd, tpprd);
		Auxiliar.formatResponse(lista);

		return lista;

	}
}
