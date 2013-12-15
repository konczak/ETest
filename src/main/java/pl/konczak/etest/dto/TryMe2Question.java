package pl.konczak.etest.dto;

import java.util.ArrayList;
import java.util.List;

public class TryMe2Question {

    private Integer id;
    private String question;
    private Integer imageId;
    private List<Answer> answers = new ArrayList<Answer>();

    public TryMe2Question() {
    }

    public TryMe2Question(Integer id, String question, Integer imageId) {
        this.id = id;
        this.question = question;
        this.imageId = imageId;
    }

    public static class Answer {

        private Integer id;
        private String answer;
        private boolean correct;
        private Integer imageId;

        public Answer() {
        }

        public Answer(Integer id, String answer, Integer imageId) {
            this.id = id;
            this.answer = answer;
            this.correct = id % 2 == 0;
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

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Integer id, String answer, Integer imageId) {
        this.answers.add(new Answer(id, answer, imageId));
    }
}
