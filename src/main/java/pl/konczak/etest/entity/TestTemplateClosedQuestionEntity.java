package pl.konczak.etest.entity;

import java.io.Serializable;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import pl.konczak.etest.entity.id.TestTemplateClosedQuestionId;

@Entity
@Table(name = "testTemplates_closedQuestions")
@AssociationOverrides({
    @AssociationOverride(name = "testTemplates",
                         joinColumns =
            @JoinColumn(name = "testTemplatesId")),
    @AssociationOverride(name = "closedQuestions",
                         joinColumns =
            @JoinColumn(name = "usersId"))})
public class TestTemplateClosedQuestionEntity
        implements Serializable {

    private TestTemplateClosedQuestionId pk = new TestTemplateClosedQuestionId();
    private boolean mandatory;

    public TestTemplateClosedQuestionEntity() {
    }

    public TestTemplateClosedQuestionEntity(TestTemplateEntity testTemplate,
            ClosedQuestionEntity closedQuestion) {
        pk = new TestTemplateClosedQuestionId(testTemplate, closedQuestion);
        mandatory = false;
    }

    @EmbeddedId
    public TestTemplateClosedQuestionId getPk() {
        return pk;
    }

    public void setPk(TestTemplateClosedQuestionId pk) {
        this.pk = pk;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public void markAsMandatory() {
        this.mandatory = true;
    }

    public void markAsNotMandatory() {
        this.mandatory = false;
    }

    @Transient
    public TestTemplateEntity getTestTemplateEntity() {
        return getPk().getTestTemplate();
    }

    @Transient
    public ClosedQuestionEntity getClosedQuestionEntity() {
        return getPk().getClosedQuestion();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.pk != null ? this.pk.hashCode() : 0);
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
        final TestTemplateClosedQuestionEntity other = (TestTemplateClosedQuestionEntity) obj;
        if (this.pk != other.pk && (this.pk == null || !this.pk.equals(other.pk))) {
            return false;
        }
        return true;
    }
}
