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
    				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getDenom(), campo.getCampotam()) );
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
    				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getPartnumpd(), campo.getCampotam()) );
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
    				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getTpprd(), campo.getCampotam()) );
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
    				sb.append(Auxiliar.addSpaces(dcrreg0.getKey().getIdmatriz(), campo.getCampotam()) );
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("peso")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg0.getPeso(), campo.getCampotam(), 5));
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("salarios")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg0.getSalarios(), campo.getCampotam(), 2));
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("encargos")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg0.getEncargos(), campo.getCampotam(), 2));
    			} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("dcrant")) {
      				if(dcrreg0.getTpdcre().equals("N")) {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces(dcrreg0.getDcrant(), campo.getCampotam()));
      				}
        		} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("procretif")) {
      				if(dcrreg0.getTpdcre().equals("N") || dcrreg0.getTpdcre().equals("S")) {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces(dcrreg0.getProcretif(), campo.getCampotam()));
      				}
        		} else
    			if(campo.getKey().getCampo().toLowerCase().trim().equals("vrspgd")) {
      				if(dcrreg0.getOrigdcr().equals(2) || dcrreg0.getOrigdcr().equals("2")) {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces(dcrreg0.getVrspgd(), campo.getCampotam()));
      				}
        		} else {
	    			Class<?> classe = dcrreg0.getClass();
	    			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	    			field.setAccessible(true);
	    			
	    			sb.append(Auxiliar.addSpaces(field.get(dcrreg0), campo.getCampotam()));
        		}
			}
        	 sb.append("\n");
         }
         bw.write(sb.toString());
         sb = new StringBuffer();
         for (Dcrreg1 dcrreg1 : reg1) {
        	 
        	 for (Dcrlayout campo : map.get("1 ")) {
      			
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("modelo")) {
      				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getModelo(), campo.getCampotam()) );
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
      				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getPartnumpd(), campo.getCampotam()) );
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
      				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getTpprd(), campo.getCampotam()));
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
      				sb.append(Auxiliar.addSpaces(dcrreg1.getKey().getIdmatriz(), campo.getCampotam()) );
      			} else
      			if(campo.getKey().getCampo().toLowerCase().trim().equals("preco")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg1.getPreco(), campo.getCampotam(), 2));
    			} else {
      			
	      			Class<?> classe = dcrreg1.getClass();
	      			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	      			field.setAccessible(true);
	      			
	      			sb.append(Auxiliar.addSpaces(field.get(dcrreg1), campo.getCampotam()));
    			}
     		}
        	 sb.append("\n");
          }
         bw.write(sb.toString());
         sb = new StringBuffer();
         for (Dcrreg2 dcrreg2 : reg2) {
        	 for (Dcrlayout campo : map.get("2 ")) {
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
     				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getNumcomp(), campo.getCampotam()) );
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
     				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getPartnumpd(), campo.getCampotam()) );
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
     				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getTpprd(), campo.getCampotam()) );
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
     				sb.append(Auxiliar.addSpaces(dcrreg2.getKey().getIdmatriz(), campo.getCampotam()) );
     			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("qtde")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg2.getQtde(), campo.getCampotam(), 7));
    			} else
     			if(campo.getKey().getCampo().toLowerCase().trim().equals("vlrunit")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg2.getVlrunit(), campo.getCampotam(), 6));
    			} else {
	     			Class<?> classe = dcrreg2.getClass();
	     			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	     			field.setAccessible(true);
	     			
	     			sb.append(Auxiliar.addSpaces(field.get(dcrreg2), campo.getCampotam()));
    			}
 			}
        	 sb.append("\n");
          }
          bw.write(sb.toString());
          sb = new StringBuffer();
          for (Dcrreg3 dcrreg3 : reg3) {
         	 
         	 for (Dcrlayout campo : map.get("3 ")) {
       			
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getNumcomp(), campo.getCampotam()) );
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numsubcomp")) {
       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getNumsubcomp(), campo.getCampotam()) );
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getPartnumpd(), campo.getCampotam()) );
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getTpprd(), campo.getCampotam()) );
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
       				sb.append(Auxiliar.addSpaces(dcrreg3.getKey().getIdmatriz(), campo.getCampotam()) );
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("qtde")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg3.getQtde(), campo.getCampotam(), 7));
    			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("vlrunit")) {
    		        sb.append(Auxiliar.addCasasDecimais(dcrreg3.getVlrunit(), campo.getCampotam(), 6));
    			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("di")) {
      				if(dcrreg3.getImpdireta().equals("S") || dcrreg3.getImpdireta().equals("s")) {
      					sb.append(Auxiliar.addZeros(dcrreg3.getDi(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("itemadicao")) {
      				if(dcrreg3.getImpdireta().equals("S") || dcrreg3.getImpdireta().equals("s")) {
      					sb.append(Auxiliar.addZeros(dcrreg3.getItemadicao(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("adicao")) {
      				if(dcrreg3.getImpdireta().equals("S") || dcrreg3.getImpdireta().equals("s")) {
      					sb.append(Auxiliar.addZeros(dcrreg3.getAdicao(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("numnf")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addZeros(dcrreg3.getNumnf(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("sernf")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addSpaces(dcrreg3.getSernf(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("cnpjfor")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addSpaces(dcrreg3.getCnpjfor(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("sernf")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addSpaces(dcrreg3.getAdicao(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("eminf")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addZeros(dcrreg3.getEminf(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
      				}
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("espec")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addSpaces(dcrreg3.getEspec(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				} 
        		} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("undcom")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addSpaces(dcrreg3.getUndcom(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
      				}
        		} else
        		if(campo.getKey().getCampo().toLowerCase().trim().equals("ncm")) {
      				if(dcrreg3.getImpdireta().equals("n") || dcrreg3.getImpdireta().equals("N")) {
      					sb.append(Auxiliar.addZeros(dcrreg3.getNcm(), campo.getCampotam()));
      				}else {
      					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
      				}
        		}else {
	       			Class<?> classe = dcrreg3.getClass();
	       			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
	       			field.setAccessible(true);
	       			
	       			sb.append(Auxiliar.addSpaces(field.get(dcrreg3), campo.getCampotam()));
        		}
   			}
         	sb.append("\n");
          }
         
          bw.write(sb.toString());
          sb = new StringBuffer();
          
          for (Dcrreg4 dcrreg4 : reg4) {
         	 
         	 for (Dcrlayout campo : map.get("4 ")) {
         			
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("numcomp")) {
         				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getNumcomp(), campo.getCampotam()) );
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
         				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getPartnumpd(), campo.getCampotam()) );
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
         				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getTpprd(), campo.getCampotam()) );
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
         				sb.append(Auxiliar.addSpaces(dcrreg4.getKey().getIdmatriz(), campo.getCampotam()) );
         			} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("qtde")) {
        		        sb.append(Auxiliar.addCasasDecimais(dcrreg4.getQtde(), campo.getCampotam(), 7));
        			} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("vlrunit")) {
        		        sb.append(Auxiliar.addCasasDecimais(dcrreg4.getVlrunit(), campo.getCampotam(), 6));
        			} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("di")) {
          				if(dcrreg4.getImpdireta().equals("S") || dcrreg4.getImpdireta().equals("s")) {
          					sb.append(Auxiliar.addZeros(dcrreg4.getDi(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("itemadicao")) {
          				if(dcrreg4.getImpdireta().equals("S") || dcrreg4.getImpdireta().equals("s")) {
          					sb.append(Auxiliar.addZeros(dcrreg4.getItemadicao(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("adicao")) {
          				if(dcrreg4.getImpdireta().equals("S") || dcrreg4.getImpdireta().equals("s")) {
          					sb.append(Auxiliar.addZeros(dcrreg4.getAdicao(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("numnf")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addZeros(dcrreg4.getNumnf(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("sernf")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addSpaces(dcrreg4.getSernf(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("cnpjfor")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addSpaces(dcrreg4.getCnpjfor(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("sernf")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addSpaces(dcrreg4.getAdicao(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("eminf")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addZeros(dcrreg4.getEminf(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
          				}
            		} else
           			if(campo.getKey().getCampo().toLowerCase().trim().equals("espec")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addSpaces(dcrreg4.getEspec(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
          				}
            		} else
         			if(campo.getKey().getCampo().toLowerCase().trim().equals("undcom")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addSpaces(dcrreg4.getUndcom(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addSpaces("", campo.getCampotam()));
          				}
            		} else
            		if(campo.getKey().getCampo().toLowerCase().trim().equals("ncm")) {
          				if(dcrreg4.getImpdireta().equals("n") || dcrreg4.getImpdireta().equals("N")) {
          					sb.append(Auxiliar.addZeros(dcrreg4.getNcm(), campo.getCampotam()));
          				}else {
          					sb.append(Auxiliar.addZeros("", campo.getCampotam()));
          				}
            		} else {
            			Class<?> classe = dcrreg4.getClass();
             			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
             			field.setAccessible(true);
             			
             			sb.append(Auxiliar.addSpaces(field.get(dcrreg4), campo.getCampotam()));
            		}
         			
     			}
         	sb.append("\n");
            }
          
          bw.write(sb.toString());
          sb = new StringBuffer();
          
          for (Dcrreg9 dcrreg9 : reg9) {
         	 
         	 for (Dcrlayout campo : map.get("9 ")) {
       			
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("partnumpd")) {
       				sb.append(Auxiliar.addSpaces(dcrreg9.getKey().getPartnumpd(), campo.getCampotam()) );
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("tpprd")) {
       				sb.append(Auxiliar.addSpaces(dcrreg9.getKey().getTpprd(), campo.getCampotam()) );
       			} else
       			if(campo.getKey().getCampo().toLowerCase().trim().equals("idmatriz")) {
       				sb.append(Auxiliar.addSpaces(dcrreg9.getKey().getIdmatriz(), campo.getCampotam()) );
       			} else {
       				Class<?> classe = dcrreg9.getClass();
           			Field field = classe.getDeclaredField(campo.getKey().getCampo().toLowerCase().trim());
           			field.setAccessible(true);
           			
           			sb.append(Auxiliar.addSpaces(field.get(dcrreg9), campo.getCampotam()));
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
