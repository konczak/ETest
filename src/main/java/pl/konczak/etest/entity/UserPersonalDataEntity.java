package pl.konczak.etest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "userPersonalData")
public class UserPersonalDataEntity {

    @GenericGenerator(name = "generator",
                      strategy = "foreign",
                      parameters =
            @Parameter(name = "property",
                       value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "usersId",
            unique = true,
            nullable = false)
    private Integer usersId;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserEntity user;
    @NotBlank
    @Column(nullable = false,
            length = 50)
    private String firstname;
    @NotBlank
    @Column(nullable = false,
            length = 50)
    private String lastname;

    protected UserPersonalDataEntity() {
    }

    public UserPersonalDataEntity(UserEntity user, String firstname, String lastname) {
        Validate.notNull(user);
        Validate.notEmpty(firstname);
        Validate.notEmpty(lastname);
        this.user = user;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
}
