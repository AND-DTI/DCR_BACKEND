package com.dcr.api.service.as400;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Matriprd;
import com.dcr.api.repository.as400.MatriprdRepository;

@Service
public class ScheduleService {
	@Autowired
	MatriprdRepository repository;
	
	public List<Object[]> getMatriprdWithNotInDcrprocc() {
		return repository.getMatriprdWithNotInDcrprocc();
	}
	
}
