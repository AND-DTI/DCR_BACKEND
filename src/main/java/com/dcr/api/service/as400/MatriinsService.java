package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matriins;
import com.dcr.api.model.dto.MatriinsDTO;
import com.dcr.api.model.keys.MatriinsKey;
import com.dcr.api.repository.as400.MatriinsRepository;
import com.dcr.api.response.PendenciaInsumoResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MatriinsService {

	@Autowired
	MatriinsRepository repository;
	
	public List<Matriins> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Matriins> getByID(MatriinsKey id) {
		
		return repository.findById(id);
	}
	
	public void delete(Matriins matriz) {
		
		repository.delete(matriz);
	}
	
	public List<PendenciaInsumoResponse> getPendencia(Integer idmatriz, String partnum) {
		List<Object[]> resultados = repository.consultaPendenciaInsumo(idmatriz, partnum);
		
		List<PendenciaInsumoResponse> produtos = new ArrayList<>();

        for (Object[] resultado : resultados) {
        	PendenciaInsumoResponse resp = new PendenciaInsumoResponse();
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
        	resp.setObspend((Object) resultado[16]);
        	resp.setStatus((Object) resultado[17]);
            produtos.add(resp);
        }
        
		return produtos;
	}
	
	public Matriins create(MatriinsDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Matriins matriz = new Matriins();
		
		MatriinsKey key = new MatriinsKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnum(dto.partnum());
		key.setPartnumpd(dto.partnumpd());
		
		matriz.setKey(key);
		matriz.setCdspn(dto.cdspn());
		matriz.setEmcomp(dto.emcomp());
		matriz.setItmorg(dto.itmorg());
		matriz.setIttyp(dto.ittyp());
		matriz.setNecfil(dto.necfil());
		matriz.setPartdesc(dto.partdesc());
		matriz.setPartnew(dto.partnew());
		matriz.setPartnewdsc(dto.partnewdsc());
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
	
	public Matriins update(Matriins matriz,  MatriinsDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		matriz.setCdspn(dto.cdspn());
		matriz.setEmcomp(dto.emcomp());
		matriz.setItmorg(dto.itmorg());
		matriz.setIttyp(dto.ittyp());
		matriz.setNecfil(dto.necfil());
		matriz.setPartdesc(dto.partdesc());
		matriz.setPartnew(dto.partnew());
		matriz.setPartnewdsc(dto.partnewdsc());
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
	
public Matriins resolverPendencia(Matriins matriz, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {

		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
}
