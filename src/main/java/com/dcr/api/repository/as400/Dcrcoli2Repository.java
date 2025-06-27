package com.dcr.api.repository.as400;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dcr.api.model.as400.Dcrcoli2;
import com.dcr.api.model.keys.Dcrcoli2Key;


public interface Dcrcoli2Repository extends JpaRepository<Dcrcoli2, Dcrcoli2Key>{



    @Query(value= "delete from DCRCOLI2 where DCRE= :num_dcre", nativeQuery= true)
    int deleteByDCRe(String num_dcre);

}
