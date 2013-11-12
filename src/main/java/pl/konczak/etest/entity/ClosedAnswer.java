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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "closedAnswers")
public class ClosedAnswer
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank
    private String answer;
    private Set<ClosedQuestionClosedAnswer> closedQuestionClosedAnswers =
            new HashSet<ClosedQuestionClosedAnswer>();

    public ClosedAnswer() {
    }

    public ClosedAnswer(String answer) {
        this.answer = answer;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closedAnswersId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(unique = true,
            nullable = false,
            length = 1000)
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "pk.closedAnswer")
    public Set<ClosedQuestionClosedAnswer> getClosedQuestionClosedAnswers() {
        return closedQuestionClosedAnswers;
    }

    public void setClosedQuestionClosedAnswers(
            Set<ClosedQuestionClosedAnswer> closedQuestionClosedAnswers) {
        this.closedQuestionClosedAnswers = closedQuestionClosedAnswers;
    }
}
