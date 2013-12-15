package pl.konczak.etest.dto.teacher.exam;

import org.joda.time.LocalDateTime;

public class ExamListRow {

    private Integer id;
    private Integer testTemplateId;
    private String testTemplateSubject;
    private Integer userGroupId;
    private String userGroupTitle;
    private String suffix;
    private Integer examinerId;
    private String examinerFirstname;
    private String examinerLastname;
    private LocalDateTime activeFrom;
    private LocalDateTime activeTo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestTemplateId() {
        return testTemplateId;
    }

    public void setTestTemplateId(Integer testTemplateId) {
        this.testTemplateId = testTemplateId;
    }

    public String getTestTemplateSubject() {
        return testTemplateSubject;
    }

    public void setTestTemplateSubject(String testTemplateSubject) {
        this.testTemplateSubject = testTemplateSubject;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupTitle() {
        return userGroupTitle;
    }

    public void setUserGroupTitle(String userGroupTitle) {
        this.userGroupTitle = userGroupTitle;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public Integer getExaminerId() {
        return examinerId;
    }

    public void setExaminerId(Integer authorId) {
        this.examinerId = authorId;
    }

    public String getExaminerFirstname() {
        return examinerFirstname;
    }

    public void setExaminerFirstname(String authorFirstname) {
        this.examinerFirstname = authorFirstname;
    }

    public String getExaminerLastname() {
        return examinerLastname;
    }

    public void setExaminerLastname(String authorLastname) {
        this.examinerLastname = authorLastname;
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
}
