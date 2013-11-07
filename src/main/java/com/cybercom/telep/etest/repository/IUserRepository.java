package com.cybercom.telep.etest.repository;

import com.cybercom.telep.etest.entity.User;

public interface IUserRepository {

    User findByEmail(String email);

    void save(User user);
}
