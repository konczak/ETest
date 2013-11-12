package pl.konczak.etest.entity;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import pl.konczak.etest.entity.id.ClosedQuestionClosedAnswerId;

@Entity
@Table(name = "closedQuestions_closedAnswers")
@AssociationOverrides({
    @AssociationOverride(name = "pk.closedQuestion",
                         joinColumns =
            @JoinColumn(name = "closedQuestionsId")),
    @AssociationOverride(name = "pk.closedAnswer",
                         joinColumns =
            @JoinColumn(name = "closedAnswersId"))})
public class ClosedQuestionClosedAnswer
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private ClosedQuestionClosedAnswerId pk = new ClosedQuestionClosedAnswerId();
    private boolean correct;

    public ClosedQuestionClosedAnswer() {
    }

    @EmbeddedId
    public ClosedQuestionClosedAnswerId getPk() {
        return pk;
    }

    public void setPk(ClosedQuestionClosedAnswerId pk) {
        this.pk = pk;
    }

    @Transient
    public ClosedQuestion getClosedQuestion() {
        return getPk().getClosedQuestion();
    }

    public void setClosedQuestion(ClosedQuestion closedQuestion) {
        getPk().setClosedQuestion(closedQuestion);
    }

    @Transient
    public ClosedAnswer getClosedAnswer() {
        return getPk().getClosedAnswer();
    }

    public void setClosedAnswer(ClosedAnswer closedAnswer) {
        getPk().setClosedAnswer(closedAnswer);
    }

    @Column(nullable = false,
            updatable = false)
    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + (this.pk != null ? this.pk.hashCode() : 0);
        hash = 31 * hash + (this.correct ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ClosedQuestionClosedAnswer other = (ClosedQuestionClosedAnswer) obj;
        if (this.pk != other.pk && (this.pk == null || !this.pk.equals(other.pk))) {
            return false;
        }
        if (this.correct != other.correct) {
            return false;
        }
        return true;
    }
}
