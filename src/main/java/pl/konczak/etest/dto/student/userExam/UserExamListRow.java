package pl.konczak.etest.dto.student.userExam;

public class UserExamListRow {

    private Integer id;
    private String testTemplateSubject;
    private String titleSuffix;
    private Integer examinerId;
    private String examinerFirstname;
    private String examinerLastname;

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

    public String getTitleSuffix() {
        return titleSuffix;
    }

    public void setTitleSuffix(String titleSuffix) {
        this.titleSuffix = titleSuffix;
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
}
