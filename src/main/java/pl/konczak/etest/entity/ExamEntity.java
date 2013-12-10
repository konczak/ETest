package pl.konczak.etest.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity
@Table(name = "exams")
public class ExamEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private UserGroupEntity userGroup;
    private UserEntity examiner;
    private LocalDateTime createdAt;
    private boolean generated;
    private Set<UserExamEntity> generatedExams = new HashSet<UserExamEntity>();

    public ExamEntity() {
    }

    public ExamEntity(UserGroupEntity userGroup, UserEntity examiner) {
        this.userGroup = userGroup;
        this.examiner = examiner;
        this.createdAt = LocalDateTime.now();
        this.generated = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "examsId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userGroupsId",
                nullable = false)
    public UserGroupEntity getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupEntity userGroup) {
        this.userGroup = userGroup;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId",
                nullable = false)
    public UserEntity getExaminer() {
        return examiner;
    }

    public void setExaminer(UserEntity examiner) {
        this.examiner = examiner;
    }

    @Column(nullable = false,
            updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isGenerated() {
        return generated;
    }

    public void setGenerated(boolean generated) {
        this.generated = generated;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "exam")
    public Set<UserExamEntity> getGeneratedExams() {
        return generatedExams;
    }

    public void setGeneratedExams(Set<UserExamEntity> generatedExams) {
        this.generatedExams = generatedExams;
    }
}
