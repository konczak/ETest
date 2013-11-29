package pl.konczak.etest.dto.user.group;

import org.hibernate.validator.constraints.NotBlank;

public class UserGroupNew {

    @NotBlank
    private String title;
    private String classPrefix;
    private String classNumber;
    private String classLetter;
    private String classYear;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassPrefix() {
        return classPrefix;
    }

    public void setClassPrefix(String classPrefix) {
        this.classPrefix = classPrefix;
    }

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public String getClassLetter() {
        return classLetter;
    }

    public void setClassLetter(String classLetter) {
        this.classLetter = classLetter;
    }

    public String getClassYear() {
        return classYear;
    }

    public void setClassYear(String classYear) {
        this.classYear = classYear;
    }
}
