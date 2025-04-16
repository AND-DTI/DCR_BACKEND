package com.dcr.api.service.as400;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Partnumber;
import com.dcr.api.repository.as400.PartnumberRepository;
import com.dcr.api.response.Interface.DocumentoIMP;
import com.dcr.api.response.Interface.DocumentoNAC;




@Service
public class PartnumberService {


	@Autowired
	PartnumberRepository repository;
	

	public List<Partnumber> getAll() {
	
		return repository.findAll();

	}
	

	public Optional<Partnumber> getByID(String partnumber) {
		
		return repository.findById(partnumber);
		
	}

	public List<DocumentoIMP> getDocumentoIMP(String numdoc, String partnum){

		numdoc = numdoc.replace("-", "");
		partnum = partnum.replace("-", "");
		
		if(!numdoc.isEmpty() && !partnum.equals("")){
			return repository.findDocumentoImpByDocnumAndPartnum(numdoc, partnum);
		}

		if(!numdoc.isEmpty() && partnum.isEmpty() ){
			return repository.findDocumentoImpByNumdoc(numdoc);
		}

		return repository.findDocumentoImpByPartnum(partnum);
		
	}

	public List<DocumentoNAC> getDocumentoNAC(String numdoc, String partnum){

		numdoc = numdoc.replace("-", "");
		partnum = partnum.replace("-", "");
		
		if(!numdoc.isEmpty() && !partnum.equals("")){
			return repository.findDocumentoNacByDocnumAndPartnum(numdoc, partnum);
		}

		if(!numdoc.isEmpty() && partnum.isEmpty() ){
			return repository.findDocumentoNacByNumdoc(numdoc);
		}

		return repository.findDocumentoNacByPartnum(partnum);
		
	}
	
	
}
