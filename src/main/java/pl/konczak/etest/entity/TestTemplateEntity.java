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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "testTemplates")
public class TestTemplateEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testTemplatesId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @Column(unique = true,
            nullable = false,
            length = 50)
    private String subject;
    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    private UserEntity author;
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "pk.testTemplate",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @OrderBy("pk.closedQuestion.id")
    private Set<TestTemplateClosedQuestionEntity> closedQuestions =
            new HashSet<TestTemplateClosedQuestionEntity>();
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "testTemplate")
    private Set<ExamEntity> exams = new HashSet<ExamEntity>();

    protected TestTemplateEntity() {
    }

    public TestTemplateEntity(String subject, UserEntity author) {
        this.subject = subject;
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public Set<TestTemplateClosedQuestionEntity> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(Set<TestTemplateClosedQuestionEntity> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }

    public void addClosedQuestion(ClosedQuestionEntity closedQuestionEntity) {
        TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity =
                new TestTemplateClosedQuestionEntity(this, closedQuestionEntity);
        closedQuestions.add(testTemplateClosedQuestionEntity);
    }

    public TestTemplateClosedQuestionEntity getClosedQuestion(Integer closedQuestionId) {
        TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity = null;
        for (TestTemplateClosedQuestionEntity entity : closedQuestions) {
            if (entity.getClosedQuestionEntity().getId().equals(closedQuestionId)) {
                testTemplateClosedQuestionEntity = entity;
                break;
            }
        }
        return testTemplateClosedQuestionEntity;
    }

    public void markClosedQuestionAsMandatory(Integer closedQuestionId) {
        TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity = null;
        for (TestTemplateClosedQuestionEntity entity : closedQuestions) {
            if (entity.getClosedQuestionEntity().getId().equals(closedQuestionId)) {
                testTemplateClosedQuestionEntity = entity;
                break;
            }
        }
        Validate.notNull(testTemplateClosedQuestionEntity, String.format("Specified ClosedQuestion <%s>"
                + " is not signed to this TestTemplate and cannot be marked as mandatory", closedQuestionId));
        testTemplateClosedQuestionEntity.markAsMandatory();
    }

    public void markClosedQuestionAsNotMandatory(Integer closedQuestionId) {
        TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity = null;
        for (TestTemplateClosedQuestionEntity entity : closedQuestions) {
            if (entity.getClosedQuestionEntity().getId().equals(closedQuestionId)) {
                testTemplateClosedQuestionEntity = entity;
                break;
            }
        }
        Validate.notNull(testTemplateClosedQuestionEntity, String.format("Specified ClosedQuestion <%s>"
                + " is not signed to this TestTemplate and cannot be marked as not mandatory",
                closedQuestionId));
        testTemplateClosedQuestionEntity.markAsNotMandatory();
    }

    public Set<ExamEntity> getExams() {
        return exams;
    }

    public void setExams(Set<ExamEntity> exams) {
        this.exams = exams;
    }

    public Set<ClosedQuestionEntity> getMandatoryClosedQuestions() {
        Set<ClosedQuestionEntity> mandatoryClosedQuestions = new HashSet<ClosedQuestionEntity>();

        for (TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity : closedQuestions) {
            if (testTemplateClosedQuestionEntity.isMandatory()) {
                mandatoryClosedQuestions.add(testTemplateClosedQuestionEntity.getClosedQuestionEntity());
            }
        }

        return mandatoryClosedQuestions;
    }

    public Set<ClosedQuestionEntity> getNotMandatoryClosedQuestions() {
        Set<ClosedQuestionEntity> notMandatoryClosedQuestions = new HashSet<ClosedQuestionEntity>();

        for (TestTemplateClosedQuestionEntity testTemplateClosedQuestionEntity : closedQuestions) {
            if (!testTemplateClosedQuestionEntity.isMandatory()) {
                notMandatoryClosedQuestions.add(testTemplateClosedQuestionEntity.getClosedQuestionEntity());
            }
        }

        return notMandatoryClosedQuestions;
    }
}
