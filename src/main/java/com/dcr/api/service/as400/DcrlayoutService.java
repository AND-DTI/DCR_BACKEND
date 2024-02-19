package com.dcr.api.service.as400;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Dcrlayout;
import com.dcr.api.model.as400.Dcrreg0;
import com.dcr.api.model.as400.Dcrreg1;
import com.dcr.api.model.as400.Dcrreg2;
import com.dcr.api.model.as400.Dcrreg3;
import com.dcr.api.model.dto.DcrlayoutDTO;
import com.dcr.api.model.keys.DcrlayoutKey;
import com.dcr.api.repository.as400.DcrlayoutRepository;
import com.dcr.api.repository.as400.Dcrreg0Repository;
import com.dcr.api.repository.as400.Dcrreg1Repository;
import com.dcr.api.repository.as400.Dcrreg2Repository;
import com.dcr.api.repository.as400.Dcrreg3Repository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class DcrlayoutService {
	@Autowired
	DcrlayoutRepository repository;
	
	@Autowired
	Dcrreg0Repository reg0Repository;
	
	@Autowired
	Dcrreg1Repository reg1Repository;
	
	@Autowired
	Dcrreg2Repository reg2Repository;
	
	@Autowired
	Dcrreg3Repository reg3Repository;
	
	public void gerarArquivoTXT(String idMatriz) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
         FileWriter fw = new FileWriter("arquivoTeste2.txt");
         BufferedWriter bw = new BufferedWriter(fw); 
         StringBuffer sb = new StringBuffer();
         Sort sort = Sort.by(Sort.Direction.ASC, "key.idreg", "posini");
         List<Dcrlayout> campos =  repository.findAll(sort);
         
         Map<Object, List<Dcrlayout>> map = campos.stream()
                 .collect(Collectors.groupingBy(dcrlayout -> dcrlayout.getKey().getIdreg()));
         
        
         List<Dcrreg0> reg0 = reg0Repository.consultaByIds(1, "PARTNUM                  ", "TP  ");
         List<Dcrreg1> reg1 = reg1Repository.consultaByIds(1, "PARTNUM                  ", "TP  ");
         List<Dcrreg2> reg2 = reg2Repository.consultaByIds(1, "PARTNUM                  ", "TP  ");
         List<Dcrreg3> reg3 = reg3Repository.consultaByIds(1, "PARTNUM                  ", "TP  ");
         for (Dcrlayout campo : map.get("0 ")) {
        	 
    		for (Dcrreg0 dcrreg0 : reg0) {
    			
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("denom")) {
    				sb.append(dcrreg0.getKey().getDenom());
    				break;
    			}
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
    				sb.append(dcrreg0.getKey().getPartnumpd());
    				break;
    			}
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
    				sb.append(dcrreg0.getKey().getTpprd());
    				break;
    			}
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
    				sb.append(""+dcrreg0.getKey().getIdmatriz());
    				break;
    			}
    				
    			Class<?> classe = dcrreg0.getClass();
    			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
    			field.setAccessible(true);
    			
    			sb.append(""+ field.get(dcrreg0));
			}
         }
         bw.write(sb.toString());
         sb = new StringBuffer();
         sb.append("\n");
         for (Dcrlayout campo : map.get("1 ")) {
        	 for (Dcrreg1 dcrreg1 : reg1) {
      			
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("modelo")) {
      				sb.append(dcrreg1.getKey().getModelo());
      				break;
      			}
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
      				sb.append(dcrreg1.getKey().getPartnumpd());
      				break;
      			}
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
      				sb.append(dcrreg1.getKey().getTpprd());
      				break;
      			}
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
      				sb.append(""+dcrreg1.getKey().getIdmatriz());
      				break;
      			}
      				
      			Class<?> classe = dcrreg1.getClass();
      			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
      			field.setAccessible(true);
      			
      			sb.append(""+ field.get(dcrreg1));
     			}
     		
          }
         bw.write(sb.toString());
         sb = new StringBuffer();
         sb.append("\n");
         for (Dcrlayout campo : map.get("2 ")) {
        	 
     		for (Dcrreg2 dcrreg2 : reg2) {
     			
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
     				sb.append(""+dcrreg2.getKey().getNumcomp());
     				break;
     			}
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
     				sb.append(dcrreg2.getKey().getPartnumpd());
     				break;
     			}
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
     				sb.append(dcrreg2.getKey().getTpprd());
     				break;
     			}
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
     				sb.append(""+dcrreg2.getKey().getIdmatriz());
     				break;
     			}
     				
     			Class<?> classe = dcrreg2.getClass();
     			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
     			field.setAccessible(true);
     			
     			sb.append(""+ field.get(dcrreg2));
 			}
          }
          bw.write(sb.toString());
          sb = new StringBuffer();
          sb.append("\n");
          for (Dcrlayout campo : map.get("3 ")) {
         	 
       		for (Dcrreg3 dcrreg3 : reg3) {
       			
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
       				sb.append(""+dcrreg3.getKey().getNumcomp());
       				break;
       			}
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numsubcomp")) {
       				sb.append(""+dcrreg3.getKey().getNumcomp());
       				break;
       			}
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
       				sb.append(dcrreg3.getKey().getPartnumpd());
       				break;
       			}
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
       				sb.append(dcrreg3.getKey().getTpprd());
       				break;
       			}
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
       				sb.append(""+dcrreg3.getKey().getIdmatriz());
       				break;
       			}
       				
       			Class<?> classe = dcrreg3.getClass();
       			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
       			field.setAccessible(true);
       			
       			sb.append(""+ field.get(dcrreg3));
   			}
          }
         
          bw.write(sb.toString());
          bw.close();
          sb = new StringBuffer();
          sb.append("\n");
          
    }
	
	
	public Optional<Dcrlayout> getById(DcrlayoutKey key) {
		return repository.findById(key);
	}
	
	public Dcrlayout create(DcrlayoutDTO dto, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Dcrlayout dcr = new Dcrlayout();
		
		DcrlayoutKey key = new DcrlayoutKey();
		key.setCampo(dto.campo());
		key.setIdreg(dto.idreg());
		
		dcr.setKey(key);
		
		dcr.setCampodesc(dto.campodesc());
		dcr.setCampotam(dto.campotam());
		dcr.setDescreg(dto.descreg());
		dcr.setObrig(dto.obrig());
		dcr.setPosfim(dto.posfim());
		dcr.setPosini(dto.posini());
		dcr.setRegra(dto.regra());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public Dcrlayout update(DcrlayoutDTO dto, Dcrlayout dcr, HttpServletRequest request) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {		
		
		dcr.setCampodesc(dto.campodesc());
		dcr.setCampotam(dto.campotam());
		dcr.setDescreg(dto.descreg());
		dcr.setObrig(dto.obrig());
		dcr.setPosfim(dto.posfim());
		dcr.setPosini(dto.posini());
		dcr.setRegra(dto.regra());
		
		Auxiliar.preencheAuditoria(dcr, request);
		
		return repository.save(dcr);
	}
	
	public List<Dcrlayout> getAll() {
		
		return repository.findAll();
	}

}
