package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.model.dto.MatriprdComCorDTO;
import com.dcr.api.model.dto.MatriprdComCorIdDTO;
import com.dcr.api.model.dto.MatriprdDTO;
import com.dcr.api.repository.as400.MatriprdRepository;
import com.dcr.api.response.CoresResponse;
//import com.dcr.api.response.CoresSimplesResponse;
import com.dcr.api.response.DocumentosResponse;
import com.dcr.api.response.InsumosProdResponse;
import com.dcr.api.response.MatriprdByTpprdResponse;
import com.dcr.api.response.MatriprdByTpprdResponseList;
import com.dcr.api.response.MatriprdResponse;
import com.dcr.api.response.MatriprdResponseList;
import com.dcr.api.response.PendenciaResponse;
//import com.dcr.api.response.PendenciaResponseSemLista;
import com.dcr.api.response.ProdutoPendenciaResponse;
import com.dcr.api.response.ProdutoPendenciaResponseList;
import com.dcr.api.response.ProdutoPendenciaSimplesResponse;
import com.dcr.api.response.ProdutoSemListaResponse;
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
		List<MatriprdResponseList> lista = new ArrayList<MatriprdResponseList>(); //j4 type added
		List<InsumosProdResponse> listaIns = new ArrayList<InsumosProdResponse>(); //j4 type added
		
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
		List<MatriprdByTpprdResponse> listaResponse = new ArrayList<MatriprdByTpprdResponse>();	//j4 - array type added 	
		List<MatriprdByTpprdResponseList> lista = new ArrayList<MatriprdByTpprdResponseList>(); //j4 - array type added 
		//List<InsumosProdResponse> listaIns = new ArrayList(); //j4 - not used

		String matriz_anterior = "";
        for (Object[] resultado : resultados) {
        	MatriprdByTpprdResponse produto /*res*/ = new MatriprdByTpprdResponse();
			MatriprdByTpprdResponseList cor/*resp*/ = new MatriprdByTpprdResponseList();        	
        	
        	produto.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	produto.setProduto(  (resultado[1] != null) ? resultado[1].toString().trim() : "" );
        	produto.setModelo(  (resultado[2] != null) ? resultado[2].toString().trim() : "" ); 
        	produto.setAnomdl(  (resultado[3] != null) ? resultado[3].toString().trim() : "" );
        	produto.setDesccom(  (resultado[4] != null) ? resultado[4].toString().trim() : "" );
        	produto.setDescrfb( (resultado[5] != null) ? resultado[5].toString().trim() : "" );
        	produto.setTpprd(  (resultado[6] != null) ? resultado[6].toString().trim() : "" );
        	produto.setProtot(  (resultado[7] != null) ? resultado[7].toString().trim() : "" );
        	produto.setSpecial( (resultado[8] != null) ? resultado[8].toString().trim() : "" );
        	produto.setTpdcre((resultado[9] != null) ? resultado[9].toString().trim() : "" );
        	produto.setOrig((resultado[10] != null) ? resultado[10].toString().trim() : "" );
        	produto.setDtneci(  (resultado[11] != null) ? resultado[11].toString().trim() : "" );
        	produto.setPriourgen(  (resultado[12] != null) ? resultado[12].toString().trim() : "" );
        	produto.setPrevfat(  (resultado[13] != null) ? resultado[13].toString().trim() : "" );
        	produto.setPrioresp(  (resultado[14] != null) ? resultado[14].toString().trim() : "" );
        	produto.setPriodtmnt(  (resultado[15] != null) ? resultado[15].toString().trim() : "" );
        	produto.setPrioHRmnt(  (resultado[16] != null) ? resultado[16].toString().trim() : "" );        	
        	cor.setPartnumpd(  (resultado[17] != null) ? resultado[17].toString().trim() : "" );
			cor.setModelo(  (resultado[18] != null) ? resultado[18].toString().trim() : "" );
        	cor.setCodcor(  (resultado[19] != null) ? resultado[19].toString().trim() : "" );
        	cor.setPartdesc(  (resultado[20] != null) ? resultado[20].toString().trim() : "" );
        	cor.setUnmed(  (resultado[21] != null) ? resultado[21].toString().trim() : "" );
			cor.setNcm(  (resultado[22] != null) ? resultado[22].toString().trim() : "" ); //j4 - added
        	cor.setPriocor(  (resultado[23] != null) ? resultado[23].toString().trim() : "" );
        	cor.setCdbej(  (resultado[24] != null) ? resultado[24].toString().trim() : "" );//j4 - old setCdbeg
        	cor.setCorpt(  (resultado[25] != null) ? resultado[25].toString().trim() : "" );
        	cor.setCoreng(  (resultado[26] != null) ? resultado[26].toString().trim() : "" );
        	cor.setTppin(  (resultado[27] != null) ? resultado[27].toString().trim() : "" );
        	produto.setDscpor(  (resultado[28] != null) ? resultado[28].toString().trim() : "" );
        	produto.setDscing(  (resultado[29] != null) ? resultado[29].toString().trim() : "" );
			produto.setNome((resultado[30] != null) ? resultado[30].toString().trim() : "" );

			//Só funciona com order by em IDMATRIZ:
			Boolean novoProduto = false;
			if(!matriz_anterior.equals(produto.getIdMatriz())){
				novoProduto = true;				
				matriz_anterior = produto.getIdMatriz().toString();
			}

			if(novoProduto){
				lista = new ArrayList<MatriprdByTpprdResponseList>();
				produto.setItens(lista);
				listaResponse.add(produto);											
			}
			lista.add(cor); //nao verificar repetidos - select deve trazer lista de cores corretamente
      	
        }
       
		return listaResponse;
	}
	

	public PendenciaResponse complementaPendencia(PendenciaResponse pend, ProdutoPendenciaSimplesResponse resp) {
		
		if(!pend.getPartnum().toString().startsWith("00000")) {
			for (ProdutoPendenciaResponseList item : resp.getItens()) {
				if(item.getPartnum().equals(pend.getPartnum())) {
					pend.setPartsugest(item.getPartsugest().toString().trim());
					pend.setPartsugdsc(item.getPartsugdsc().toString().trim());
					break;
				}
			}
			
			for (DocumentosResponse doc : resp.getDocumentos()) {
				if(doc.getPartnum().equals(pend.getPartnum())) {
					pend.setNumdoc2(doc.getNumdoc2().toString().trim());
					break;
				}
			}
		}
		
		
		try {
			List<Object[]> resultadosDoc = repository.complementaPendenciaDoc(pend.getIdmatriz().toString(), pend.getPartnum().toString(), pend.getCdpend().toString());
			pend.setNumdoc(resultadosDoc.get(0)[0].toString().trim());
			pend.setSerdoc(resultadosDoc.get(0)[1].toString().trim());
		} catch (Exception e) {
			
		}
		
		
		List<Object[]> resultadosDesc = repository.complementaPendenciaDesc(pend.getCdpend().toString());
		if(resultadosDesc.size() > 0) {
			pend.setDescpend(resultadosDesc.get(0)[0].toString().trim());
			pend.setTpreg(resultadosDesc.get(0)[1].toString().trim());
		}
		
		
		return pend;
	}
	

	//public ProdutoPendenciaSimplesResponse getProdutoPendencia(Integer id, String partnumpd) {
	public ProdutoPendenciaResponse getProdutoPendencia(Integer id, String partnumpd) {


		List<Object[]> resultados = repository.consultaProdutoPendencia(id, partnumpd);	
		
		ProdutoPendenciaResponse produto = new ProdutoPendenciaResponse(); //j4 - old ProdutoPendenciaSimplesResponse resp
		List<InsumosProdResponse> listaInsumo = new ArrayList<>(); //j4 - old listaItem
		List<PendenciaResponse> listaPend = new ArrayList<>();
		List<DocumentosResponse> listaDoc = new ArrayList<>();
		List<CoresResponse> listaCor = new ArrayList<>();
		
		produto.setCores(listaCor);	
		produto.setPendencias(listaPend);		
		produto.setInsumos(listaInsumo); //j4 - old produto.setItens(listaItem);			
		produto.setDocumentos(listaDoc);
		produto.setQtdependencias(repository.countPendenciasCor(id.toString(), partnumpd));
		produto.setQtdependenciasEmAberto(repository.countPendenciasCorEmAberto(id.toString(), partnumpd));


        for (Object[] resultado : resultados) {
			

			CoresResponse cor = new CoresResponse(); //old CoresSimplesResponse						
        	PendenciaResponse pend = new PendenciaResponse();
			InsumosProdResponse insumo = new InsumosProdResponse(); //j4 - old ProdutoPendenciaResponseList
        	DocumentosResponse doc = new DocumentosResponse();

			produto.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	produto.setProduto(  (resultado[1] != null) ? resultado[1].toString().trim() : "");
        	produto.setModelo(  (resultado[2] != null) ? resultado[2].toString().trim() : "");
        	produto.setAnomdl(  (resultado[3] != null) ? resultado[3].toString().trim() : "");
        	produto.setDesccom(  (resultado[4] != null) ? resultado[4].toString().trim() : "");
        	produto.setDescrfb(  (resultado[5] != null) ? resultado[5].toString().trim() : "");
        	produto.setTpprd( (resultado[6] != null) ? resultado[6].toString().trim() : "");
        	produto.setProtot(  (resultado[7] != null) ? resultado[7].toString().trim() : "");
        	produto.setSpecial(  (resultado[8] != null) ? resultado[8].toString().trim() : "");
        	produto.setTpdcre((resultado[9] != null) ? resultado[9].toString().trim() : "");
        	produto.setOrig((resultado[10] != null) ? resultado[10].toString().trim() : "");
        	produto.setDtneci(  (resultado[11] != null) ? resultado[11].toString().trim() : "");
        	produto.setPriourgen(  (resultado[12] != null) ? resultado[12].toString().trim() : "");
        	produto.setPrevfat(  (resultado[13] != null) ? resultado[13].toString().trim() : "");
        	produto.setPrioresp(  (resultado[14] != null) ? resultado[14].toString().trim() : "");
        	produto.setPriodtmnt(  (resultado[15] != null) ? resultado[15].toString().trim() : "");
        	produto.setPrioHRmnt(  (resultado[16] != null) ? resultado[16].toString().trim() : "");
        	
			cor.setIdmatriz(produto.getIdMatriz());
        	cor.setPartnumpd(  (resultado[17] != null) ? resultado[17].toString().trim() : "");        
        	cor.setCodcor(  (resultado[18] != null) ? resultado[18].toString().trim() : "");
        	cor.setCorpt((resultado[19] != null) ? resultado[19].toString().trim() : "");
			cor.setPartdesc(  (resultado[20] != null) ? resultado[20].toString().trim() : "");
        	cor.setUnmed(  (resultado[21] != null) ? resultado[21].toString().trim() : "");
        	cor.setPriocor(  (resultado[22] != null) ? resultado[22].toString().trim() : "");        													
			cor.setStatus(  (resultado[23] != null) ? resultado[23].toString().trim() : ""); //j4 - old produto.setStatus - status proc é por cor
			cor.setCdbej( (resultado[24] != null) ? resultado[24].toString().trim() : "");        	
        	cor.setCoreng((resultado[25] != null) ? resultado[25].toString().trim() : "");
        	cor.setTppin((resultado[26] != null) ? resultado[26].toString().trim() : "");
        	
        	pend.setIdmatriz(produto.getIdMatriz());
			pend.setPartnumpd(cor.getPartnumpd());
			pend.setNumpend( (resultado[27] != null) ? resultado[27].toString().trim() : "");
        	pend.setCdpend( (resultado[28] != null) ? resultado[28].toString().trim() : "");
        	pend.setObsresol( (resultado[29] != null) ? resultado[29].toString().trim() : ""); 
        	pend.setStatus( (resultado[30] != null) ? resultado[30].toString().trim() : ""); 
			pend.setDescpend( (resultado[31] != null) ? resultado[31].toString().trim() : ""); 
			pend.setObspend( (resultado[32] != null) ? resultado[32].toString().trim() : ""); 
			pend.setObsdetail( (resultado[33] != null) ? resultado[33].toString().trim() : ""); 
			pend.setTpreg( (resultado[34] != null) ? resultado[34].toString().trim() : ""); 			 
        	pend.setPartnum( (resultado[35] != null) ? resultado[35].toString().trim() : "");  
			pend.setPartsugest( (resultado[36] != null) ? resultado[36].toString().trim() : "");
			pend.setPartsugdsc( (resultado[37] != null) ? resultado[37].toString().trim() : "");
			pend.setPartnew( (resultado[38] != null) ? resultado[38].toString().trim() : "");
			pend.setPartnewdsc( (resultado[39] != null) ? resultado[39].toString().trim() : "");
			
			//Detalhe insumo da pendencia
			insumo.setIdmatriz(produto.getIdMatriz());
			insumo.setPartnumpd( cor.getPartnumpd());
			insumo.setPartnum( (resultado[35] != null) ? resultado[35].toString().trim() : "");  
			insumo.setPartsugest( (resultado[36] != null) ? resultado[36].toString().trim() : "");
			insumo.setPartsugdsc( (resultado[37] != null) ? resultado[37].toString().trim() : "");
			insumo.setPartnew( (resultado[38] != null) ? resultado[38].toString().trim() : "");
			insumo.setPartnewdsc( (resultado[39] != null) ? resultado[39].toString().trim() : "");			
			insumo.setPartdesc( (resultado[40] != null) ? resultado[40].toString().trim() : "");
			insumo.setItmorg( (resultado[41] != null) ? resultado[41].toString().trim() : "");
			insumo.setIttyp( (resultado[42] != null) ? resultado[42].toString().trim() : "");
			insumo.setUnmsr( (resultado[43] != null) ? resultado[43].toString().trim() : "");
			insumo.setNecfil( (resultado[44] != null) ? resultado[44].toString().trim() : "");
			insumo.setCdspn( (resultado[45] != null) ? resultado[45].toString().trim() : "");
			insumo.setWeght( (resultado[46] != null) ? resultado[46].toString().trim() : "");
			insumo.setEmcomp( (resultado[47] != null) ? resultado[47].toString().trim() : "");
			insumo.setEspec( (resultado[48] != null) ? resultado[48].toString().trim() : "");
			insumo.setUndcom( (resultado[49] != null) ? resultado[49].toString().trim() : "");
			insumo.setNcm( (resultado[50] != null) ? resultado[50].toString().trim() : "");
			insumo.setVlrunit( (resultado[51] != null) ? resultado[51].toString().trim() : "");																		        							    	
        				
			//Documentos insumo  
			doc.setIdmatriz(produto.getIdMatriz());
			doc.setPartnumpd(cor.getPartnumpd());
			doc.setPartnum( insumo.getPartnum());           				
        	doc.setTpdoc((resultado[52] != null) ? resultado[52].toString().trim() : "");
        	doc.setNumdoc((resultado[53] != null) ? resultado[53].toString().trim() : "");
        	doc.setSerdoc((resultado[54] != null) ? resultado[54].toString().trim() : "");
        	doc.setEmidoc((resultado[55] != null) ? resultado[55].toString().trim() : "");
			doc.setCnpjfor((resultado[56] != null) ? resultado[56].toString().trim() : "");
        	doc.setIe((resultado[57] != null) ? resultado[57].toString().trim() : "");
        	doc.setAdicao((resultado[58] != null) ? resultado[58].toString().trim() : "");
        	doc.setItadicao((resultado[59] != null) ? resultado[59].toString().trim() : "");
			doc.setVlrunit((resultado[60] != null) ? resultado[60].toString().trim() : "");
			doc.setSiglaund((resultado[61] != null) ? resultado[61].toString().trim() : "");
			doc.setCodinco((resultado[62] != null) ? resultado[62].toString().trim() : "");
			doc.setModal((resultado[63] != null) ? resultado[63].toString().trim() : "");        							
			doc.setNumdoc2((resultado[64] != null) ? resultado[64].toString().trim() : "");
        	doc.setSerdoc2((resultado[65] != null) ? resultado[65].toString().trim() : "");
        	doc.setEmidoc2((resultado[66] != null) ? resultado[66].toString().trim() : "");
        	doc.setCnpjfor2((resultado[67] != null) ? resultado[67].toString().trim() : "");
        	doc.setIe2((resultado[68] != null) ? resultado[68].toString().trim() : "");
        	doc.setAdicao2((resultado[69] != null) ? resultado[69].toString().trim() : "");
        	doc.setItadicao2((resultado[70] != null) ? resultado[70].toString().trim() : "");
			doc.setVlrunit2((resultado[71] != null) ? resultado[71].toString().trim() : "");
			doc.setSiglaund2((resultado[72] != null) ? resultado[72].toString().trim() : "");
			doc.setCodinco2((resultado[73] != null) ? resultado[73].toString().trim() : "");
			doc.setModal2((resultado[74] != null) ? resultado[74].toString().trim() : "");     				
			doc.setNumdoc3((resultado[75] != null) ? resultado[75].toString().trim() : "");
        	doc.setSerdoc3((resultado[76] != null) ? resultado[76].toString().trim() : "");
        	doc.setEmidoc3((resultado[77] != null) ? resultado[77].toString().trim() : "");			        	        	        	        	        	         	        	
         	doc.setCnpjfor3((resultado[78] != null) ? resultado[78].toString().trim() : "");
        	doc.setIe3((resultado[79] != null) ? resultado[79].toString().trim() : "");
        	doc.setAdicao3((resultado[80] != null) ? resultado[80].toString().trim() : "");
        	doc.setItadicao3((resultado[81] != null) ? resultado[81].toString().trim() : "");
			doc.setVlrunit3((resultado[82] != null) ? resultado[82].toString().trim() : "");
			doc.setSiglaund3((resultado[83] != null) ? resultado[83].toString().trim() : "");
			doc.setCodinco3((resultado[84] != null) ? resultado[84].toString().trim() : "");
			doc.setModal3((resultado[85] != null) ? resultado[85].toString().trim() : "");     
			
			//Complementa dados da pendencia - sql ja traz dados do insumo relacionado a pendencia - substitui complementaPendencia(...)
			pend.setNumdoc(doc.getNumdoc());
			pend.setSerdoc(doc.getSerdoc());
			pend.setEmidoc(doc.getEmidoc());
			pend.setNumdoc2(doc.getNumdoc2());
			pend.setSerdoc2(doc.getSerdoc2());
			pend.setEmidoc2(doc.getEmidoc2());
						

			//Add distinct cor			
			Boolean corRepetida = false;			
			for(CoresResponse color : listaCor){
				if(color.getPartnumpd().equals(cor.getPartnumpd())){
					corRepetida = true;   break;
				}
			}
			if(!corRepetida){  listaCor.add(cor);  }
			
			//Add pendencia - não se repete pois é o último nível
			if(!pend.getNumpend().equals("")){
				listaPend.add(pend);				
			}
			
			//Add distinct insumo - pode ser repetir porque pode ter mais de uma pendencia para o mesmo insumo	
			Boolean insumoRepetido = false;
			if(!insumo.getPartnum().equals("")){
				for(InsumosProdResponse ins :  listaInsumo){
					if(ins.getPartnum().equals(insumo.getPartnum())){
						insumoRepetido = true;   break;
					}
				}							
			}
			if(!insumoRepetido && !insumo.getPartnum().equals("")){  
				listaInsumo.add(insumo); 
				if(!doc.getTpdoc().equals("")){
					listaDoc.add(doc); 	//insumo x doc - 1 pra 1	
				}					
			}

		}

        	/*resp.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	resp.setProduto(  (resultado[1] != null) ? resultado[1].toString().trim() : "");
        	resp.setModelo(  (resultado[2] != null) ? resultado[2].toString().trim() : "");
        	resp.setAnomdl(  (resultado[3] != null) ? resultado[3].toString().trim() : "");
        	resp.setDesccom(  (resultado[4] != null) ? resultado[4].toString().trim() : "");
        	resp.setDescrfb(  (resultado[5] != null) ? resultado[5].toString().trim() : "");
        	resp.setTpprd( (resultado[6] != null) ? resultado[6].toString().trim() : "");
        	resp.setProtot(  (resultado[7] != null) ? resultado[7].toString().trim() : "");
        	resp.setSpecial(  (resultado[8] != null) ? resultado[8].toString().trim() : "");
        	resp.setTpdcre((resultado[9] != null) ? resultado[9].toString().trim() : "");
        	resp.setOrig((resultado[10] != null) ? resultado[10].toString().trim() : "");
        	resp.setDtneci(  (resultado[11] != null) ? resultado[11].toString().trim() : "");
        	resp.setPriourgen(  (resultado[12] != null) ? resultado[12].toString().trim() : "");
        	resp.setPrevfat(  (resultado[13] != null) ? resultado[13].toString().trim() : "");
        	resp.setPrioresp(  (resultado[14] != null) ? resultado[14].toString().trim() : "");
        	resp.setPriodtmnt(  (resultado[15] != null) ? resultado[15].toString().trim() : "");
        	resp.setPrioHRmnt(  (resultado[16] != null) ? resultado[16].toString().trim() : "");
        	resp.setModelo((resultado[18] != null) ? resultado[18].toString().trim() : "");
        	
        	cor.setPartnumpd(  (resultado[17] != null) ? resultado[17].toString().trim() : "");
        	cor.setCodcor(  (resultado[19] != null) ? resultado[19].toString().trim() : "");
        	cor.setPartdesc(  (resultado[20] != null) ? resultado[20].toString().trim() : "");
        	cor.setUnmed(  (resultado[21] != null) ? resultado[21].toString().trim() : "");
        	cor.setPriocor(  (resultado[22] != null) ? resultado[22].toString().trim() : "");
        	cor.setIdmatriz((resultado[49] != null) ? resultado[49].toString().trim() : "");
        	cor.setCorpt((resultado[67] != null) ? resultado[67].toString().trim() : "");
        	
        	item.setPartnum( (resultado[23] != null) ? resultado[23].toString().trim() : "");
        	item.setItmorg((resultado[24] != null) ? resultado[24].toString().trim() : "");
        	item.setIttyp( (resultado[25] != null) ? resultado[25].toString().trim() : "");
        	item.setUnmsr( (resultado[26] != null) ? resultado[26].toString().trim() : "");
        	item.setNecfil( (resultado[27] != null) ? resultado[27].toString().trim() : "");
        	item.setCdspn( (resultado[28] != null) ? resultado[28].toString().trim() : "");
        	item.setWeght( (resultado[29] != null) ? resultado[29].toString().trim() : "");
        	item.setEmcomp( (resultado[30] != null) ? resultado[30].toString().trim() : "");
        	item.setPartsugest( (resultado[31] != null) ? resultado[31].toString().trim() : "");
        	item.setPartsugdsc((resultado[32] != null) ? resultado[32].toString().trim() : "");
        	item.setPartnew( (resultado[33] != null) ? resultado[33].toString().trim() : "");
        	item.setPartnewdsc( (resultado[34] != null) ? resultado[34].toString().trim() : "");
        	item.setPartnumpd((resultado[65] != null) ? resultado[65].toString().trim() : "");
        	
        	pend.setNumpend( (resultado[35] != null) ? resultado[35].toString().trim() : "");
        	pend.setCdpend( (resultado[36] != null) ? resultado[36].toString().trim() : "");
        	pend.setObspend( (resultado[68] != null) ? resultado[68].toString().trim() : "");
        	pend.setStatus( (resultado[38] != null) ? resultado[38].toString().trim() : "");
        	pend.setPartnum((resultado[50] != null) ? resultado[50].toString().trim() : "");
        	pend.setIdmatriz((resultado[51] != null) ? resultado[51].toString().trim() : "");
        	pend.setPartnumpd((resultado[66] != null) ? resultado[66].toString().trim() : "");
        	pend.setObsresol((resultado[69] != null) ? resultado[69].toString().trim() : "");
        	
        	pend.setNumdoc((resultado[40] != null) ? resultado[40].toString().trim() : "");
        	pend.setNumdoc2((resultado[43] != null) ? resultado[43].toString().trim() : "");
        	pend.setSerdoc((resultado[41] != null) ? resultado[41].toString().trim() : "");
        	pend.setSerdoc2((resultado[44] != null) ? resultado[44].toString().trim() : "");
        	pend.setEmidoc((resultado[42] != null) ? resultado[42].toString().trim() : "");
        	pend.setEmidoc2((resultado[45] != null) ? resultado[45].toString().trim() : "");
        	
        	doc.setTpdoc((resultado[39] != null) ? resultado[39].toString().trim() : "");
        	doc.setNumdoc((resultado[40] != null) ? resultado[40].toString().trim() : "");
        	doc.setSerdoc((resultado[41] != null) ? resultado[41].toString().trim() : "");
        	doc.setEmidoc((resultado[42] != null) ? resultado[42].toString().trim() : "");
        	doc.setNumdoc2((resultado[43] != null) ? resultado[43].toString().trim() : "");
        	doc.setSerdoc2((resultado[44] != null) ? resultado[44].toString().trim() : "");
        	doc.setEmidoc2((resultado[45] != null) ? resultado[45].toString().trim() : "");
        	doc.setNumdoc3((resultado[46] != null) ? resultado[46].toString().trim() : "");
        	doc.setSerdoc3((resultado[47] != null) ? resultado[47].toString().trim() : "");
        	doc.setEmidoc3((resultado[48] != null) ? resultado[48].toString().trim() : "");
        	doc.setPartnum((resultado[52] != null) ? resultado[52].toString().trim() : "");
        	
        	doc.setCnpjfor((resultado[53] != null) ? resultado[53].toString().trim() : "");
        	doc.setIe((resultado[54] != null) ? resultado[54].toString().trim() : "");
        	doc.setAdicao((resultado[55] != null) ? resultado[55].toString().trim() : "");
        	doc.setItadicao((resultado[56] != null) ? resultado[56].toString().trim() : "");
        	
         	doc.setCnpjfor2((resultado[57] != null) ? resultado[57].toString().trim() : "");
        	doc.setIe2((resultado[58] != null) ? resultado[58].toString().trim() : "");
        	doc.setAdicao2((resultado[59] != null) ? resultado[59].toString().trim() : "");
        	doc.setItadicao2((resultado[60] != null) ? resultado[60].toString().trim() : "");
        	
         	doc.setCnpjfor3((resultado[61] != null) ? resultado[61].toString().trim() : "");
        	doc.setIe3((resultado[62] != null) ? resultado[62].toString().trim() : "");
        	doc.setAdicao3((resultado[63] != null) ? resultado[63].toString().trim() : "");
        	doc.setItadicao3((resultado[64] != null) ? resultado[64].toString().trim() : "");
        	doc.setPartnumpd((resultado[70] != null) ? resultado[70].toString().trim() : "");
        	Boolean existeItem = Boolean.FALSE;
        	for (ProdutoPendenciaResponseList coresSimplesResponse : listaItemSet) {
				if(coresSimplesResponse.getPartnum().equals(item.getPartnum())) {
					existeItem = Boolean.TRUE;
				}
			}
        	if(!existeItem) {
        		listaItemSet.add(item);
        	}
        	
        	
        	Boolean existeCor = Boolean.FALSE;
        	for (CoresSimplesResponse coresSimplesResponse : coresSet) {
				if(coresSimplesResponse.getPartnumpd().equals(cor.getPartnumpd())) {
					existeCor = Boolean.TRUE;
				}
			}
        	if(!existeCor) {
        		coresSet.add(cor);
        	}
        	
        	
        	Boolean existePend = Boolean.FALSE;
        	for (PendenciaResponse coresSimplesResponse : listaPendSet) {
				if(coresSimplesResponse.getNumpend().equals(pend.getNumpend())) {
					existePend = Boolean.TRUE;
				}
			}
        	if(!existePend) {
        		listaPendSet.add(pend);
        	}
        	
        	Boolean existeDoc = Boolean.FALSE;
        	for (DocumentosResponse coresSimplesResponse : listaDocSet) {
				if(coresSimplesResponse.getNumdoc().equals(doc.getNumdoc()) && coresSimplesResponse.getPartnumpd().equals(doc.getPartnumpd())) {
					existeDoc = Boolean.TRUE;
				}
			}
        	if(!existeDoc) {
        		listaDocSet.add(doc);
        	}
        }
        List<CoresSimplesResponse> listaCor = new ArrayList<>(coresSet);
        List<ProdutoPendenciaResponseList> listaItem = new ArrayList<>(listaItemSet);
        List<PendenciaResponse> listaPend = new ArrayList<>(listaPendSet);
        List<DocumentosResponse> listaDoc = new ArrayList<>(listaDocSet);
        resp.setItens(removerDuplicatas(listaItem));
        resp.setPendencias(listaPend);
        resp.setDocumentos(removerDuplicatas(listaDoc));
        resp.setCores(removerDuplicatas(listaCor));
       
        for (PendenciaResponse pendencia : resp.getPendencias()) {
			pendencia = complementaPendencia(pendencia, resp);
		}*/

		return produto; 
	}
	
	
	public List<ProdutoPendenciaResponse> getTodasAsPendencias(List<Integer> status) {
		
		List<Object[]> resultados = repository.consultaTodasAsPendencias(status);		
		List<ProdutoPendenciaResponse> produtos = new ArrayList<>();
		//Set<ProdutoPendenciaResponseList> listaItemSet = new HashSet<>();
		Set<PendenciaResponse> listaPendSet = new HashSet<>();
		Set<DocumentosResponse> listaDocSet = new HashSet<>();
		Set<CoresResponse> coresSet = new HashSet<>();

		List<CoresResponse> listaCor = new ArrayList<>(coresSet);
		//List<ProdutoPendenciaResponseList> listaItem = new ArrayList<>();
		List<InsumosProdResponse> listaInsumo = new ArrayList<>();
		List<PendenciaResponse> listaPend = new ArrayList<>(listaPendSet);
		List<DocumentosResponse> listaDoc = new ArrayList<>(listaDocSet);

		String matriz_anterior = "";
        for (Object[] resultado : resultados) {

        	ProdutoPendenciaResponse produto = new ProdutoPendenciaResponse();    		
        	CoresResponse cor = new CoresResponse();						
        	PendenciaResponse pend = new PendenciaResponse();
			InsumosProdResponse insumo = new InsumosProdResponse(); //j4 - old ProdutoPendenciaResponseList
        	DocumentosResponse doc = new DocumentosResponse();
        	
        	produto.setIdMatriz( (resultado[0] != null) ? resultado[0].toString().trim() : "" );
        	produto.setProduto(  (resultado[1] != null) ? resultado[1].toString().trim() : "");
        	produto.setModelo(  (resultado[2] != null) ? resultado[2].toString().trim() : "");
        	produto.setAnomdl(  (resultado[3] != null) ? resultado[3].toString().trim() : "");
        	produto.setDesccom(  (resultado[4] != null) ? resultado[4].toString().trim() : "");
        	produto.setDescrfb(  (resultado[5] != null) ? resultado[5].toString().trim() : "");
        	produto.setTpprd( (resultado[6] != null) ? resultado[6].toString().trim() : "");
        	produto.setProtot(  (resultado[7] != null) ? resultado[7].toString().trim() : "");
        	produto.setSpecial(  (resultado[8] != null) ? resultado[8].toString().trim() : "");
        	produto.setTpdcre((resultado[9] != null) ? resultado[9].toString().trim() : "");
        	produto.setOrig((resultado[10] != null) ? resultado[10].toString().trim() : "");
        	produto.setDtneci(  (resultado[11] != null) ? resultado[11].toString().trim() : "");
        	produto.setPriourgen(  (resultado[12] != null) ? resultado[12].toString().trim() : "");
        	produto.setPrevfat(  (resultado[13] != null) ? resultado[13].toString().trim() : "");
        	produto.setPrioresp(  (resultado[14] != null) ? resultado[14].toString().trim() : "");
        	produto.setPriodtmnt(  (resultado[15] != null) ? resultado[15].toString().trim() : "");
        	produto.setPrioHRmnt(  (resultado[16] != null) ? resultado[16].toString().trim() : "");
        	
			cor.setIdmatriz(produto.getIdMatriz());
        	cor.setPartnumpd(  (resultado[17] != null) ? resultado[17].toString().trim() : "");        
        	cor.setCodcor(  (resultado[18] != null) ? resultado[18].toString().trim() : "");
        	cor.setCorpt((resultado[19] != null) ? resultado[19].toString().trim() : "");
			cor.setPartdesc(  (resultado[20] != null) ? resultado[20].toString().trim() : "");
        	cor.setUnmed(  (resultado[21] != null) ? resultado[21].toString().trim() : "");
        	cor.setPriocor(  (resultado[22] != null) ? resultado[22].toString().trim() : "");        													
			cor.setStatus(  (resultado[23] != null) ? resultado[23].toString().trim() : ""); //j4 - old produto.setStatus - status proc é por cor
			cor.setCdbej( (resultado[24] != null) ? resultado[24].toString().trim() : "");        	
        	cor.setCoreng((resultado[25] != null) ? resultado[25].toString().trim() : "");
        	cor.setTppin((resultado[26] != null) ? resultado[26].toString().trim() : "");
        	
        	pend.setIdmatriz(produto.getIdMatriz());
			pend.setPartnumpd(cor.getPartnumpd());
			pend.setNumpend( (resultado[27] != null) ? resultado[27].toString().trim() : "");
        	pend.setCdpend( (resultado[28] != null) ? resultado[28].toString().trim() : "");
        	pend.setObsresol( (resultado[29] != null) ? resultado[29].toString().trim() : ""); 
        	pend.setStatus( (resultado[30] != null) ? resultado[30].toString().trim() : ""); 
			pend.setDescpend( (resultado[31] != null) ? resultado[31].toString().trim() : ""); 
			pend.setObspend( (resultado[32] != null) ? resultado[32].toString().trim() : ""); 
			pend.setObsdetail( (resultado[33] != null) ? resultado[33].toString().trim() : ""); 
			pend.setTpreg( (resultado[34] != null) ? resultado[34].toString().trim() : ""); 			 
        	pend.setPartnum( (resultado[35] != null) ? resultado[35].toString().trim() : "");  
			pend.setPartsugest( (resultado[36] != null) ? resultado[36].toString().trim() : "");
			pend.setPartsugdsc( (resultado[37] != null) ? resultado[37].toString().trim() : "");
			pend.setPartnew( (resultado[38] != null) ? resultado[38].toString().trim() : "");
			pend.setPartnewdsc( (resultado[39] != null) ? resultado[39].toString().trim() : "");
			
			//Detalhe insumo da pendencia
			insumo.setIdmatriz(produto.getIdMatriz());
			insumo.setPartnumpd( cor.getPartnumpd());
			insumo.setPartnum( (resultado[35] != null) ? resultado[35].toString().trim() : "");  
			insumo.setPartsugest( (resultado[36] != null) ? resultado[36].toString().trim() : "");
			insumo.setPartsugdsc( (resultado[37] != null) ? resultado[37].toString().trim() : "");
			insumo.setPartnew( (resultado[38] != null) ? resultado[38].toString().trim() : "");
			insumo.setPartnewdsc( (resultado[39] != null) ? resultado[39].toString().trim() : "");			
			insumo.setPartdesc( (resultado[40] != null) ? resultado[40].toString().trim() : "");
			insumo.setItmorg( (resultado[41] != null) ? resultado[41].toString().trim() : "");
			insumo.setIttyp( (resultado[42] != null) ? resultado[42].toString().trim() : "");
			insumo.setUnmsr( (resultado[43] != null) ? resultado[43].toString().trim() : "");
			insumo.setNecfil( (resultado[44] != null) ? resultado[44].toString().trim() : "");
			insumo.setCdspn( (resultado[45] != null) ? resultado[45].toString().trim() : "");
			insumo.setWeght( (resultado[46] != null) ? resultado[46].toString().trim() : "");
			insumo.setEmcomp( (resultado[47] != null) ? resultado[47].toString().trim() : "");
			insumo.setEspec( (resultado[48] != null) ? resultado[48].toString().trim() : "");
			insumo.setUndcom( (resultado[49] != null) ? resultado[49].toString().trim() : "");
			insumo.setNcm( (resultado[50] != null) ? resultado[50].toString().trim() : "");
			insumo.setVlrunit( (resultado[51] != null) ? resultado[51].toString().trim() : "");																		        							    	
        				
			//Documentos insumo  
			doc.setIdmatriz(produto.getIdMatriz());
			doc.setPartnumpd(cor.getPartnumpd());
			doc.setPartnum( insumo.getPartnum());           				
        	doc.setTpdoc((resultado[52] != null) ? resultado[52].toString().trim() : "");
        	doc.setNumdoc((resultado[53] != null) ? resultado[53].toString().trim() : "");
        	doc.setSerdoc((resultado[54] != null) ? resultado[54].toString().trim() : "");
        	doc.setEmidoc((resultado[55] != null) ? resultado[55].toString().trim() : "");
			doc.setCnpjfor((resultado[56] != null) ? resultado[56].toString().trim() : "");
        	doc.setIe((resultado[57] != null) ? resultado[57].toString().trim() : "");
        	doc.setAdicao((resultado[58] != null) ? resultado[58].toString().trim() : "");
        	doc.setItadicao((resultado[59] != null) ? resultado[59].toString().trim() : "");
			doc.setVlrunit((resultado[60] != null) ? resultado[60].toString().trim() : "");
			doc.setSiglaund((resultado[61] != null) ? resultado[61].toString().trim() : "");
			doc.setCodinco((resultado[62] != null) ? resultado[62].toString().trim() : "");
			doc.setModal((resultado[63] != null) ? resultado[63].toString().trim() : "");        							
			doc.setNumdoc2((resultado[64] != null) ? resultado[64].toString().trim() : "");
        	doc.setSerdoc2((resultado[65] != null) ? resultado[65].toString().trim() : "");
        	doc.setEmidoc2((resultado[66] != null) ? resultado[66].toString().trim() : "");
        	doc.setCnpjfor2((resultado[67] != null) ? resultado[67].toString().trim() : "");
        	doc.setIe2((resultado[68] != null) ? resultado[68].toString().trim() : "");
        	doc.setAdicao2((resultado[69] != null) ? resultado[69].toString().trim() : "");
        	doc.setItadicao2((resultado[70] != null) ? resultado[70].toString().trim() : "");
			doc.setVlrunit2((resultado[71] != null) ? resultado[71].toString().trim() : "");
			doc.setSiglaund2((resultado[72] != null) ? resultado[72].toString().trim() : "");
			doc.setCodinco2((resultado[73] != null) ? resultado[73].toString().trim() : "");
			doc.setModal2((resultado[74] != null) ? resultado[74].toString().trim() : "");     				
			doc.setNumdoc3((resultado[75] != null) ? resultado[75].toString().trim() : "");
        	doc.setSerdoc3((resultado[76] != null) ? resultado[76].toString().trim() : "");
        	doc.setEmidoc3((resultado[77] != null) ? resultado[77].toString().trim() : "");			        	        	        	        	        	         	        	
         	doc.setCnpjfor3((resultado[78] != null) ? resultado[78].toString().trim() : "");
        	doc.setIe3((resultado[79] != null) ? resultado[79].toString().trim() : "");
        	doc.setAdicao3((resultado[80] != null) ? resultado[80].toString().trim() : "");
        	doc.setItadicao3((resultado[81] != null) ? resultado[81].toString().trim() : "");
			doc.setVlrunit3((resultado[82] != null) ? resultado[82].toString().trim() : "");
			doc.setSiglaund3((resultado[83] != null) ? resultado[83].toString().trim() : "");
			doc.setCodinco3((resultado[84] != null) ? resultado[84].toString().trim() : "");
			doc.setModal3((resultado[85] != null) ? resultado[85].toString().trim() : "");     
			

			//Complementa dados da pendencia - sql ja traz dados do insumo relacionado a pendencia - substitui complementaPendencia(...)
			pend.setNumdoc(doc.getNumdoc());
			pend.setSerdoc(doc.getSerdoc());
			pend.setEmidoc(doc.getEmidoc());
			pend.setNumdoc2(doc.getNumdoc2());
			pend.setSerdoc2(doc.getSerdoc2());
			pend.setEmidoc2(doc.getEmidoc2());

			
			//Só funciona com order by em IDMATRIZ - repositorio deve garantir:
			Boolean novoProduto = false;
			if(!matriz_anterior.equals(produto.getIdMatriz())){
				novoProduto = true;				
				matriz_anterior = produto.getIdMatriz().toString();
			}

			if(novoProduto){
				
				//Reset lists
				//coresSet = new HashSet<>();
				//listaItemSet = new HashSet<>();
				//listaPendSet = new HashSet<>();
				//listaDocSet = new HashSet<>();
				//listaCor = new ArrayList<>(coresSet);
				//listaPend = new ArrayList<>(listaPendSet);
				//listaDoc = new ArrayList<>(listaDocSet);

				listaCor = new ArrayList<>();
				listaInsumo = new ArrayList<>(); //j4 - old listaItem
				listaPend = new ArrayList<>();
				listaDoc = new ArrayList<>();
	
				produtos.add(produto);
				produto.setCores(listaCor);	
				produto.setPendencias(listaPend);		
				produto.setInsumos(listaInsumo); //j4 - old produto.setItens(listaItem);			
				produto.setDocumentos(listaDoc);
															
			}

			//Add distinct cor			
			Boolean corRepetida = false;			
			for(CoresResponse color : listaCor){
				if(color.getPartnumpd().equals(cor.getPartnumpd())){
					corRepetida = true;   break;
				}
			}
			if(!corRepetida){  listaCor.add(cor);  }
			
			//Add pendencia - não se repete pois é o último nível
			if(!pend.getNumpend().equals("")){
				listaPend.add(pend);				
			}
			
			//Add distinct insumo - pode ser repetir porque pode ter mais de uma pendencia para o mesmo insumo	
			Boolean insumoRepetido = false;
			if(!insumo.getPartnum().equals("")){
				for(InsumosProdResponse ins :  listaInsumo){
					if(ins.getPartnum().equals(insumo.getPartnum())){
						insumoRepetido = true;   break;
					}
				}							
			}
			if(!insumoRepetido && !insumo.getPartnum().equals("")){  
				listaInsumo.add(insumo); 
				if(!doc.getTpdoc().equals("")){
					listaDoc.add(doc); 	//insumo x doc - 1 pra 1	
				}					
			}


		

			/*
        	if (!(pend.getCdpend().equals("") && pend.getNumpend().equals("") && pend.getObspend().equals("") && pend.getStatus().equals(""))) {
                listaPendSet.add(pend); // Adiciona à lista apenas se não estiver duplicado
            }
        	
        	if (!(doc.getTpdoc().equals("") && doc.getSerdoc().equals("") && doc.getNumdoc().equals("") && doc.getEmidoc().equals("") 
                    && doc.getSerdoc2().equals("") && doc.getNumdoc2().equals("") && doc.getEmidoc2().equals("") 
                    && doc.getSerdoc3().equals("") && doc.getNumdoc3().equals("") && doc.getEmidoc3().equals(""))) {
                listaDocSet.add(doc); // Adiciona à lista apenas se não estiver duplicado
            }
        	
        	coresSet.add(cor);
        	
        	Boolean existeItem = false;
            for (ProdutoPendenciaResponseList it : listaItemSet) {
                if (it.getPartnum().equals(insumo.getPartnum())) {
                    existeItem = true;
                    break;
                }
            }

            if (!existeItem) {
                listaItemSet.add(insumo);
            }
            
        	Boolean existe = Boolean.FALSE;
            for (ProdutoPendenciaResponse prd : produtos) {
				if(prd.getIdMatriz().equals(produto.getIdMatriz())) {
					existe = Boolean.TRUE;
				}
			}
            
            List<CoresResponse> listaCor = new ArrayList<>(coresSet);
            List<ProdutoPendenciaResponseList> listaItem = new ArrayList<>();
            List<PendenciaResponse> listaPend = new ArrayList<>(listaPendSet);
            List<DocumentosResponse> listaDoc = new ArrayList<>(listaDocSet);

            produto.setItens(listaItem);
            produto.setPendencias(listaPend);
            produto.setDocumentos(listaDoc);
            produto.setCores(listaCor);

            if(!existe) {
            	produtos.add(produto);
            }else {
            	Boolean existeCor = Boolean.FALSE;
            	for (ProdutoPendenciaResponse prod : produtos) {
					for (CoresResponse corAtual : prod.getCores()) {
						for (CoresResponse coresAdd : listaCor) {
							if(corAtual.getPartnumpd().equals(coresAdd.getPartnumpd())) {
								existeCor = Boolean.TRUE;
							}
						}
					}
					if(!existeCor && cor.getIdmatriz().equals(prod.getIdMatriz())) {
						prod.getCores().add(cor);
					}
				}
            	
            	Boolean existePendencias = Boolean.FALSE;
            	for (ProdutoPendenciaResponse prod : produtos) {
					for (PendenciaResponse pendAtual : prod.getPendencias()) {
						for (PendenciaResponse coresAdd : listaPend) {
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
            	for (ProdutoPendenciaResponse prod : produtos) {
					for (DocumentosResponse docAtual : prod.getDocumentos()) {
						for (DocumentosResponse coresAdd : listaDoc) {
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
            produto.setCores(removerDuplicatas(listaCor));                                                                                                        
            coresSet = new HashSet<>();
            listaItemSet = new HashSet<>();
            listaPendSet = new HashSet<>();
            listaDocSet = new HashSet<>();
			*/
        }
        
        for (ProdutoPendenciaResponse produto : produtos) {
			produto.setQtdependencias(repository.countPendenciasNoPartnum(produto.getIdMatriz().toString()));
		}

		return produtos;
	}
	

	public List<ProdutoSemListaResponse> getPendenciasSemLista(List<Integer> status) {
		
		List<Object[]> resultados = repository.consultaTodasAsPendencias(status); //old consultaTodasAsPendenciasSemLista		
		List<ProdutoSemListaResponse> produtos = new ArrayList<>();		
		List<PendenciaResponse> listaPend = new ArrayList<>();;
		//List<DocumentosResponse> listaDoc = new ArrayList<>();;

		String chave_anterior = "";
        for (Object[] resultado : resultados) {


			ProdutoSemListaResponse produto = new ProdutoSemListaResponse();
        	PendenciaResponse pend = new PendenciaResponse(); //j4 - old PendenciaResponseSemLista
        	DocumentosResponse doc = new DocumentosResponse();        	
			int i = 0;

			String _IdMatriz  = (resultado[0] != null) ? resultado[0].toString().trim() : ""; i++;
			String _Produto   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Modelo    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Anomdl    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Desccom   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Descrfb   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Tpprd 	  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Protot    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Special   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Tpdcre    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Orig 	  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Dtneci    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Priourgen = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Prevfat   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Prioresp  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Priodtmnt = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _PrioHRmnt = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //16		
			
			String _Partnumpd = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;       
			String _Codcor    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Corpt     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partdesc  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Unmed     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Priocor   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;        													
			String _Status    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; 
			String _Cdbej     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;        	
			String _Coreng    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Tppin     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //26	
			
			String _Numpend    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Cdpend     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Obsresol   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; 
        	String _StatusPend = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; 
			String _Descpend   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; 
			String _Obspend    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; 
			String _Obsdetail  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; 
			String _Tpreg      = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; 			 
        	String _Partnum    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;  
			String _Partsugest = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partsugdsc = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partnew    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partnewdsc = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //39

			/*insumo.setPartdesc( (resultado[40] != null) ? resultado[40].toString().trim() : "");
			insumo.setItmorg( (resultado[41] != null) ? resultado[41].toString().trim() : "");
			insumo.setIttyp( (resultado[42] != null) ? resultado[42].toString().trim() : "");
			insumo.setUnmsr( (resultado[43] != null) ? resultado[43].toString().trim() : "");
			insumo.setNecfil( (resultado[44] != null) ? resultado[44].toString().trim() : "");
			insumo.setCdspn( (resultado[45] != null) ? resultado[45].toString().trim() : "");
			insumo.setWeght( (resultado[46] != null) ? resultado[46].toString().trim() : "");
			insumo.setEmcomp( (resultado[47] != null) ? resultado[47].toString().trim() : "");
			insumo.setEspec( (resultado[48] != null) ? resultado[48].toString().trim() : "");
			insumo.setUndcom( (resultado[49] != null) ? resultado[49].toString().trim() : "");
			insumo.setNcm( (resultado[50] != null) ? resultado[50].toString().trim() : "");
			insumo.setVlrunit( (resultado[51] != null) ? resultado[51].toString().trim() : "");	*/
			
			String _Tpdoc     = ( resultado[52] != null) ? resultado[52].toString().trim() : ""; i=53; //52
        	String _Numdoc    = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Serdoc    = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Emidoc    = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cnpjfor   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Ie        = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Adicao    = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Itadicao  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal     = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;        							
			String _Numdoc2   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Serdoc2   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Emidoc2   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Cnpjfor2  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Ie2       = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Adicao2   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Itadicao2 = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit2  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund2 = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco2  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal2    = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;     				
			String _Numdoc3   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Serdoc3   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Emidoc3   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;			        	        	        	        	        	         	        	
         	String _Cnpjfor3  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Ie3       = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Adicao3   = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Itadicao3 = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit3  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund3 = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco3  = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal3    = ( resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;  //85 			

        	produto.setIdMatriz( _IdMatriz );
        	produto.setProduto( _Produto );
        	produto.setModelo( _Modelo );
        	produto.setAnomdl( _Anomdl );
        	produto.setDesccom( _Desccom );
        	produto.setDescrfb( _Descrfb );
        	produto.setTpprd( _Tpprd );
        	produto.setProtot( _Protot );
        	produto.setSpecial( _Special );
        	produto.setTpdcre( _Tpdcre );
        	produto.setOrig( _Orig );
        	produto.setDtneci( _Dtneci );
        	produto.setPriourgen( _Priourgen );
        	produto.setPrevfat( _Prevfat );
        	produto.setPrioresp( _Prioresp );
        	produto.setPriodtmnt( _Priodtmnt );
        	produto.setPrioHRmnt( _PrioHRmnt );			
						
			produto.setStatus( _Status );
			produto.setPartnumpd( _Partnumpd );
			produto.setCodcor( _Codcor );
			produto.setPartdesc (_Partdesc );
			produto.setUnmed( _Unmed );
			produto.setPriocor( _Priocor );
			produto.setCdbej( _Cdbej );
			produto.setCorpt( _Corpt );
			produto.setCoreng( _Coreng );
			produto.setTppin( _Tppin );
	
        	pend.setIdmatriz(produto.getIdMatriz());
			pend.setPartnumpd(produto.getPartnumpd());
			pend.setNumpend( _Numpend );
        	pend.setCdpend( _Cdpend );
        	pend.setObsresol( _Obsresol ); 
        	pend.setStatus( _StatusPend ); 
			pend.setDescpend( _Descpend ); 
			pend.setObspend( _Obspend ); 
			pend.setObsdetail( _Obsdetail ); 
			pend.setTpreg( _Tpreg ); 			 
        	pend.setPartnum( _Partnum );  
			pend.setPartsugest( _Partsugest );
			pend.setPartsugdsc( _Partsugdsc );
			pend.setPartnew( _Partnew );
			pend.setPartnewdsc( _Partnewdsc );
			
			//Complementa dados da pendencia - sql ja traz dados do insumo relacionado a pendencia - substitui complementaPendencia(...)
			pend.setNumdoc( _Numdoc );
			pend.setSerdoc( _Serdoc );
			pend.setEmidoc( _Emidoc );
			pend.setNumdoc2( _Numdoc2 );
			pend.setSerdoc2( _Serdoc2 );
			pend.setEmidoc2( _Emidoc2 );
																				        							    				
			//Documentos insumo  
			doc.setIdmatriz(produto.getIdMatriz());
			doc.setPartnumpd(produto.getPartnumpd());
			doc.setPartnum(pend.getPartnum());           				
        	doc.setTpdoc( _Tpdoc);
        	doc.setNumdoc( _Numdoc );
        	doc.setSerdoc( _Serdoc );
        	doc.setEmidoc( _Emidoc );
			doc.setCnpjfor( _Cnpjfor );
        	doc.setIe( _Ie );
        	doc.setAdicao( _Adicao );
        	doc.setItadicao( _Itadicao );
			doc.setVlrunit( _Vlrunit );
			doc.setSiglaund( _Siglaund );
			doc.setCodinco( _Codinco );
			doc.setModal( _Modal );
			doc.setNumdoc2( _Numdoc2 );
        	doc.setSerdoc2( _Serdoc2 );
        	doc.setEmidoc2( _Emidoc2 );
        	doc.setCnpjfor2( _Cnpjfor2 );
        	doc.setIe2( _Ie2 );
        	doc.setAdicao2( _Adicao2 );
        	doc.setItadicao2( _Itadicao2 );
			doc.setVlrunit2( _Vlrunit2 );
			doc.setSiglaund2( _Siglaund2 );
			doc.setCodinco2( _Codinco2 );
			doc.setModal2( _Modal2 );
			doc.setNumdoc3( _Numdoc3 );
        	doc.setSerdoc3( _Serdoc3 );
        	doc.setEmidoc3( _Emidoc3 );
         	doc.setCnpjfor3( _Cnpjfor3 );
        	doc.setIe3( _Ie3 );
        	doc.setAdicao3( _Adicao3 );
        	doc.setItadicao3( _Itadicao3 );
			doc.setVlrunit3( _Vlrunit3 );
			doc.setSiglaund3( _Siglaund3 );
			doc.setCodinco3( _Codinco3 );
			doc.setModal3( _Modal3 );
			
	
			//Só funciona com order by em IDMATRIZ - repositorio deve garantir:
			Boolean novoProduto = false;
			String chave = produto.getIdMatriz()+"-"+produto.getPartnumpd();
			if( !chave_anterior.equals(chave) ){
				novoProduto = true;				
				chave_anterior = chave;
			}

			if(novoProduto){
											
				listaPend = new ArrayList<>();
				//listaDoc = new ArrayList<>();
	
				produtos.add(produto);			
				produto.setPendencias(listaPend);
				//produto.setDocumentos(listaDoc);

				produto.setQtdependencias(repository.countPendenciasCor(_IdMatriz, _Partnumpd));
				produto.setQtdependenciasEmAberto(repository.countPendenciasCorEmAberto(_IdMatriz, _Partnumpd));
															
			}
	
			//Add pendencia - não se repete pois é o último nível
			if(!pend.getNumpend().equals("")){
				listaPend.add(pend);				
			}
			
		
		}

		return produtos;
	
		
        /*
		//Set<PendenciaResponseSemLista> listaPendSet = new HashSet<>();
		  for...	
		
			ProdutoSemListaResponse resp = new ProdutoSemListaResponse();    		
        	PendenciaResponseSemLista pend = new PendenciaResponseSemLista();
        	
        	resp.setIdMatriz((resultado[0] != null) ? resultado[0].toString().trim() : "");
        	resp.setProduto((resultado[1] != null) ? resultado[1].toString().trim() : "");
        	resp.setModelo((resultado[2] != null) ? resultado[2].toString().trim() : "");
        	resp.setAnomdl((resultado[3] != null) ? resultado[3].toString().trim() : "");
        	resp.setDesccom((resultado[4] != null) ? resultado[4].toString().trim() : "");
        	resp.setDescrfb((resultado[5] != null) ? resultado[5].toString().trim() : "");
        	resp.setTpprd((resultado[6] != null) ? resultado[6].toString().trim() : "");
        	resp.setProtot((resultado[7] != null) ? resultado[7].toString().trim() : "");
        	resp.setSpecial((resultado[8] != null) ? resultado[8].toString().trim() : "");
        	resp.setTpdcre((resultado[9] != null) ? resultado[9].toString().trim() : "");        	
        	resp.setOrig((resultado[10] != null) ? resultado[10].toString().trim() : "");
        	resp.setDtneci((resultado[11] != null) ? resultado[11].toString().trim() : "");
        	resp.setPriourgen((resultado[12] != null) ? resultado[12].toString().trim() : "");
        	resp.setPrevfat((resultado[13] != null) ? resultado[13].toString().trim() : "");
        	resp.setPrioresp((resultado[14] != null) ? resultado[14].toString().trim() : "");
        	resp.setPriodtmnt((resultado[15] != null) ? resultado[15].toString().trim() : "");
        	resp.setPrioHRmnt((resultado[16] != null) ? resultado[16].toString().trim() : "");

        	resp.setPartnumpd((resultado[17] != null) ? resultado[17].toString().trim() : "");
        	resp.setModelo((resultado[18] != null) ? resultado[18].toString().trim() : "");
        	resp.setCodcor((resultado[19] != null) ? resultado[19].toString().trim() : "");
        	resp.setPartdesc((resultado[20] != null) ? resultado[20].toString().trim() : "");
        	resp.setUnmed((resultado[21] != null) ? resultado[21].toString().trim() : "");
        	resp.setPriocor((resultado[22] != null) ? resultado[22].toString().trim() : "");
        	
        	pend.setNumpend((resultado[23] != null) ? resultado[23].toString().trim() : "");
        	pend.setCdpend((resultado[24] != null) ? resultado[24].toString().trim() : "");
        	pend.setObsresol((resultado[25] != null) ? resultado[25].toString().trim() : "");
        	pend.setStatus((resultado[26] != null) ? resultado[26].toString().trim() : "");
        	pend.setPartnum((resultado[42] != null) ? resultado[42].toString().trim() : "");
        	pend.setIdmatriz((resultado[43] != null) ? resultado[43].toString().trim() : "");
        	pend.setPartnumpd((resultado[57] != null) ? resultado[57].toString().trim() : "");
        	
        	pend.setNumdoc((resultado[28] != null) ? resultado[28].toString().trim() : "");
        	pend.setSerdoc((resultado[29] != null) ? resultado[29].toString().trim() : "");
        	pend.setEmidoc((resultado[30] != null) ? resultado[30].toString().trim() : "");
        	pend.setNumdoc2((resultado[31] != null) ? resultado[31].toString().trim() : "");
        	pend.setSerdoc2((resultado[32] != null) ? resultado[32].toString().trim() : "");
        	pend.setEmidoc2((resultado[33] != null) ? resultado[33].toString().trim() : "");
        	pend.setNumdoc3((resultado[34] != null) ? resultado[34].toString().trim() : "");
        	pend.setSerdoc3((resultado[35] != null) ? resultado[35].toString().trim() : "");
        	pend.setEmidoc3((resultado[36] != null) ? resultado[36].toString().trim() : "");        	
        	pend.setCdbej((resultado[38] != null) ? resultado[38].toString().trim() : "");
        	pend.setCorpt((resultado[39] != null) ? resultado[39].toString().trim() : "");
        	pend.setCoreng((resultado[40] != null) ? resultado[40].toString().trim() : "");
        	pend.setTppin((resultado[41] != null) ? resultado[41].toString().trim() : "");        	
        	resp.setCdbej((resultado[38] != null) ? resultado[38].toString().trim() : "");
        	resp.setCorpt((resultado[39] != null) ? resultado[39].toString().trim() : "");
        	resp.setCoreng((resultado[40] != null) ? resultado[40].toString().trim() : "");
        	resp.setTppin((resultado[41] != null) ? resultado[41].toString().trim() : "");
        	
        	resp.setStatus((resultado[58] != null) ? resultado[58].toString().trim() : "");
        	if (!(pend.getCdpend().equals("") && pend.getNumpend().equals("") && pend.getStatus().equals(""))) {
                listaPendSet.add(pend); // Adiciona à lista apenas se não estiver duplicado
            }
        	
        	Boolean existe = Boolean.FALSE;
            for (ProdutoSemListaResponse prd : produtos) {
				if(prd.getIdMatriz().equals(resp.getIdMatriz()) && prd.getPartnumpd().equals(resp.getPartnumpd())) {
					existe = Boolean.TRUE;
				}
			}
            
            if(listaPendSet.size() < 1 && !pend.getNumpend().equals("")) {
            	listaPendSet.add(pend);
            }
            for (PendenciaResponseSemLista pendencia : listaPendSet) {
            	if(!pendencia.getCdpend().equals(pend.getCdpend()) && !pend.getNumpend().equals("") && pend.getIdmatriz().equals(resp.getIdMatriz())) {
    				listaPendSet.add(pend);
    			}
			}
            
            List<PendenciaResponseSemLista> listaPend = new ArrayList<>(listaPendSet);

            resp.setPendencias(listaPend);
            
            
            if(!existe) {
                resp.setQtdependencias(repository.countPendencias(resp.getIdMatriz().toString(), resp.getPartnumpd().toString()));
            	if((repository.countPendencias(resp.getIdMatriz().toString(), resp.getPartnumpd().toString())) < 1) {
            		produtos.add(resp);
            	} else {
            		listaPend = new ArrayList<>();
            		listaPendSet = new HashSet<>();
            	}
                
            }        	           
        }*/
        
		
	}
	

	public static <T> List<T> removerDuplicatas(List<T> lista) {
        Set<T> conjunto = new HashSet<>(lista);
        return new ArrayList<>(conjunto);
    }
	

	public void delete(Matriprd matriz) {
		
		repository.delete(matriz);
	}
	

	public Matriprd save(Matriprd matriz, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
				
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
		
	}


	public Matriprd createComCor(MatriprdComCorDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Matriprd matriz = new Matriprd();
		matriz.setProduto(dto.produto());
		matriz.setModelo(dto.modelo());
		matriz.setAnomdl(dto.anomdl());
		matriz.setTpprd(dto.tpprd());
		matriz.setDesccom(dto.desccom());
		matriz.setDescrfb(dto.descrfb());
		//if(dto.dtneci() == null) {
		//	matriz.setDtneci("");
		//}else {
		//	matriz.setDtneci(dto.dtneci());
		//}
		matriz.setProtot(dto.protot());
		matriz.setSpecial(dto.special());
		matriz.setTpdcre(dto.tpdcre());		
		matriz.setOrigprd(dto.origprd()==null? "":dto.origprd()); //j4 - add null controle in entity
		matriz.setDtneci(dto.dtneci()==null? "":dto.dtneci()); //J4
		matriz.setPrevfat(dto.prevfat()==null? "":dto.prevfat());//j4
		matriz.setPriodtmnt(Auxiliar.getDtFormated());
		matriz.setPriohrmnt(Auxiliar.getHrFormatedSemSegundo());
		matriz.setPrioresp(Auxiliar.getUser(request));
		matriz.setPriourgen(dto.priourgen());
		matriz.setItgarantia(""); //add set no update
		matriz.setObsprio(""); //add set no update
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
		matriz.setItgarantia(dto.itgarantia());
		matriz.setObsprio(dto.obsprio());
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
		matriz.setItgarantia(dto.itgarantia());
		matriz.setObsprio(dto.obsprio());
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
		
	}
	

	public Matriprd updateComCor(Matriprd matriz,  MatriprdComCorIdDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
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
