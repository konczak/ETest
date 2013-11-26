package pl.konczak.etest.bo.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IUserGroupBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class UserGroupBO
        implements IUserGroupBO {

    private static final Logger LOGGER = Logger.getLogger(UserGroupBO.class);
    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    @Override
    public UserGroupEntity add(String title) {
        Validate.notEmpty(title);
        if (userGroupRepository.findByTitle(title) != null) {
            throw new IllegalArgumentException(
                    String.format("UserGroup with title <%s> already exists", title));
        }
        UserGroupEntity userGroupEntity = new UserGroupEntity(title);

        userGroupRepository.save(userGroupEntity);

        LOGGER.info(String.format("Add UserGroup <%s>", userGroupEntity.getId()));
        return userGroupEntity;
    }

    @Transactional
    @Override
    public UserGroupEntity addUserAsMember(Integer userId, Integer userGroupId) {
        Validate.notNull(userId);
        Validate.notNull(userGroupId);

        UserEntity userEntity = userRepository.getById(userId);
        UserGroupEntity userGroupEntity = userGroupRepository.getById(userGroupId);

        userGroupEntity.addUserToMembers(userEntity);

        userGroupRepository.save(userGroupEntity);

        LOGGER.info(String.format("Add user <%s> to UserGroup <%s>",
                userEntity.getId(), userGroupEntity.getId()));
        return userGroupEntity;
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        Validate.notNull(id);

        UserGroupEntity userGroupEntity = userGroupRepository.getById(id);

        userGroupRepository.delete(userGroupEntity);
        LOGGER.info(String.format("Removed UserGroup <%s>", userGroupEntity.getId()));
    }
}
