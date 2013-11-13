package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.Role;

public interface IRoleRepository {

    List<Role> findAll();

    void save(Role role);
}
