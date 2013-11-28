package pl.konczak.etest.bo;

import pl.konczak.etest.entity.UserGroupEntity;

public interface IUserGroupBO {

    UserGroupEntity add(String title);

    UserGroupEntity addUserAsMember(Integer userId, Integer userGroupId);

    UserGroupEntity removeUserFromMembers(Integer userId, Integer userGroupId);

    UserGroupEntity changeTitle(Integer id, String title);

    void remove(Integer id);
}
