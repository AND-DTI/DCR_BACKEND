package com.dcr.api.repository.as400;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrcorsip;
import com.dcr.api.model.keys.DcrcorsipKey;

public interface DcrcorsipRepository  extends JpaRepository<Dcrcorsip, DcrcorsipKey>{

	@Query(value = "SELECT * FROM HD4DCDHH.DCRCORSIP AS IP WHERE IP.CNPJEXT = :cnpjext", nativeQuery = true)
	List<Dcrcorsip> getByCnpj(String cnpjext);
}
