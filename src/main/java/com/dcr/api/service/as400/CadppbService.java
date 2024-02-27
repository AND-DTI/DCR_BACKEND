package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.controller.TipoProdutoController;
import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.as400.Dcrlayout;
import com.dcr.api.model.dto.CadppbDTO;
import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.model.projection.Nivel1Projection;
import com.dcr.api.model.projection.Nivel2Projection;
import com.dcr.api.model.projection.ProdsProjection;
import com.dcr.api.model.projection.TipoProjection;
import com.dcr.api.model.projection.TpprdProjection;
import com.dcr.api.repository.as400.CadppbRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CadppbService {
	
	@Autowired
	CadppbRepository repository;
	
	public List<Cadppb> getAll() {
	
		return repository.findAll();
	}
	
	public Optional<Cadppb> getByID(ProdutoKey key) {
		
		return repository.findById(key);
	}

	public TpprdProjection getByTpprd(List<String> tpprdList) {
		List<ProdsProjection> results = repository.consultaByTpprd(tpprdList);

		
		Map<Object, List<ProdsProjection>> map = results.stream()
	                 .collect(Collectors.groupingBy(dcrlayout -> dcrlayout.getCdPrd()));
		 
		
		TpprdProjection tpprdProjection = new TpprdProjection();
		
		tpprdProjection.setProdutos(new ArrayList());
		tpprdProjection.setTipos(new ArrayList());
		for (Map.Entry<Object, List<ProdsProjection>> entry : map.entrySet()) {
			
			TipoProjection tp = new TipoProjection();
			tp.setTpPrd(entry.getValue().get(0).getTpPrd());
			tp.setDescPor(entry.getValue().get(0).getDescPor());
			
			
			
			Boolean existe = Boolean.FALSE;
			for (TipoProjection tipo : tpprdProjection.getTipos()) {
				if(tipo.getTpPrd().equals(entry.getValue().get(0).getTpPrd())) {
					existe = Boolean.TRUE;
				}
			}
			if(!existe) {
				tpprdProjection.getTipos().add(tp);
			}
			
			Nivel1Projection nvl1 = new Nivel1Projection();
			nvl1.setCdPrd(entry.getValue().get(0).getCdPrd());
            nvl1.setDescCom(entry.getValue().get(0).getDescCom());
            nvl1.setDescRfb(entry.getValue().get(0).getDescRfb());
            nvl1.setPrdDest(entry.getValue().get(0).getPrdDest());
            nvl1.setPpbPrd(entry.getValue().get(0).getPpbPrd());
            nvl1.setModelo(entry.getValue().get(0).getModelo());
            nvl1.setAnoMdl(entry.getValue().get(0).getAnoMdl());
            nvl1.setTpPrd(entry.getValue().get(0).getTpPrd());
            nvl1.setCores(new ArrayList());
			for (ProdsProjection result : entry.getValue()) {
	            Nivel2Projection nvl2 = new Nivel2Projection();
	            nvl2.setPartnumPd(result.getPartnumPd());
	            nvl2.setDescPor(result.getDescPor());
	            nvl2.setDescIng(result.getDescIng());
	            nvl2.setuEngNo(result.getuEngNo());
	            nvl2.setCodCor(result.getCodCor());
	            nvl2.setCorPt(result.getCorPt());
	            
	            
	            nvl1.getCores().add(nvl2);
	            
			}
			tpprdProjection.getProdutos().add(nvl1);
           
		}
        
        return tpprdProjection;
    }
	


	public void delete(Cadppb ppb) {
		
		repository.delete(ppb);
	}
	
	public Cadppb create(CadppbDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Cadppb ppb = new Cadppb();
		
		ProdutoKey key = new ProdutoKey();
		
		key.setCdprd(dto.cdprd());
		key.setTpprd(dto.tpprd());
		
		ppb.setKey(key);
		ppb.setDesccom(dto.desccom());
		ppb.setDescrfb(dto.descrfb());
		ppb.setPpbprd(dto.ppbprd());
		ppb.setPrddest(dto.prddest());
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
	
	public Cadppb update(Cadppb ppb,  CadppbDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		ppb.setDesccom(dto.desccom());
		ppb.setDescrfb(dto.descrfb());
		ppb.setPpbprd(dto.ppbprd());
		ppb.setPrddest(dto.prddest());
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
}
