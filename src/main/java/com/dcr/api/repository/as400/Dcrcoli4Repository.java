package com.dcr.api.repository.as400;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Dcrcoli4;
import com.dcr.api.model.keys.Dcrcoli4Key;



public interface Dcrcoli4Repository extends JpaRepository<Dcrcoli4, Dcrcoli4Key>{


    @Query(value= "delete from DCRCOLI4 where DCRE= :num_dcre", nativeQuery= true)
    int deleteByDCRe(String num_dcre);

    
}
