package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrcorsrq;
import com.dcr.api.model.dto.DcrcorsrqDTO;
import com.dcr.api.repository.as400.DcrcorsrqRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class DcrcorsrqService {


	@Autowired
	DcrcorsrqRepository repository;
	
	
	public List<Dcrcorsrq> getAll(){
		return repository.findAll();
	}
	
	public List<Dcrcorsrq> getIpsByRota(String rota, String ip){
		return repository.getByRota(rota, ip);
	}
	
	public List<Dcrcorsrq> getByCnpj(String cnpjext){
		return repository.getByCnpj(cnpjext);
	}
	
	public Optional<Dcrcorsrq> getByID(Long idreg){
		return repository.findById(idreg);
	}
	
	public Dcrcorsrq create(DcrcorsrqDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		Dcrcorsrq dcr = new Dcrcorsrq();
		
		dcr.setIdreq(dto.idreg());
		dcr.setCnpjext(dto.cnpjext());
		dcr.setTpreq(dto.tpreg());
		dcr.setObjreq(dto.objreq());
		dcr.setRotareq(dto.rotareq());
		
		Auxiliar.preencheAuditoria(dcr, request);
		return repository.save(dcr);
	}
	
	public Dcrcorsrq update(Dcrcorsrq dcr, DcrcorsrqDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{

		dcr.setCnpjext(dto.cnpjext());
		dcr.setTpreq(dto.tpreg());
		dcr.setObjreq(dto.objreq());
		dcr.setRotareq(dto.rotareq());
		
		Auxiliar.preencheAuditoria(dcr, request);
		return repository.save(dcr);
	}
	
	public void delete(Dcrcorsrq dcr) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException{
		repository.delete(dcr);
	}
}
