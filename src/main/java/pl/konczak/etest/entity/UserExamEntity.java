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
@Table(name = "userExams")
public class UserExamEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private ExamEntity exam;
    private UserEntity examined;
    private LocalDateTime activeFrom;
    private LocalDateTime activeTo;
    private Set<UserExamClosedQuestionEntity> closedQuestions = new HashSet<UserExamClosedQuestionEntity>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userExamsId",
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
    @JoinColumn(name = "examsId",
                nullable = false)
    public ExamEntity getExam() {
        return exam;
    }

    public void setExam(ExamEntity exam) {
        this.exam = exam;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId",
                nullable = false)
    public UserEntity getExamined() {
        return examined;
    }

    public void setExamined(UserEntity examined) {
        this.examined = examined;
    }

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(LocalDateTime activeFrom) {
        this.activeFrom = activeFrom;
    }

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    public LocalDateTime getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(LocalDateTime activeTo) {
        this.activeTo = activeTo;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "userExam")
    public Set<UserExamClosedQuestionEntity> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(Set<UserExamClosedQuestionEntity> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }
}