package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.model.dto.MatriprdDTO;
import com.dcr.api.repository.as400.MatriprdRepository;
import com.dcr.api.response.MatriprdResponse;
import com.dcr.api.response.ProdutoPendenciaResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MatriprdService {

	@Autowired
	MatriprdRepository repository;
	
	public List<Matriprd> getAll() {
		
		return repository.findAll();
	}
	
	public Optional<Matriprd> getByID(Integer id) {
		
		return repository.findById(id);
	}
	
	public List<MatriprdResponse> getDetail(Integer id) {
		List<Object[]> resultados = repository.consultaJoin(id);
		
		List<MatriprdResponse> produtos = new ArrayList<>();

        for (Object[] resultado : resultados) {
        	MatriprdResponse resp = new MatriprdResponse();
        	resp.setIdMatriz(  resultado[0]);
        	resp.setProduto(  resultado[1]);
        	resp.setModelo(  resultado[2]);
        	resp.setAnomdl(  resultado[3]);
        	resp.setDesccom(  resultado[4]);
        	resp.setDescrfb(  resultado[5]);
        	resp.setTpprd(  resultado[6]);
        	resp.setProtot(  resultado[7]);
        	resp.setSpecial(  resultado[8]);
        	resp.setTpdcre(resultado[9]);
        	resp.setOrig(resultado[10]);
        	resp.setDtneci(  resultado[11]);
        	resp.setPriourgen(  resultado[12]);
        	resp.setPrevfat(  resultado[13]);
        	resp.setPrioresp(  resultado[14]);
        	resp.setPriodtmnt(  resultado[15]);
        	resp.setPrioHRmnt(  resultado[16]);
        	resp.setPartnumpd(  resultado[17]);
        	resp.setModelo(resultado[18]);
        	resp.setCodcor(  resultado[19]);
        	resp.setPartdesc(  resultado[20]);
        	resp.setUnmed(  resultado[21]);
        	resp.setPriocor(  resultado[22]);
        	resp.setCdbeg(  resultado[23]);
        	resp.setCorpt(  resultado[24]);
        	resp.setCoreng(  resultado[25]);
        	resp.setTppin(  resultado[26]);
        	resp.setDscpor(  resultado[27]);
        	resp.setDscing(  resultado[28]);
            produtos.add(resp);
        }
        
		return produtos;
	}
	
	public List<ProdutoPendenciaResponse> getProdutoPendencia(Integer id) {
		List<Object[]> resultados = repository.consultaProdutoPendencia(id);
		
		List<ProdutoPendenciaResponse> produtos = new ArrayList<>();

        for (Object[] resultado : resultados) {
        	ProdutoPendenciaResponse resp = new ProdutoPendenciaResponse();
        	resp.setIdMatriz(  resultado[0]);
        	resp.setProduto(  resultado[1]);
        	resp.setModelo(  resultado[2]);
        	resp.setAnomdl(  resultado[3]);
        	resp.setDesccom(  resultado[4]);
        	resp.setDescrfb(  resultado[5]);
        	resp.setTpprd(  resultado[6]);
        	resp.setProtot(  resultado[7]);
        	resp.setSpecial(  resultado[8]);
        	resp.setTpdcre(resultado[9]);
        	resp.setOrig(resultado[10]);
        	resp.setDtneci(  resultado[11]);
        	resp.setPriourgen(  resultado[12]);
        	resp.setPrevfat(  resultado[13]);
        	resp.setPrioresp(  resultado[14]);
        	resp.setPriodtmnt(  resultado[15]);
        	resp.setPrioHRmnt(  resultado[16]);
        	resp.setPartnumpd(  resultado[17]);
        	resp.setModelo(resultado[18]);
        	resp.setCodcor(  resultado[19]);
        	resp.setPartdesc(  resultado[20]);
        	resp.setUnmed(  resultado[21]);
        	resp.setPriocor(  resultado[22]);
        	resp.setPartnum( resultado[23]);
        	resp.setItmorg( resultado[24]);
        	resp.setIttyp( resultado[25]);
        	resp.setUnmsr( resultado[26]);
        	resp.setNecfil( resultado[27]);
        	resp.setCdspn( resultado[28]);
        	resp.setWeght( resultado[29]);
        	resp.setEmcomp( resultado[30]);
        	resp.setPartsugest( resultado[31]);
        	resp.setPartsugdsc( resultado[32]);
        	resp.setPartnew( resultado[33]);
        	resp.setPartnewdsc( resultado[34]);
        	resp.setNumpend( resultado[35]);
        	resp.setCdpend( resultado[36]);
        	resp.setObspend( resultado[37]);
        	resp.setStatus( resultado[38]);
            produtos.add(resp);
        }
        
		return produtos;
	}
	
	public void delete(Matriprd matriz) {
		
		repository.delete(matriz);
	}
	
	public Matriprd create(MatriprdDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Matriprd matriz = new Matriprd();
		
		matriz.setAnomdl(dto.anomdl());
		matriz.setDesccom(dto.desccom());
		matriz.setDescrfb(dto.descrfb());
		matriz.setDtneci(dto.dtneci());
		matriz.setIdmatriz(dto.idmatriz());
		matriz.setModelo(dto.modelo());
		matriz.setOrigprd(dto.origprd());
		matriz.setPrevfat(dto.prevfat());
		matriz.setPriodtmnt(dto.priodtmnt());
		matriz.setPriohrmnt(dto.priohrmnt());
		matriz.setPrioresp(dto.prioresp());
		matriz.setPriourgen(dto.priourgen());
		matriz.setProduto(dto.produto());
		matriz.setProtot(dto.protot());
		matriz.setSpecial(dto.special());
		matriz.setTpdcre(dto.tpdcre());
		matriz.setTpprd(dto.tpprd());
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
	
	public Matriprd update(Matriprd matriz,  MatriprdDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		matriz.setAnomdl(dto.anomdl());
		matriz.setDesccom(dto.desccom());
		matriz.setDescrfb(dto.descrfb());
		matriz.setDtneci(dto.dtneci());
		matriz.setModelo(dto.modelo());
		matriz.setOrigprd(dto.origprd());
		matriz.setPrevfat(dto.prevfat());
		matriz.setPriodtmnt(dto.priodtmnt());
		matriz.setPriohrmnt(dto.priohrmnt());
		matriz.setPrioresp(dto.prioresp());
		matriz.setPriourgen(dto.priourgen());
		matriz.setProduto(dto.produto());
		matriz.setProtot(dto.protot());
		matriz.setSpecial(dto.special());
		matriz.setTpdcre(dto.tpdcre());
		matriz.setTpprd(dto.tpprd());
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
}
