package com.dcr.api.service.as400;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dcr.api.model.as400.Partnumber;
import com.dcr.api.repository.as400.PartnumberRepository;




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
	
	
}
