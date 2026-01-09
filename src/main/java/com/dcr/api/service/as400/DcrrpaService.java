package com.dcr.api.service.as400;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Dcrrpa;
import com.dcr.api.model.as400.DcrrpaDTO;
import com.dcr.api.repository.as400.DcrrpaRepository;
import com.dcr.api.service.AuditoriaService;
import com.dcr.api.utils.Auxiliar;



@Service
public class DcrrpaService {


	@Autowired
	DcrrpaRepository repository;

	@Autowired
	AuditoriaService auditoria;

	
	public List<Dcrrpa> getAll() {
		
		return repository.findAll();

	}
	
	public Optional<Dcrrpa> getByUser() throws Exception {
		
		String username= auditoria.getUser();
		return repository.findById(username);

	}
	
	/*public Optional<Dcrapi> getByDate(DcrapiKeyDTO dto) {
		DcrapiKey key = new DcrapiKey();
		key.setConfvigini(dto.confvigini());
		key.setConfvigfim(dto.confvigfim());
		return repository.findById(key);
	}*/
	
	public Dcrrpa create(DcrrpaDTO dto) throws Exception{
		
		Dcrrpa dcr = new Dcrrpa();
		dcr.setUsername(dto.username());
		if(dto.username()==null || dto.username().isBlank()){
			dcr.setUsername(auditoria.getUser());
		}
		//Use mapper:
		dcr.setMaquina(dto.maquina());
		dcr.setPorta(dto.porta());
		dcr.setBaseurl(dto.baseurl());
		dcr.setRpauri(dto.rpauri());
		dcr.setPlaypath(dto.playpath());
		dcr.setPlaybrowse(dto.playbrowse());
		dcr.setPlaychanel(dto.playchanel());
		
		auditoria.preencheAuditoria(dcr);
		Auxiliar.formatResponse(dcr);
		return repository.save(dcr);

	}

	public void update(Dcrrpa dcr, DcrrpaDTO dto) throws Exception{
		
		//Dcrrpa dcr = repository.getReferenceById(dto.username());
		dcr.setMaquina(dto.maquina());
		dcr.setPorta(dto.porta());
		dcr.setBaseurl(dto.baseurl());
		dcr.setRpauri(dto.rpauri());
		dcr.setPlaypath(dto.playpath());
		dcr.setPlaybrowse(dto.playbrowse());
		dcr.setPlaychanel(dto.playchanel());
		
		auditoria.preencheAuditoria(dcr);
		Auxiliar.formatResponse(dcr);
		repository.save(dcr);

	}
	
	/*public void update(DcrapiDTO dto, Dcrapi dcr, HttpServletRequest request, String dtFim) throws UnknownHostException, JsonMappingException, JsonProcessingException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {	
		
		repository.updateStsconfigAndConfvigfim(dcr.getDcrapiKey().getConfvigini(), dcr.getDcrapiKey().getConfvigfim(), 0, dtFim);
	}*/
}
