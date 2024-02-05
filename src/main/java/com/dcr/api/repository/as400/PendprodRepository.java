package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Pendprod;
import com.dcr.api.model.keys.PendprodKey;

public interface PendprodRepository extends JpaRepository<Pendprod, PendprodKey>{

}
