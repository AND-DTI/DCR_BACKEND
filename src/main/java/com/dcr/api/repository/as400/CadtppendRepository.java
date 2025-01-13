package com.dcr.api.repository.as400;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Cadtppend;
import com.dcr.api.model.projection.RespPendProjection;



public interface CadtppendRepository extends JpaRepository<Cadtppend, String>{	

    @Query("SELECT p FROM Cadtppend p LEFT JOIN FETCH p.responsaveis r")
    List<Cadtppend> findPendenciasAndResponsaveis();
    

    @Query("SELECT p FROM Cadtppend p LEFT JOIN FETCH p.responsaveis r where p.cdpend = :cdpend")
    Optional<Cadtppend> findPendenciasAndResponsaveisByCdPend(String cdpend);


    //@Query("SELECT p FROM Cadtppend p LEFT JOIN FETCH p.responsaveis r where p.cdpend= :cdpend and r.subtipo= :subtipo")
    @Query(value="""
    SELECT p.*, --p.cdpend, p.descpend, p.obspend, p.tpreg,
           responsaveis.subtipo, cdresp, nmresp
    FROM   hd4dcdhh.Cadtppend p left join
           hd4dcdhh.Pendresp responsaveis on responsaveis.cdpend= p.cdpend
    WHERE  p.cdpend= :cdpend and responsaveis.subtipo= :subtipo 
    """, nativeQuery=true)                        
    List<RespPendProjection> findPendenciasAndResponsaveisByCdPend2(String cdpend, String subtipo);

}
