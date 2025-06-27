package com.dcr.api.service.as400;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrcoli0;
import com.dcr.api.model.as400.Dcrcoli1;
import com.dcr.api.model.as400.Dcrcoli2;
import com.dcr.api.model.as400.Dcrcoli3;
import com.dcr.api.model.dto.Dcrcoli0DTO;
import com.dcr.api.model.dto.Dcrcoli1DTO;
import com.dcr.api.model.keys.Dcrcoli0Key;
import com.dcr.api.model.keys.Dcrcoli1Key;
import com.dcr.api.model.keys.Dcrcoli2Key;
import com.dcr.api.model.keys.Dcrcoli3Key;
import com.dcr.api.repository.as400.Dcrcoli0Repository;
import com.dcr.api.repository.as400.Dcrcoli1Repository;
import com.dcr.api.repository.as400.Dcrcoli2Repository;
import com.dcr.api.repository.as400.Dcrcoli3Repository;
import com.dcr.api.repository.as400.Dcrcoli4Repository;
import com.dcr.api.schedule.dto.Reg2TXT;
import com.dcr.api.schedule.dto.Reg3TXT;
import com.dcr.api.service.AuditoriaService;
//import com.dcr.api.utils.Auxiliar;
import java.util.List;
//import java.util.Optional;



@Service
public class ColigadaService {


	@Autowired
	Dcrcoli0Repository repository0;	
	@Autowired
	Dcrcoli1Repository repository1;
	@Autowired
	Dcrcoli2Repository repository2;
	@Autowired
	Dcrcoli3Repository repository3;
	@Autowired
	Dcrcoli4Repository repository4;
	@Autowired
    AuditoriaService auditoriaService;


	
	public Dcrcoli0 criaReg0(Dcrcoli0DTO dto) throws Exception  {		

		
		Dcrcoli0Key key = new Dcrcoli0Key();
		key.setDcre(dto.dcre());
		key.setDenom(dto.denom());

		Optional<Dcrcoli0> dcr0 = repository0.findById(key);					
		if (!dcr0.isEmpty()) {				
			repository0.delete(dcr0.get());
		}
	
		Dcrcoli0 dcr = new Dcrcoli0();		
		dcr.setKey(key);
		dcr.setCdclient(dto.cdclient());
		dcr.setDtdcre(dto.dtdcre());
		dcr.setCnpj(dto.cnpj());
		dcr.setCpfrl(dto.cpfrl());
		dcr.setDcrant(dto.dcrant());
		dcr.setEncargos(dto.encargos());
		dcr.setIdreg(dto.idreg());
		dcr.setNcm(dto.ncm());
		dcr.setOrigdcr(dto.origdcr());
		dcr.setPeso(dto.peso());
		dcr.setPpb(dto.ppb());
		dcr.setProcretif(dto.procretif());
		dcr.setSalarios(dto.salarios());
		dcr.setTpcoef(dto.tpcoef());
		dcr.setTpdcre(dto.tpdcre());
		dcr.setUndcom(dto.undcom());
		dcr.setVrspgd(dto.vrspgd());		
		auditoriaService.preencheAuditoriaNoUser(dcr);		
		
		return repository0.save(dcr);


	}
	
		
	public Dcrcoli1 criaReg1(Dcrcoli1DTO dto) throws Exception  {
		
		Dcrcoli1Key key = new Dcrcoli1Key();
		key.setDcre(dto.dcre());
		key.setCdclient(dto.cdclient());
		key.setModelo(dto.modelo());
		
		Optional<Dcrcoli1> dcr1 = repository1.findById(key);					
		if (!dcr1.isEmpty()) {				
			repository1.delete(dcr1.get());
		}
		
		Dcrcoli1 dcr = new Dcrcoli1();		
		dcr.setKey(key);
		dcr.setIdreg(dto.idreg());
		dcr.setDescricao(dto.descricao());
		dcr.setPreco(dto.preco());		
		auditoriaService.preencheAuditoriaNoUser(dcr);
		
		return repository1.save(dcr);		

	}


	public int criaReg2(String dcre, List<Reg2TXT> lista) throws Exception  {
		
		
		//Limpa lista reg2 do DCR
		repository2.deleteByDCRe(dcre);

		for (Reg2TXT reg2 : lista){
	
			Dcrcoli2Key key = new Dcrcoli2Key();			
			key.setDcre(dcre);
			key.setNumcomp(reg2.getNumcomp());
												
			Dcrcoli2 dcr = new Dcrcoli2();
			dcr.setKey(key);			
			dcr.setIdreg("2");
			dcr.setNcm(reg2.getNcm());
			dcr.setUndcom(reg2.getUndcom());
			dcr.setCnpjfor(Long.valueOf(reg2.getCnpjfor()));
			dcr.setEminf(reg2.getEminf());
			dcr.setEspec(reg2.getEspec());
			dcr.setIe(reg2.getIe());
			dcr.setNumnf(reg2.getNumnf().toString());
			dcr.setQtde(reg2.getQtde());
			dcr.setSernf(reg2.getSernf());
			dcr.setVlrunit(reg2.getVlrunit());																						
			auditoriaService.preencheAuditoriaNoUser(dcr);
			
			repository2.save(dcr);

		}

		return 1;

	}


	public int criaReg3(String dcre, List<Reg3TXT> lista) throws Exception  {
		
		
		//Limpa lista reg3 do DCR
		repository3.deleteByDCRe(dcre);

		for (Reg3TXT reg3 : lista){
	
			Dcrcoli3Key key = new Dcrcoli3Key();			
			key.setDcre(dcre);
			key.setNumcomp(reg3.getNumcomp());
			key.setNumsubcomp(reg3.getNumsubcomp());
			
			Dcrcoli3 dcr = new Dcrcoli3();
			dcr.setKey(key);									
			dcr.setIdreg("3");
			dcr.setNcm(reg3.getNcm());
			dcr.setUndcom(reg3.getUndcom());
			dcr.setAdicao(reg3.getAdicao());
			dcr.setCnpjfor(Long.valueOf(reg3.getCnpjfor()));
			dcr.setDi(Long.valueOf(reg3.getDi()));
			dcr.setEminf(reg3.getEminf());
			dcr.setEspec(reg3.getEspec());
			dcr.setIe(reg3.getIe());
			dcr.setIibasecalc(reg3.getIibasecalc());
			dcr.setImpdireta(reg3.getImpdireta());
			dcr.setIndreducii(reg3.getInreducii());
			dcr.setItemadicao(Integer.valueOf(reg3.getItemadicao()));
			dcr.setNcm(reg3.getNcm());
			dcr.setNumnf(reg3.getNumnf().toString());
			dcr.setQtde(reg3.getQtde());
			dcr.setSernf(reg3.getSernf());
			dcr.setSuspens(reg3.getSuspens());
			dcr.setUndcom(reg3.getUndcom());
			dcr.setVlrunit(reg3.getVlrunit());			

			repository3.save(dcr);

		}

		return 1;

	}	

	 

}
