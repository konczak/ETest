package pl.konczak.etest.dto.admin.category;

import java.util.ArrayList;
import java.util.List;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.CategoryEntity;

public class CategoryDTO {

    private Integer id;
    private String name;
    private int countOfClosedQuestions;
    private List<CategoryDTO> childrens = new ArrayList<CategoryDTO>();

    public CategoryDTO(CategoryEntity categoryEntity) {
        Validate.notNull(categoryEntity);
        this.id = categoryEntity.getId();
        this.name = categoryEntity.getName();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCountOfClosedQuestions() {
        return countOfClosedQuestions;
    }

    public void setCountOfClosedQuestions(int countOfClosedQuestions) {
        this.countOfClosedQuestions = countOfClosedQuestions;
    }

    public List<CategoryDTO> getChildrens() {
        return childrens;
    }
}
