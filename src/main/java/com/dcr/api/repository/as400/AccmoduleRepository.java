package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Accmodule;
import com.dcr.api.model.keys.AccModuleKey;

public interface AccmoduleRepository extends JpaRepository<Accmodule, AccModuleKey> {

}
