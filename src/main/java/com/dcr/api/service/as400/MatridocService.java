package com.dcr.api.service.as400;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Matridoc;
import com.dcr.api.model.dto.MatridocDTO;
import com.dcr.api.model.keys.MatridocKey;
import com.dcr.api.repository.as400.MatridocRepository;
import com.dcr.api.utils.Auxiliar;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import jakarta.servlet.http.HttpServletRequest;




@Service
public class MatridocService {

	
	@Autowired
	MatridocRepository repository;
	
	public List<Matridoc> getAll() {
		
		return repository.findAll();
	}
	

	public Optional<Matridoc> getByID(MatridocKey id) {
		
		return repository.findById(id);
	}
	

	public Optional<Matridoc> buscaDoc(Integer idmatriz, String partnum, String partnumpd) {
		
		return repository.buscaDoc(idmatriz, partnum, partnumpd);
	}

	
	public void delete(Matridoc matriz) {
		
		repository.delete(matriz);
	}
	

	public Matridoc create(MatridocDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Matridoc matriz = new Matridoc();
		MatridocKey key = new MatridocKey();
		key.setIdmatriz(dto.idmatriz());
		key.setPartnum(dto.partnum());
		key.setPartnumpd(dto.partnumpd());
		key.setTpdoc(dto.tpdoc());
		matriz.setKey(key);

		matriz.setEmidoc(dto.emidoc());
		matriz.setNumdoc(dto.numdoc());
		matriz.setSerdoc(dto.serdoc());
		matriz.setCnpjfor(dto.cnpjfor());
		matriz.setIe(dto.ie());
		matriz.setAdicao(dto.adicao());
		matriz.setItadicao(dto.itadicao());
		matriz.setVlrunit(dto.vlrunit()); //j4
		matriz.setSiglaund(dto.siglaund()); //j4
		matriz.setCodinco(dto.codinco()); //j4
		matriz.setModal(dto.modal()); //j4
		
		matriz.setEmidoc2(dto.emidoc2());
		matriz.setNumdoc2(dto.numdoc2());
		matriz.setSerdoc2(dto.serdoc2());
		matriz.setCnpjfor2(dto.cnpjfor2());
		matriz.setIe2(dto.ie2());
		matriz.setAdicao2(dto.adicao2());
		matriz.setItadicao2(dto.itadicao2());
		matriz.setVlrunit2(dto.vlrunit2()); //j4
		matriz.setSiglaund2(dto.siglaund2()); //j4
		matriz.setCodinco2(dto.codinco2()); //j4
		matriz.setModal2(dto.modal2()); //j4
		
		matriz.setEmidoc3(dto.emidoc3());
		matriz.setNumdoc3(dto.numdoc3());
		matriz.setSerdoc3(dto.serdoc3());
		matriz.setCnpjfor3(dto.cnpjfor3());
		matriz.setIe3(dto.ie3());
		matriz.setAdicao3(dto.adicao3());
		matriz.setItadicao3(dto.itadicao3());
		matriz.setVlrunit3(dto.vlrunit3()); //j4
		matriz.setSiglaund3(dto.siglaund3()); //j4
		matriz.setCodinco3(dto.codinco3()); //j4
		matriz.setModal3(dto.modal3()); //j4
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
	

	public Matridoc update(Matridoc matriz,  MatridocDTO dto, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		matriz.setEmidoc(dto.emidoc());
		matriz.setNumdoc(dto.numdoc());
		matriz.setSerdoc(dto.serdoc());
		matriz.setCnpjfor(dto.cnpjfor());
		matriz.setIe(dto.ie());
		matriz.setAdicao(dto.adicao());
		matriz.setItadicao(dto.itadicao());
		
		matriz.setEmidoc2(dto.emidoc2());
		matriz.setNumdoc2(dto.numdoc2());
		matriz.setSerdoc2(dto.serdoc2());
		matriz.setCnpjfor2(dto.cnpjfor2());
		matriz.setIe2(dto.ie2());
		matriz.setAdicao2(dto.adicao2());
		matriz.setItadicao2(dto.itadicao2());
		
		matriz.setEmidoc3(dto.emidoc3());
		matriz.setNumdoc3(dto.numdoc3());
		matriz.setSerdoc3(dto.serdoc3());
		matriz.setCnpjfor3(dto.cnpjfor3());
		matriz.setIe3(dto.ie3());
		matriz.setAdicao3(dto.adicao3());
		matriz.setItadicao3(dto.itadicao3());
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
	}
	

	public Matridoc resolverPendencia(Matridoc matriz, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);

	}

	//j4 - added
	public Matridoc save(Matridoc matriz, HttpServletRequest request) throws JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, UnknownHostException {
		
		Auxiliar.preencheAuditoria(matriz, request);
		return repository.save(matriz);
		
	}
	
}
