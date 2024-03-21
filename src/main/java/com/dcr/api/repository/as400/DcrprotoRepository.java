package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Dcrproto;

public interface DcrprotoRepository extends JpaRepository<Dcrproto, String>{

}
