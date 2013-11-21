package pl.konczak.etest.assembler.question.category;

import org.springframework.stereotype.Component;

import pl.konczak.etest.dto.question.category.CategoryOfQuestionEdit;
import pl.konczak.etest.dto.question.category.CategoryOfQuestionListRow;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;

@Component
public class CategoryOfQuestionAssembler {

    public CategoryOfQuestionListRow toListRow(CategoryOfQuestionEntity categoryOfQuestionEntity) {
        return new CategoryOfQuestionListRow(categoryOfQuestionEntity.getId(),
                categoryOfQuestionEntity.getTitle());
    }

    public CategoryOfQuestionEdit toEdit(CategoryOfQuestionEntity categoryOfQuestionEntity) {
        return new CategoryOfQuestionEdit(categoryOfQuestionEntity.getId(),
                categoryOfQuestionEntity.getTitle());
    }
}
