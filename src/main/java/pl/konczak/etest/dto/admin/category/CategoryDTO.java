package pl.konczak.etest.dto.admin.category;

import java.util.ArrayList;
import java.util.List;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.CategoryEntity;

public class CategoryDTO {

    private Integer id;
    private String name;
    private Integer parentId;
    private int countOfClosedQuestions;
    private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();

    public CategoryDTO(CategoryEntity categoryEntity, CategoryEntity parent, int countOfClosedQuestions) {
        Validate.notNull(categoryEntity);
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
        this.parentId = parent == null ? null : parent.getId();
        this.countOfClosedQuestions = countOfClosedQuestions;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public int getCountOfClosedQuestions() {
        return countOfClosedQuestions;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
