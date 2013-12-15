package pl.konczak.etest.dto.teacher.exam;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class ExamNew {

    @NotNull
    private Integer testTemplateId;
    @NotNull
    private Integer userGroupId;
    private String suffix;
    @NotBlank
    private String duration;
    @NotNull
    private Integer maxClosedQuestionsPerUserExam;
    @NotNull
    private Integer maxClosedAnswersPerClosedQuestion;

    public Integer getTestTemplateId() {
        return testTemplateId;
    }

    public void setTestTemplateId(Integer testTemplateId) {
        this.testTemplateId = testTemplateId;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Integer getMaxClosedAnswersPerClosedQuestion() {
        return maxClosedAnswersPerClosedQuestion;
    }

    public void setMaxClosedAnswersPerClosedQuestion(Integer maxClosedAnswersPerClosedQuestion) {
        this.maxClosedAnswersPerClosedQuestion = maxClosedAnswersPerClosedQuestion;
    }

    public Integer getMaxClosedQuestionsPerUserExam() {
        return maxClosedQuestionsPerUserExam;
    }

    public void setMaxClosedQuestionsPerUserExam(Integer maxClosedQuestionsPerUserExam) {
        this.maxClosedQuestionsPerUserExam = maxClosedQuestionsPerUserExam;
    }
}
