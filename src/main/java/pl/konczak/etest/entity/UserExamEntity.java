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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "userExams")
public class UserExamEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private ExamEntity exam;
    private UserEntity examined;
    private boolean checked;
    private Set<UserExamClosedQuestionEntity> closedQuestions = new HashSet<UserExamClosedQuestionEntity>();

    public class UserExamResult {

        private Integer resultingPoints;
        private Integer maximalPoints;

        protected UserExamResult(Integer resultingPoints, Integer maximalPoints) {
            this.resultingPoints = resultingPoints;
            this.maximalPoints = maximalPoints;
        }

        public Integer getResultingPoints() {
            return resultingPoints;
        }

        public Integer getMaximalPoints() {
            return maximalPoints;
        }
    }

    public UserExamEntity() {
    }

    public UserExamEntity(ExamEntity exam, UserEntity examined) {
        Validate.notNull(exam);
        Validate.notNull(examined);
        Validate.notNull(closedQuestions);
        this.exam = exam;
        this.examined = examined;
        this.checked = false;
    }

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

    @ManyToOne(fetch = FetchType.EAGER)
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void markAsChecked() {
        Validate.isFalse(checked, String.format("UserExam <%s> has been already checked", id));
        this.checked = true;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "userExam")
    @Cascade(CascadeType.ALL)
    @OrderBy("userExamClosedQuestionsId")
    public Set<UserExamClosedQuestionEntity> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(Set<UserExamClosedQuestionEntity> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }

    public void addClosedQuestion(ClosedQuestionEntity closedQuestion, Integer closedQuestionOrderNumber,
            Set<ClosedAnswerEntity> closedAnswers) {
        UserExamClosedQuestionEntity userExamClosedQuestionEntity =
                new UserExamClosedQuestionEntity(this, closedQuestion, closedQuestionOrderNumber);
        for (ClosedAnswerEntity closedAnswerEntity : closedAnswers) {
            userExamClosedQuestionEntity.addClosedAnswer(closedAnswerEntity);
        }
        this.closedQuestions.add(userExamClosedQuestionEntity);
    }

    public UserExamClosedQuestionEntity getUserExamClosedQuestionEntity(Integer userExamCLosedQuestionId) {
        UserExamClosedQuestionEntity userExamClosedQuestionEntity = null;
        for (UserExamClosedQuestionEntity element : closedQuestions) {
            if (element.getId().equals(userExamCLosedQuestionId)) {
                userExamClosedQuestionEntity = element;
            }
        }
        Validate.notNull(userExamClosedQuestionEntity, "Not found UserExamClosedQuestion in UserExam");

        return userExamClosedQuestionEntity;
    }

    @Transient
    public UserExamResult getResults() {
        Validate.isTrue(checked, String.format("UserExam <%s> is not checked yet and cannot get results", id));
        int resultPoints = 0;
        int maxPoints = 0;
        for (UserExamClosedQuestionEntity userExamClosedQuestionEntity : closedQuestions) {
            resultPoints += userExamClosedQuestionEntity.getPoints();
            maxPoints += userExamClosedQuestionEntity.getPointsMax();
        }
        return new UserExamResult(resultPoints, maxPoints);
    }
}
