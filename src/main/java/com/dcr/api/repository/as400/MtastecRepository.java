package com.dcr.api.repository.as400;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dcr.api.model.as400.Mtastec;

public interface MtastecRepository extends JpaRepository<Mtastec, Integer>{

	Mtastec findByIdmatrizAndPartnumpd(Integer idmatriz, String partnumpd);
}
