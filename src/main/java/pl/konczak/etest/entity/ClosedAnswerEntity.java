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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "closedAnswers")
public class ClosedAnswerEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closedAnswersId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @NotBlank
    @Column(unique = false,
            nullable = false,
            length = 1000)
    private String answer;
    @Column(nullable = false)
    private boolean correct;
    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    @JoinColumn(name = "imagesId",
                unique = true,
                nullable = true)
    private ImageEntity image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "closedQuestionsId",
                nullable = false)
    private ClosedQuestionEntity closedQuestion;
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "closedAnswer")
    private Set<UserExamClosedAnswerEntity> usages = new HashSet<UserExamClosedAnswerEntity>();

    protected ClosedAnswerEntity() {
    }

    public ClosedAnswerEntity(ClosedQuestionEntity closedQuestion, String answer, boolean correct) {
        Validate.notNull(closedQuestion);
        Validate.notEmpty(answer);
        this.answer = answer;
        this.correct = correct;
        this.closedQuestion = closedQuestion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public ClosedQuestionEntity getClosedQuestion() {
        return closedQuestion;
    }

    public void setClosedQuestion(ClosedQuestionEntity closedQuestion) {
        this.closedQuestion = closedQuestion;
    }

    public Set<UserExamClosedAnswerEntity> getUsages() {
        return usages;
    }

    public void setUsages(Set<UserExamClosedAnswerEntity> usages) {
        this.usages = usages;
    }
}
