package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Pendresp;
import com.dcr.api.model.keys.PendenciaKey;

public interface PendrespRepository extends JpaRepository<Pendresp, PendenciaKey>{

}
