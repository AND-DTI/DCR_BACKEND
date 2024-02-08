package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Matridoc;
import com.dcr.api.model.keys.MatridocKey;

public interface MatridocRepository extends JpaRepository<Matridoc, MatridocKey> {

}
