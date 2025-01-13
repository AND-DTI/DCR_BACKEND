package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrreg0;
import com.dcr.api.model.dto.Dcrreg0DTO;
import com.dcr.api.model.keys.Dcrreg0Key;
import com.dcr.api.repository.as400.Dcrreg0Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class Dcrreg0Service {


	@Autowired
	Dcrreg0Repository repository;
	


	public Dcrreg0 create(Dcrreg0DTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Dcrreg0 dcr = new Dcrreg0();		
		Dcrreg0Key key = new Dcrreg0Key();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnumpd(dto.partnumpd());
		key.setDenom(dto.denom());
		key.setTpprd(dto.tpprd());
		
		dcr.setKey(key);
		
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
	

	public Dcrreg0 update(Dcrreg0DTO dto, Dcrreg0 dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

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
	

	public List<Dcrreg0> getAll() {
		return repository.findAll();
	}
	

	public Optional<Dcrreg0> getByKey(Dcrreg0Key key) {

		return repository.findById(key);

	}
	
	
	public Dcrreg0 getById(Integer idmatriz, String partnumpd, String tpprd) {

		//return repository.consultaByIds(idmatriz, partnumpd, tpprd);
	
		List<Dcrreg0> regs = repository.consultaByIds(idmatriz, partnumpd, tpprd);
		
		Dcrreg0 reg0 = regs.isEmpty() ? null: regs.get(0);
		Auxiliar.formatResponse(reg0);

		return reg0;

	}


	public void remove(Dcrreg0 reg0){

		repository.delete(reg0);
		
	}

}
