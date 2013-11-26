package pl.konczak.etest.bo;

import pl.konczak.etest.entity.UserGroupEntity;

public interface IUserGroupBO {

    UserGroupEntity add(String title);

    UserGroupEntity addUserAsMember(Integer userId, Integer userGroupId);

    void remove(Integer id);
}
