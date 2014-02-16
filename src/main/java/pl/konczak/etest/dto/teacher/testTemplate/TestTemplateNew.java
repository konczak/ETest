package pl.konczak.etest.dto.teacher.testTemplate;

import org.hibernate.validator.constraints.NotBlank;

public class TestTemplateNew {

    @NotBlank
    private String subject;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
