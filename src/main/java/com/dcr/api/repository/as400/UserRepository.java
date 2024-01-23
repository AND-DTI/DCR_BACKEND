package com.dcr.api.repository.as400;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.Accuser;

public interface UserRepository extends JpaRepository<Accuser, String> {

    List<Accuser> findAll();

    Optional<Accuser> findByUsername(String username);

    List<Accuser> findDistinctByUsername(String username);

    List<Accuser> findDistinctByEmail(String email);

}