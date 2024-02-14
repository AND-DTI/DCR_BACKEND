package com.dcr.api.service.as400;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accoper;
import com.dcr.api.model.dto.AccoperDTO;
import com.dcr.api.repository.as400.AccoperRepository;
import com.dcr.api.response.Item;
import com.dcr.api.response.OperacoesItens;
import com.dcr.api.response.OperacoesResponse;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AccoperService {

	@Autowired
	AccoperRepository repository;
	private List<OperacoesResponse> formatJson(List<Accoper> operacoes) {
		
		List<Accoper> nivelZero = new ArrayList();
		List<Accoper> nivelUm = new ArrayList();
		List<Accoper> nivelDois = new ArrayList();
		List<OperacoesResponse> lista = new ArrayList();
		
		for (Accoper accoper : operacoes) {
			if(accoper.getNivel().equals(0)) {
				nivelZero.add(accoper);
			}else if (accoper.getNivel().equals(1)) {
				nivelUm.add(accoper);
			}else if(accoper.getNivel().equals(2)) {
				nivelDois.add(accoper);
			}
		}
		
		Map<Integer, List<Accoper>> nivelMap = new HashMap<>();

		for (Accoper accoper : operacoes) {
		    nivelMap.computeIfAbsent(accoper.getNivel(), k -> new ArrayList<>()).add(accoper);
		}
		
		for (Accoper zero : nivelZero) {
		    OperacoesResponse op = new OperacoesResponse();
		    op.setTitleName(zero.getDescoper().trim());
		    op.setChildrensItems(new ArrayList<>());

		    for (Accoper um : nivelUm) {
		        if (um.getIdpai().equals(zero.getIdoper())) {
		            OperacoesItens itens = new OperacoesItens();
		            itens.setName(um.getDescoper().trim());
		            itens.setIcon(um.getIcon().trim());
		            itens.setItems(new ArrayList<>());

		            for (Accoper dois : nivelDois) {
		                if (dois.getIdpai().equals(um.getIdoper())) {
		                    Item item = new Item();
		                    item.setName(dois.getDescoper().trim());
		                    item.setTo(dois.getRota().trim());
		                    item.setIcon(dois.getIcon().trim());
		                    itens.getItems().add(item);
		                }
		            }
		            op.getChildrensItems().add(itens);
		           
		            List<Accoper> filhosGen = nivelMap.getOrDefault(um.getNivel() + 1, Collections.emptyList());
		            for (Accoper filhoGen : filhosGen) {
		                if (filhoGen.getIdpai().equals(um.getIdoper())) {
		                    OperacoesItens itensGen = new OperacoesItens();
		                    itensGen.setName(filhoGen.getDescoper().trim());
		                    itensGen.setIcon(filhoGen.getIcon().trim());
		                    itensGen.setItems(new ArrayList<>());
		                    
		                    processarNivelGen(Collections.singletonList(filhoGen), nivelMap, op);
		                }
		            }
		        }
		    }
		    lista.add(op);
		}
		return lista;
	}
	
	public void processarNivelGen(List<Accoper> nivelGen, Map<Integer, List<Accoper>> nivelMap, OperacoesResponse op) {
	    for (Accoper gen : nivelGen) {
	        OperacoesItens itens = new OperacoesItens();
	        itens.setName(gen.getDescoper().trim());
	        itens.setIcon(gen.getIcon().trim());
	        itens.setItems(new ArrayList<>());

	        List<Accoper> filhos = nivelMap.getOrDefault(gen.getNivel() + 1, Collections.emptyList());
	        for (Accoper filho : filhos) {
	            if (filho.getIdpai().equals(gen.getIdoper())) {
	                Item item = new Item();
	                item.setName(filho.getDescoper().trim());
	                item.setTo(filho.getRota().trim());
	                item.setIcon(filho.getIcon().trim());
	                itens.getItems().add(item);
	            }
	        }

	        op.getChildrensItems().add(itens);
	        processarNivelGen(filhos, nivelMap, op);
	    }
	}
	
	public List<OperacoesResponse> getAll() {
		return formatJson(repository.findAll());
	}
	
	public List<OperacoesResponse> getByCdmodule(String cdmodule) {
		return formatJson(repository.findByCdmoduleAndAtivo(cdmodule));
	}
	
	public Optional<Accoper> getByID(Integer id) {
		
		return repository.findById(id);
	}
	
	public void delete(Accoper ppb) {
		
		repository.delete(ppb);
	}
	
	public Accoper create(AccoperDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		Accoper oper = new Accoper();
		
		oper.setIdoper(dto.idoper());
		oper.setAtivo(dto.ativo());
		oper.setTpoper(dto.tpoper());
		oper.setCdmodule(dto.cdmodule());
		oper.setDescoper(dto.descoper());
		oper.setIdpai(dto.idpai());
		oper.setNivel(dto.nivel());
		oper.setRota(dto.rota());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
	
	public Accoper update(Accoper oper,  AccoperDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		oper.setAtivo(dto.ativo());
		oper.setTpoper(dto.tpoper());
		oper.setCdmodule(dto.cdmodule());
		oper.setDescoper(dto.descoper());
		oper.setIdpai(dto.idpai());
		oper.setNivel(dto.nivel());
		oper.setRota(dto.rota());
		
		Auxiliar.preencheAuditoria(oper, request);
		return repository.save(oper);
	}
}
