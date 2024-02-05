package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Mtasteins;
import com.dcr.api.model.keys.MtasteinsKey;

public interface MtasteinsRepository extends JpaRepository<Mtasteins, MtasteinsKey>{

}
