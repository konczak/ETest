package pl.konczak.etest.bo;

import pl.konczak.etest.entity.UserEntity;

public interface IUserBO {

    UserEntity register(String email, String password,
            String firstname, String lastname);
}
