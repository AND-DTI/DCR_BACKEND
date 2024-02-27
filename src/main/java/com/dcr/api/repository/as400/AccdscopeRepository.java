package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Accdscope;
import com.dcr.api.model.keys.AccdscopeKey;

public interface AccdscopeRepository extends JpaRepository<Accdscope, AccdscopeKey>{

}
