package pl.konczak.etest.entity;

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

@Entity
@Table(name = "closedAnswers")
public class ClosedAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closedAnswersId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @NotBlank
    @Column(nullable = false,
            length = 1000)
    private String answer;
    @Column(nullable = false)
    private boolean correct;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "closedQuestionsId",
                nullable = false)
    private ClosedQuestion closedQuestion;

    public Integer getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public ClosedQuestion getClosedQuestion() {
        return closedQuestion;
    }
}
