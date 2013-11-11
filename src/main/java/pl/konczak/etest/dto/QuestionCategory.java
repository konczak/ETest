package pl.konczak.etest.dto;

import org.hibernate.validator.constraints.NotBlank;

public class QuestionCategory {

    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
