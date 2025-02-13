package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.dto.MtastecDTO;
import com.dcr.api.model.keys.MtastecKey;
import com.dcr.api.repository.as400.MtastecRepository;
import com.dcr.api.response.AstecDetailResponse;
import com.dcr.api.response.AstecPendenciaResponse;
import com.dcr.api.response.CoresResponse;
import com.dcr.api.response.CoresSimplesResponse;
import com.dcr.api.response.DocumentosAstecResponse;
import com.dcr.api.response.DocumentosResponse;
import com.dcr.api.response.InsumosAstecResponse;
import com.dcr.api.response.InsumosProdResponse;
import com.dcr.api.response.InsumosResponse;
import com.dcr.api.response.MatriprdResponse;
import com.dcr.api.response.MatriprdResponseList;
import com.dcr.api.response.PendenciaAstecResponse;
import com.dcr.api.response.PendenciaResponse;
import com.dcr.api.response.PendenciaResponseSemLista;
import com.dcr.api.response.PendenciaSemListaAstecResponse;
import com.dcr.api.response.ProdutoPendenciaAstecResponse;
import com.dcr.api.response.ProdutoPendenciaResponse;
import com.dcr.api.response.ProdutoPendenciaResponseList;
import com.dcr.api.response.ProdutoPendenciaSimplesAstecResponse;
import com.dcr.api.response.ProdutoPendenciaSimplesResponse;
import com.dcr.api.response.ProdutoSemListaAstecResponse;
import com.dcr.api.response.ProdutoSemListaResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;
import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class MtastecService {

	@Autowired
	MtastecRepository repository;
	
	public List<Mtastec> getAll() {
		
		return repository.findAll();
	}
	public List<Pendastec> findPendenciasZero(Long idmatriz, String partnumpd) {
		return repository.findPendenciasZero(idmatriz, partnumpd);
	}
	public Optional<Mtastec> getByID(Integer idmatriz, String partnumpd) {
		try {
			return Optional.of(repository.findByIdmatrizAndPartnumpd(idmatriz, partnumpd));
		}catch (Exception e) {
			return Optional.empty();
		}
		
	}
	
	public void delete(Mtastec matriz) {
		
		repository.delete(matriz);
	}
	
	
	public List<AstecDetailResponse> getDetail(Integer idmatriz) {
		List<Object[]> resultados = repository.consultaDetalhe(idmatriz);
		
		List<AstecDetailResponse> produtos = new ArrayList<>();
		AstecDetailResponse res = new AstecDetailResponse();
		
		List<InsumosResponse> lista = new ArrayList();
        
		for (Object[] resultado : resultados) {
        	InsumosResponse resp = new InsumosResponse();
        	
			res.setIdmatriz(  resultado[0].toString().trim());
        	res.setDesccom(  resultado[1].toString().trim());
        	res.setDescrfb(  resultado[2].toString().trim());
        	res.setOrigprd(  resultado[3].toString().trim());
        	res.setDtneci(  resultado[4].toString().trim());
        	res.setPriourgen(  resultado[5].toString().trim());
        	res.setPrevfat(  resultado[6].toString().trim());
        	res.setPrioresp(  resultado[7].toString().trim());
        	res.setPriodtmnt(  resultado[8].toString().trim());
        	res.setPriohrmnt(  resultado[9].toString().trim());
        	
        	resp.setItmorg(  resultado[10].toString().trim());
        	resp.setCdspn(  resultado[10].toString().trim());
        	resp.setEmcomp(  resultado[10].toString().trim());
        	resp.setIttyp(  resultado[10].toString().trim());
        	resp.setPartdesc(  resultado[10].toString().trim());
        	resp.setPartnew(  resultado[10].toString().trim());
        	resp.setPartnewdsc(  resultado[10].toString().trim());
        	resp.setPartsugdsc(  resultado[10].toString().trim());
        	resp.setPartsugest(  resultado[10].toString().trim());
        	resp.setUnmsr(  resultado[10].toString().trim());
        	lista.add(resp);
        	res.setInsumos(lista);
        }
		produtos.add(res);
		return produtos;
	}

	public List<AstecPendenciaResponse> getPendencia(Integer idmatriz) {
		List<Object[]> resultados = repository.consultaPendencia(idmatriz);
		
		List<AstecPendenciaResponse> produtos = new ArrayList<>();

        for (Object[] resultado : resultados) {
        	AstecPendenciaResponse resp = new AstecPendenciaResponse();
        	resp.setIdMatriz((Object) resultado[0]);
        	resp.setPartnumpd((Object) resultado[1]);
        	resp.setDesccom((Object) resultado[2]);
        	resp.setDescrfb((Object) resultado[3]);
        	resp.setUnmed((Object) resultado[4]);
        	resp.setOrigprd((Object) resultado[5]);
        	resp.setDtneci((Object) resultado[6]);
        	resp.setPriourgen((Object) resultado[7]);
        	resp.setPrevfat((Object) resultado[8]);
        	resp.setPrioresp((Object) resultado[9]);
        	resp.setPriodtmnt((Object) resultado[10]);
        	resp.setPrioHrmnt((Object) resultado[11]);
        	resp.setPartnum((Object) resultado[12]);
        	resp.setPartdesc((Object) resultado[13]);
        	resp.setItmorg((Object) resultado[14]);
        	resp.setIttyp((Object) resultado[15]);
        	resp.setUnmsr((Object) resultado[16]);
        	resp.setNecfil((Object) resultado[17]);
        	resp.setCdspn((Object) resultado[18]);
        	resp.setWeght((Object) resultado[19]);
        	resp.setEmcomp((Object) resultado[20]);
        	resp.setPartsugest((Object) resultado[21]);
        	resp.setPartsugdsc((Object) resultado[22]);
        	resp.setPartnew((Object) resultado[23]);
        	resp.setPartnewdsc((Object) resultado[24]);
        	resp.setNumpend((Object) resultado[25]);
        	resp.setCdpend((Object) resultado[26]);
        	resp.setStatus((Object) resultado[27]);
        	resp.setTpdcre((Object) resultado[28]);
            produtos.add(resp);
        }
        
		return produtos;
	}
	
	public Mtastec create(MtastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Mtastec astec = new Mtastec();
		
		
		astec.setPartnumpd(dto.partnumpd());
		astec.setDesccom(dto.desccom());
		astec.setDescrfb(dto.descrfb());
		astec.setDtneci(dto.dtneci());
		astec.setOrigprd(dto.origprd());
		astec.setPrevfat(dto.prevfat());
		astec.setPriodtmnt(dto.priodtmnt());
		astec.setPriohrmnt(dto.priohrmnt());
		astec.setPrioresp(dto.prioresp());
		astec.setPriourgen(dto.priourgen());
		astec.setUnmed(dto.unmed());
		astec.setTpdcre(dto.tpdcre());
		astec.setItgarantia(dto.itgarantia());
		astec.setObsprio(dto.obsprio());
		Auxiliar.preencheAuditoria(astec, request);
		return repository.save(astec);
	}
	
	public Mtastec update(Mtastec astec,  MtastecDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		astec.setDesccom(dto.desccom());
		astec.setDescrfb(dto.descrfb());
		astec.setDtneci(dto.dtneci());
		astec.setOrigprd(dto.origprd());
		astec.setPrevfat(dto.prevfat());
		astec.setPriodtmnt(dto.priodtmnt());
		astec.setPriohrmnt(dto.priohrmnt());
		astec.setPrioresp(dto.prioresp());
		astec.setPriourgen(dto.priourgen());
		astec.setUnmed(dto.unmed());
		astec.setTpdcre(dto.tpdcre());
		astec.setItgarantia(dto.itgarantia());
		astec.setObsprio(dto.obsprio());
		Auxiliar.preencheAuditoria(astec, request);
		return repository.save(astec);
	}
	
	public ProdutoPendenciaSimplesAstecResponse getProdutoPendencia(Integer id, String partnum) {
		List<Object[]> resultados = repository.consultaProdutoPendencia(id, partnum);
		
		List<ProdutoPendenciaSimplesAstecResponse> produtos = new ArrayList<>();
		ProdutoPendenciaSimplesAstecResponse resp = new ProdutoPendenciaSimplesAstecResponse();
		List<PendenciaAstecResponse> listaPendSet = new ArrayList();
		Set<DocumentosAstecResponse> listaDocSet = new HashSet<>();
		Set<InsumosAstecResponse> listaInsumoSet = new HashSet<>();
        for (Object[] resultado : resultados) {
        	ProdutoPendenciaResponseList item = new ProdutoPendenciaResponseList();
        	DocumentosAstecResponse doc = new DocumentosAstecResponse();
        	PendenciaAstecResponse pend = new PendenciaAstecResponse();
        	InsumosAstecResponse ins = new InsumosAstecResponse();
        	
        	resp.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	resp.setPartnumpd((resultado[1] != null) ? resultado[1].toString().trim() : "" );
        	resp.setDesccom(  (resultado[2] != null) ? resultado[2].toString().trim() : "");
        	resp.setDescrfb(  (resultado[3] != null) ? resultado[3].toString().trim() : "");
        	resp.setUnmed((resultado[4] != null) ? resultado[4].toString().trim() : "");
        	resp.setOrigprd((resultado[5] != null) ? resultado[5].toString().trim() : "");
        	resp.setDtneci(  (resultado[6] != null) ? resultado[6].toString().trim() : "");
        	resp.setPriourgen(  (resultado[7] != null) ? resultado[7].toString().trim() : "");
        	resp.setPrevfat(  (resultado[8] != null) ? resultado[8].toString().trim() : "");
        	resp.setPrioresp(  (resultado[9] != null) ? resultado[9].toString().trim() : "");
        	resp.setPriodtmnt(  (resultado[10] != null) ? resultado[10].toString().trim() : "");
        	resp.setPrioHRmnt(  (resultado[11] != null) ? resultado[11].toString().trim() : "");
        	resp.setPpbprd((resultado[57] != null) ? resultado[57].toString().trim() : "");
        	resp.setPrddest((resultado[58] != null) ? resultado[58].toString().trim() : "");
        	resp.setTpdcre((resultado[59] != null) ? resultado[59].toString().trim() : "");
        	
        	pend.setPartnum((resultado[12] != null) ? resultado[12].toString().trim() : "");
        	pend.setNumpend( (resultado[13] != null) ? resultado[13].toString().trim() : "");
        	pend.setCdpend( (resultado[14] != null) ? resultado[14].toString().trim() : "");
        	pend.setObsresol((resultado[15] != null) ? resultado[15].toString().trim() : "");
        	pend.setStatus( (resultado[16] != null) ? resultado[16].toString().trim() : "");
        	pend.setIdmatriz((resultado[56] != null) ? resultado[56].toString().trim() : "");
        	ins.setPartnum((resultado[17] != null) ? resultado[17].toString().trim() : "");
        	ins.setPartdesc((resultado[18] != null) ? resultado[18].toString().trim() : "");
        	ins.setItmorg((resultado[19] != null) ? resultado[19].toString().trim() : "");
        	ins.setIttyp((resultado[20] != null) ? resultado[20].toString().trim() : "");
        	ins.setUnmsr((resultado[21] != null) ? resultado[21].toString().trim() : "");
        	ins.setNecfil((resultado[22] != null) ? resultado[22].toString().trim() : "");
        	ins.setCdspn((resultado[23] != null) ? resultado[23].toString().trim() : "");
        	ins.setWeght((resultado[24] != null) ? resultado[24].toString().trim() : "");
        	ins.setEmcomp((resultado[25] != null) ? resultado[25].toString().trim() : "");
        	ins.setNcm((resultado[26] != null) ? resultado[26].toString().trim() : "");
        	ins.setVlrunit((resultado[27] != null) ? resultado[27].toString().trim() : "");
        	ins.setPartsugest((resultado[28] != null) ? resultado[28].toString().trim() : "");
        	ins.setPartsugdsc((resultado[29] != null) ? resultado[29].toString().trim() : "");
        	ins.setPartnew((resultado[30] != null) ? resultado[30].toString().trim() : "");
        	ins.setPartnewdsc((resultado[31] != null) ? resultado[31].toString().trim() : "");
        	
        	doc.setPartnum((resultado[32] != null) ? resultado[32].toString().trim() : "");
        	doc.setAdicao((resultado[33] != null) ? resultado[33].toString().trim() : "");
        	doc.setAdicao2((resultado[34] != null) ? resultado[34].toString().trim() : "");
        	doc.setAdicao3((resultado[35] != null) ? resultado[35].toString().trim() : "");
        	doc.setCnpjfor((resultado[36] != null) ? resultado[36].toString().trim() : "");
        	doc.setCnpjfor2((resultado[37] != null) ? resultado[37].toString().trim() : "");
        	doc.setCnpjfor3((resultado[38] != null) ? resultado[38].toString().trim() : "");
        	doc.setEmidoc((resultado[39] != null) ? resultado[39].toString().trim() : "");
        	doc.setEmidoc2((resultado[40] != null) ? resultado[40].toString().trim() : "");
        	doc.setEmidoc3((resultado[41] != null) ? resultado[41].toString().trim() : "");
        	doc.setIe((resultado[42] != null) ? resultado[42].toString().trim() : "");
        	doc.setIe2((resultado[43] != null) ? resultado[43].toString().trim() : "");
        	doc.setIe3((resultado[44] != null) ? resultado[44].toString().trim() : "");
        	doc.setItadicao((resultado[45] != null) ? resultado[45].toString().trim() : "");
        	doc.setItadicao2((resultado[46] != null) ? resultado[46].toString().trim() : "");
        	doc.setItadicao3((resultado[47] != null) ? resultado[47].toString().trim() : "");
        	doc.setNumdoc((resultado[48] != null) ? resultado[48].toString().trim() : "");
        	doc.setNumdoc2((resultado[49] != null) ? resultado[49].toString().trim() : "");
        	doc.setNumdoc3((resultado[50] != null) ? resultado[50].toString().trim() : "");
        	doc.setPartnum((resultado[51] != null) ? resultado[51].toString().trim() : "");
        	doc.setSerdoc((resultado[52] != null) ? resultado[52].toString().trim() : "");
        	doc.setSerdoc2((resultado[53] != null) ? resultado[53].toString().trim() : "");
        	doc.setSerdoc3((resultado[54] != null) ? resultado[54].toString().trim() : "");
        	doc.setTpdoc((resultado[55] != null) ? resultado[55].toString().trim() : "");
        	
        	Boolean existeInsumo = Boolean.FALSE;
        	for (InsumosAstecResponse coresSimplesResponse : listaInsumoSet) {
				if(coresSimplesResponse.getPartnum().equals(ins.getPartnum())) {
					existeInsumo = Boolean.TRUE;
				}
			}
        	if(!existeInsumo) {
        		listaInsumoSet.add(ins);
        	}
        	
        	
        	Boolean existePend = Boolean.FALSE;
        	for (PendenciaAstecResponse coresSimplesResponse : listaPendSet) {
				if(coresSimplesResponse.getNumpend().equals(pend.getNumpend())) {
					existePend = Boolean.TRUE;
				}
			}
        	if(!existePend) {
        		listaPendSet.add(pend);
        	}
        	
        	Boolean existeDoc = Boolean.FALSE;
        	for (DocumentosAstecResponse coresSimplesResponse : listaDocSet) {
				if(coresSimplesResponse.getNumdoc().equals(doc.getNumdoc()) && coresSimplesResponse.getPartnum().equals(doc.getPartnum())) {
					existeDoc = Boolean.TRUE;
				}
			}
        	if(!existeDoc && (!doc.getPartnum().equals("") && !doc.getNumdoc().equals(""))) {
        		listaDocSet.add(doc);
        	}  
        }
        List<PendenciaAstecResponse> listaPend = new ArrayList<>(listaPendSet);
        List<DocumentosAstecResponse> listaDoc = new ArrayList<>(listaDocSet);
        List<InsumosAstecResponse> listaInsumo = new ArrayList<>(listaInsumoSet);
        resp.setPendencias(listaPend);
        resp.setInsumos(listaInsumo);
        resp.setDocumentos(listaDoc);
       
		return resp;
	}
	
	public List<ProdutoPendenciaAstecResponse> getTodasAsPendencias(List<Integer> status) {
		List<Object[]> resultados = repository.consultaTodasAsPendencias(status);
		
		List<ProdutoPendenciaAstecResponse> produtos = new ArrayList<>();
	
		Set<PendenciaAstecResponse> listaPendSet = new HashSet<>();
		Set<DocumentosAstecResponse> listaDocSet = new HashSet<>();
        for (Object[] resultado : resultados) {
        	ProdutoPendenciaAstecResponse resp = new ProdutoPendenciaAstecResponse();
    		
        	
        	PendenciaAstecResponse pend = new PendenciaAstecResponse();
        	DocumentosAstecResponse doc = new DocumentosAstecResponse();
        	
        	resp.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	resp.setPartnumpd((resultado[1] != null) ? resultado[1].toString().trim() : "" );
        	resp.setDesccom(  (resultado[2] != null) ? resultado[2].toString().trim() : "");
        	resp.setDescrfb(  (resultado[3] != null) ? resultado[3].toString().trim() : "");
        	resp.setUnmed((resultado[4] != null) ? resultado[4].toString().trim() : "");
        	resp.setOrigprd((resultado[5] != null) ? resultado[5].toString().trim() : "");
        	resp.setDtneci(  (resultado[6] != null) ? resultado[6].toString().trim() : "");
        	resp.setPriourgen(  (resultado[7] != null) ? resultado[7].toString().trim() : "");
        	resp.setPrevfat(  (resultado[8] != null) ? resultado[8].toString().trim() : "");
        	resp.setPrioresp(  (resultado[9] != null) ? resultado[9].toString().trim() : "");
        	resp.setPriodtmnt(  (resultado[10] != null) ? resultado[10].toString().trim() : "");
        	resp.setPrioHRmnt(  (resultado[11] != null) ? resultado[11].toString().trim() : "");
        	resp.setStatus((resultado[57] != null) ? resultado[57].toString().trim() : "");
        	resp.setPpbprd((resultado[58] != null) ? resultado[58].toString().trim() : "");
        	resp.setPrddest((resultado[59] != null) ? resultado[59].toString().trim() : "");
        	resp.setTpdcre((resultado[60] != null) ? resultado[60].toString().trim() : "");
        	
        	pend.setPartnum((resultado[12] != null) ? resultado[12].toString().trim() : "");
        	pend.setNumpend( (resultado[13] != null) ? resultado[13].toString().trim() : "");
        	pend.setCdpend( (resultado[14] != null) ? resultado[14].toString().trim() : "");
        	pend.setObsresol((resultado[15] != null) ? resultado[15].toString().trim() : "");
        	pend.setStatus( (resultado[16] != null) ? resultado[16].toString().trim() : "");
        	pend.setIdmatriz((resultado[56] != null) ? resultado[56].toString().trim() : "");
        	
        	doc.setPartnum((resultado[32] != null) ? resultado[32].toString().trim() : "");
        	doc.setAdicao((resultado[33] != null) ? resultado[33].toString().trim() : "");
        	doc.setAdicao2((resultado[34] != null) ? resultado[34].toString().trim() : "");
        	doc.setAdicao3((resultado[35] != null) ? resultado[35].toString().trim() : "");
        	doc.setCnpjfor((resultado[36] != null) ? resultado[36].toString().trim() : "");
        	doc.setCnpjfor2((resultado[37] != null) ? resultado[37].toString().trim() : "");
        	doc.setCnpjfor3((resultado[38] != null) ? resultado[38].toString().trim() : "");
        	doc.setEmidoc((resultado[39] != null) ? resultado[39].toString().trim() : "");
        	doc.setEmidoc2((resultado[40] != null) ? resultado[40].toString().trim() : "");
        	doc.setEmidoc3((resultado[41] != null) ? resultado[41].toString().trim() : "");
        	doc.setIe((resultado[42] != null) ? resultado[42].toString().trim() : "");
        	doc.setIe2((resultado[43] != null) ? resultado[43].toString().trim() : "");
        	doc.setIe3((resultado[44] != null) ? resultado[44].toString().trim() : "");
        	doc.setItadicao((resultado[45] != null) ? resultado[45].toString().trim() : "");
        	doc.setItadicao2((resultado[46] != null) ? resultado[46].toString().trim() : "");
        	doc.setItadicao3((resultado[47] != null) ? resultado[47].toString().trim() : "");
        	doc.setNumdoc((resultado[48] != null) ? resultado[48].toString().trim() : "");
        	doc.setNumdoc2((resultado[49] != null) ? resultado[49].toString().trim() : "");
        	doc.setNumdoc3((resultado[50] != null) ? resultado[50].toString().trim() : "");
        	doc.setPartnum((resultado[51] != null) ? resultado[51].toString().trim() : "");
        	doc.setSerdoc((resultado[52] != null) ? resultado[52].toString().trim() : "");
        	doc.setSerdoc2((resultado[53] != null) ? resultado[53].toString().trim() : "");
        	doc.setSerdoc3((resultado[54] != null) ? resultado[54].toString().trim() : "");
        	doc.setTpdoc((resultado[55] != null) ? resultado[55].toString().trim() : "");
        	
        	if (!(pend.getCdpend().equals("") && pend.getNumpend().equals("") && pend.getObsresol().equals("") && pend.getStatus().equals(""))) {
                listaPendSet.add(pend); // Adiciona à lista apenas se não estiver duplicado
            }
        	
        	if (!(doc.getTpdoc().equals("") && doc.getSerdoc().equals("") && doc.getNumdoc().equals("") && doc.getEmidoc().equals("") 
                    && doc.getSerdoc2().equals("") && doc.getNumdoc2().equals("") && doc.getEmidoc2().equals("") 
                    && doc.getSerdoc3().equals("") && doc.getNumdoc3().equals("") && doc.getEmidoc3().equals(""))) {
                listaDocSet.add(doc); // Adiciona à lista apenas se não estiver duplicado
            }
        
        	
        		 
            
        	Boolean existe = Boolean.FALSE;
            for (ProdutoPendenciaAstecResponse prd : produtos) {
				if(prd.getIdMatriz().equals(resp.getIdMatriz())) {
					existe = Boolean.TRUE;
				}
			}
            
            
           
            List<PendenciaAstecResponse> listaPend = new ArrayList<>(listaPendSet);
            List<DocumentosAstecResponse> listaDoc = new ArrayList<>(listaDocSet);

            
            resp.setPendencias(listaPend);
            resp.setDocumentos(listaDoc);
           
            if(!existe) {
            	produtos.add(resp);
            }else {
            	
            	
            	Boolean existePendencias = Boolean.FALSE;
            	for (ProdutoPendenciaAstecResponse prod : produtos) {
					for (PendenciaAstecResponse pendAtual : prod.getPendencias()) {
						for (PendenciaAstecResponse coresAdd : listaPend) {
							if(pendAtual.getCdpend().equals(coresAdd.getCdpend())) {
								existePendencias = Boolean.TRUE;
							}
						}
					}
					if(!existePendencias && pend.getIdmatriz().equals(prod.getIdMatriz())) {
						prod.getPendencias().add(pend);
					}
				}
            	
            	Boolean existeDoc = Boolean.FALSE;
            	for (ProdutoPendenciaAstecResponse prod : produtos) {
					for (DocumentosAstecResponse docAtual : prod.getDocumentos()) {
						for (DocumentosAstecResponse coresAdd : listaDoc) {
							if(docAtual.getTpdoc().equals(coresAdd.getTpdoc())) {
								existeDoc = Boolean.TRUE;
							}
						}
					}
					if(!existeDoc && pend.getIdmatriz().equals(prod.getIdMatriz())) {
						prod.getDocumentos().add(doc);
					}
				}
            }
            listaPendSet = new HashSet<>();
            listaDocSet = new HashSet<>();
        }
        
        for (ProdutoPendenciaAstecResponse produto : produtos) {
			produto.setQtdependencias(repository.countPendenciasNoPartnum(produto.getIdMatriz().toString()));
		}
		return produtos;
	}
	
	
	public List<ProdutoSemListaAstecResponse> getPendenciasSemLista(List<Integer> status) {
		List<Object[]> resultados = repository.consultaTodasAsPendenciasSemLista(status);
		
		List<ProdutoSemListaAstecResponse> produtos = new ArrayList<>();
		Set<PendenciaSemListaAstecResponse> listaPendSet = new HashSet<>();
        for (Object[] resultado : resultados) {
        	ProdutoSemListaAstecResponse resp = new ProdutoSemListaAstecResponse();
    		
        	PendenciaSemListaAstecResponse pend = new PendenciaSemListaAstecResponse();
        	
        	resp.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	resp.setPartnumpd((resultado[1] != null) ? resultado[1].toString().trim() : "" );
        	resp.setDesccom(  (resultado[2] != null) ? resultado[2].toString().trim() : "");
        	resp.setDescrfb(  (resultado[3] != null) ? resultado[3].toString().trim() : "");
        	
        	resp.setDtneci(  (resultado[6] != null) ? resultado[6].toString().trim() : "");
        	resp.setPriourgen(  (resultado[7] != null) ? resultado[7].toString().trim() : "");
        	resp.setPrevfat(  (resultado[8] != null) ? resultado[8].toString().trim() : "");
        	resp.setPrioresp(  (resultado[9] != null) ? resultado[9].toString().trim() : "");
        	resp.setPriodtmnt(  (resultado[10] != null) ? resultado[10].toString().trim() : "");
        	resp.setPrioHRmnt(  (resultado[11] != null) ? resultado[11].toString().trim() : "");
        	resp.setStatus((resultado[57] != null) ? resultado[57].toString().trim() : "");
        	resp.setPpbprd((resultado[58] != null) ? resultado[58].toString().trim() : "");
        	resp.setPpbprd((resultado[59] != null) ? resultado[59].toString().trim() : "");
        	resp.setTpdcre((resultado[60] != null) ? resultado[60].toString().trim() : "");
        	
        	pend.setPartnum((resultado[12] != null) ? resultado[12].toString().trim() : "");
        	pend.setNumpend( (resultado[13] != null) ? resultado[13].toString().trim() : "");
        	pend.setCdpend( (resultado[14] != null) ? resultado[14].toString().trim() : "");
        	pend.setObsresol((resultado[15] != null) ? resultado[15].toString().trim() : "");
        	pend.setStatus( (resultado[16] != null) ? resultado[16].toString().trim() : "");
        	pend.setIdmatriz((resultado[56] != null) ? resultado[56].toString().trim() : "");
        	
        	pend.setPartnum((resultado[32] != null) ? resultado[32].toString().trim() : "");
        	pend.setAdicao((resultado[33] != null) ? resultado[33].toString().trim() : "");
        	pend.setAdicao2((resultado[34] != null) ? resultado[34].toString().trim() : "");
        	pend.setAdicao3((resultado[35] != null) ? resultado[35].toString().trim() : "");
        	pend.setCnpjfor((resultado[36] != null) ? resultado[36].toString().trim() : "");
        	pend.setCnpjfor2((resultado[37] != null) ? resultado[37].toString().trim() : "");
        	pend.setCnpjfor3((resultado[38] != null) ? resultado[38].toString().trim() : "");
        	pend.setEmidoc((resultado[39] != null) ? resultado[39].toString().trim() : "");
        	pend.setEmidoc2((resultado[40] != null) ? resultado[40].toString().trim() : "");
        	pend.setEmidoc3((resultado[41] != null) ? resultado[41].toString().trim() : "");
        	pend.setIe((resultado[42] != null) ? resultado[42].toString().trim() : "");
        	pend.setIe2((resultado[43] != null) ? resultado[43].toString().trim() : "");
        	pend.setIe3((resultado[44] != null) ? resultado[44].toString().trim() : "");
        	pend.setItadicao((resultado[45] != null) ? resultado[45].toString().trim() : "");
        	pend.setItadicao2((resultado[46] != null) ? resultado[46].toString().trim() : "");
        	pend.setItadicao3((resultado[47] != null) ? resultado[47].toString().trim() : "");
        	pend.setNumdoc((resultado[48] != null) ? resultado[48].toString().trim() : "");
        	pend.setNumdoc2((resultado[49] != null) ? resultado[49].toString().trim() : "");
        	pend.setNumdoc3((resultado[50] != null) ? resultado[50].toString().trim() : "");
        	pend.setPartnum((resultado[51] != null) ? resultado[51].toString().trim() : "");
        	pend.setSerdoc((resultado[52] != null) ? resultado[52].toString().trim() : "");
        	pend.setSerdoc2((resultado[53] != null) ? resultado[53].toString().trim() : "");
        	pend.setSerdoc3((resultado[54] != null) ? resultado[54].toString().trim() : "");
        	pend.setTpdoc((resultado[55] != null) ? resultado[55].toString().trim() : "");
        	
        	if (!(pend.getCdpend().equals("") && pend.getNumpend().equals("") && pend.getStatus().equals(""))) {
                listaPendSet.add(pend); // Adiciona à lista apenas se não estiver duplicado
            }
        	
        	Boolean existe = Boolean.FALSE;
            for (ProdutoSemListaAstecResponse prd : produtos) {
				if(prd.getIdMatriz().equals(resp.getIdMatriz()) && prd.getPartnumpd().equals(resp.getPartnumpd())) {
					existe = Boolean.TRUE;
				}
			}
            
            if(listaPendSet.size() < 1 && !pend.getNumpend().equals("")) {
            	listaPendSet.add(pend);
            }
            for (PendenciaSemListaAstecResponse pendencia : listaPendSet) {
            	if(!pendencia.getCdpend().equals(pend.getCdpend()) && !pend.getNumpend().equals("") && pend.getIdmatriz().equals(resp.getIdMatriz())) {
    				listaPendSet.add(pend);
    			}
			}
            
            List<PendenciaSemListaAstecResponse> listaPend = new ArrayList<>(listaPendSet);

            resp.setPendencias(listaPend);
            
            
            if(!existe) {
                resp.setQtdependencias(repository.countPendencias(resp.getIdMatriz().toString(), pend.getPartnum().toString()));
            	if((repository.countPendencias(resp.getIdMatriz().toString(), pend.getPartnum().toString())) < 1) {
            		produtos.add(resp);
            	} else {
            		listaPend = new ArrayList<>();
            		listaPendSet = new HashSet<>();
            	}
                
            }
        	
           
        }
        
		return produtos;
	}
	
	public static <T> List<T> removerDuplicatas(List<T> lista) {
        Set<T> conjunto = new HashSet<>(lista);
        return new ArrayList<>(conjunto);
    }
	
	
}
