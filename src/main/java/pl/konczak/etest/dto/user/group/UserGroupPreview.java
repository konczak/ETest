package pl.konczak.etest.dto.user.group;

import java.util.HashSet;
import java.util.Set;

public class UserGroupPreview {

    private Integer id;
    private String title;
    private Set<MemberInternal> members = new HashSet<MemberInternal>();

    public static class MemberInternal {

        private Integer id;
        private String firstname;
        private String lastname;

        public MemberInternal(Integer id, String firstname, String lastname) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public Integer getId() {
            return id;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }
    }

    public UserGroupPreview(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<MemberInternal> getMembers() {
        return members;
    }

    public void addMember(Integer id, String firstname, String lastname) {
        members.add(new MemberInternal(id, firstname, lastname));
    }
}
