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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "userExamClosedQuestions")
public class UserExamClosedQuestionEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Integer points;
    private Integer pointsMax;
    private UserExamEntity userExam;
    private ClosedQuestionEntity closedQuestion;
    private Set<UserExamClosedAnswerEntity> closedAnswers = new HashSet<UserExamClosedAnswerEntity>();

    public UserExamClosedQuestionEntity() {
    }

    public UserExamClosedQuestionEntity(UserExamEntity userExam, ClosedQuestionEntity closedQuestion) {
        Validate.notNull(userExam);
        Validate.notNull(closedQuestion);
        this.userExam = userExam;
        this.closedQuestion = closedQuestion;
        this.points = 0;
        this.pointsMax = 1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userExamClosedQuestionsId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getPointsMax() {
        return pointsMax;
    }

    public void setPointsMax(Integer pointsMax) {
        this.pointsMax = pointsMax;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closedQuestionsId",
                nullable = false)
    public ClosedQuestionEntity getClosedQuestion() {
        return closedQuestion;
    }

    public void setClosedQuestion(ClosedQuestionEntity closedQuestion) {
        this.closedQuestion = closedQuestion;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "closedQuestion")
    @Cascade(CascadeType.ALL)
    public Set<UserExamClosedAnswerEntity> getClosedAnswers() {
        return closedAnswers;
    }

    public void setClosedAnswers(Set<UserExamClosedAnswerEntity> closedAnswers) {
        this.closedAnswers = closedAnswers;
    }
    
    public void addClosedAnswer(ClosedAnswerEntity closedAnswer) {
        this.closedAnswers.add(new UserExamClosedAnswerEntity(closedAnswer, this));
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userExamsId",
                nullable = false)
    public UserExamEntity getUserExam() {
        return userExam;
    }

    public void setUserExam(UserExamEntity userExam) {
        this.userExam = userExam;
    }
}
