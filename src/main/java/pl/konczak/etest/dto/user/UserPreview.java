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
    private Set<UserExamInternal> userExams =
            new HashSet<UserExamInternal>();

    public static class UserGroupInternal {

        private String title;

        public UserGroupInternal(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class UserExamInternal {

        private Integer id;
        private String subject;
        private LocalDateTime activeFrom;
        private LocalDateTime activeTo;

        public UserExamInternal(Integer id, String subject, LocalDateTime activeFrom, LocalDateTime activeTo) {
            this.id = id;
            this.subject = subject;
            this.activeFrom = activeFrom;
            this.activeTo = activeTo;
        }

        public Integer getId() {
            return id;
        }

        public String getSubject() {
            return subject;
        }

        public LocalDateTime getActiveFrom() {
            return activeFrom;
        }

        public LocalDateTime getActiveTo() {
            return activeTo;
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

    public Set<UserExamInternal> getUserExams() {
        return userExams;
    }

    public void setUserExams(Set<UserExamInternal> userExams) {
        this.userExams = userExams;
    }

    public void addUserExam(Integer id, String subject, LocalDateTime activeFrom, LocalDateTime activeTo) {
        this.userExams.add(new UserExamInternal(id, subject, activeFrom, activeTo));
    }
}
