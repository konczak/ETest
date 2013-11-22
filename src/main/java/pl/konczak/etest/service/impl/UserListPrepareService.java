package pl.konczak.etest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.user.UserListRow;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IUserRepository;
import pl.konczak.etest.service.IUserListPrepareService;

@Service
public class UserListPrepareService
        implements IUserListPrepareService {

    @Autowired
    private IUserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserListRow> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserListRow> userListRows = new ArrayList<UserListRow>();

        for (UserEntity userEntity : userEntities) {
            UserListRow userListRow = new UserListRow();
            userListRow.setId(userEntity.getId());
            userListRow.setEmail(userEntity.getEmail());
            userListRow.setLocked(userEntity.isLocked());
            UserPersonalDataEntity userPersonalDataEntity = userEntity.getUserPersonalData();
            userListRow.setFirstname(userPersonalDataEntity.getFirstname());
            userListRow.setLastname(userPersonalDataEntity.getLastname());

            userListRows.add(userListRow);
        }

        return userListRows;
    }
}
