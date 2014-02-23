package pl.konczak.etest.bo;

import pl.konczak.etest.entity.CategoryEntity;

public interface ICategoryBO {

    CategoryEntity add(String name);

    CategoryEntity add(String name, Integer parentId);

    CategoryEntity changeName(Integer categoryId, String name);

    void remove(Integer categoryId);

    CategoryEntity moveToTopLevelOfHierarchy(Integer categoryId);

    CategoryEntity changeParent(Integer categoryId, Integer parentCategoryId);
}
