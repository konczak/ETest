package pl.konczak.etest.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "roles",
       uniqueConstraints =
        @UniqueConstraint(name = "roles_name_unique",
                          columnNames = {"name"}))
public class Role
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank
    private String name;
    private Set<User> userRoles;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolesId",
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
            nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
               uniqueConstraints = {
        @UniqueConstraint(name = "users_roles_unique",
                          columnNames = {"usersId", "rolesId"})},
               joinColumns = {
        @JoinColumn(name = "rolesId",
                    nullable = false,
                    updatable = false)},
               inverseJoinColumns = {
        @JoinColumn(name = "usersId",
                    nullable = false,
                    updatable = false)})
    public Set<User> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(
            Set<User> userRoles) {
        this.userRoles = userRoles;
    }
}
