package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.dto.PendastecDTO;
import com.dcr.api.model.dto.PendastecDTO3;
import com.dcr.api.model.dto.resolverPendenciaDTO;
import com.dcr.api.model.keys.PendastecKey;
import com.dcr.api.repository.as400.PendastecRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest; 


 

@Service
public class PendastecService {


	@Autowired
	PendastecRepository repository;
	
	
	public List<Pendastec> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Pendastec> getByID(PendastecKey id) {
		
		return repository.findById(id);
	}
	
	public List<Pendastec> findPendenciasZero(Long idmatriz) {
		
		return repository.findPendenciasZero(idmatriz);

	}

	public List<Pendastec> getByCdPend(String cdpend) {
		
		return repository.findByCdPend(cdpend);
	}
	
	public void delete(Pendastec matriz) {
		
		repository.delete(matriz);
	}
	
	public Pendastec create(PendastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Pendastec pend = new Pendastec();
		
		PendastecKey key = new PendastecKey();
		key.setIdmatriz(dto.idmatriz());
		key.setNumpend(dto.numpend());
		key.setPartnum(dto.partnum());
		
		pend.setKey(key);
		pend.setCdpend(dto.cdpend());
		pend.setStatus(dto.status());
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}
	
	public Pendastec create2(PendastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Pendastec pend = new Pendastec();		
		PendastecKey key = new PendastecKey();
		key.setIdmatriz(dto.idmatriz());		
		key.setPartnum(dto.partnum());		
		key.setNumpend(repository.getLastPend(dto.idmatriz()) +1); //get Max + 1
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

	public Pendastec create3(PendastecDTO3 dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Pendastec pend = new Pendastec();		
		PendastecKey key = new PendastecKey();
		key.setIdmatriz(dto.getIdmatriz());		
		key.setPartnum(dto.getPartnum());		
		key.setNumpend(repository.getLastPend(dto.getIdmatriz()) +1); //get Max + 1
		pend.setKey(key);

		pend.setCdpend(dto.getCdpend());		
		pend.setStatus(0);
		pend.setObsresol("");
		pend.setFlex1flw(dto.getFlex1flw());
		pend.setFlex2flw(dto.getFlex2flw());
		pend.setFlex3flw(dto.getFlex3flw());
		pend.setFlex4flw(dto.getFlex4flw());
		pend.setFlex5flw(dto.getFlex5flw());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);

	}
	
	


	public Pendastec update(Pendastec pend,  PendastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		pend.setCdpend(dto.cdpend());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}


	public Pendastec resolverPendencia(Pendastec pend, resolverPendenciaDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		pend.setCdpend(dto.cdpend());
		pend.setObsresol(dto.obsresol());
		pend.setStatus(dto.status());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);

	}


	public Pendastec finalizaTratativa(Integer idmatriz, String partnumpd, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Pendastec pend = new Pendastec();		
		PendastecKey key = new PendastecKey();
		key.setIdmatriz(idmatriz);		
		key.setPartnum(partnumpd);
		key.setNumpend(repository.getLastPend(idmatriz)+1); //get Max + 1
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


	public void removeEND(Integer idmatriz, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
								
		repository.removePend_END(idmatriz);

	}

	
	public void vinculaProtocolo(Integer idmatriz, String protocolo) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
								
		repository.vinculaProtocolo(idmatriz, protocolo);

	}



	
}
