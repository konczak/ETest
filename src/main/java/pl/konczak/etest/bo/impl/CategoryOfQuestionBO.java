package pl.konczak.etest.bo.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.bo.ICategoryOfQuestionBO;
import pl.konczak.etest.core.Validate;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Service
public class CategoryOfQuestionBO
        implements ICategoryOfQuestionBO {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryOfQuestionBO.class);
    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;

    @Transactional
    @Override
    public CategoryOfQuestionEntity add(String title) {
        Validate.notEmpty(title);

        if (categoryOfQuestionRepository.findByTitle(title) != null) {
            throw new IllegalArgumentException(
                    String.format("CategoryOfQuestion with title <%s> already exists", title));
        }

        CategoryOfQuestionEntity categoryOfQuestionEntity = new CategoryOfQuestionEntity(title);
        categoryOfQuestionRepository.save(categoryOfQuestionEntity);

        LOGGER.info("Add CategoryOfQuestion <{}> with title <{}>",
                categoryOfQuestionEntity.getId(), categoryOfQuestionEntity.getTitle());
        return categoryOfQuestionEntity;
    }

    @Transactional
    @Override
    public CategoryOfQuestionEntity changeTitle(Integer categoryOfQuestionId, String title) {
        Validate.notNull(categoryOfQuestionId);
        Validate.notEmpty(title);

        if (categoryOfQuestionRepository.findByTitle(title) != null) {
            throw new IllegalArgumentException(
                    String.format("CategoryOfQuestion with title <%s> already exists", title));
        }

        CategoryOfQuestionEntity categoryOfQuestionEntity =
                categoryOfQuestionRepository.getById(categoryOfQuestionId);
        String oldTitle = categoryOfQuestionEntity.getTitle();

        categoryOfQuestionEntity.setTitle(title);

        categoryOfQuestionRepository.save(categoryOfQuestionEntity);

        LOGGER.info("Changed title of CategoryOfQuestion <{}> from <{}> to <{}>",
                categoryOfQuestionEntity.getId(), oldTitle, categoryOfQuestionEntity.getTitle());

        return categoryOfQuestionEntity;
    }

    @Transactional
    @Override
    public void remove(Integer categoryOfQuestionId) {
        Validate.notNull(categoryOfQuestionId);
        CategoryOfQuestionEntity categoryOfQuestionEntity =
                categoryOfQuestionRepository.getById(categoryOfQuestionId);

        categoryOfQuestionRepository.delete(categoryOfQuestionEntity);

        LOGGER.info("Removed CategoryOfQuestion <{}>", categoryOfQuestionEntity.getId());
    }
}
