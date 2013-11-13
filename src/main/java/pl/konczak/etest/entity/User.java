package pl.konczak.etest.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users",
       uniqueConstraints =
        @UniqueConstraint(name = "users_email_unique",
                          columnNames = {"email"}))
public class User
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @Email
    private String email;
    @NotBlank
    private String password;
    private boolean locked;
    private Set<Role> roles;
    private Set<ClosedQuestion> closedQuestions;

    public User() {
    }

    public User(String email, String password, Set<Role> roles) {
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
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(
            Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "author")
    public Set<ClosedQuestion> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(
            Set<ClosedQuestion> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }
}
