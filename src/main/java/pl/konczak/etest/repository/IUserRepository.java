package pl.konczak.etest.repository;

import pl.konczak.etest.entity.User;

public interface IUserRepository {

    User getById(Integer id);

    User getByEmail(String email);

    User findByEmail(String email);

    void save(User user);
}
