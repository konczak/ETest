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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "closedQuestions")
public class ClosedQuestionEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closedQuestionsId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @NotBlank
    @Column(nullable = false,
            length = 1000)
    private String question;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_usersId",
                nullable = false)
    private UserEntity author;
    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    @JoinColumn(name = "imagesId",
                unique = true,
                nullable = true)
    private ImageEntity image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoriesId",
                nullable = false)
    private CategoryEntity category;
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "closedQuestion")
    @OrderBy("id")
    private Set<ClosedAnswerEntity> closedAnswers = new HashSet<ClosedAnswerEntity>();
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "pk.closedQuestion",
               cascade = CascadeType.ALL)
    private Set<TestTemplateClosedQuestionEntity> testTemplateClosedQuestions = new HashSet<TestTemplateClosedQuestionEntity>();
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "closedQuestion")
    private Set<UserExamClosedQuestionEntity> usages = new HashSet<UserExamClosedQuestionEntity>();

    protected ClosedQuestionEntity() {
    }

    public ClosedQuestionEntity(String question, UserEntity author, CategoryEntity category) {
        this.question = question;
        this.author = author;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public Set<ClosedAnswerEntity> getClosedAnswers() {
        return closedAnswers;
    }

    public void setClosedAnswers(
            Set<ClosedAnswerEntity> closedAnswers) {
        this.closedAnswers = closedAnswers;
    }

    public void addClosedAnswer(ClosedAnswerEntity closedAnswer) {
        this.closedAnswers.add(closedAnswer);
    }

    public Set<TestTemplateClosedQuestionEntity> getTestTemplateClosedQuestions() {
        return testTemplateClosedQuestions;
    }

    public void setTestTemplateClosedQuestions(
            Set<TestTemplateClosedQuestionEntity> testTemplateClosedQuestions) {
        this.testTemplateClosedQuestions = testTemplateClosedQuestions;
    }

    public Set<UserExamClosedQuestionEntity> getUsages() {
        return usages;
    }

    public void setUsages(Set<UserExamClosedQuestionEntity> usages) {
        this.usages = usages;
    }

    public Set<ClosedAnswerEntity> getCorrectClosedAnswers() {
        Set<ClosedAnswerEntity> correctClosedAnswers = new HashSet<ClosedAnswerEntity>();
        for (ClosedAnswerEntity closedAnswer : closedAnswers) {
            if (closedAnswer.isCorrect()) {
                correctClosedAnswers.add(closedAnswer);
            }
        }
        return correctClosedAnswers;
    }

    public Set<ClosedAnswerEntity> getIncorrectClosedAnswers() {
        Set<ClosedAnswerEntity> incorrectClosedAnswers = new HashSet<ClosedAnswerEntity>();
        for (ClosedAnswerEntity closedAnswer : closedAnswers) {
            if (!closedAnswer.isCorrect()) {
                incorrectClosedAnswers.add(closedAnswer);
            }
        }
        return incorrectClosedAnswers;
    }
}
