package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.RoleEntity;

public interface IRoleRepository {

    List<RoleEntity> findAll();

    void save(RoleEntity role);
}
