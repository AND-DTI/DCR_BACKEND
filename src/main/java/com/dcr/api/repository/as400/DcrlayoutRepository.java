package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Dcrlayout;
import com.dcr.api.model.keys.DcrlayoutKey;

public interface DcrlayoutRepository extends JpaRepository<Dcrlayout, DcrlayoutKey>{

}
