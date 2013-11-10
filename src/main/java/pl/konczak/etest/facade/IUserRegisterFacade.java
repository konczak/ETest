package pl.konczak.etest.facade;

import pl.konczak.etest.dto.UserRegistration;
import pl.konczak.etest.entity.User;

public interface IUserRegisterFacade {

    User register(UserRegistration userRegistration);
}
