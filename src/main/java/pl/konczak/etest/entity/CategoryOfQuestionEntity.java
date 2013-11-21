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
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "categoryOfQuestions")
public class CategoryOfQuestionEntity
        implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @NotBlank
    private String title;
    private Set<ClosedQuestionEntity> closedQuestions = new HashSet<ClosedQuestionEntity>();

    public CategoryOfQuestionEntity() {
    }

    public CategoryOfQuestionEntity(String title) {
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryOfQuestionsId",
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
            length = 25)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinTable(name = "closedQuestions_categoryOfQuestions",
               joinColumns = {
        @JoinColumn(name = "categoryOfQuestionsId",
                    nullable = false,
                    updatable = false)
    },
               inverseJoinColumns = {
        @JoinColumn(name = "closedQuestionsId",
                    nullable = false,
                    updatable = false)})
    public Set<ClosedQuestionEntity> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(
            Set<ClosedQuestionEntity> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }

    public void addClosedQuestion(ClosedQuestionEntity closedQuestionEntity) {
        this.closedQuestions.add(closedQuestionEntity);
    }
}
