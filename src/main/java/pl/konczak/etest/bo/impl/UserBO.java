package pl.konczak.etest.bo.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IUserBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.RoleEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IRoleRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class UserBO
        implements IUserBO {

    private static final Logger LOGGER = Logger.getLogger(UserBO.class);
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserEntity register(String email, String password, String firstname, String lastname) {
        Validate.notEmpty(email);
        Validate.notEmpty(password);
        Validate.notEmpty(firstname);
        Validate.notEmpty(lastname);

        if (userRepository.findByEmail(email) != null) {
            throw new IllegalArgumentException(String.format("User with email <%s> already exists", email));
        }

        Set<RoleEntity> roles = new HashSet<RoleEntity>(roleRepository.findAll());

        String passwordEncrypted = passwordEncoder.encode(password);

        UserEntity user = new UserEntity(email, passwordEncrypted, roles);

        UserPersonalDataEntity userPersonalData = new UserPersonalDataEntity(user, firstname, lastname);

        user.setUserPersonalData(userPersonalData);

        userRepository.save(user);

        LOGGER.info(String.format("Registered User <%s> with email <%s>",
                user.getId(), user.getEmail()));

        return user;
    }
}
