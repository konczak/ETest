package pl.konczak.etest.assembler.question.closedQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionPreview;
import pl.konczak.etest.entity.CategoryOfQuestionEntity;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Component
public class ClosedQuestionAssembler {

    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;

    @Transactional(readOnly = true)
    public ClosedQuestionPreview toPreview(Integer closedQuestionId) {
        ClosedQuestionEntity entity =
                closedQuestionRepository.getById(closedQuestionId);
        entity.getCategories().size();
        entity.getClosedAnswers().size();

        ClosedQuestionPreview closedQuestionPreview = new ClosedQuestionPreview();

        closedQuestionPreview.setId(entity.getId());
        closedQuestionPreview.setQuestion(entity.getQuestion());

        ImageEntity imageEntity = entity.getImage();

        if (imageEntity != null) {
            closedQuestionPreview.setImageId(imageEntity.getId());
        }

        for (CategoryOfQuestionEntity categoryOfQuestionEntity : entity.getCategories()) {
            closedQuestionPreview.addCategoryOfQuestion(categoryOfQuestionEntity.getId(),
                    categoryOfQuestionEntity.getTitle());
        }

        for (ClosedAnswerEntity closedAnswerEntity : entity.getClosedAnswers()) {
            imageEntity = closedAnswerEntity.getImage();
            closedQuestionPreview.addClosedAnswer(closedAnswerEntity.getId(),
                    closedAnswerEntity.getAnswer(),
                    closedAnswerEntity.isCorrect(),
                    imageEntity == null ? null : imageEntity.getId());
        }

        return closedQuestionPreview;
    }
}
