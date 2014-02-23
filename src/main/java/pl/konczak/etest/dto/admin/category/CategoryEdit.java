package pl.konczak.etest.dto.admin.category;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoryEdit {

    @NotNull
    private Integer categoryId;
    @NotEmpty
    private String name;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
