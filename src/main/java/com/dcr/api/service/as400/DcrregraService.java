package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.dto.DcrregraDTO;
import com.dcr.api.model.dto.DcrregraKeyDTO;
import com.dcr.api.model.keys.DcrregraKey;
import com.dcr.api.repository.as400.DcrregraRepository;
import com.dcr.api.utils.Auxiliar;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrregraService {

	@Autowired
	DcrregraRepository repository;
	
	public Optional<Dcrregra> getAtivo() {
		return repository.findAtivo();
	}
	
	public Dcrregra create(DcrregraDTO dto, HttpServletRequest request) throws UnknownHostException {
		Dcrregra regra = new Dcrregra();
		
		DcrregraKey key = new DcrregraKey();
		key.setConfvigfim("");
		key.setConfvigini(Auxiliar.getDtFormated());
		regra.setDcrregraKey(key);
		
		regra.setAlertaprev(dto.alertaprev());
		regra.setCarencia(dto.carencia());
		regra.setDiasprevia(dto.diasprevia());
		regra.setExpiraprev(dto.expiraprev());
		regra.setPeritran(dto.peritran());
		regra.setProccarenc(dto.proccarenc());
		regra.setProcsemppb(dto.procsemppb());
		regra.setSubstfat(dto.substfat());
		regra.setSubstfatn(dto.substfatn());
		regra.setSubstituto(dto.substituto());
		regra.setTaxamanual(dto.taxamanual());
		regra.setTpvalor(dto.tpvalor());
		regra.setStsconfig(1);
		regra.setTrancarenc(dto.trancarenc());
		regra.setItauddt(Auxiliar.getDtFormated());
		regra.setItaudhr(Auxiliar.getHrFormated());
		regra.setItaudhst(Auxiliar.getClientHost(request));
		regra.setItaudsys("DCR-Backend");
		regra.setItaudusr(dto.itaudusr());
		
		return repository.save(regra);
	}
	
	public Dcrregra update(DcrregraDTO dto, Dcrregra regra, HttpServletRequest request) throws UnknownHostException {		

		regra.setStsconfig(0);
			
		regra.setItauddt(Auxiliar.getDtFormated());
		regra.setItaudhr(Auxiliar.getHrFormated());
		regra.setItaudhst(Auxiliar.getClientHost(request));
		regra.setItaudsys("DCR-Backend");
		regra.setItaudusr(dto.itaudusr());
		
		return repository.save(regra);
	}
	
	public List<Dcrregra> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Dcrregra> getByDate(DcrregraKeyDTO dto) {
		DcrregraKey key = new DcrregraKey();
		key.setConfvigini(dto.confvigini());
		key.setConfvigfim(dto.confvigfim());
		return repository.findById(key);
	}
}
