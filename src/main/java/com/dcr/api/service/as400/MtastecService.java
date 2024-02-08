package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.dto.MtastecDTO;
import com.dcr.api.model.keys.MtastecKey;
import com.dcr.api.repository.as400.MtastecRepository;
import com.dcr.api.response.AstecPendenciaResponse;
import com.dcr.api.response.MatriprdResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class MtastecService {

	@Autowired
	MtastecRepository repository;
	
	public List<Mtastec> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Mtastec> getByID(Integer idmatriz, String partnumpd) {
		try {
			return Optional.of(repository.findByIdmatrizAndPartnumpd(idmatriz, partnumpd));
		}catch (Exception e) {
			return Optional.empty();
		}
		
	}
	
	public void delete(Mtastec matriz) {
		
		repository.delete(matriz);
	}
	
	public List<AstecPendenciaResponse> getPendencia(Integer idmatriz) {
		List<Object[]> resultados = repository.consultaPendencia(idmatriz);
		
		List<AstecPendenciaResponse> produtos = new ArrayList<>();

        for (Object[] resultado : resultados) {
        	AstecPendenciaResponse resp = new AstecPendenciaResponse();
        	resp.setIdMatriz((Object) resultado[0]);
        	resp.setPartnumpd((Object) resultado[1]);
        	resp.setDesccom((Object) resultado[2]);
        	resp.setDescrfb((Object) resultado[3]);
        	resp.setUnmed((Object) resultado[4]);
        	resp.setOrigprd((Object) resultado[5]);
        	resp.setDtneci((Object) resultado[6]);
        	resp.setPriourgen((Object) resultado[7]);
        	resp.setPrevfat((Object) resultado[8]);
        	resp.setPrioresp((Object) resultado[9]);
        	resp.setPriodtmnt((Object) resultado[10]);
        	resp.setPrioHrmnt((Object) resultado[11]);
        	resp.setPartnum((Object) resultado[12]);
        	resp.setPartdesc((Object) resultado[13]);
        	resp.setItmorg((Object) resultado[14]);
        	resp.setIttyp((Object) resultado[15]);
        	resp.setUnmsr((Object) resultado[16]);
        	resp.setNecfil((Object) resultado[17]);
        	resp.setCdspn((Object) resultado[18]);
        	resp.setWeght((Object) resultado[19]);
        	resp.setEmcomp((Object) resultado[20]);
        	resp.setPartsugest((Object) resultado[21]);
        	resp.setPartsugdsc((Object) resultado[22]);
        	resp.setPartnew((Object) resultado[23]);
        	resp.setPartnewdsc((Object) resultado[24]);
        	resp.setNumpend((Object) resultado[25]);
        	resp.setCdpend((Object) resultado[26]);
        	resp.setStatus((Object) resultado[27]);
            produtos.add(resp);
        }
        
		return produtos;
	}
	
	public Mtastec create(MtastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Mtastec astec = new Mtastec();
		
		
		astec.setPartnumpd(dto.partnumpd());
		astec.setDesccom(dto.desccom());
		astec.setDescrfb(dto.descrfb());
		astec.setDtneci(dto.dtneci());
		astec.setOrigprd(dto.origprd());
		astec.setPrevfat(dto.prevfat());
		astec.setPriodtmnt(dto.priodtmnt());
		astec.setPriohrmnt(dto.priohrmnt());
		astec.setPrioresp(dto.prioresp());
		astec.setPriourgen(dto.priourgen());
		astec.setUnmed(dto.unmed());
		
		Auxiliar.preencheAuditoria(astec, request);
		return repository.save(astec);
	}
	
	public Mtastec update(Mtastec astec,  MtastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		astec.setDesccom(dto.desccom());
		astec.setDescrfb(dto.descrfb());
		astec.setDtneci(dto.dtneci());
		astec.setOrigprd(dto.origprd());
		astec.setPrevfat(dto.prevfat());
		astec.setPriodtmnt(dto.priodtmnt());
		astec.setPriohrmnt(dto.priohrmnt());
		astec.setPrioresp(dto.prioresp());
		astec.setPriourgen(dto.priourgen());
		astec.setUnmed(dto.unmed());
		
		Auxiliar.preencheAuditoria(astec, request);
		return repository.save(astec);
	}
}
