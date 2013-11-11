package pl.konczak.etest.facade.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.konczak.etest.dto.QuestionCategory;
import pl.konczak.etest.entity.CategoryOfQuestion;
import pl.konczak.etest.facade.ICategoryOfQuestionFacade;
import pl.konczak.etest.repository.ICategoryOfQuestionRepository;

@Service
public class CategoryOfQuestionFacade implements ICategoryOfQuestionFacade {

    @Autowired
    private ICategoryOfQuestionRepository categoryOfQuestionRepository;

    @Transactional
    @Override
    public List<CategoryOfQuestion> searchAll() {
        return categoryOfQuestionRepository.findAll();
    }

    @Transactional
    @Override
    public CategoryOfQuestion add(QuestionCategory questionCategory) {
        String title = questionCategory.getTitle();

        CategoryOfQuestion categoryOfQuestion = new CategoryOfQuestion(title);
        categoryOfQuestionRepository.save(categoryOfQuestion);

        return categoryOfQuestion;
    }

    @Transactional
    @Override
    public void remove(Integer id) {
        CategoryOfQuestion categoryOfQuestion = categoryOfQuestionRepository.getById(id);
        categoryOfQuestionRepository.delete(categoryOfQuestion);
    }
}
