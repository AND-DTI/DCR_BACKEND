package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Pendastec;
import com.dcr.api.model.keys.PendastecKey;

public interface PendastecRepository extends JpaRepository<Pendastec, PendastecKey>{

}
