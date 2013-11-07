package com.cybercom.telep.etest.repository;

import java.util.List;

import com.cybercom.telep.etest.entity.Role;

public interface IRoleRepository {

    List<Role> findAll();
}
