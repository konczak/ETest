package pl.konczak.etest.bo.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IUserBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.RoleEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.error.SystemException;
import pl.konczak.etest.error.ValidationCode;
import pl.konczak.etest.repository.IRoleRepository;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class UserBO
        implements IUserBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserBO.class);
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUserGroupRepository userGroupRepository;

    @Transactional
    @Override
    public UserEntity register(String email, String password, String firstname, String lastname) {
        Validate.notEmpty(email);
        Validate.notEmpty(password);
        Validate.notEmpty(firstname);
        Validate.notEmpty(lastname);
        validateEmailIsFree(email);

        Set<RoleEntity> roles = new HashSet<RoleEntity>(roleRepository.findAll());

        String passwordEncrypted = passwordEncoder.encode(password);

        UserEntity user = new UserEntity(email, passwordEncrypted, roles);

        UserPersonalDataEntity userPersonalData = new UserPersonalDataEntity(user, firstname, lastname);

        user.setUserPersonalData(userPersonalData);

        userRepository.save(user);

        LOGGER.info("Registered User <{}> with email <{}>",
                user.getId(), user.getEmail());

        return user;
    }

    @Transactional
    @Override
    public UserEntity addUserToGroup(Integer userId, Integer userGroupId) {
        Validate.notNull(userId);
        Validate.notNull(userGroupId);

        UserEntity userEntity = userRepository.getById(userId);
        UserGroupEntity userGroupEntity =
                userGroupRepository.getById(userGroupId);

        userEntity.addGroup(userGroupEntity);

        userRepository.save(userEntity);

        LOGGER.info("Add User <{}> to UserGroup <{}>",
                userEntity.getId(), userGroupEntity.getId());
        return userEntity;
    }

    @Transactional
    @Override
    public UserEntity removeUserFromGroup(Integer userId, Integer userGroupId) {
        Validate.notNull(userId);
        Validate.notNull(userGroupId);

        UserEntity userEntity = userRepository.getById(userId);
        UserGroupEntity userGroupEntity = userGroupRepository.getById(userGroupId);

        Set<UserGroupEntity> groups = userEntity.getGroups();
        if (!groups.contains(userGroupEntity)) {
            throw new IllegalArgumentException(
                    String.format("User <%s> does not belongs to UserGroup <%s>",
                    userEntity.getId(), userGroupEntity.getId()));
        }
        userEntity.removeGroup(userGroupEntity);

        userRepository.save(userEntity);

        LOGGER.info("Remove User <{}> from UserGroup <{}>",
                userEntity.getId(), userGroupEntity.getId());
        return userEntity;
    }

    private void validateEmailIsFree(String email) {
        if (userRepository.findByEmail(email) != null) {
            throw new SystemException(ValidationCode.ALREADY_TAKEN)
                    .add("class", "User")
                    .add("field", "email")
                    .add("value", email);
        }
    }
}
