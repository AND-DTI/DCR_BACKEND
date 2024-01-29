package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Dcrregra;
import com.dcr.api.model.keys.DcrregraKey;

public interface DcrregraRepository extends JpaRepository<Dcrregra, DcrregraKey>{

}
