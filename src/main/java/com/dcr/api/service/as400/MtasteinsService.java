package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.model.as400.Mtasteins;
import com.dcr.api.model.dto.MatriprdDTO;
import com.dcr.api.model.dto.MtasteinsDTO;
import com.dcr.api.model.keys.MtasteinsKey;
import com.dcr.api.repository.as400.MtasteinsRepository;
import com.dcr.api.response.PendenciaInsumoAstecResponse;
import com.dcr.api.response.PendenciaInsumoResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MtasteinsService {

	@Autowired
	MtasteinsRepository repository;
	
	public List<Mtasteins> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Mtasteins> getByID(MtasteinsKey id) {
		
		return repository.findById(id);
	}
	
	public void delete(Mtasteins matriz) {
		
		repository.delete(matriz);
	}
	
	public List<PendenciaInsumoAstecResponse> getPendencia(Integer idmatriz, String partnum) {
		List<Object[]> resultados = repository.consultaPendenciaInsumo(idmatriz, partnum);
		
		List<PendenciaInsumoAstecResponse> produtos = new ArrayList<>();

        for (Object[] resultado : resultados) {
        	PendenciaInsumoAstecResponse resp = new PendenciaInsumoAstecResponse();
        	resp.setIdmatriz((Object) resultado[0]);
        	resp.setPartnum((Object) resultado[1]);
        	resp.setPartdesc((Object) resultado[2]);
        	resp.setItmorg((Object) resultado[3]);
        	resp.setIttyp((Object) resultado[4]);
        	resp.setUnmsr((Object) resultado[5]);
        	resp.setNecfil((Object) resultado[6]);
        	resp.setCdspn((Object) resultado[7]);
        	resp.setWeght((Object) resultado[8]);
        	resp.setEmcomp((Object) resultado[9]);
        	resp.setPartsugest((Object) resultado[10]);
        	resp.setPartsugdsc((Object) resultado[11]);
        	resp.setPartnew((Object) resultado[12]);
        	resp.setPartnewdsc((Object) resultado[13]);
        	resp.setNumpend((Object) resultado[14]);
        	resp.setCdpend((Object) resultado[15]);
        	resp.setStatus((Object) resultado[16]);
            produtos.add(resp);
        }
        
		return produtos;
	}
	
	public Mtasteins create(MtasteinsDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Mtasteins matriz = new Mtasteins();
		
		MtasteinsKey key = new MtasteinsKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnum(dto.partnum());
		
		matriz.setKey(key);
		
		matriz.setCdspn(dto.cdspn());
		matriz.setEmcomp(dto.emcomp());
		matriz.setItmorg(dto.itmorg());
		matriz.setIttyp(dto.ittyp());
		matriz.setNecfil(dto.necfil());
		matriz.setPartdesc(dto.partdesc());
		matriz.setPartnew(dto.partnew());
		matriz.setPartnewdsc(dto.partnew());
		matriz.setPartsugdsc(dto.partsugdsc());
		matriz.setPartsugest(dto.partsugest());
		matriz.setUnmsr(dto.unmsr());
		matriz.setWeght(dto.weght());
		matriz.setEspec(dto.espec());
		matriz.setUndcom(dto.undcom());
		matriz.setNcm(dto.ncm());
		matriz.setVlrunit(dto.vlrunit());
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
	
	public Mtasteins update(Mtasteins matriz,  MtasteinsDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		
		matriz.setCdspn(dto.cdspn());
		matriz.setEmcomp(dto.emcomp());
		matriz.setItmorg(dto.itmorg());
		matriz.setIttyp(dto.ittyp());
		matriz.setNecfil(dto.necfil());
		matriz.setPartdesc(dto.partdesc());
		matriz.setPartnew(dto.partnew());
		matriz.setPartsugdsc(dto.partsugdsc());
		matriz.setPartsugest(dto.partsugest());
		matriz.setUnmsr(dto.unmsr());
		matriz.setWeght(dto.weght());
		matriz.setEspec(dto.espec());
		matriz.setUndcom(dto.undcom());
		matriz.setNcm(dto.ncm());
		matriz.setVlrunit(dto.vlrunit());
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
}
