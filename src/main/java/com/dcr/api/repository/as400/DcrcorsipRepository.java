package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Dcrcorsip;
import com.dcr.api.model.keys.DcrcorsipKey;

public interface DcrcorsipRepository  extends JpaRepository<Dcrcorsip, DcrcorsipKey>{

}
