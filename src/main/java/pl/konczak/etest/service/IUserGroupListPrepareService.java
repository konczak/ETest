package pl.konczak.etest.service;

import java.util.List;

import pl.konczak.etest.dto.user.group.UserGroupListRow;

public interface IUserGroupListPrepareService {

    List<UserGroupListRow> findAll();
}
