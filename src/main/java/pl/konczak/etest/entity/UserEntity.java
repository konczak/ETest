package pl.konczak.etest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.LocalDateTime;

import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "users",
       uniqueConstraints =
        @UniqueConstraint(name = "users_email_unique",
                          columnNames = {"email"}))
public class UserEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usersId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @Email
    @Column(unique = true,
            nullable = false,
            length = 50)
    private String email;
    @NotBlank
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean locked;
    @Column(nullable = false,
            updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime registeredAt;
    @OneToOne(fetch = FetchType.LAZY,
              mappedBy = "user",
              cascade = CascadeType.ALL)
    private UserPersonalDataEntity userPersonalData;
    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
               uniqueConstraints = {
        @UniqueConstraint(name = "users_roles_unique",
                          columnNames = {"usersId", "rolesId"})
    },
               joinColumns = {
        @JoinColumn(name = "usersId",
                    nullable = false,
                    updatable = false)
    },
               inverseJoinColumns = {
        @JoinColumn(name = "rolesId",
                    nullable = false,
                    updatable = false)
    })
    private Set<RoleEntity> roles = new HashSet<RoleEntity>();
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "author")
    private Set<ClosedQuestionEntity> closedQuestions = new HashSet<ClosedQuestionEntity>();
    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinTable(name = "users_userGroups",
               uniqueConstraints = {
        @UniqueConstraint(name = "users_userGroups_unique",
                          columnNames = {"usersId", "userGroupsId"})},
               joinColumns = {
        @JoinColumn(name = "usersId",
                    nullable = false,
                    updatable = false)
    },
               inverseJoinColumns = {
        @JoinColumn(name = "userGroupsId",
                    nullable = false,
                    updatable = false)
    })
    private Set<UserGroupEntity> groups = new HashSet<UserGroupEntity>();
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "examined")
    private Set<UserExamEntity> exams = new HashSet<UserExamEntity>();
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "examiner")
    private Set<ExamEntity> controlledExams = new HashSet<ExamEntity>();

    protected UserEntity() {
    }

    public UserEntity(String email, String password, Set<RoleEntity> roles) {
        Validate.notEmpty(email);
        Validate.notEmpty(password);
        Validate.notNull(roles);
        Validate.notEmpty(roles);
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.registeredAt = LocalDateTime.now();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public UserPersonalDataEntity getUserPersonalData() {
        return userPersonalData;
    }

    public void setUserPersonalData(UserPersonalDataEntity userPersonalData) {
        this.userPersonalData = userPersonalData;
    }

    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    public Set<ClosedQuestionEntity> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(Set<ClosedQuestionEntity> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }

    public Set<UserGroupEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<UserGroupEntity> groups) {
        this.groups = groups;
    }

    public void addGroup(UserGroupEntity userGroup) {
        this.groups.add(userGroup);
    }

    public void removeGroup(UserGroupEntity userGroup) {
        this.groups.remove(userGroup);
    }

    public Set<UserExamEntity> getExams() {
        return exams;
    }

    public void setExams(Set<UserExamEntity> exams) {
        this.exams = exams;
    }

    public Set<ExamEntity> getControlledExams() {
        return controlledExams;
    }

    public void setControlledExams(Set<ExamEntity> controlledExams) {
        this.controlledExams = controlledExams;
    }
}
