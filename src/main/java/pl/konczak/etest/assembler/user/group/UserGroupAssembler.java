package pl.konczak.etest.assembler.user.group;

import org.springframework.stereotype.Component;

import pl.konczak.etest.dto.user.group.UserGroupEdit;
import pl.konczak.etest.entity.UserGroupEntity;

@Component
public class UserGroupAssembler {

    public UserGroupEdit toEdit(UserGroupEntity userGroupEntity) {
        return new UserGroupEdit(userGroupEntity.getId(),
                userGroupEntity.getTitle());
    }
}
