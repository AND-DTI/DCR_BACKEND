package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Prodmod;
import com.dcr.api.model.dto.ProdmodDTO;
import com.dcr.api.repository.as400.ProdmodRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class ProdmodService {

	
	@Autowired
	ProdmodRepository repository;
	
	
	public List<Prodmod> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Prodmod> getByID(String partnumpd) {
		
		return repository.findById(partnumpd);
	}
	
	public void delete(Prodmod pend) {
		
		repository.delete(pend);
	}
	
	public Prodmod create(ProdmodDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Prodmod prod = new Prodmod();
		
		prod.setAnomdl(dto.anomdl());
		prod.setCodcor(dto.codcor());
		prod.setDescing(dto.descing());
		prod.setDescpor(dto.descpor());
		prod.setMdsugest(dto.mdsugest());
		prod.setModelo(dto.modelo());
		prod.setPartnumpd(dto.partnumpd());
		prod.setUengno(dto.uengno());
		prod.setPsliq(dto.psliq());
		prod.setPsbrt(dto.psbrt());
		Auxiliar.preencheAuditoria(prod, request);
		return repository.save(prod);
	}
	
	public Prodmod update(Prodmod prod, ProdmodDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		prod.setAnomdl(dto.anomdl());
		prod.setCodcor(dto.codcor());
		prod.setDescing(dto.descing());
		prod.setDescpor(dto.descpor());
		prod.setMdsugest(dto.mdsugest());
		prod.setModelo(dto.modelo());
		prod.setPartnumpd(dto.partnumpd());
		prod.setUengno(dto.uengno());
		
		Auxiliar.preencheAuditoria(prod, request);
		
		return repository.save(prod);
	}
}
