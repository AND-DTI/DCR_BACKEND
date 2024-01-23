package com.dcr.api.repository.as400;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.dcr.api.model.as400.User;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    List<User> findDistinctByUsername(String username);

    List<User> findDistinctByEmail(String email);

}