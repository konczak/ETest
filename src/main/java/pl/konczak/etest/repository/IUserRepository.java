package pl.konczak.etest.repository;

import pl.konczak.etest.entity.UserEntity;

public interface IUserRepository {

    UserEntity getById(Integer id);

    UserEntity getByEmail(String email);

    UserEntity findByEmail(String email);

    void save(UserEntity user);
}
