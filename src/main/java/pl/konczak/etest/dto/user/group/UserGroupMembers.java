package pl.konczak.etest.dto.user.group;

import java.util.HashMap;
import java.util.Map;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.dto.user.UserGroups;

public class UserGroupMembers {

    private Integer id;
    private String title;
    private Map<Integer, MemberInternal> members =
            new HashMap<Integer, MemberInternal>();

    public static class MemberInternal {

        private boolean alreadyIn;
        private String firstname;
        private String lastname;

        public MemberInternal(String firstname, String lastname) {
            this.alreadyIn = false;
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public boolean isAlreadyIn() {
            return alreadyIn;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void markAsAlreadyIn() {
            this.alreadyIn = true;
        }
    }

    public UserGroupMembers(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Map<Integer, MemberInternal> getMembers() {
        return members;
    }

    public void addMember(Integer userId, String firstname, String lastname) {
        members.put(userId, new MemberInternal(firstname, lastname));
    }

    public void markMemberAsAlreadyIn(Integer userId) {
        Validate.isTrue(members.containsKey(userId), String.format(
                "Member <%s> is not in map yet"
                + " so cannot mark as already in group", userId));
        MemberInternal memberInternal = members.get(userId);
        memberInternal.markAsAlreadyIn();
    }

    public Integer getCountOfMembers() {
        Integer countOfMembers = 0;
        for (Map.Entry<Integer, MemberInternal> entry : members.entrySet()) {
            if (entry.getValue().isAlreadyIn()) {
                countOfMembers++;
            }
        }
        return countOfMembers;
    }
}
