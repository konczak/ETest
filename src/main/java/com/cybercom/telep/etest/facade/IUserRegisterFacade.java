package com.cybercom.telep.etest.facade;

import com.cybercom.telep.etest.dto.UserRegistration;
import com.cybercom.telep.etest.entity.User;

public interface IUserRegisterFacade {

    User register(UserRegistration userRegistration);
}
