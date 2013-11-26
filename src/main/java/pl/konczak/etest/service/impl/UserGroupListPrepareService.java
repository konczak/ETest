package pl.konczak.etest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.user.group.UserGroupListRow;
import pl.konczak.etest.entity.UserGroupEntity;
import pl.konczak.etest.repository.IUserGroupRepository;
import pl.konczak.etest.service.IUserGroupListPrepareService;

@Service
public class UserGroupListPrepareService
        implements IUserGroupListPrepareService {

    @Autowired
    private IUserGroupRepository userGroupRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserGroupListRow> findAll() {
        List<UserGroupEntity> userGroupEntities = userGroupRepository.findAll();
        List<UserGroupListRow> userGroupListRows = new ArrayList<UserGroupListRow>();

        for (UserGroupEntity userGroupEntity : userGroupEntities) {
            Integer membersCount = userGroupEntity.getMembers().size();

            UserGroupListRow userGroupListRow = new UserGroupListRow(userGroupEntity.getId(),
                    userGroupEntity.getTitle(),
                    membersCount);

            userGroupListRows.add(userGroupListRow);
        }

        return userGroupListRows;
    }
}
