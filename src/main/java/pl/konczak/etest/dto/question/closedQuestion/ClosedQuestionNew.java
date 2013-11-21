package pl.konczak.etest.dto.question.closedQuestion;


import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

public class ClosedQuestionNew {

    @NotBlank
    private String question;
    private MultipartFile multipartFile;

    public ClosedQuestionNew() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
