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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;
import pl.konczak.etest.core.Validate;

@Entity
@Table(name = "categories")
public class CategoryEntity
        implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoriesId",
            unique = true,
            nullable = false,
            updatable = false)
    private Integer id;
    @NotBlank
    @Column(unique = true,
            nullable = false,
            length = 25)
    private String name;
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "category")
    @OrderBy("id")
    private Set<ClosedQuestionEntity> closedQuestions = new HashSet<ClosedQuestionEntity>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId",
                referencedColumnName = "categoriesId",
                nullable = true)
    private CategoryEntity parent;
    @OneToMany(fetch = FetchType.LAZY,
               mappedBy = "parent")
    @OrderBy("name")
    private Set<CategoryEntity> childrens = new HashSet<CategoryEntity>();

    protected CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
    }

    public CategoryEntity(String name, CategoryEntity parent) {
        this.name = name;
        this.parent = parent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void changeName(String newName) {
        Validate.notEmpty(newName);
        this.name = newName;
    }

    public Set<ClosedQuestionEntity> getClosedQuestions() {
        return closedQuestions;
    }

    public Set<ClosedQuestionEntity> getClosedQuestionsWithThoseFromChildrens() {
        Set<ClosedQuestionEntity> all = new HashSet<ClosedQuestionEntity>();
        for (CategoryEntity categoryEntity : childrens) {
            all.addAll(categoryEntity.getClosedQuestionsWithThoseFromChildrens());
        }

        all.addAll(closedQuestions);

        return all;
    }

    public int getCountOfClosedQuestionsWithThoseFromChildrens() {
        return getClosedQuestionsWithThoseFromChildrens().size();
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public Set<CategoryEntity> getChildrens() {
        return childrens;
    }
}
