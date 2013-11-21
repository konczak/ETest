package pl.konczak.etest.dto.question.category;

import org.hibernate.validator.constraints.NotBlank;

public class CategoryOfQuestionNew {

    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
