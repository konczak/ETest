package pl.konczak.etest.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.bo.ICategoryBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.CategoryEntity;
import pl.konczak.etest.error.SystemException;
import pl.konczak.etest.error.ValidationCode;
import pl.konczak.etest.repository.ICategoryRepository;

@Service
public class CategoryBO
        implements ICategoryBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryBO.class);
    @Autowired
    private ICategoryRepository categoryRepository;

    @Transactional
    @Override
    public CategoryEntity add(String name) {
        Validate.notEmpty(name);
        validateNameIsFree(name);

        CategoryEntity categoryEntity = new CategoryEntity(name);
        categoryRepository.save(categoryEntity);

        LOGGER.info("Add Category <{}> with name <{}>",
                categoryEntity.getId(), categoryEntity.getName());
        return categoryEntity;
    }

    @Transactional
    @Override
    public CategoryEntity add(String name, Integer parentId) {
        Validate.notEmpty(name);
        validateNameIsFree(name);
        CategoryEntity parent = categoryRepository.getById(parentId);

        CategoryEntity categoryEntity = new CategoryEntity(name, parent);
        categoryRepository.save(categoryEntity);

        LOGGER.info("Add Category <{}> with name <{}> and parent <{}> <{}>",
                categoryEntity.getId(), categoryEntity.getName(),
                parent.getId(), parent.getName());
        return categoryEntity;
    }

    @Transactional
    @Override
    public CategoryEntity changeName(Integer categoryId, String name) {
        Validate.notNull(categoryId);
        Validate.notEmpty(name);
        validateTitleIsFree(categoryId, name);

        CategoryEntity categoryEntity =
                categoryRepository.getById(categoryId);

        String oldName = categoryEntity.getName();

        categoryEntity.changeName(name);

        categoryRepository.save(categoryEntity);

        LOGGER.info("Changed name of Catgory <{}> from <{}> to <{}>",
                categoryEntity.getId(), oldName, categoryEntity.getName());

        return categoryEntity;
    }

    @Transactional
    @Override
    public void remove(Integer categoryId) {
        Validate.notNull(categoryId);
        CategoryEntity categoryEntity =
                categoryRepository.getById(categoryId);

        categoryRepository.delete(categoryEntity);

        LOGGER.info("Removed Category <{}>", categoryEntity.getId());
    }

    private void validateNameIsFree(String name) {
        if (categoryRepository.findByName(name) != null) {
            throw new SystemException(ValidationCode.ALREADY_TAKEN)
                    .add("class", "Category")
                    .add("field", "name")
                    .add("value", name);
        }
    }

    private void validateTitleIsFree(Integer categoryId, String name) {
        CategoryEntity categoryEntity = categoryRepository.findByName(name);
        if (categoryEntity != null
                && !categoryEntity.getId().equals(categoryId)) {
            throw new SystemException(ValidationCode.ALREADY_TAKEN)
                    .add("class", "Category")
                    .add("field", "name")
                    .add("value", name);
        }
    }
}
