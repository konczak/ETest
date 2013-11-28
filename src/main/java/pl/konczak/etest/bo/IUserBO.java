package pl.konczak.etest.bo;

import pl.konczak.etest.entity.UserEntity;

public interface IUserBO {

    UserEntity register(String email, String password,
            String firstname, String lastname);

    UserEntity addUserToGroup(Integer userId, Integer userGroupId);

    UserEntity removeUserFromGroup(Integer userId, Integer userGroupId);
}
