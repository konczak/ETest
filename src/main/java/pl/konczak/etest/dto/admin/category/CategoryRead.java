package pl.konczak.etest.dto.admin.category;

import java.util.List;

public class CategoryRead {

    private List<CategoryDTO> categories;

    public CategoryRead(List<CategoryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryDTO> getCategories() {
        return categories;
    }
}
