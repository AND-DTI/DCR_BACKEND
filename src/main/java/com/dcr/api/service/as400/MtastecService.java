package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.dto.MtastecDTO;
import com.dcr.api.repository.as400.MtastecRepository;
import com.dcr.api.response.AstecDetailResponse;
import com.dcr.api.response.AstecPendenciaResponse;
import com.dcr.api.response.CoresResponse;
import com.dcr.api.response.DocumentosAstecResponse;
import com.dcr.api.response.DocumentosResponse;
import com.dcr.api.response.InsumosAstecResponse;
import com.dcr.api.response.InsumosProdResponse;
import com.dcr.api.response.InsumosResponse;
import com.dcr.api.response.PendenciaAstecResponse;
import com.dcr.api.response.PendenciaResponse;
import com.dcr.api.response.PendenciaSemListaAstecResponse;
import com.dcr.api.response.ProdutoPendenciaAstecResponse;
import com.dcr.api.response.ProdutoPendenciaSimplesAstecResponse;
import com.dcr.api.response.ProdutoSemListaAstecResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;
//import com.dcr.api.response.CoresResponse;
//import com.dcr.api.response.CoresSimplesResponse;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import com.dcr.api.model.keys.MtastecKey;
//import com.dcr.api.response.InsumosProdResponse;
//import com.dcr.api.response.MatriprdResponse;
//import com.dcr.api.response.MatriprdResponseList;
//import com.dcr.api.response.PendenciaResponseSemLista;
//import com.dcr.api.response.ProdutoPendenciaAstecResponse;
//import com.dcr.api.response.ProdutoPendenciaResponse;
//import com.dcr.api.response.ProdutoPendenciaResponseList;
//import com.dcr.api.response.ProdutoPendenciaSimplesResponse;
//import com.dcr.api.response.ProdutoSemListaResponse;
//import net.bytebuddy.implementation.bytecode.Throw;



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
	

	public ProdutoPendenciaAstecResponse getProdutoPendencia(Integer id, String partnum) { //old ProdutoPendenciaSimplesAstecResponse
		
		List<Object[]> resultados = repository.consultaProdutoPendencia(id, partnum);	

		ProdutoPendenciaAstecResponse produto = new ProdutoPendenciaAstecResponse(); //old ProdutoPendenciaSimplesAstecResponse
		List<InsumosAstecResponse> listaInsumo = new ArrayList<>(); 
		List<PendenciaAstecResponse> listaPend = new ArrayList<>();
		List<DocumentosAstecResponse> listaDoc = new ArrayList<>();
		
		produto.setPendencias(listaPend);		
		produto.setInsumos(listaInsumo); 
		produto.setDocumentos(listaDoc);
		produto.setQtdependencias(repository.countPendenciasMatriz(id.toString()));
		produto.setQtdependenciasEmAberto(repository.countPendenciasEmAberto(id.toString(), partnum));
		

        for (Object[] resultado : resultados) {

        	
        	DocumentosAstecResponse doc = new DocumentosAstecResponse();
        	PendenciaAstecResponse pend = new PendenciaAstecResponse();
        	InsumosAstecResponse insumo = new InsumosAstecResponse();		
			int i = 0;


			String _IdMatriz  = (resultado[0] != null) ? resultado[0].toString().trim() : ""; i++;
        	String _Partnumpd = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Desccom   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Descrfb   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Prddest   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ppbprd    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;

			String _Tpdcre    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Origprd   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Dtneci    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Priourgen = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Prevfat   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Prioresp  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Priodtmnt = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _PrioHRmnt = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;						
			String _Unmed     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Preco     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ncm       = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;			        	  	        	
        	String _Status    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //17

			String _Numpend   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Cdpend    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Obsresol  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _StatusPend= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Descpend  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Obspend   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Obsdetail = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Tpreg     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //25
			 
			String _Partnum   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;			
			String _Partsugest= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partsugdsc= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partnew   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partnewdsc= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //30

			//insumos
			String _Partdesc   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Itmorg     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ittyp      = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Unmsr      = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Necfil     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cdspn      = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Weght      = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Emcomp     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Espec      = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Undcom     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _NcmIns     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _VlrunitIns = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //42
						
        	String _Tpdoc     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //43
			String _Numdoc    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Serdoc    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Emidoc    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cnpjfor   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ie        = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Adicao    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Itadicao  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //54		
        	
			String _Numdoc2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Serdoc2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Emidoc2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cnpjfor2  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ie2       = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Adicao2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Itadicao2 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit2  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund2 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco2  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal2    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //65

			String _Numdoc3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;        	        	        	
        	String _Serdoc3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Emidoc3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cnpjfor3  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ie3       = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Adicao3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Itadicao3 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit3  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund3 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco3  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal3    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //76

        	        	        	        	
			produto.setIdMatriz( _IdMatriz );
        	produto.setPartnumpd( _Partnumpd );
        	produto.setDesccom( _Desccom );
        	produto.setDescrfb( _Descrfb );
			produto.setPrddest( _Prddest );
			produto.setPpbprd( _Ppbprd );

			produto.setTpdcre( _Tpdcre ); 
			produto.setOrigprd( _Origprd );
			produto.setDtneci( _Dtneci );
        	produto.setPriourgen( _Priourgen );
        	produto.setPrevfat( _Prevfat );
        	produto.setPrioresp(_Prioresp );
        	produto.setPriodtmnt( _Priodtmnt );
        	produto.setPrioHRmnt( _PrioHRmnt );						
			produto.setUnmed( _Unmed );
			produto.setPreco( _Preco );
			produto.setNcm( _Ncm );	
        	produto.setStatus( _Status ); 

			pend.setIdmatriz( _IdMatriz );
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
			//Complementa dados da pendencia 
			pend.setNumdoc( _Numdoc );
			pend.setSerdoc( _Serdoc );
			pend.setEmidoc( _Emidoc );
			pend.setNumdoc2( _Numdoc2 );
			pend.setSerdoc2( _Serdoc2 );
			pend.setEmidoc2( _Emidoc2 );


        	insumo.setPartnum( _Partnum );
        	insumo.setPartdesc( _Partdesc );
        	insumo.setItmorg( _Itmorg );
        	insumo.setIttyp( _Ittyp );
        	insumo.setUnmsr( _Unmsr );
        	insumo.setNecfil( _Necfil );
        	insumo.setCdspn( _Cdspn );
        	insumo.setWeght( _Weght );
        	insumo.setEmcomp( _Emcomp );
			insumo.setEspec( _Espec );
			insumo.setUndcom( _Undcom );
			insumo.setNcm( _NcmIns );			
			insumo.setVlrunit( _VlrunitIns );
        	insumo.setPartsugest( _Partsugest );
        	insumo.setPartsugdsc( _Partsugdsc );
        	insumo.setPartnew( _Partnew );
        	insumo.setPartnewdsc( _Partnewdsc );
        	
        	doc.setIdmatriz( _IdMatriz );
			doc.setPartnum( _Partnum );
        	doc.setTpdoc( _Tpdoc );
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
        	doc.setSerdoc3( _Serdoc3);
			doc.setEmidoc3( _Emidoc3 );
			doc.setCnpjfor3( _Cnpjfor3 );
			doc.setIe3( _Ie3 );
			doc.setAdicao3( _Adicao3 );
        	doc.setItadicao3( _Itadicao3 );
			doc.setVlrunit3( _Vlrunit3 );
			doc.setSiglaund3( _Siglaund3 );
			doc.setCodinco3( _Codinco3 );
			doc.setModal3( _Modal3 ); 


			//Add pendencia - não se repete pois é o último nível
			if(!pend.getNumpend().equals("")){
				listaPend.add(pend);				
			}
			
			//Add distinct insumo - pode se repetir porque pode ter mais de uma pendencia para o mesmo insumo	
			Boolean insumoRepetido = false;
			if( (!insumo.getPartnum().equals("")) && (!_Partnum.equals(_Partnumpd)) ){
				for(InsumosAstecResponse ins :  listaInsumo){
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
       
       
		return produto;
	}
	

	public List<ProdutoSemListaAstecResponse> getTodasAsPendencias(List<Integer> status) { //old List<ProdutoPendenciaAstecResponse>
		
		List<Object[]> resultados = repository.consultaTodasAsPendencias(status);
			
		//List<ProdutoSemListaResponse> produtos = new ArrayList<>();		
		//List<PendenciaResponse> listaPend = new ArrayList<>();
		List<ProdutoSemListaAstecResponse> produtos = new ArrayList<>();		
		List<PendenciaAstecResponse> listaPend = new ArrayList<>();

		//old:
		//List<ProdutoPendenciaAstecResponse> produtos = new ArrayList<>();
		//Set<PendenciaAstecResponse> listaPendSet = new HashSet<>();
		//Set<DocumentosAstecResponse> listaDocSet = new HashSet<>();

		String matriz_anterior = "";
        for (Object[] resultado : resultados) {

        	ProdutoSemListaAstecResponse produto = new ProdutoSemListaAstecResponse(); 
			PendenciaAstecResponse pend = new PendenciaAstecResponse();        	
        	DocumentosAstecResponse doc = new DocumentosAstecResponse(); 
			int i = 0;


			String _IdMatriz  = (resultado[0] != null) ? resultado[0].toString().trim() : ""; i++;
        	String _Partnumpd = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Desccom   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Descrfb   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Prddest   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ppbprd    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;

			String _Tpdcre    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Origprd   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Dtneci    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Priourgen = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Prevfat   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Prioresp  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Priodtmnt = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _PrioHRmnt = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;						
			String _Unmed     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Preco     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ncm       = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;			        	  	        	
        	String _Status    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //17

			String _Numpend   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Cdpend    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Obsresol  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _StatusPend= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Descpend  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Obspend   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Obsdetail = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Tpreg     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //25
			 
			String _Partnum   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;			
			String _Partsugest= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partsugdsc= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partnew   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Partnewdsc= (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //30

			//insumos
			//ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.ESPEC, ins.UNDCOM, ins.NCM, ins.VLRUNIT, /*31 ~ 42*/  
        	
			i = 43;
        	String _Tpdoc     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Numdoc    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Serdoc    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Emidoc    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cnpjfor   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ie        = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Adicao    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Itadicao  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal     = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //54		
        	
			String _Numdoc2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Serdoc2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Emidoc2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cnpjfor2  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ie2       = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Adicao2   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Itadicao2 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit2  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund2 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco2  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal2    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //65

			String _Numdoc3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;        	        	        	
        	String _Serdoc3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Emidoc3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Cnpjfor3  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Ie3       = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Adicao3   = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
        	String _Itadicao3 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Vlrunit3  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Siglaund3 = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Codinco3  = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++;
			String _Modal3    = (resultado[i] != null) ? resultado[i].toString().trim() : ""; i++; //76
			


	

        	produto.setIdMatriz( _IdMatriz );
        	produto.setPartnumpd( _Partnumpd );
        	produto.setDesccom( _Desccom );
        	produto.setDescrfb( _Descrfb );
			produto.setPrddest( _Prddest );
			produto.setPpbprd( _Ppbprd );

			produto.setTpdcre( _Tpdcre ); 
			produto.setOrigprd( _Origprd );
			produto.setDtneci( _Dtneci );
        	produto.setPriourgen( _Priourgen );
        	produto.setPrevfat( _Prevfat );
        	produto.setPrioresp(_Prioresp );
        	produto.setPriodtmnt( _Priodtmnt );
        	produto.setPrioHRmnt( _PrioHRmnt );						
			produto.setUnmed( _Unmed );
			produto.setPreco( _Preco );
			produto.setNcm( _Ncm );	
        	produto.setStatus( _Status ); 

			pend.setIdmatriz( _IdMatriz );
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

			//Complementa dados da pendencia 
			pend.setNumdoc( _Numdoc );
			pend.setSerdoc( _Serdoc );
			pend.setEmidoc( _Emidoc );
			pend.setNumdoc2( _Numdoc2 );
			pend.setSerdoc2( _Serdoc2 );
			pend.setEmidoc2( _Emidoc2 );
			
			//insumo.set...:
			//ins.PARTDESC, ins.ITMORG, ins.ITTYP, ins.UNMSR, ins.NECFIL, ins.CDSPN, ins.WEGHT, ins.EMCOMP, ins.ESPEC, ins.UNDCOM, ins.NCM, ins.VLRUNIT
			        	        	
			doc.setPartnum( _Partnum );
        	doc.setTpdoc( _Tpdoc );
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
        	doc.setSerdoc3( _Serdoc3);
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
			if(!matriz_anterior.equals(produto.getIdMatriz())){
				novoProduto = true;				
				matriz_anterior = produto.getIdMatriz().toString();
			}


			if(novoProduto){
											
				listaPend = new ArrayList<>();
				//listaDoc = new ArrayList<>();
	
				produtos.add(produto);			
				produto.setPendencias(listaPend);
				//produto.setDocumentos(listaDoc);

				produto.setQtdependencias(repository.countPendenciasMatriz(produto.getIdMatriz().toString()));				
				produto.setQtdependenciasEmAberto(repository.countPendenciasEmAberto(_IdMatriz, "-"));
															
			}
	
			//Add pendencia - não se repete pois é o último nível
			if(!pend.getNumpend().equals("")){
				listaPend.add(pend);				
			}
			
        	
		}

		//for (ProdutoPendenciaAstecResponse produto : produtos) {
		//	produto.setQtdependencias(repository.countPendenciasNoPartnum(produto.getIdMatriz().toString()));
		//}
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
        	//resp.setPpbprd((resultado[58] != null) ? resultado[58].toString().trim() : ""); //j4 - commented
        	//resp.setPpbprd((resultado[59] != null) ? resultado[59].toString().trim() : ""); //j4 - commented
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
			

            //resp.setPendencias(listaPend); //@ j4 - classe refeita - refatore all function
            
            
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
