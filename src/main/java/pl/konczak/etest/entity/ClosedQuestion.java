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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "closedQuestions")
public class ClosedQuestion
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank
    private String question;
    @NotNull
    private User author;
    private Set<CategoryOfQuestion> categories = new HashSet<CategoryOfQuestion>();
    private Set<ClosedQuestionClosedAnswer> closedQuestionClosedAnswers = new HashSet<ClosedQuestionClosedAnswer>();

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
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.EAGER)
    @JoinTable(name = "closedQuestions_categoryOfQuestions",
               joinColumns = {
        @JoinColumn(name = "closedQuestionsId",
                    nullable = false,
                    updatable = false)},
               inverseJoinColumns = {
        @JoinColumn(name = "categoryOfQuestionsId",
                    nullable = false,
                    updatable = false)})
    public Set<CategoryOfQuestion> getCategories() {
        return categories;
    }

    public void setCategories(
            Set<CategoryOfQuestion> categories) {
        this.categories = categories;
    }

    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "pk.closedQuestion")
    public Set<ClosedQuestionClosedAnswer> getClosedQuestionClosedAnswers() {
        return closedQuestionClosedAnswers;
    }

    public void setClosedQuestionClosedAnswers(
            Set<ClosedQuestionClosedAnswer> closedQuestionClosedAnswers) {
        this.closedQuestionClosedAnswers = closedQuestionClosedAnswers;
    }
}
