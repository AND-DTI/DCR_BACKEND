package com.dcr.api.service.as400;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Partnumber;
import com.dcr.api.repository.as400.PartnumberRepository;
import com.dcr.api.response.Interface.DCRModeloBase;
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

	public List<DocumentoNAC> getDocumentoNAC(String numdoc, String partnum, String itemLike, Integer years, Boolean elder){

		numdoc = numdoc.replace("-", "");
		partnum = partnum.replace("-", "");
		itemLike = itemLike.trim()+'%';


		//Consulta com documento informado - sem limitação de período:
		if(!numdoc.isEmpty()){
			if(!partnum.equals("")){
				return repository.findDocumentoNacByDocnumAndPartnum(numdoc, partnum);
			}

			if(partnum.isEmpty() && itemLike.isEmpty()){
				return repository.findDocumentoNacByNumdoc(numdoc);
			}
			
			return repository.findDocumentoNacByDocnumAndItemLike(numdoc, itemLike);
		}

		//Consulta sem documento informado - com limitação de período:
		if (!elder){
			
			if(!partnum.isEmpty()){
				return repository.findDocumentoNacByPartnum(partnum, years);
			}

			return repository.findDocumentoNacByItemLike(itemLike, years);

		}else{

			if(!partnum.isEmpty()){
				return repository.findDocumentoNacByPartnumElder(partnum, years);
			}

			return repository.findDocumentoNacByItemLikeElder(itemLike, years);

		}
		

	}

	public List<DocumentoNAC> getDocumentoNAC_Elder(String numdoc, String partnum, String itemLike, int years){

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
			return repository.findDocumentoNacByItemLike(itemLike, years);		
		}

		return repository.findDocumentoNacByPartnum(partnum, years);
		
	}
	
	

	public DCRModeloBase getDCRModeloBase(String modeloBase){

		return repository.findDCRModeloBase(modeloBase);

	}

	public List<DCRModeloBase> getDCRsModelo(String modeloBase, String dcre, String modeloLike, String partnumber) {
		
		if(modeloBase.trim().length() == 0 && !dcre.isEmpty() && modeloLike.isEmpty() && partnumber.isEmpty()){
			return repository.findDCRNum(dcre);
		}

		if(modeloBase.trim().length() == 0 && dcre.isEmpty() && modeloLike.isEmpty() && !partnumber.isEmpty()){
			return repository.findDCRPartnumber(partnumber);
		}
				
		return repository.findDCRsModelo(modeloBase, dcre, modeloLike, partnumber);
		
	}

}
