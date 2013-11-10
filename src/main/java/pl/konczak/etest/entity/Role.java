package pl.konczak.etest.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
               joinColumns = {
        @JoinColumn(name = "role_id",
                    referencedColumnName = "id")},
               inverseJoinColumns = {
        @JoinColumn(name = "user_id",
                    referencedColumnName = "id")})
    private Set<User> userRoles;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getUserRoles() {
        return userRoles;
    }
}
