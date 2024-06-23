package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Accoperol;
import com.dcr.api.model.keys.AccoperolKey;

public interface AccoperolRepository extends JpaRepository<Accoperol, AccoperolKey>{

}
