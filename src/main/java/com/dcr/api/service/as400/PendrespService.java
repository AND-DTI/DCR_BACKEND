package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Pendresp;
import com.dcr.api.model.dto.PendrespDTO;
import com.dcr.api.model.keys.PendenciaKey;
import com.dcr.api.repository.as400.PendrespRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;



@Service
public class PendrespService {

	@Autowired
	PendrespRepository repository;
	
	public List<Pendresp> getAll() {
		
		return repository.findAll();

	}
	
	public Optional<Pendresp> getByID(PendenciaKey key) {
		
		return repository.findById(key);
	}
	
	public List<Pendresp> getByCdPend(String cdpend) {
		
		return repository.findByCdPend(cdpend);
	}
	
	public void delete(Pendresp pend) {
		
		repository.delete(pend);
	}
	
	public Pendresp create(PendrespDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		PendenciaKey key = new PendenciaKey();
		key.setCdpend(dto.cdpend());
		key.setCdresp(dto.cdresp());
		
		Pendresp pend = new Pendresp();
		pend.setKey(key);
		pend.setNmresp(dto.nmresp());
		
		Auxiliar.preencheAuditoria(pend, request);
		return repository.save(pend);
	}

    public Pendresp create2(PendrespDTO responsavel, String subtipo, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
        PendenciaKey key = new PendenciaKey();
		key.setCdpend(responsavel.cdpend());		
        key.setSubtipo(subtipo);
        key.setCdresp(responsavel.cdresp());
    		
		Pendresp pend = new Pendresp();
		pend.setKey(key);
		pend.setNmresp(responsavel.nmresp());		
		Auxiliar.preencheAuditoria(pend, request);

		return repository.save(pend);

	}
	
	public Pendresp update(Pendresp pend, PendrespDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		pend.setNmresp(dto.nmresp());
		
		Auxiliar.preencheAuditoria(pend, request);
		
		return repository.save(pend);

	}
	
}
