package pl.konczak.etest.dto.student.userExam;

import org.joda.time.LocalDateTime;

public class UserExamAlreadyFinished {

    private String testTemplateSubject;
    private String titleSuffix;
    private LocalDateTime activeTo;

    public UserExamAlreadyFinished(String testTemplateSubject, String titleSuffix, LocalDateTime activeTo) {
        this.testTemplateSubject = testTemplateSubject;
        this.titleSuffix = titleSuffix;
        this.activeTo = activeTo;
    }

    public String getTestTemplateSubject() {
        return testTemplateSubject;
    }

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public LocalDateTime getActiveTo() {
        return activeTo;
    }
}
