package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Dcroriprd;
import com.dcr.api.model.keys.DcroriprdKey;

public interface DcroriprdRepository extends JpaRepository<Dcroriprd, DcroriprdKey>{

}
