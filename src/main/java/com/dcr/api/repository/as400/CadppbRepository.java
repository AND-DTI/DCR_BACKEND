package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Cadppb;
import com.dcr.api.model.keys.ProdutoKey;

public interface CadppbRepository extends JpaRepository<Cadppb, ProdutoKey>{

}
