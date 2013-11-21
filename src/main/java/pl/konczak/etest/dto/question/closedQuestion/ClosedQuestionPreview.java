package pl.konczak.etest.dto.question.closedQuestion;

import java.util.ArrayList;
import java.util.List;

public class ClosedQuestionPreview {

    private Integer id;
    private String question;
    private Integer imageid;
    private byte[] image;
    private List<CategoryOfQuestionInternal> categoriesOfQuestion =
            new ArrayList<CategoryOfQuestionInternal>();
    private List<ClosedAnswerInternal> closedAnswers =
            new ArrayList<ClosedAnswerInternal>();

    public static class CategoryOfQuestionInternal {

        private Integer id;
        private String title;

        public CategoryOfQuestionInternal(Integer id, String title) {
            this.id = id;
            this.title = title;
        }

        public Integer getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }
    }

    public static class ClosedAnswerInternal {

        private Integer id;
        private String answer;
        private boolean correct;

        public ClosedAnswerInternal(Integer id, String answer, boolean correct) {
            this.id = id;
            this.answer = answer;
            this.correct = correct;
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
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getImageid() {
        return imageid;
    }

    public void setImageid(Integer imageid) {
        this.imageid = imageid;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public List<CategoryOfQuestionInternal> getCategoriesOfQuestion() {
        return categoriesOfQuestion;
    }

    public void addCategoryOfQuestion(Integer categoryOfQuestionId, String title) {
        categoriesOfQuestion.add(new CategoryOfQuestionInternal(categoryOfQuestionId, title));
    }

    public List<ClosedAnswerInternal> getClosedAnswers() {
        return closedAnswers;
    }

    public void addClosedAnswer(Integer closedAnswerId, String answer, boolean correct) {
        closedAnswers.add(new ClosedAnswerInternal(closedAnswerId, answer, correct));
    }
}
