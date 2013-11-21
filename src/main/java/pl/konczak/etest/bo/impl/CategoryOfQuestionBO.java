package pl.konczak.etest.bo.impl;

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

        return categoryOfQuestionEntity;
    }

    @Transactional
    @Override
    public CategoryOfQuestionEntity changeTitle(Integer categoryOfQuestionId, String title) {
        Validate.notNull(categoryOfQuestionId);
        Validate.notEmpty(title);

        CategoryOfQuestionEntity categoryOfQuestionEntity =
                categoryOfQuestionRepository.getById(categoryOfQuestionId);

        categoryOfQuestionEntity.setTitle(title);

        categoryOfQuestionRepository.save(categoryOfQuestionEntity);

        return categoryOfQuestionEntity;
    }

    @Transactional
    @Override
    public void remove(Integer categoryOfQuestionId) {
        Validate.notNull(categoryOfQuestionId);
        CategoryOfQuestionEntity categoryOfQuestionEntity =
                categoryOfQuestionRepository.getById(categoryOfQuestionId);

        categoryOfQuestionRepository.delete(categoryOfQuestionEntity);
    }
}
