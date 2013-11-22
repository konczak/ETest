package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.UserEntity;

public interface IUserRepository {

    UserEntity getById(Integer id);

    UserEntity getByEmail(String email);

    List<UserEntity> findAll();

    UserEntity findByEmail(String email);

    void save(UserEntity user);
}
