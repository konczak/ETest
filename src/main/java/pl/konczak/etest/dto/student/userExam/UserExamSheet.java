package pl.konczak.etest.dto.student.userExam;

import java.util.Set;
import java.util.TreeSet;
import org.joda.time.LocalDateTime;

public class UserExamSheet {

    private Integer id;
    private String testTemplateSubject;
    private String testTemplateSuffix;
    private LocalDateTime activeFrom;
    private LocalDateTime activeTo;
    private Integer activeInSeconds;
    private Integer inactiveInSeconds;
    private ClosedQuestionInternal closedQuestion = null;
    private Set<Integer> closedQuestions =
            new TreeSet<Integer>();

    public static class ClosedQuestionInternal {

        public ClosedQuestionInternal() {
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestTemplateSubject() {
        return testTemplateSubject;
    }

    public void setTestTemplateSubject(String testTemplateSubject) {
        this.testTemplateSubject = testTemplateSubject;
    }

    public String getTestTemplateSuffix() {
        return testTemplateSuffix;
    }

    public void setTestTemplateSuffix(String testTemplateSuffix) {
        this.testTemplateSuffix = testTemplateSuffix;
    }

    public LocalDateTime getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(LocalDateTime activeFrom) {
        this.activeFrom = activeFrom;
    }

    public LocalDateTime getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(LocalDateTime activeTo) {
        this.activeTo = activeTo;
    }

    public Integer getActiveInSeconds() {
        return activeInSeconds;
    }

    public void setActiveInSeconds(Integer activeInSeconds) {
        this.activeInSeconds = activeInSeconds;
    }

    public Integer getInactiveInSeconds() {
        return inactiveInSeconds;
    }

    public void setInactiveInSeconds(Integer inactiveInSeconds) {
        this.inactiveInSeconds = inactiveInSeconds;
    }

    public ClosedQuestionInternal getClosedQuestion() {
        return closedQuestion;
    }

    public Set<Integer> getClosedQuestions() {
        return closedQuestions;
    }

    public void addClosedQuestion(Integer userExamClosedQuestionId) {
        this.closedQuestions.add(userExamClosedQuestionId);
    }
}
