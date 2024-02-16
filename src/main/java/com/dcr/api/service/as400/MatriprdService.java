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
import com.dcr.api.response.MatriprdResponseList;
import com.dcr.api.response.ProdutoPendenciaResponse;
import com.dcr.api.response.ProdutoPendenciaResponseList;
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
	
	public MatriprdResponse getDetail(Integer id) {
		List<Object[]> resultados = repository.consultaJoin(id);
		
		List<MatriprdResponse> produtos = new ArrayList<>();
		MatriprdResponse res = new MatriprdResponse();
		List<MatriprdResponseList> lista = new ArrayList();
        for (Object[] resultado : resultados) {
        	MatriprdResponseList resp = new MatriprdResponseList();
        	
			res.setIdMatriz(  resultado[0].toString().trim());
        	res.setProduto(  resultado[1].toString().trim());
        	res.setModelo(  resultado[2].toString().trim()); 
        	res.setAnomdl(  resultado[3].toString().trim());
        	res.setDesccom(  resultado[4].toString().trim());
        	res.setDescrfb(  resultado[5].toString().trim());
        	res.setTpprd(  resultado[6].toString().trim());
        	res.setProtot(  resultado[7].toString().trim());
        	res.setSpecial(  resultado[8].toString().trim());
        	res.setTpdcre(resultado[9].toString().trim());
        	res.setOrig(resultado[10].toString().trim());
        	res.setDtneci(  resultado[11].toString().trim());
        	res.setPriourgen(  resultado[12].toString().trim());
        	res.setPrevfat(  resultado[13].toString().trim());
        	res.setPrioresp(  resultado[14].toString().trim());
        	res.setPriodtmnt(  resultado[15].toString().trim());
        	res.setPrioHRmnt(  resultado[16].toString().trim());
        	
        	resp.setPartnumpd(  resultado[17].toString().trim());
        	resp.setCodcor(  resultado[19].toString().trim());
        	resp.setPartdesc(  resultado[20].toString().trim());
        	resp.setUnmed(  resultado[21].toString().trim());
        	resp.setPriocor(  resultado[22].toString().trim());
        	resp.setCdbeg(  resultado[23].toString().trim());
        	resp.setCorpt(  resultado[24].toString().trim());
        	resp.setCoreng(  resultado[25].toString().trim());
        	resp.setTppin(  resultado[26].toString().trim());
        	resp.setDscpor(  resultado[27].toString().trim());
        	resp.setDscing(  resultado[28].toString().trim());
        	lista.add(resp);
        	res.setItens(lista);
            produtos.add(res);
        }
        
		return res;
	}
	
	public ProdutoPendenciaResponse getProdutoPendencia(Integer id) {
		List<Object[]> resultados = repository.consultaProdutoPendencia(id);
		
		List<ProdutoPendenciaResponse> produtos = new ArrayList<>();
		ProdutoPendenciaResponse resp = new ProdutoPendenciaResponse();
		List<ProdutoPendenciaResponseList> lista = new ArrayList();
        for (Object[] resultado : resultados) {
        	ProdutoPendenciaResponseList item = new ProdutoPendenciaResponseList();
        	resp.setIdMatriz(  resultado[0].toString().trim());
        	resp.setProduto(  resultado[1].toString().trim());
        	resp.setModelo(  resultado[2].toString().trim());
        	resp.setAnomdl(  resultado[3].toString().trim());
        	resp.setDesccom(  resultado[4].toString().trim());
        	resp.setDescrfb(  resultado[5].toString().trim());
        	resp.setTpprd(  resultado[6].toString().trim());
        	resp.setProtot(  resultado[7].toString().trim());
        	resp.setSpecial(  resultado[8].toString().trim());
        	resp.setTpdcre(resultado[9].toString().trim());
        	resp.setOrig(resultado[10].toString().trim());
        	resp.setDtneci(  resultado[11].toString().trim());
        	resp.setPriourgen(  resultado[12].toString().trim());
        	resp.setPrevfat(  resultado[13].toString().trim());
        	resp.setPrioresp(  resultado[14].toString().trim());
        	resp.setPriodtmnt(  resultado[15].toString().trim());
        	resp.setPrioHRmnt(  resultado[16].toString().trim());
        	resp.setPartnumpd(  resultado[17].toString().trim());
        	resp.setModelo(resultado[18].toString().trim());
        	resp.setCodcor(  resultado[19].toString().trim());
        	resp.setPartdesc(  resultado[20].toString().trim());
        	resp.setUnmed(  resultado[21].toString().trim());
        	resp.setPriocor(  resultado[22].toString().trim());
        	resp.setPartnum( resultado[23].toString().trim());
        	resp.setItmorg( resultado[24].toString().trim());
        	resp.setIttyp( resultado[25].toString().trim());
        	resp.setUnmsr( resultado[26].toString().trim());
        	resp.setNecfil( resultado[27].toString().trim());
        	resp.setCdspn( resultado[28].toString().trim());
        	resp.setWeght( resultado[29].toString().trim());
        	resp.setEmcomp( resultado[30].toString().trim());
        	resp.setPartsugest( resultado[31].toString().trim());
        	resp.setPartsugdsc( resultado[32].toString().trim());
        	resp.setPartnew( resultado[33].toString().trim());
        	resp.setPartnewdsc( resultado[34].toString().trim());
        	item.setNumpend( resultado[35]);
        	item.setCdpend( resultado[36]);
        	item.setObspend( resultado[37]);
        	item.setStatus( resultado[38]);
        	lista.add(item);
            produtos.add(resp);
        }
        resp.setItens(lista);
		return resp;
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
