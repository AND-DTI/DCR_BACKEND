package com.dcr.api.service.as400;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
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
import com.dcr.api.model.as400.Dcrreg4;
import com.dcr.api.model.as400.Dcrreg9;
import com.dcr.api.model.dto.DcrlayoutDTO;
import com.dcr.api.model.keys.DcrlayoutKey;
import com.dcr.api.repository.as400.DcrlayoutRepository;
import com.dcr.api.repository.as400.Dcrreg0Repository;
import com.dcr.api.repository.as400.Dcrreg1Repository;
import com.dcr.api.repository.as400.Dcrreg2Repository;
import com.dcr.api.repository.as400.Dcrreg3Repository;
import com.dcr.api.repository.as400.Dcrreg4Repository;
import com.dcr.api.repository.as400.Dcrreg9Repository;
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
	
	@Autowired
	Dcrreg4Repository reg4Repository;
	
	@Autowired
	Dcrreg9Repository reg9Repository;
	
	public void gerarArquivoTXT(Integer idMatriz, String partnumpd, String tpprd) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
         FileWriter fw = new FileWriter("arquivoTeste2.txt");
         BufferedWriter bw = new BufferedWriter(fw); 
         StringBuffer sb = new StringBuffer();
         Sort sort = Sort.by(Sort.Direction.ASC, "key.idreg", "posini");
         List<Dcrlayout> campos =  repository.findAll(sort);
         
         Map<Object, List<Dcrlayout>> map = campos.stream()
                 .collect(Collectors.groupingBy(dcrlayout -> dcrlayout.getKey().getIdreg()));
         
        
         List<Dcrreg0> reg0 = reg0Repository.consultaByIds(idMatriz, partnumpd, tpprd);
         List<Dcrreg1> reg1 = reg1Repository.consultaByIds(idMatriz, partnumpd, tpprd);
         List<Dcrreg2> reg2 = reg2Repository.consultaByIds(idMatriz, partnumpd, tpprd);
         List<Dcrreg3> reg3 = reg3Repository.consultaByIds(idMatriz, partnumpd, tpprd);
         List<Dcrreg4> reg4 = reg4Repository.consultaByIds(idMatriz, partnumpd, tpprd);
         List<Dcrreg9> reg9 = reg9Repository.consultaByIds(idMatriz, partnumpd, tpprd);
         for (Dcrreg0 dcrreg0 : reg0) {
        	 
        	 for (Dcrlayout campo : map.get("0 ")) {
    			
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("denom")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "denom"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getDenom(), campo.getCampotam()));
	       			}
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "partnumpd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getPartnumpd(), campo.getCampotam()) );
	       			}
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "tpprd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getTpprd(), campo.getCampotam()) );
	       			}
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "idmatriz"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getIdmatriz(), campo.getCampotam()) );
	       			}
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("peso")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "peso"));
	       			}else {
	       				sb.append(Auxiliar.addCasasDecimais(dcrreg0.getPeso(), campo.getCampotam(), 5));
	       			}
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("salarios")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "salarios"));
	       			}else {
	       				sb.append(Auxiliar.addCasasDecimais(dcrreg0.getSalarios(), campo.getCampotam(), 2));
	       			}
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("encargos")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "encargos"));
	       			}else {
	       				sb.append(Auxiliar.addCasasDecimais(dcrreg0.getEncargos(), campo.getCampotam(), 2));
	       			}
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("dcrant")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "dcrant"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg0.getDcrant(), campo.getCampotam()));
	       			}
        		} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("procretif")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "procretif"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg0.getProcretif(), campo.getCampotam()));
	       			}
        		} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("vrspgd")) {
    				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg0, campo, "vrspgd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg0.getVrspgd(), campo.getCampotam()));
	       			}
        		} else {
	    			Class<?> classe = dcrreg0.getClass();
	    			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	    			field.setAccessible(true);
	    			
	    			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(field.get(dcrreg0), campo, campo.getKey().getCampo().toLowerCase().trim()));
	       			}else if(campo.getFillblank()!= null) {
	     				sb.append(Auxiliar.addSpaces(field.get(dcrreg0), campo.getCampotam()));
	     			}else {
	     				sb.append(Auxiliar.addZeros(field.get(dcrreg0), campo.getCampotam()));
	     			}
        		}
			}
        	 sb.append("\n");
         }
         bw.write(sb.toString());
         sb = new StringBuffer();
         for (Dcrreg1 dcrreg1 : reg1) {
        	 
        	 for (Dcrlayout campo : map.get("1 ")) {
      			
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("modelo")) {
      				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg1, campo, "modelo"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getModelo(), campo.getCampotam()) );
	       			}
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
      				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg1, campo, "partnumpd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getPartnumpd(), campo.getCampotam()) );
	       			}
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
      				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg1, campo, "partnumpd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getPartnumpd(), campo.getCampotam()) );
	       			}
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
      				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg1, campo, "idmatriz"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getIdmatriz(), campo.getCampotam()) );
	       			}
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("preco")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg1.getPreco(), campo.getCampotam(), 2));
    			} else {
      			
	      			Class<?> classe = dcrreg1.getClass();
	      			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	      			field.setAccessible(true);
	      			
	      			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(field.get(dcrreg1), campo, campo.getKey().getCampo().toLowerCase().trim()));
	       			}else if(campo.getFillblank()!= null) {
	     				sb.append(Auxiliar.addSpaces(field.get(dcrreg1), campo.getCampotam()));
	     			}else {
	     				sb.append(Auxiliar.addZeros(field.get(dcrreg1), campo.getCampotam()));
	     			}
    			}
     		}
        	 sb.append("\n");
          }
         bw.write(sb.toString());
         sb = new StringBuffer();
         for (Dcrreg2 dcrreg2 : reg2) {
        	 for (Dcrlayout campo : map.get("2 ")) {
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
     				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg2, campo, "numcomp"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getNumcomp(), campo.getCampotam()) );
	       			}
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
     				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg2, campo, "partnumpd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getPartnumpd(), campo.getCampotam()) );
	       			}
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
     				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg2, campo, "tpprd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getTpprd(), campo.getCampotam()) );
	       			}
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
     				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg2, campo, "idmatriz"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getIdmatriz(), campo.getCampotam()) );
	       			}
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("qtde")) {
     				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg2, campo, "qtde"));
	       			}else {
	       				sb.append(Auxiliar.addCasasDecimais(dcrreg2.getQtde(), campo.getCampotam(), 7));
	       			}
    			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("vlrunit")) {
     				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg2, campo, "vlrunit"));
	       			}else {
	       				sb.append(Auxiliar.addCasasDecimais(dcrreg2.getVlrunit(), campo.getCampotam(), 6));
	       			}
    			} else {
	     			Class<?> classe = dcrreg2.getClass();
	     			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	     			field.setAccessible(true);
	     			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(field.get(dcrreg2), campo, campo.getKey().getCampo().toLowerCase().trim()));
	       			}else if(campo.getFillblank()!= null) {
	     				sb.append(Auxiliar.addSpaces(field.get(dcrreg2), campo.getCampotam()));
	     			}else {
	     				sb.append(Auxiliar.addZeros(field.get(dcrreg2), campo.getCampotam()));
	     			}
	     			
    			}
 			}
        	 sb.append("\n");
          }
          bw.write(sb.toString());
          sb = new StringBuffer();
          for (Dcrreg3 dcrreg3 : reg3) {
         	 
         	 for (Dcrlayout campo : map.get("3 ")) {
       			
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "numcomp"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getNumcomp(), campo.getCampotam()) );
	       			}
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numsubcomp")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "numsubcomp"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getNumsubcomp(), campo.getCampotam()) );
	       			}
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "partnumpd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getPartnumpd(), campo.getCampotam()) );
	       			}
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "tpprd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getTpprd(), campo.getCampotam()) );
	       			}
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "idmatriz"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getIdmatriz(), campo.getCampotam()) );
	       			}
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("qtde")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "qtde"));
	       			}else {
	       				sb.append(Auxiliar.addCasasDecimais(dcrreg3.getQtde(), campo.getCampotam(), 7));
	       			}
    			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("vlrunit")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "vlrunit"));
	       			}else {
	       				sb.append(Auxiliar.addCasasDecimais(dcrreg3.getVlrunit(), campo.getCampotam(), 6));
	       			}
    			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("di")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "di"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getDi(), campo.getCampotam()));
	       			}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("itemadicao")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "itemadicao"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getItemadicao(), campo.getCampotam()));
	       			}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("adicao")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "adicao"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getAdicao(), campo.getCampotam()));
	       			}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numnf")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "numnf"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getNumnf(), campo.getCampotam()));
	       			}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("sernf")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "sernf"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getSernf(), campo.getCampotam()));
	       			}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("cnpjfor")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "cnpjfor"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getCnpjfor(), campo.getCampotam()));
	       			}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("eminf")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "eminf"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getEminf(), campo.getCampotam()));
	       			}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("espec")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "espec"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getEspec(), campo.getCampotam()));
	       			}
      				
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("undcom")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "undcom"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getUndcom(), campo.getCampotam()));
	       			}
        		} else
        		if(campo.getKey().getCampo().toLowerCase().trim().equals("ncm")) {
        			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg3, campo, "ncm"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg3.getNcm(), campo.getCampotam()));
	       			}
        		}else {
	       			Class<?> classe = dcrreg3.getClass();
	       			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	       			field.setAccessible(true);
	       			
	       			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(field.get(dcrreg3), campo, campo.getKey().getCampo().toLowerCase().trim()));
	       			}else if(campo.getFillblank()!= null) {
	     				sb.append(Auxiliar.addSpaces(field.get(dcrreg3), campo.getCampotam()));
	     			}else {
	     				sb.append(Auxiliar.addZeros(field.get(dcrreg3), campo.getCampotam()));
	     			}
        		}
   			}
         	sb.append("\n");
          }
         
          bw.write(sb.toString());
          sb = new StringBuffer();
          
          for (Dcrreg4 dcrreg4 : reg4) {
         	 
         	 for (Dcrlayout campo : map.get("4 ")) {
         			
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
         				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "numcomp"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getNumcomp(), campo.getCampotam()) );
    	       			}
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
         				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "partnumpd"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getPartnumpd(), campo.getCampotam()) );
    	       			}
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
         				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "tpprd"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getTpprd(), campo.getCampotam()) );
    	       			}
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
         				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "idmatriz"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getIdmatriz(), campo.getCampotam()) );
    	       			}
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("qtde")) {
         				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "qtde"));
    	       			}else {
    	       			 sb.append(Auxiliar.addCasasDecimais(dcrreg4.getQtde(), campo.getCampotam(), 7));
    	       			}
        			} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("vlrunit")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "vlrunit"));
    	       			}else {
    	       			 sb.append(Auxiliar.addCasasDecimais(dcrreg4.getVlrunit(), campo.getCampotam(), 6));
    	       			}
        			} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("di")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "di"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getDi(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("itemadicao")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "itemadicao"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getItemadicao(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("adicao")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "adicao"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getAdicao(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("numnf")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "numnf"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getNumnf(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("sernf")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "sernf"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getSernf(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("cnpjfor")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "cnpjfor"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getCnpjfor(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("sernf")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "sernf"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getSernf(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("eminf")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "eminf"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getEminf(), campo.getCampotam()));
    	       			}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("espec")) {
           				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "espec"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getEspec(), campo.getCampotam()));
    	       			}
            		} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("undcom")) {
         				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "undcom"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getUndcom(), campo.getCampotam()));
    	       			}
            		} else
            		if(campo.getKey().getCampo().toLowerCase().trim().equals("ncm")) {
            			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg4, campo, "ncm"));
    	       			}else {
    	       				sb.append(Auxiliar.addSpaces(dcrreg4.getNcm(), campo.getCampotam()));
    	       			}
            		} else {
            			Class<?> classe = dcrreg4.getClass();
             			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
             			field.setAccessible(true);
             			
             			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
    	       				sb.append(Auxiliar.verificarPreenchimento(field.get(dcrreg4), campo, campo.getKey().getCampo().toLowerCase().trim()));
    	       			}else if(campo.getFillblank()!= null) {
    	     				sb.append(Auxiliar.addSpaces(field.get(dcrreg4), campo.getCampotam()));
    	     			}else {
    	     				sb.append(Auxiliar.addZeros(field.get(dcrreg4), campo.getCampotam()));
    	     			}
            		}
         			
     			}
         	sb.append("\n");
            }
          
          bw.write(sb.toString());
          sb = new StringBuffer();
          
          for (Dcrreg9 dcrreg9 : reg9) {
         	 
         	 for (Dcrlayout campo : map.get("9 ")) {
       			
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg9, campo, "partnumpd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg9.getKey().getPartnumpd(), campo.getCampotam()) );
	       			}
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg9, campo, "tpprd"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg9.getKey().getTpprd(), campo.getCampotam()) );
	       			}
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
       				if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(dcrreg9, campo, "idmatriz"));
	       			}else {
	       				sb.append(Auxiliar.addSpaces(dcrreg9.getKey().getIdmatriz(), campo.getCampotam()) );
	       			}
       			} else {
       				Class<?> classe = dcrreg9.getClass();
           			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
           			field.setAccessible(true);
           			
           			if(campo.getCondfield() != null && campo.getCondfield().trim().length() > 0) {
	       				sb.append(Auxiliar.verificarPreenchimento(field.get(dcrreg9), campo, campo.getKey().getCampo().toLowerCase().trim()));
	       			}else if(campo.getFillblank()!= null) {
	     				sb.append(Auxiliar.addSpaces(field.get(dcrreg9), campo.getCampotam()));
	     			}else {
	     				sb.append(Auxiliar.addZeros(field.get(dcrreg9), campo.getCampotam()));
	     			}
       			}
       				
       			
   			}
         	sb.append("\n");
          }
          bw.write(sb.toString());
          sb = new StringBuffer();
          bw.close();
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
