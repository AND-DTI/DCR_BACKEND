package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Accdscmod;
import com.dcr.api.model.keys.AccdscmodKey;

public interface AccdscmodRepository extends JpaRepository<Accdscmod, AccdscmodKey>{

}

