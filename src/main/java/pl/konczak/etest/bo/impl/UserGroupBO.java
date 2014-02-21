package pl.konczak.etest.bo.impl;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.IUserGroupBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.error.SystemException;
import pl.konczak.etest.error.ValidationCode;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.repository.IUserRepository;

@Service
public class UserGroupBO
        implements IUserGroupBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestTemplateBO.class);
    @Autowired
    private IUserGroupRepository userGroupRepository;
    @Autowired
    private IUserRepository userRepository;

    @Transactional
    @Override
    public UserGroupEntity add(String title) {
        Validate.notEmpty(title);
        validateTitleIsFree(title);

        UserGroupEntity userGroupEntity = new UserGroupEntity(title);

        userGroupRepository.save(userGroupEntity);

        LOGGER.info("Add UserGroup <{}>", userGroupEntity.getId());
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

        LOGGER.info("Add User <{}> to UserGroup <{}>",
                userEntity.getId(), userGroupEntity.getId());
        return userGroupEntity;
    }

    @Transactional
    @Override
    public UserGroupEntity removeUserFromMembers(Integer userId, Integer userGroupId) {
        Validate.notNull(userId);
        Validate.notNull(userGroupId);

        UserEntity userEntity = userRepository.getById(userId);
        UserGroupEntity userGroupEntity = userGroupRepository.getById(userGroupId);

        Set<UserEntity> members = userGroupEntity.getMembers();
        if (!members.contains(userEntity)) {
            throw new IllegalArgumentException(
                    String.format("UserGroup <%s> does not contains User <%s>",
                    userGroupEntity.getId(), userEntity.getId()));
        }
        userGroupEntity.removeUserFromMembers(userEntity);

        userGroupRepository.save(userGroupEntity);

        LOGGER.info("Remove User <{}> from UserGroup <{}>",
                userEntity.getId(), userGroupEntity.getId());
        return userGroupEntity;
    }

    @Transactional
    @Override
    public UserGroupEntity changeTitle(Integer id, String title) {
        Validate.notNull(id);
        Validate.notEmpty(title);
        validateTitleIsFree(id, title);

        UserGroupEntity userGroupEntity = userGroupRepository.getById(id);
        String oldTitle = userGroupEntity.getTitle();

        userGroupEntity.setTitle(title);

        userGroupRepository.save(userGroupEntity);

        LOGGER.info("Changed title of UserGroup <{}> from <{}> to <{}>",
                userGroupEntity.getId(), oldTitle, userGroupEntity.getTitle());

        return userGroupEntity;
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        Validate.notNull(id);

        UserGroupEntity userGroupEntity = userGroupRepository.getById(id);

        userGroupRepository.delete(userGroupEntity);
        LOGGER.info("Removed UserGroup <{}>", userGroupEntity.getId());
    }

    private void validateTitleIsFree(String title) {
        if (userGroupRepository.findByTitle(title) != null) {
            throw new SystemException(ValidationCode.ALREADY_TAKEN)
                    .add("class", "UserGroup")
                    .add("field", "title")
                    .add("value", title);
        }
    }

    private void validateTitleIsFree(Integer id, String title) {
        UserGroupEntity userGroupEntity = userGroupRepository.findByTitle(title);
        if (userGroupEntity != null
                && !userGroupEntity.getId().equals(id)) {
            throw new SystemException(ValidationCode.ALREADY_TAKEN)
                    .add("class", "UserGroup")
                    .add("field", "title")
                    .add("value", "title");
        }
    }
}
