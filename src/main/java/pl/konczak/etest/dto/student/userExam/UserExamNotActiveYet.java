package pl.konczak.etest.dto.student.userExam;

import org.joda.time.LocalDateTime;

public class UserExamNotActiveYet {

    private String testTemplateSubject;
    private String titleSuffix;
    private LocalDateTime activeFrom;
    private Integer activeInSeconds;

    public UserExamNotActiveYet(String testTemplateSubject, String titleSuffix, LocalDateTime activeFrom, Integer activeInSeconds) {
        this.testTemplateSubject = testTemplateSubject;
        this.titleSuffix = titleSuffix;
        this.activeFrom = activeFrom;
        this.activeInSeconds = activeInSeconds;
    }

    public String getTestTemplateSubject() {
        return testTemplateSubject;
    }

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public LocalDateTime getActiveFrom() {
        return activeFrom;
    }

    public Integer getActiveInSeconds() {
        return activeInSeconds;
    }
}
