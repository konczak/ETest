package pl.konczak.etest.dto.student.userExam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UserExamClosedQuestion {

    private Integer id;
    private String question;
    private Integer imageId;
    private List<UserExamClosedAnswer> userExamClosedAnswers =
            new ArrayList<UserExamClosedAnswer>();

    public static class UserExamClosedAnswer {

        private Integer id;
        private String answer;
        private boolean correct;
        private Integer imageId;

        public UserExamClosedAnswer() {
        }

        public UserExamClosedAnswer(Integer id, String answer, Integer imageId) {
            this.id = id;
            this.answer = answer;
            this.correct = false;
            this.imageId = imageId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public boolean isCorrect() {
            return correct;
        }

        public void setCorrect(boolean correct) {
            this.correct = correct;
        }

        public Integer getImageId() {
            return imageId;
        }

        public void setImageId(Integer imageId) {
            this.imageId = imageId;
        }
    }

    public UserExamClosedQuestion() {
    }

    public UserExamClosedQuestion(Integer id, String question, Integer imageId) {
        this.id = id;
        this.question = question;
        this.imageId = imageId;
    }

    public Integer getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public List<UserExamClosedAnswer> getUserExamClosedAnswers() {
        return userExamClosedAnswers;
    }

    public void setUserExamClosedAnswers(List<UserExamClosedAnswer> userExamClosedAnswers) {
        this.userExamClosedAnswers = userExamClosedAnswers;
    }

    public void addClosedAnswer(Integer id, String answer, Integer imageId) {
        this.userExamClosedAnswers.add(new UserExamClosedAnswer(id, answer, imageId));
    }
}
