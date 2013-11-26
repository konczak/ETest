package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.UserGroupEntity;

public interface IUserGroupRepository {

    UserGroupEntity getById(Integer id);

    UserGroupEntity findByTitle(String title);

    List<UserGroupEntity> findAll();

    void delete(UserGroupEntity userGroup);

    void save(UserGroupEntity userGroup);
}
