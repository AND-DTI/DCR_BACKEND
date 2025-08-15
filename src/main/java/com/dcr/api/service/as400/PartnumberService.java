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

	public List<DocumentoIMP> getDocumentoIMP(String numdoc, String partnum, String itemLike){

		numdoc = numdoc.replace("-", "");
		partnum = partnum.replace("-", "");
		itemLike = itemLike.trim()+'%';
		
		if(!numdoc.isEmpty() && !partnum.equals("")){
			return repository.findDocumentoImpByDocnumAndPartnum(numdoc, partnum);
		}

		if(!numdoc.isEmpty() && partnum.isEmpty() && itemLike.isEmpty()){
			return repository.findDocumentoImpByNumdoc(numdoc);
		}

		if(!numdoc.isEmpty() && partnum.isEmpty() && !itemLike.isEmpty()){
			return repository.findDocumentoImpByDocnumAndItemLike(numdoc, itemLike);
		}

		if(!itemLike.isEmpty() && numdoc.isEmpty() && partnum.isEmpty()){
			return repository.findDocumentoImpByItemLike(itemLike);		
		}

		return repository.findDocumentoImpByPartnum(partnum);
		
	}

	public List<DocumentoNAC> getDocumentoNAC(String numdoc, String partnum, String itemLike){

		numdoc = numdoc.replace("-", "");
		partnum = partnum.replace("-", "");
		itemLike = itemLike.trim()+'%';
		
		if(!numdoc.isEmpty() && !partnum.equals("")){
			return repository.findDocumentoNacByDocnumAndPartnum(numdoc, partnum);
		}

		if(!numdoc.isEmpty() && partnum.isEmpty() && itemLike.isEmpty()){
			return repository.findDocumentoNacByNumdoc(numdoc);
		}

		if(!numdoc.isEmpty() && partnum.isEmpty() && !itemLike.isEmpty()){
			return repository.findDocumentoNacByDocnumAndItemLike(numdoc, itemLike);
		}

		if(!itemLike.isEmpty() && numdoc.isEmpty() && partnum.isEmpty()){
			return repository.findDocumentoNacByItemLike(itemLike);		
		}

		return repository.findDocumentoNacByPartnum(partnum);
		
	}
	
	
}
