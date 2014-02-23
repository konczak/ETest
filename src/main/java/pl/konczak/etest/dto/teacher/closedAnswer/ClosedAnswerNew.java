package pl.konczak.etest.dto.teacher.closedAnswer;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class ClosedAnswerNew {

    private Integer closedQuestionId;
    private String question;
    @NotBlank
    private String answer;
    private boolean correct;
    private MultipartFile multipartFile;

    public Integer getClosedQuestionId() {
        return closedQuestionId;
    }

    public void setClosedQuestionId(Integer closedQuestionId) {
        this.closedQuestionId = closedQuestionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
