package pl.konczak.etest.entity;

import java.io.Serializable;
import java.util.HashSet;
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
public class RoleEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rolesId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @NotBlank
    @Column(unique = true,
            nullable = false)
    private String name;
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
    private Set<UserEntity> userRoles = new HashSet<UserEntity>();

    protected RoleEntity() {
    }

    public RoleEntity(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserEntity> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(
            Set<UserEntity> userRoles) {
        this.userRoles = userRoles;
    }
}
