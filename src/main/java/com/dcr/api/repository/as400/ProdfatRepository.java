package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Prodfat;
import com.dcr.api.model.keys.ProdfatKey;

public interface ProdfatRepository extends JpaRepository<Prodfat, ProdfatKey>{

}
