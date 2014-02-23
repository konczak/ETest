package pl.konczak.etest.assembler.teacher.closedQuestion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionPreview;
import pl.konczak.etest.entity.CategoryEntity;
import pl.konczak.etest.entity.ClosedAnswerEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Component
public class ClosedQuestionAssembler {

    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;

    @Transactional(readOnly = true)
    public ClosedQuestionPreview toPreview(Integer closedQuestionId) {
        ClosedQuestionEntity closedQuestionEntity =
                closedQuestionRepository.getById(closedQuestionId);
        closedQuestionEntity.getClosedAnswers().size();

        ImageEntity imageEntity = closedQuestionEntity.getImage();
        CategoryEntity categoryEntity = closedQuestionEntity.getCategory();
        UserEntity author = closedQuestionEntity.getAuthor();
        UserPersonalDataEntity authorPersonalDataEntity = author.getUserPersonalData();

        ClosedQuestionPreview closedQuestionPreview = 
                new ClosedQuestionPreview(closedQuestionEntity, imageEntity, author, authorPersonalDataEntity, categoryEntity);

        for (ClosedAnswerEntity closedAnswerEntity : closedQuestionEntity.getClosedAnswers()) {
            imageEntity = closedAnswerEntity.getImage();
            closedQuestionPreview.addClosedAnswer(closedAnswerEntity.getId(),
                    closedAnswerEntity.getAnswer(),
                    closedAnswerEntity.isCorrect(),
                    imageEntity == null ? null : imageEntity.getId());
        }

        return closedQuestionPreview;
    }
}
