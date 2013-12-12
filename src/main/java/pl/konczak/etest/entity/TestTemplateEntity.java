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

    private Integer id;
    private String subject;
    private UserEntity author;
    private Set<TestTemplateClosedQuestionEntity> closedQuestions =
            new HashSet<TestTemplateClosedQuestionEntity>();
    private Set<ExamEntity> exams = new HashSet<ExamEntity>();

    public TestTemplateEntity() {
    }

    public TestTemplateEntity(String subject, UserEntity author) {
        this.subject = subject;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "testTemplatesId",
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
            length = 50)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "pk.testTemplate",
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @OrderBy("pk.closedQuestion.id")
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

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "testTemplate")
    public Set<ExamEntity> getExams() {
        return exams;
    }

    public void setExams(Set<ExamEntity> exams) {
        this.exams = exams;
    }
}
