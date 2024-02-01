package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Matriins;
import com.dcr.api.model.keys.MatriinsKey;

public interface MatriinsRepository extends JpaRepository<Matriins, MatriinsKey>{

}
