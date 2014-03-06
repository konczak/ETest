package pl.konczak.etest.assembler.admin.category;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.admin.category.CategoryDTO;
import pl.konczak.etest.entity.CategoryEntity;
import pl.konczak.etest.repository.ICategoryRepository;

@Component
public class CategoryAssembler {

    @Autowired
    private ICategoryRepository categoryRepository;

    @Transactional(readOnly = true)
    public List<CategoryDTO> toTree() {
        List<CategoryDTO> categoriesDTO = new ArrayList<CategoryDTO>();

        List<CategoryEntity> categoryEntities = categoryRepository.findAllWithoutParent();
        for (CategoryEntity categoryEntity : categoryEntities) {
            prepareCategoryAndSubcategories(categoriesDTO, categoryEntity);
        }

        return categoriesDTO;
    }

    private void prepareCategoryAndSubcategories(List<CategoryDTO> categoriesDTO, CategoryEntity categoryEntity) {
        CategoryDTO categoryDTO = new CategoryDTO(categoryEntity,
                categoryEntity.getParent(),
                categoryEntity.getCountOfClosedQuestionsWithThoseFromChildrens());

        for (CategoryEntity children : categoryEntity.getChildrens()) {
            prepareCategoryAndSubcategories(categoryDTO.getCategories(), children);
        }

        categoriesDTO.add(categoryDTO);
    }
}
