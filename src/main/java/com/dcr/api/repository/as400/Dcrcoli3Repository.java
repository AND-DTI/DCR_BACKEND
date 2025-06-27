package com.dcr.api.repository.as400;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Dcrcoli3;
import com.dcr.api.model.keys.Dcrcoli3Key;



public interface Dcrcoli3Repository extends JpaRepository<Dcrcoli3, Dcrcoli3Key>{


    @Query(value= "delete from DCRCOLI3 where DCRE= :num_dcre", nativeQuery= true)
    int deleteByDCRe(String num_dcre);
    

}
