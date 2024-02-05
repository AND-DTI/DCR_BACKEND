package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Mtastec;
import com.dcr.api.model.keys.MtastecKey;

public interface MtastecRepository extends JpaRepository<Mtastec, MtastecKey>{

}
