package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Dcrprocc;
import com.dcr.api.model.keys.DcrproccKey;

public interface DcrproccRepository extends JpaRepository<Dcrprocc, DcrproccKey>{

}
