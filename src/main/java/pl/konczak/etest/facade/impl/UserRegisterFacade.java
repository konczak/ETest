package pl.konczak.etest.facade.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.UserRegistration;
import pl.konczak.etest.entity.Role;
import pl.konczak.etest.entity.User;
import pl.konczak.etest.facade.IUserRegisterFacade;
import pl.konczak.etest.repository.IRoleRepository;
import pl.konczak.etest.repository.IUserRepository;

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
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return user;
    }
}
