package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.ArrayList;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Iterator;
import java.util.List;
//import java.util.ListIterator;
import java.util.Map;
//import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
//import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import com.dcr.api.controller.TipoProdutoController;
import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.dto.AstecDTO;
//import com.dcr.api.model.as400.Dcrlayout;
import com.dcr.api.model.dto.CadppbDTO;
import com.dcr.api.model.projection.AstecProjection;
import com.dcr.api.model.projection.ListAstecProjection;
//import com.dcr.api.model.keys.ProdutoKey;
import com.dcr.api.model.projection.Nivel1Projection;
import com.dcr.api.model.projection.Nivel2Projection;
import com.dcr.api.model.projection.ProdsProjection;
import com.dcr.api.model.projection.TipoProjection;
import com.dcr.api.model.projection.TipoProjection2;
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


	//public Optional<Cadppb> getByID(ProdutoKey key) { //j4
	public Optional<Cadppb> getByID(String key) {  //j4 added

		return repository.findById(key);
	}


	public ListAstecProjection getAllASTEC() { //Added j4
	

		List<AstecProjection> results = repository.findAllASTEC();	
		//List<AstecDTO> lista = repository.findAllASTEC();	
		//return lista;

		Map<Object, List<AstecProjection>> map = results.stream()
		  .collect(Collectors.groupingBy(dcrlayout -> dcrlayout.getPartnumpd()));

		ListAstecProjection listAstec = new ListAstecProjection();
		listAstec.setProdutos(new ArrayList<AstecDTO>());
		listAstec.setTipos(new ArrayList<TipoProjection2>());

	
		for (Map.Entry<Object, List<AstecProjection>> entry : map.entrySet()) {
			for (AstecProjection obj : entry.getValue()) {

				AstecDTO astec = new AstecDTO();
				astec.setPartnumpd(obj.getPartnumpd());
				astec.setDesccom(obj.getDesccom());
				astec.setDescrfb(obj.getDescrfb());
				astec.setPrddest(obj.getPrddest());
				astec.setPpbprd(obj.getPpbprd());
				astec.setTpprd(obj.getTpprd());
				astec.setDscpor(obj.getDscpor());
				astec.setFlex4flw(obj.getFlex4flw());
				listAstec.getProdutos().add(astec);

				//Description tipo produto (join CADTPPRD)
				TipoProjection2 tp = new TipoProjection2(
					obj.getTpprd(), obj.getDscpor().trim()
				);				
				Boolean existeTp = Boolean.FALSE;
				for (TipoProjection2 tipo : listAstec.getTipos()) {										
					if(tipo.getTpPrd().equals(obj.getTpprd())) {
						existeTp = Boolean.TRUE;
					}
				}
				if(!existeTp) {  listAstec.getTipos().add(tp);  }

			};
		};
		Auxiliar.formatResponse(listAstec);
		Auxiliar.formatResponse(listAstec.getTipos());
		return listAstec;
	}
	
	
	public TpprdProjection getByTpprd(List<String> tpprdList) {
		List<ProdsProjection> results = repository.consultaByTpprd(tpprdList);

		
		Map<Object, List<ProdsProjection>> map = results.stream()
	                 .collect(Collectors.groupingBy(dcrlayout -> dcrlayout.getCdPrd()));
		 
		
		TpprdProjection tpprdProjection = new TpprdProjection();
		
		//tpprdProjection.setProdutos(new ArrayList());//J4
		//tpprdProjection.setTipos(new ArrayList());		
		tpprdProjection.setProdutos(new ArrayList<Nivel1Projection>());//J4
		tpprdProjection.setTipos(new ArrayList<TipoProjection>());//J4
		//@@@@List<TipoProjection> lista = new ArrayList<TipoProjection>();
		for (Map.Entry<Object, List<ProdsProjection>> entry : map.entrySet()) {
			for (ProdsProjection obj : entry.getValue()) {
				
			
				//Decription tipo produto (join CADTPPRD)
				TipoProjection tp = new TipoProjection();
				tp.setTpPrd(obj.getTpPrd());
				tp.setDscPor(obj.getDscPor().trim());
				
				
				Boolean existeTp = Boolean.FALSE;
				for (TipoProjection tipo : tpprdProjection.getTipos()) {
					if(tipo.getTpPrd().equals(obj.getTpPrd())) {
						existeTp = Boolean.TRUE;
					}
				}
				if(!existeTp) {
					tpprdProjection.getTipos().add(tp);
				}
				
				
				Boolean existePr = Boolean.FALSE;
				for (Nivel1Projection prod : tpprdProjection.getProdutos()) {					
					String cdprd = prod.getCdPrd().toString().trim(); //agrupa base cód. faturamento
					//String tpprd = prod.getTpPrd().toString().trim();
					if(cdprd.equals(obj.getCdPrd().trim()) /*&& tpprd.equals(obj.getTpPrd().trim())*/) {
						existePr = Boolean.TRUE;
					}				
				}
				
				Nivel1Projection nvl1 = new Nivel1Projection();
				nvl1.setCdPrd((obj.getCdPrd() != null) ? obj.getCdPrd().trim() : ""); 
				nvl1.setUncome((obj.getUncome() != null) ? obj.getUncome().trim() : "");//added j4
				nvl1.setAnofat((obj.getAnofat() != null) ? obj.getAnofat().trim() : "");//added j4
				nvl1.setFrstanofab((obj.getFrstanofab() != null) ? obj.getFrstanofab().trim() : "");//added j4
				nvl1.setLastanofab((obj.getLastanofab() != null) ? obj.getLastanofab().trim() : "");//added j4
				nvl1.setTpPrd((obj.getTpPrd() != null) ? obj.getTpPrd().trim() : "");
				nvl1.setDscpor((obj.getDscPor() != null) ? obj.getDscPor().trim() : "");//added j4	            
				nvl1.setDescCom((obj.getDescCom() != null) ? obj.getDescCom().trim() : "");
	            nvl1.setDescRfb((obj.getDescRfb() != null) ? obj.getDescRfb().trim() : "");
	            nvl1.setPrdDest((obj.getPrdDest() != null) ? obj.getPrdDest().trim() : "");
	            nvl1.setPpbPrd((obj.getPpbPrd() != null) ? obj.getPpbPrd().trim() : "");
	            nvl1.setModelo((obj.getModelo() != null) ? obj.getModelo().trim() : "");
	            nvl1.setAnoMdl((obj.getAnoMdl() != null) ? obj.getAnoMdl() : "");
	            
	            //nvl1.setItens(new ArrayList()); //j4
				nvl1.setItens(new ArrayList<Nivel2Projection>()); //j4
				for (ProdsProjection result : entry.getValue()) {
		            Nivel2Projection nvl2 = new Nivel2Projection();
		            nvl2.setPartnumPd((result.getPartnumPd() != null) ? result.getPartnumPd().trim() : "");
		            nvl2.setDescPor((result.getDescPor() != null) ? result.getDescPor().trim() : "");
		            nvl2.setDescIng((result.getDescIng() != null) ? result.getDescIng().trim() : "");
		            nvl2.setuEngNo((result.getuEngNo() != null) ? result.getuEngNo().trim() : "");
		            nvl2.setCodCor((result.getCodCor() != null) ? result.getCodCor().trim() : "");
		            nvl2.setCorPt((result.getCorPt() != null) ? result.getCorPt().trim() : "");
		            //nvl2.setUncome((result.getUncome() != null) ? result.getUncome().trim() : ""); //J4	
		            Boolean existeCor = Boolean.FALSE;
		            for (Nivel2Projection prod : nvl1.getItens()) {
		            	if(prod.getCodCor().equals(result.getCodCor())) {
		            		existeCor = Boolean.TRUE;
		            	}
		            }
		            if(!existeCor) {
		            	nvl1.getItens().add(nvl2);
		            }
		            
		            
				}
				if(!existePr) {
					tpprdProjection.getProdutos().add(nvl1);
				}
				
			}
			
           
		}
        
        return tpprdProjection;
    }
	
	


	public void delete(Cadppb ppb) {
		
		repository.delete(ppb);
	}
	
	public Cadppb create(CadppbDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Cadppb ppb = new Cadppb();
		
		//ProdutoKey key = new ProdutoKey();
		//key.setCdprd(dto.cdprd());
		//key.setTpprd(dto.tpprd());
		//ppb.setKey(key);
		ppb.setPartnumpd(dto.partnumpd()); //added j4
		ppb.setTpprd(dto.tpprd()); //added j4

		ppb.setDesccom(dto.desccom());
		ppb.setDescrfb(dto.descrfb());
		ppb.setPpbprd(dto.ppbprd());
		ppb.setPrddest(dto.prddest());
		ppb.setFlex4flw("PENDENTE CHECK MODELO"); //added j4 - to schedule get
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
	
	public Cadppb update(Cadppb ppb,  CadppbDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		ppb.setDesccom(dto.tpprd()); //added j4
		ppb.setDesccom(dto.desccom());
		ppb.setDescrfb(dto.descrfb());
		ppb.setPpbprd(dto.ppbprd());
		ppb.setPrddest(dto.prddest());
		Auxiliar.preencheAuditoria(ppb, request);
		return repository.save(ppb);
	}
}
