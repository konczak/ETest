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
    private boolean ended;
    private boolean checked;
    private List<ExaminedUserInternal> examinedUsers = new ArrayList<ExaminedUserInternal>();

    public static class ExaminedUserInternal {

        private Integer id;
        private String firstname;
        private String lastname;
        private Integer userExamId;
        private Integer resultPoints;
        private Integer maxPoints;

        public ExaminedUserInternal(Integer id,
                String firstname, String lastname,
                Integer userExamId,
                Integer resultPoints, Integer maxPoints) {
            this.id = id;
            this.firstname = firstname;
            this.lastname = lastname;
            this.userExamId = userExamId;
            this.resultPoints = resultPoints;
            this.maxPoints = maxPoints;
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

        public Integer getUserExamId() {
            return userExamId;
        }

        public Integer getResultPoints() {
            return resultPoints;
        }

        public Integer getMaxPoints() {
            return maxPoints;
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
        if (activeTo.isBefore(LocalDateTime.now())) {
            ended = true;
        }
    }

    public boolean isEnded() {
        return ended;
    }

    public boolean isChecked() {
        return checked;
    }

    public void markAsChecked() {
        this.checked = true;
    }

    public List<ExaminedUserInternal> getExaminedUsers() {
        return examinedUsers;
    }

    public void addExaminedUser(Integer userId, String firstname, String lastname,
            Integer userExamId, Integer resultPoints, Integer maxPoints) {
        this.examinedUsers
                .add(new ExaminedUserInternal(userId, firstname, lastname, userExamId, resultPoints, maxPoints));
    }
}
