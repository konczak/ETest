package com.cybercom.telep.etest.facade.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cybercom.telep.etest.dto.UserRegistration;
import com.cybercom.telep.etest.entity.Role;
import com.cybercom.telep.etest.entity.User;
import com.cybercom.telep.etest.facade.IUserRegisterFacade;
import com.cybercom.telep.etest.repository.IRoleRepository;
import com.cybercom.telep.etest.repository.IUserRepository;

@Service
public class UserRegisterFacade
        implements IUserRegisterFacade {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(UserRegistration userRegistration) {
        Set<Role> roles = new HashSet<Role>(roleRepository.findAll());
        User user = new User(userRegistration.getEmail(), userRegistration.getPassword(), roles);

        userRepository.save(user);
        String password = user.getPassword();
//        user.setPassword(passwordEncoder.encode(password));

//        userRepository.save(user);
        return user;
    }
}
