package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Matriitm;
import com.dcr.api.model.keys.MatriitmKey;

public interface MatriitmRepository extends JpaRepository<Matriitm, MatriitmKey>{

}
