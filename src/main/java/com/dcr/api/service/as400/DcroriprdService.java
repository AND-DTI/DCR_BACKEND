package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcroriprd;
import com.dcr.api.model.dto.DcroriprdDTO;
import com.dcr.api.model.dto.DcroriprdKeyDTO;
import com.dcr.api.model.keys.DcroriprdKey;
import com.dcr.api.repository.as400.DcroriprdRepository;
import com.dcr.api.utils.Auxiliar;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcroriprdService {

	@Autowired
	DcroriprdRepository repository;
	
	public Optional<Dcroriprd> getAtivo() {
		return repository.findAtivo();
	}
	
	
	public Dcroriprd create(DcroriprdDTO dto, HttpServletRequest request) throws UnknownHostException {
		Dcroriprd dcr = new Dcroriprd();
		
		DcroriprdKey key = new DcroriprdKey();
		key.setConfvigfim("");
		key.setConfvigini(Auxiliar.getDtFormated());
		dcr.setDcroriprdKey(key);
		
		dcr.setPlanopd(dto.planopd());
		dcr.setEstoq(dto.estoq());
		dcr.setAstecped(dto.astecped());
		dcr.setAstecopen(dto.astecopen());
		dcr.setAstecppa(dto.astecppa());
		dcr.setGarantia(dto.garantia());
		dcr.setPlanon0(dto.planon0());
		dcr.setPlanon1(dto.planon1());
		dcr.setStsconfig(1);
		dcr.setAstecpedn0(dto.astecpedn0());
		dcr.setAstecpedn1(dto.astecpedn1());
		dcr.setAstecppan0(dto.astecppan0());
		dcr.setAstecppan1(dto.astecppan1());
		
		dcr.setItauddt(Auxiliar.getDtFormated());
		dcr.setItaudhr(Auxiliar.getHrFormated());
		dcr.setItaudhst(Auxiliar.getClientHost(request));
		dcr.setItaudsys("DCR-Backend");
		dcr.setItaudusr(dto.itaudusr());
		
		return repository.save(dcr);
	}
	
	public Dcroriprd update(DcroriprdDTO dto, Dcroriprd dcr, HttpServletRequest request) throws UnknownHostException {		
		dcr.setStsconfig(0);
		
		dcr.setItauddt(Auxiliar.getDtFormated());
		dcr.setItaudhr(Auxiliar.getHrFormated());
		dcr.setItaudhst(Auxiliar.getClientHost(request));
		dcr.setItaudsys("DCR-Backend");
		dcr.setItaudusr(dto.itaudusr());
		
		return repository.save(dcr);
	}
	
	public List<Dcroriprd> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Dcroriprd> getByDate(DcroriprdKeyDTO dto) {
		DcroriprdKey key = new DcroriprdKey();
		key.setConfvigini(dto.confvigini());
		key.setConfvigfim(dto.confvigfim());
		return repository.findById(key);
	}
}
