package pl.konczak.etest.entity.id;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.TestTemplateEntity;

@Embeddable
public class TestTemplateClosedQuestionId
        implements Serializable {

    private TestTemplateEntity testTemplate;
    private ClosedQuestionEntity closedQuestion;

    public TestTemplateClosedQuestionId() {
    }

    public TestTemplateClosedQuestionId(TestTemplateEntity testTemplate,
            ClosedQuestionEntity closedQuestion) {
        this.testTemplate = testTemplate;
        this.closedQuestion = closedQuestion;
    }

    @ManyToOne
    public TestTemplateEntity getTestTemplate() {
        return testTemplate;
    }

    public void setTestTemplate(TestTemplateEntity testTemplate) {
        this.testTemplate = testTemplate;
    }

    @ManyToOne
    public ClosedQuestionEntity getClosedQuestion() {
        return closedQuestion;
    }

    public void setClosedQuestion(ClosedQuestionEntity closedQuestion) {
        this.closedQuestion = closedQuestion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.testTemplate != null ? this.testTemplate.hashCode() : 0);
        hash = 89 * hash + (this.closedQuestion != null ? this.closedQuestion.hashCode() : 0);
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
        final TestTemplateClosedQuestionId other = (TestTemplateClosedQuestionId) obj;
        if (this.testTemplate != other.testTemplate && (this.testTemplate == null || !this.testTemplate.equals(other.testTemplate))) {
            return false;
        }
        if (this.closedQuestion != other.closedQuestion && (this.closedQuestion == null || !this.closedQuestion.equals(other.closedQuestion))) {
            return false;
        }
        return true;
    }
}
