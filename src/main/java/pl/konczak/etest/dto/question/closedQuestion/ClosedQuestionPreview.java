package pl.konczak.etest.dto.question.closedQuestion;

import java.util.ArrayList;
import java.util.List;
import pl.konczak.etest.entity.CategoryEntity;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.ImageEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;

public class ClosedQuestionPreview {

    private Integer id;
    private String question;
    private Integer imageId;
    private Integer authorId;
    private String authorFirstname;
    private String authorLastname;
    private Integer categoryId;
    private String categoryName;
    private List<ClosedAnswerInternal> closedAnswers =
            new ArrayList<ClosedAnswerInternal>();

    public static class ClosedAnswerInternal {

        private Integer id;
        private String answer;
        private boolean correct;
        private Integer imageId;

        public ClosedAnswerInternal(Integer id, String answer, boolean correct, Integer imageId) {
            this.id = id;
            this.answer = answer;
            this.correct = correct;
            this.imageId = imageId;
        }

        public Integer getId() {
            return id;
        }

        public String getAnswer() {
            return answer;
        }

        public boolean isCorrect() {
            return correct;
        }

        public Integer getImageId() {
            return imageId;
        }
    }

    public ClosedQuestionPreview(ClosedQuestionEntity closedQuestionEntity, ImageEntity imageEntity,
            UserEntity author, UserPersonalDataEntity authorPersonalDataEntity, CategoryEntity categoryEntity) {
        this.id = closedQuestionEntity.getId();
        this.question = closedQuestionEntity.getQuestion();
        this.imageId = imageEntity == null ? null : imageEntity.getId();
        this.categoryId = categoryEntity.getId();
        this.categoryName = categoryEntity.getName();
        this.authorId = author.getId();
        this.authorFirstname = authorPersonalDataEntity.getFirstname();
        this.authorLastname = authorPersonalDataEntity.getLastname();
    }

    public Integer getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public Integer getImageId() {
        return imageId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<ClosedAnswerInternal> getClosedAnswers() {
        return closedAnswers;
    }

    public void addClosedAnswer(Integer closedAnswerId, String answer, boolean correct, Integer imageId) {
        closedAnswers.add(new ClosedAnswerInternal(closedAnswerId, answer, correct, imageId));
    }
}
