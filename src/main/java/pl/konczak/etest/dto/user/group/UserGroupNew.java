package pl.konczak.etest.dto.user.group;

import org.hibernate.validator.constraints.NotBlank;

public class UserGroupNew {

    @NotBlank
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
