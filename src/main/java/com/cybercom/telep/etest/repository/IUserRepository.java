package com.cybercom.telep.etest.repository;

import com.cybercom.telep.etest.entity.User;

public interface IUserRepository {

    User getByEmail(String email);

    User findByEmail(String email);

    void save(User user);
}
