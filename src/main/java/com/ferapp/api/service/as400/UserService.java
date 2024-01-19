package com.ferapp.api.service.as400;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import com.ferapp.api.configs.security.Auditoria;
import com.ferapp.api.model.as400.User;
import com.ferapp.api.model.dto.CtpuserDTO;
import com.ferapp.api.repository.as400.UserRepository;
import com.ferapp.api.utils.Auxiliar;

@Service
public class UserService {

    final UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;

    }

    public List<User> listarTodos() {

        return userRepository.findAll();

    }

    public List<User> listByUsername(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return userRepository.findDistinctByUsername(username);

    }

    public List<User> listByEmail(String email) {

        return userRepository.findDistinctByEmail(email);

    }

    public Optional<User> getByUsernameOptional(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return userRepository.findByUsername(username);

    }

    public User getByUsername(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return (User) userRepository.findDistinctByUsername(username);

    }

    public Integer getMatricula(String username) {

        Integer matricula = 0;

        try {
            username = Auxiliar.trimNull(username).toUpperCase();
        } catch (Exception e) {
        }

        Optional<User> optUser = userRepository.findByUsername(username);

        if (!optUser.isEmpty()) {
            User user = optUser.get();
            matricula = user.getUserid();
        }

        return matricula;

    }

    public User saveUser(CtpuserDTO userDTO) {

        Auditoria audit = new Auditoria();

        User user = mapDTOToEntity(userDTO);
        user.setUsername(user.getUsername().toUpperCase());
        user.setItauddt(audit.getData_audit());
        user.setItaudhr(audit.getHora_audit());

        if (user.getToken().isEmpty()) {
            user.setToken("");
        }

        User userNew = userRepository.save(user);

        return userNew;

    }

    public User save(User user) {

        user.setUsername(user.getUsername().toUpperCase());
        return userRepository.save(user);

    }

    public User save0(User user) {

        return userRepository.save(user);

    }

    public User mapDTOToEntity(CtpuserDTO userDTO) {

        User user = mapper.map(userDTO, User.class);

        return user;

    }

}
