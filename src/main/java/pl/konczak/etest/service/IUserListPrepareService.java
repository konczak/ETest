package pl.konczak.etest.service;

import java.util.List;

import pl.konczak.etest.dto.user.UserListRow;

public interface IUserListPrepareService {

    List<UserListRow> findAll();
}
