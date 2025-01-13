package com.dcr.api.repository.as400;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.dcr.api.model.as400.Accuser;



public interface UserRepository extends JpaRepository<Accuser, String> {

    List<Accuser> findAll();

    Optional<Accuser> findByUsername(String username);

    List<Accuser> findDistinctByUsername(String username);

    List<Accuser> findDistinctByEmail(String email);

    List<Accuser> findDistinctByUserid(Integer userid);
    
    List<Accuser> findDistinctByAtivo(String ativo);
    
    @Modifying
    @Query("update Accuser c set c.password = :password WHERE c.username = :username")
    void setPassword(String username, String password);

}