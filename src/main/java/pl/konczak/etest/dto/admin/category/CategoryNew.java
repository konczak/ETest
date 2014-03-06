package pl.konczak.etest.dto.admin.category;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class CategoryNew {

    @NotNull
    private Integer parentId;
    @NotEmpty
    private String name;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryNew{" + "parentId=" + parentId + ", name=" + name + '}';
    }
}
