package pl.konczak.etest.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "userExamClosedAnswers")
public class UserExamClosedAnswerEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private boolean markedByUser;
    private ClosedAnswerEntity closedAnswer;
    private UserExamClosedQuestionEntity closedQuestion;

    public UserExamClosedAnswerEntity() {
    }

    public UserExamClosedAnswerEntity(ClosedAnswerEntity closedAnswer) {
        this.closedAnswer = closedAnswer;
        this.markedByUser = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userExamClosedAnswersId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false,
            updatable = false)
    public boolean isMarkedByUser() {
        return markedByUser;
    }

    public void setMarkedByUser(boolean markedByUser) {
        this.markedByUser = markedByUser;
    }

    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    public ClosedAnswerEntity getClosedAnswer() {
        return closedAnswer;
    }

    public void setClosedAnswer(ClosedAnswerEntity closedAnswer) {
        this.closedAnswer = closedAnswer;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userExamClosedQuestionsId",
                nullable = false)
    public UserExamClosedQuestionEntity getClosedQuestion() {
        return closedQuestion;
    }

    public void setClosedQuestion(UserExamClosedQuestionEntity closedQuestion) {
        this.closedQuestion = closedQuestion;
    }
}
