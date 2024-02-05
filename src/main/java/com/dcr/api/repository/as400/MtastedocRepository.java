package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Mtastedoc;
import com.dcr.api.model.keys.MtastedocKey;

public interface MtastedocRepository extends JpaRepository<Mtastedoc, MtastedocKey>{

}
