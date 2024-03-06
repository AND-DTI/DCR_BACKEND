package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.model.dto.MatriprdComCorDTO;
import com.dcr.api.model.dto.MatriprdDTO;
import com.dcr.api.repository.as400.MatriprdRepository;
import com.dcr.api.response.InsumosProdResponse;
import com.dcr.api.response.InsumosResponse;
import com.dcr.api.response.MatriprdByTpprdResponse;
import com.dcr.api.response.MatriprdByTpprdResponseList;
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
		List<InsumosProdResponse> listaIns = new ArrayList();
        for (Object[] resultado : resultados) {
        	MatriprdResponseList resp = new MatriprdResponseList();
        	InsumosProdResponse ins = new InsumosProdResponse();
        	
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
        	res.setDscpor(  resultado[27].toString().trim());
        	res.setDscing(  resultado[28].toString().trim());
        	
        	ins.setItmorg(  resultado[29].toString().trim());
        	ins.setIttyp(  resultado[30].toString().trim());
        	ins.setUnmsr(  resultado[31].toString().trim());
        	ins.setNecfil(  resultado[32].toString().trim());
        	ins.setCdspn(  resultado[33].toString().trim());
        	ins.setWeght(  resultado[34].toString().trim());
        	ins.setEmcomp(  resultado[35].toString().trim());
        	ins.setPartsugest(  resultado[36].toString().trim());
        	ins.setPartsugdsc(  resultado[37].toString().trim());
        	ins.setPartnew(  resultado[38].toString().trim());
        	ins.setPartnewdsc(  resultado[39].toString().trim());
        	ins.setPartdesc(  resultado[40].toString().trim());
        	
        	
        	
        	Boolean contemIns = Boolean.FALSE;
        	for (InsumosProdResponse item : listaIns) {
				if(ins.getPartdesc().equals(item.getPartdesc())) {
					contemIns = Boolean.TRUE;
				}
			}
        	if(!contemIns) {
        		listaIns.add(ins);
        	}
        	
        	Boolean contem = Boolean.FALSE;
        	for (MatriprdResponseList item : lista) {
				if(item.getPartnumpd().equals(resp.getPartnumpd())) {
					contem = Boolean.TRUE;
				}
			}
        	if(!contem) {
        		lista.add(resp);
        	}
        	resp.setInsumos(listaIns);
            
        }
       
        res.setItens(lista);
        produtos.add(res);
		return res;
	}
	
	public List<MatriprdByTpprdResponse> getDetailByTpprd(List<String> tpprdList) {
		List<Object[]> resultados = repository.consultaByTpprd(tpprdList);
		
		List<MatriprdByTpprdResponse> listaResponse = new ArrayList();
		
		List<MatriprdByTpprdResponseList> lista = new ArrayList();
		List<InsumosProdResponse> listaIns = new ArrayList();
        for (Object[] resultado : resultados) {
        	MatriprdByTpprdResponseList resp = new MatriprdByTpprdResponseList();
        	MatriprdByTpprdResponse res = new MatriprdByTpprdResponse();
        	
        	res.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	res.setProduto(  (resultado[1] != null) ? resultado[1].toString().trim() : "" );
        	res.setModelo(  (resultado[2] != null) ? resultado[2].toString().trim() : "" ); 
        	res.setAnomdl(  (resultado[3] != null) ? resultado[3].toString().trim() : "" );
        	res.setDesccom(  (resultado[4] != null) ? resultado[4].toString().trim() : "" );
        	res.setDescrfb( (resultado[5] != null) ? resultado[5].toString().trim() : "" );
        	res.setTpprd(  (resultado[6] != null) ? resultado[6].toString().trim() : "" );
        	res.setProtot(  (resultado[7] != null) ? resultado[7].toString().trim() : "" );
        	res.setSpecial( (resultado[8] != null) ? resultado[8].toString().trim() : "" );
        	res.setTpdcre((resultado[9] != null) ? resultado[9].toString().trim() : "" );
        	res.setOrig((resultado[10] != null) ? resultado[10].toString().trim() : "" );
        	res.setDtneci(  (resultado[11] != null) ? resultado[11].toString().trim() : "" );
        	res.setPriourgen(  (resultado[12] != null) ? resultado[12].toString().trim() : "" );
        	res.setPrevfat(  (resultado[13] != null) ? resultado[13].toString().trim() : "" );
        	res.setPrioresp(  (resultado[14] != null) ? resultado[14].toString().trim() : "" );
        	res.setPriodtmnt(  (resultado[15] != null) ? resultado[15].toString().trim() : "" );
        	res.setPrioHRmnt(  (resultado[16] != null) ? resultado[16].toString().trim() : "" );
        	res.setNome((resultado[29] != null) ? resultado[29].toString().trim() : "" );
        	resp.setPartnumpd(  (resultado[17] != null) ? resultado[17].toString().trim() : "" );
        	resp.setCodcor(  (resultado[19] != null) ? resultado[19].toString().trim() : "" );
        	resp.setPartdesc(  (resultado[20] != null) ? resultado[20].toString().trim() : "" );
        	resp.setUnmed(  (resultado[21] != null) ? resultado[21].toString().trim() : "" );
        	resp.setPriocor(  (resultado[22] != null) ? resultado[22].toString().trim() : "" );
        	resp.setCdbeg(  (resultado[23] != null) ? resultado[23].toString().trim() : "" );
        	resp.setCorpt(  (resultado[24] != null) ? resultado[24].toString().trim() : "" );
        	resp.setCoreng(  (resultado[25] != null) ? resultado[25].toString().trim() : "" );
        	resp.setTppin(  (resultado[26] != null) ? resultado[26].toString().trim() : "" );
        	res.setDscpor(  (resultado[27] != null) ? resultado[27].toString().trim() : "" );
        	res.setDscing(  (resultado[28] != null) ? resultado[28].toString().trim() : "" );

        	Boolean contem = Boolean.FALSE;
        	for (MatriprdByTpprdResponseList item : lista) {
				if(item.getPartnumpd().equals(resp.getPartnumpd())) {
					contem = Boolean.TRUE;
				}
			}
        	if(!contem) {
        		lista.add(resp);
        	}
        	
        	Boolean contemItem = Boolean.FALSE;
        	for (MatriprdByTpprdResponse item : listaResponse) {
				if(item.getProduto().equals(res.getProduto())) {
					contemItem = Boolean.TRUE;
					lista = new ArrayList();
				}
			}
        	
        	if(!contemItem) {
        		 res.setItens(lista);
        		 listaResponse.add(res); 
        		 
        	}
        	
        	
        }
       
		return listaResponse;
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
        	item.setPartnum( resultado[23].toString().trim());
        	item.setItmorg( resultado[24].toString().trim());
        	item.setIttyp( resultado[25].toString().trim());
        	item.setUnmsr( resultado[26].toString().trim());
        	item.setNecfil( resultado[27].toString().trim());
        	item.setCdspn( resultado[28].toString().trim());
        	item.setWeght( resultado[29].toString().trim());
        	item.setEmcomp( resultado[30].toString().trim());
        	item.setPartsugest( resultado[31].toString().trim());
        	item.setPartsugdsc( resultado[32].toString().trim());
        	item.setPartnew( resultado[33].toString().trim());
        	item.setPartnewdsc( resultado[34].toString().trim());
        	item.setNumpend( resultado[35].toString().trim());
        	item.setCdpend( resultado[36].toString().trim());
        	item.setObspend( resultado[37].toString().trim());
        	item.setStatus( resultado[38].toString().trim());
        	lista.add(item);
            produtos.add(resp);
        }
        resp.setItens(lista);
		return resp;
	}
	
	public void delete(Matriprd matriz) {
		
		repository.delete(matriz);
	}
	
	public Matriprd createComCor(MatriprdComCorDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Matriprd matriz = new Matriprd();
		
		matriz.setAnomdl(dto.anomdl());
		matriz.setDesccom(dto.desccom());
		matriz.setDescrfb(dto.descrfb());
		matriz.setDtneci(dto.dtneci());
		matriz.setIdmatriz(dto.idmatriz());
		matriz.setModelo(dto.modelo());
		matriz.setOrigprd(dto.origprd());
		matriz.setPrevfat(dto.prevfat());
		matriz.setPriodtmnt(Auxiliar.getDtFormated());
		matriz.setPriohrmnt(Auxiliar.getHrFormatedSemSegundo());
		matriz.setPrioresp(Auxiliar.getUser(request));
		matriz.setPriourgen(dto.priourgen());
		matriz.setProduto(dto.produto());
		matriz.setProtot(dto.protot());
		matriz.setSpecial(dto.special());
		matriz.setTpdcre(dto.tpdcre());
		matriz.setTpprd(dto.tpprd());
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
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
	
public Matriprd updateComCor(Matriprd matriz,  MatriprdComCorDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		matriz.setAnomdl(dto.anomdl());
		matriz.setDesccom(dto.desccom());
		matriz.setDescrfb(dto.descrfb());
		matriz.setDtneci(dto.dtneci());
		matriz.setModelo(dto.modelo());
		matriz.setOrigprd(dto.origprd());
		matriz.setPrevfat(dto.prevfat());
		matriz.setPriodtmnt(Auxiliar.getDtFormated());
		matriz.setPriohrmnt(Auxiliar.getHrFormatedSemSegundo());
		matriz.setPrioresp(Auxiliar.getUser(request));
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
