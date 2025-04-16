package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrreg9;
import com.dcr.api.model.dto.Dcrreg9DTO;
import com.dcr.api.model.keys.Dcrreg9Key;
import com.dcr.api.repository.as400.Dcrreg9Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class Dcrreg9Service {


	@Autowired
	Dcrreg9Repository repository;
	
	
	public Dcrreg9 create(Dcrreg9DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrreg9 dcr = new Dcrreg9();
		
		Dcrreg9Key key = new Dcrreg9Key();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setTpprd(dto.tpprd());
		
		dcr.setKey(key);
		dcr.setIdreg(dto.idreg());
		dcr.setQtdred(dto.qtdred());
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrreg9 update(Dcrreg9DTO dto, Dcrreg9 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setIdreg(dto.idreg());
		dcr.setQtdred(dto.qtdred());
			
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrreg9> getAll() {
		return repository.findAll();
	}
	
	public Optional<Dcrreg9> getByKey(Dcrreg9Key dto) {

		return repository.findById(dto);
	}
	
	public Dcrreg9 getByIds(Integer idmatriz, String partnumpd, String tpprd) {

		//return repository.consultaByIds(idmatriz, partnumpd, tpprd);

		List<Dcrreg9> regs = repository.consultaByIds(idmatriz, partnumpd, tpprd);
		
		Dcrreg9 reg9 = regs.isEmpty() ? null: regs.get(0);
		Auxiliar.formatResponse(reg9);
		
		return reg9;
		
	}
}
