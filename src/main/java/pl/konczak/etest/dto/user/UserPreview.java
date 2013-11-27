package pl.konczak.etest.dto.user;

import java.util.HashSet;
import java.util.Set;
import org.joda.time.LocalDateTime;

public class UserPreview {

    private Integer id;
    private String email;
    private String firstname;
    private String lastname;
    private boolean locked;
    private LocalDateTime registeredAt;
    private Set<UserGroupInternal> userGroups =
            new HashSet<UserGroupInternal>();

    public static class UserGroupInternal {

        private String title;

        public UserGroupInternal(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public Set<UserGroupInternal> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<UserGroupInternal> userGroups) {
        this.userGroups = userGroups;
    }

    public void addUserGroup(String title) {
        this.userGroups.add(new UserGroupInternal(title));
    }
}
