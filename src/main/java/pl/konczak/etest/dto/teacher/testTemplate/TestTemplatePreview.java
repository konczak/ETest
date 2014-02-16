package pl.konczak.etest.dto.teacher.testTemplate;

import java.util.ArrayList;
import java.util.List;

public class TestTemplatePreview {

    private Integer id;
    private String subject;
    private Integer authorId;
    private String authorFirstname;
    private String authorLastname;
    private List<ClosedQuestionInternal> closedQuestions =
            new ArrayList<ClosedQuestionInternal>();

    public static class ClosedQuestionInternal {

        private Integer id;
        private String question;
        private boolean mandatory;

        public ClosedQuestionInternal(Integer id, String question, boolean mandatory) {
            this.id = id;
            this.question = question;
            this.mandatory = mandatory;
        }

        public Integer getId() {
            return id;
        }

        public String getQuestion() {
            return question;
        }

        public boolean isMandatory() {
            return mandatory;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getAuthorFirstname() {
        return authorFirstname;
    }

    public void setAuthorFirstname(String authorFirstname) {
        this.authorFirstname = authorFirstname;
    }

    public String getAuthorLastname() {
        return authorLastname;
    }

    public void setAuthorLastname(String authorLastname) {
        this.authorLastname = authorLastname;
    }

    public List<ClosedQuestionInternal> getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(List<ClosedQuestionInternal> closedQuestions) {
        this.closedQuestions = closedQuestions;
    }

    public void addClosedQuestion(Integer id, String question, boolean mandatory) {
        this.closedQuestions.add(new ClosedQuestionInternal(id, question, mandatory));
    }
}
