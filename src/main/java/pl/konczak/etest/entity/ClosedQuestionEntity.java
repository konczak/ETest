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
    private Integer id;
    @NotBlank
    private String question;
    @NotNull
    private UserEntity author;
    private ImageEntity image;
    private Set<CategoryOfQuestionEntity> categories = new HashSet<CategoryOfQuestionEntity>();
    private Set<ClosedAnswerEntity> closedAnswers = new HashSet<ClosedAnswerEntity>();
    private Set<TestTemplateClosedQuestionEntity> testTemplateClosedQuestions = new HashSet<TestTemplateClosedQuestionEntity>();
    private Set<UserExamClosedQuestionEntity> usages = new HashSet<UserExamClosedQuestionEntity>();

    public ClosedQuestionEntity() {
    }

    public ClosedQuestionEntity(String question, UserEntity author) {
        this.question = question;
        this.author = author;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "closedQuestionsId",
            unique = true,
            nullable = false,
            updatable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false,
            length = 1000)
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_usersId",
                nullable = false)
    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    @OneToOne(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
    @JoinColumn(name = "imagesId",
                unique = true,
                nullable = true)
    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }

    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinTable(name = "closedQuestions_categoryOfQuestions",
               joinColumns = {
        @JoinColumn(name = "closedQuestionsId",
                    nullable = false,
                    updatable = false)},
               inverseJoinColumns = {
        @JoinColumn(name = "categoryOfQuestionsId",
                    nullable = false,
                    updatable = false)})
    @OrderBy("title")
    public Set<CategoryOfQuestionEntity> getCategories() {
        return categories;
    }

    public void setCategories(
            Set<CategoryOfQuestionEntity> categories) {
        this.categories = categories;
    }

    public void addCategoryOfQuestion(CategoryOfQuestionEntity categoryOfQuestionEntity) {
        this.categories.add(categoryOfQuestionEntity);
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "closedQuestion")
    @OrderBy("id")
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

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "pk.closedQuestion",
               cascade = CascadeType.ALL)
    public Set<TestTemplateClosedQuestionEntity> getTestTemplateClosedQuestions() {
        return testTemplateClosedQuestions;
    }

    public void setTestTemplateClosedQuestions(
            Set<TestTemplateClosedQuestionEntity> testTemplateClosedQuestions) {
        this.testTemplateClosedQuestions = testTemplateClosedQuestions;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "closedQuestion")
    public Set<UserExamClosedQuestionEntity> getUsages() {
        return usages;
    }

    public void setUsages(Set<UserExamClosedQuestionEntity> usages) {
        this.usages = usages;
    }
}
