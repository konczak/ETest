package pl.konczak.etest.dto.teacher.exam;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalDateTime;

public class ExamPreview {

    private Integer id;
    private Integer testTemplateId;
    private String testTampleteSubject;
    private Integer userGroupId;
    private String userGroupTitle;
    private String suffix;
    private Integer examinerId;
    private String examinerFirstname;
    private String examinerLastname;
    private LocalDateTime activeFrom;
    private LocalDateTime activeTo;
    private List<UserGroupMemberInternal> userGroupMembers = new ArrayList<UserGroupMemberInternal>();

    public static class UserGroupMemberInternal {

        private Integer id;
        private String firstname;
        private String lastname;

        public UserGroupMemberInternal(Integer id, String firstname, String lastname) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public Integer getId() {
            return id;
        }

        public String getFirstname() {
            return firstname;
        }

        public String getLastname() {
            return lastname;
        }
    }

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

    public String getTestTampleteSubject() {
        return testTampleteSubject;
    }

    public void setTestTampleteSubject(String testTampleteSubject) {
        this.testTampleteSubject = testTampleteSubject;
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

    public void setExaminerId(Integer examinerId) {
        this.examinerId = examinerId;
    }

    public String getExaminerFirstname() {
        return examinerFirstname;
    }

    public void setExaminerFirstname(String examinerFirstname) {
        this.examinerFirstname = examinerFirstname;
    }

    public String getExaminerLastname() {
        return examinerLastname;
    }

    public void setExaminerLastname(String examinerLastname) {
        this.examinerLastname = examinerLastname;
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

    public List<UserGroupMemberInternal> getUserGroupMembers() {
        return userGroupMembers;
    }

    public void addMember(Integer userId, String firstname, String lastname) {
        this.userGroupMembers.add(new UserGroupMemberInternal(userId, firstname, lastname));
    }
}
