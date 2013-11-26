package pl.konczak.etest.dto.user.group;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class UserGroupEdit {

    @NotNull
    private Integer id;
    @NotBlank
    private String title;

    public UserGroupEdit() {
    }

    public UserGroupEdit(Integer id, String title) {
        this.id = id;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
