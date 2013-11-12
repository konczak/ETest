package pl.konczak.etest.entity.id;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import pl.konczak.etest.entity.ClosedAnswer;
import pl.konczak.etest.entity.ClosedQuestion;

@Embeddable
public class ClosedQuestionClosedAnswerId
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private ClosedQuestion closedQuestion;
    private ClosedAnswer closedAnswer;

    @ManyToOne
    public ClosedQuestion getClosedQuestion() {
        return closedQuestion;
    }

    public void setClosedQuestion(ClosedQuestion closedQuestion) {
        this.closedQuestion = closedQuestion;
    }

    @ManyToOne
    public ClosedAnswer getClosedAnswer() {
        return closedAnswer;
    }

    public void setClosedAnswer(ClosedAnswer closedAnswer) {
        this.closedAnswer = closedAnswer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.closedQuestion != null ? this.closedQuestion.hashCode() : 0);
        hash = 37 * hash + (this.closedAnswer != null ? this.closedAnswer.hashCode() : 0);
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
        final ClosedQuestionClosedAnswerId other = (ClosedQuestionClosedAnswerId) obj;
        if (this.closedQuestion != other.closedQuestion && (this.closedQuestion == null || !this.closedQuestion.equals(
                other.closedQuestion))) {
            return false;
        }
        if (this.closedAnswer != other.closedAnswer && (this.closedAnswer == null || !this.closedAnswer.equals(
                other.closedAnswer))) {
            return false;
        }
        return true;
    }
}
