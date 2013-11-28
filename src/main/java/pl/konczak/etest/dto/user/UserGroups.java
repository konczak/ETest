package pl.konczak.etest.dto.user;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import pl.konczak.etest.core.Validate;

public class UserGroups {

    private Integer id;
    private String email;
    private String firstname;
    private String lastname;
    private Map<Integer, GroupInternal> groups =
            new HashMap<Integer, GroupInternal>();

    public static class GroupInternal {

        private String title;
        private boolean alreadyMember;

        public GroupInternal(String title) {
            this.title = title;
            this.alreadyMember = false;
        }

        public String getTitle() {
            return title;
        }

        public boolean isAlreadyMember() {
            return alreadyMember;
        }

        public void markAsAlreadyMember() {
            this.alreadyMember = true;
        }
    }

    public UserGroups(Integer userId, String email, String firstname, String lastname) {
        this.id = userId;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Map<Integer, GroupInternal> getGroups() {
        return groups;
    }

    public void addGroup(Integer userGroupId, String title) {
        this.groups.put(userGroupId, new GroupInternal(title));
    }

    public void markGroupAsAlreadyMember(Integer userGroupId) {
        Validate.isTrue(groups.containsKey(userGroupId), String.format(
                "Group <%s> is not in map yet"
                + " so cannot mark as already member", id));
        GroupInternal groupInternal = groups.get(userGroupId);

        groupInternal.markAsAlreadyMember();
    }

    public Integer getCountOfGroupsBelongTo() {
        Integer countOfGroupsBelongTo = 0;
        for (Entry<Integer, GroupInternal> entry : groups.entrySet()) {
            if (entry.getValue().isAlreadyMember()) {
                countOfGroupsBelongTo++;
            }
        }
        return countOfGroupsBelongTo;
    }
}
