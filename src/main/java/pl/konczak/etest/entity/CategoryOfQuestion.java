package pl.konczak.etest.entity;

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
public class CategoryOfQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryOfQuestionsId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @NotBlank
    @Column(unique = true,
            nullable = false,
            length = 25)
    private String title;
    @ManyToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    @JoinTable(name = "categoryOfQuestions_closedQuestions",
               joinColumns = {
        @JoinColumn(name = "categoryOfQuestionsId",
                    nullable = false,
                    updatable = false)
    },
               inverseJoinColumns = {
        @JoinColumn(name = "closedQuestionsId",
                    nullable = false,
                    updatable = false)})
    private Set<ClosedQuestion> closedQuestions;

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Set<ClosedQuestion> getClosedQuestions() {
        return closedQuestions;
    }
}
