package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.dto.PendprodDTO;
import com.dcr.api.model.dto.resolverPendenciaDTO;
import com.dcr.api.model.keys.PendprodKey;
import com.dcr.api.repository.as400.PendprodRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;



@Service
public class PendprodService {


	@Autowired
	PendprodRepository repository;


	
	public List<Pendprod> getAll() {
		
		return repository.findAll();

	}
	
	public Optional<Pendprod> getByID(PendprodKey id) {
		
		return repository.findById(id);

	}
	
	public List<Pendprod> findPendenciasZero(Long idmatriz, String partnumpd) {
		
		return repository.findPendenciasZero(idmatriz, partnumpd);

	}

	public List<Pendprod> getByCdPend(String cdpend) {
		
		return repository.findByCdPend(cdpend);

	}

	public void delete(Pendprod matriz) {
		
		repository.delete(matriz);

	}
	
	public Pendprod create(PendprodDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Pendprod pend = new Pendprod();
		
		PendprodKey key = new PendprodKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnum(dto.partnum());
		//key.setNumpend(dto.numpend());
		key.setPartnumpd(dto.partnumpd());
		pend.setKey(key);

		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);

	}

	public Pendprod create2(PendprodDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Pendprod pend = new Pendprod();		
		PendprodKey key = new PendprodKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setPartnum(dto.partnum());		
		key.setNumpend(repository.getLastPend(dto.idmatriz(), dto.partnumpd()) +1); //get Max + 1
		pend.setKey(key);

		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());

		pend.setFlex1flw(dto.flex1flw());
		pend.setFlex2flw(dto.flex2flw());
		pend.setFlex3flw(dto.flex3flw());
		pend.setFlex4flw(dto.flex4flw());
		pend.setFlex5flw(dto.flex5flw());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);

	}
	
	public Pendprod update(Pendprod pend,  PendprodDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);

	}
	

	public Pendprod resolverPendencia(Pendprod pend,  resolverPendenciaDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);

	}


	public Pendprod finalizaTratativa(Integer idmatriz, String partnumpd, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Pendprod pend = new Pendprod();		
		PendprodKey key = new PendprodKey();
		key.setIdmatriz(idmatriz);
		key.setPartnumpd(partnumpd);
		key.setPartnum("");
		key.setNumpend(repository.getLastPend(idmatriz, partnumpd)+1); //get Max + 1
		pend.setCdpend("END");
		pend.setKey(key);		
		pend.setObsresol("Confirmação de finalização das pendências");
		pend.setStatus(0);
		pend.setFlex1flw(0L);
		pend.setFlex2flw(0L);
		pend.setFlex3flw("");
		pend.setFlex4flw("");
		pend.setFlex5flw("");
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);

	}


	public void removeEND(Integer idmatriz, String partnumpd, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
								
		repository.removePend_END(idmatriz, partnumpd);

	}

	
	public void vinculaProtocolo(Integer idmatriz, String partnumpd, String protocolo) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
								
		repository.vinculaProtocolo(idmatriz, partnumpd, protocolo);

	}



}
