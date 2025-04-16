package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.as400.Dcrprocch;
import com.dcr.api.model.dto.DcrprocchDTO;
import com.dcr.api.repository.as400.DcrprocchRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class DcrprocchService {


	@Autowired
	DcrprocchRepository repository;
	

	public Dcrprocch create(DcrprocchDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrprocch dcr = new Dcrprocch();
		
		dcr.setDtstatus(dto.dtstatus());
		dcr.setHrstatus(dto.hrstatus());
		dcr.setIdhist(dto.idhist());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setRespstatus(dto.respstatus());
		dcr.setStsnew(dto.stsnew());
		dcr.setStsold(dto.stsold());
		dcr.setTpprd(dto.tpprd());
	
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);

	}
	

	public Dcrprocch update(DcrprocchDTO dto, Dcrprocch dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		

		dcr.setDtstatus(dto.dtstatus());
		dcr.setHrstatus(dto.hrstatus());
		dcr.setIdmatriz(dto.idmatriz());
		dcr.setPartnumpd(dto.partnumpd());
		dcr.setRespstatus(dto.respstatus());
		dcr.setStsnew(dto.stsnew());
		dcr.setStsold(dto.stsold());
		dcr.setTpprd(dto.tpprd());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	

	public Dcrprocch setStatus(Dcrprocc dcr, Integer statusOld, Integer statusNew, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {				
		
		Dcrprocch histDCR = new Dcrprocch();
		
		histDCR.setIdmatriz(dcr.getKey().getIdmatriz());
		histDCR.setPartnumpd(dcr.getKey().getPartnumpd());
		histDCR.setRespstatus(dcr.getRespstaus());
		histDCR.setTpprd(dcr.getKey().getTpprd());
		
		histDCR.setDtstatus(Auxiliar.getDtFormated());
		histDCR.setHrstatus(Auxiliar.getHrFormated());
		
		histDCR.setStsnew(statusNew);
		histDCR.setStsold(statusOld);
		Auxiliar.preencheAuditoria(histDCR, request);
		return repository.save(histDCR);

	}
	

	public List<Dcrprocch> getAll() {
		return repository.findAll();
	}
	

	public Optional<Dcrprocch> getByKey(Long idhist) {

		return repository.findById(idhist);

	}
}
