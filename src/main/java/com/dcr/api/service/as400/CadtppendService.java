package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Cadtppend;
import com.dcr.api.model.dto.CadtppendDTO;
import com.dcr.api.repository.as400.CadtppendRepository;
import com.dcr.api.utils.Auxiliar;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadtppendService {

	@Autowired
	CadtppendRepository repository;
	
	public List<Cadtppend> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Cadtppend> getByID(String id) {
		
		return repository.findById(id);
	}
	
	public Cadtppend create(CadtppendDTO dto, HttpServletRequest request) throws UnknownHostException {
		Cadtppend pend = new Cadtppend();
		
		pend.setCdpend(dto.cdpend());
		pend.setDescpend(dto.descpend());
		pend.setObspend(dto.obspend());
		
		pend.setItauddt(Auxiliar.getDtFormated());
		pend.setItaudhr(Auxiliar.getHrFormated());
		pend.setItaudhst(Auxiliar.getClientHost(request));
		pend.setItaudsys("DCR-Backend");
		pend.setItaudusr(dto.itaudusr());
		
		return repository.save(pend);
	}
	
	public Cadtppend update(Cadtppend pend, CadtppendDTO dto, HttpServletRequest request) throws UnknownHostException {
		pend.setDescpend(dto.descpend());
		pend.setObspend(dto.obspend());
		
		pend.setItauddt(Auxiliar.getDtFormated());
		pend.setItaudhr(Auxiliar.getHrFormated());
		pend.setItaudhst(Auxiliar.getClientHost(request));
		pend.setItaudsys("DCR-Backend");
		pend.setItaudusr(dto.itaudusr());
		
		return repository.save(pend);
	}
	
	public void delete(Cadtppend pendencia) {
		
		repository.delete(pendencia);
	}
}
