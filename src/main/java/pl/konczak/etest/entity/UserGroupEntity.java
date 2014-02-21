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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "userGroups")
public class UserGroupEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userGroupsId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @NotBlank
    @Column(unique = true,
            nullable = false)
    private String title;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_userGroups",
               uniqueConstraints = {
        @UniqueConstraint(name = "users_userGroups_unique",
                          columnNames = {"usersId", "userGroupsId"})},
               joinColumns = {
        @JoinColumn(name = "userGroupsId",
                    nullable = false,
                    updatable = false)
    },
               inverseJoinColumns = {
        @JoinColumn(name = "usersId",
                    nullable = false,
                    updatable = false)
    })
    private Set<UserEntity> members = new HashSet<UserEntity>();
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "userGroup")
    private Set<ExamEntity> exams = new HashSet<ExamEntity>();

    protected UserGroupEntity() {
    }

    public UserGroupEntity(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<UserEntity> getMembers() {
        return members;
    }

    public void setMembers(Set<UserEntity> members) {
        this.members = members;
    }

    public void addUserToMembers(UserEntity user) {
        this.members.add(user);
    }

    public void removeUserFromMembers(UserEntity user) {
        this.members.remove(user);
    }

    public Set<ExamEntity> getExams() {
        return exams;
    }

    public void setExams(Set<ExamEntity> exams) {
        this.exams = exams;
    }
}
