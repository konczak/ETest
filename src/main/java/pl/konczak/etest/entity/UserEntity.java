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

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "users",
       uniqueConstraints =
        @UniqueConstraint(name = "users_email_unique",
                          columnNames = {"email"}))
public class UserEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @Email
    private String email;
    @NotBlank
    private String password;
    private boolean locked;
    private UserPersonalDataEntity userPersonalData;
    private Set<RoleEntity> roles = new HashSet<RoleEntity>();
    private Set<ClosedQuestionEntity> closedQuestions = new HashSet<ClosedQuestionEntity>();

    public UserEntity() {
    }

    public UserEntity(String email, String password, Set<RoleEntity> roles) {
        Validate.notEmpty(email);
        Validate.notEmpty(password);
        Validate.notNull(roles);
        Validate.notEmpty(roles);
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usersId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(unique = true,
            nullable = false,
            length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(nullable = false)
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    @OneToOne(fetch = FetchType.LAZY,
              mappedBy = "user",
              cascade = CascadeType.ALL)
    public UserPersonalDataEntity getUserPersonalData() {
        return userPersonalData;
    }

    public void setUserPersonalData(UserPersonalDataEntity userPersonalData) {
        this.userPersonalData = userPersonalData;
    }

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
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(
            Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "author")
    public Set<ClosedQuestionEntity> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(
            Set<ClosedQuestionEntity> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }
}