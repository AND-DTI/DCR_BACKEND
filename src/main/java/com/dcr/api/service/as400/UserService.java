package com.dcr.api.service.as400;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.dcr.api.configs.security.Auditoria;
import com.dcr.api.model.as400.Accuser;
import com.dcr.api.model.as400.User_Role;
import com.dcr.api.repository.as400.RoleRepository;
import com.dcr.api.repository.as400.UserRepository;
import com.dcr.api.repository.as400.UserRoleRepository;
import com.dcr.api.utils.Auxiliar;

@Service
public class UserService {

    final UserRepository userRepository;
    
    @Autowired
    RoleService roleService;

    @Autowired
    private ModelMapper mapper;

    public UserService(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;

    }

    public List<Accuser> listarTodos() {

        return userRepository.findAll();

    }

    public void updatePassword(String username, String password) {

      userRepository.setPassword(username, password);

    }
    
    public List<Accuser> listByUsername(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return userRepository.findDistinctByUsername(username);

    }

    public List<Accuser> listByEmail(String email) {

        return userRepository.findDistinctByEmail(email);

    }

    public Optional<Accuser> getByUsernameOptional(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return userRepository.findByUsername(username);

    }

    public Accuser getByUsername(String username) {

        try {
            username = username.toUpperCase();
        } catch (Exception e) {
        }

        return (Accuser) userRepository.findDistinctByUsername(username);

    }

    public Integer getMatricula(String username) {

        Integer matricula = 0;

        try {
            username = Auxiliar.trimNull(username).toUpperCase();
        } catch (Exception e) {
        }

        Optional<Accuser> optUser = userRepository.findByUsername(username);

        if (!optUser.isEmpty()) {
            Accuser user = optUser.get();
            matricula = user.getUserid();
        }

        return matricula;

    }

//    public User saveUser(CtpuserDTO userDTO) {
//
//        Auditoria audit = new Auditoria();
//
//        User user = mapDTOToEntity(userDTO);
//        user.setUsername(user.getUsername().toUpperCase());
//        user.setItauddt(audit.getData_audit());
//        user.setItaudhr(audit.getHora_audit());
//
//        if (user.getToken().isEmpty()) {
//            user.setToken("");
//        }
//
//        User userNew = userRepository.save(user);
//
//        return userNew;
//
//    }

    public Accuser save(Accuser user) {

        return userRepository.save(user);

    }

    public Accuser save0(Accuser user) {

        return userRepository.save(user);

    }

//    public User mapDTOToEntity(CtpuserDTO userDTO) {
//
//        User user = mapper.map(userDTO, User.class);
//
//        return user;
//
//    }

}
