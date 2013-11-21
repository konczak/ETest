package pl.konczak.etest.dto.question.category;

public class CategoryOfQuestionListRow {

    private Integer id;
    private String title;

    public CategoryOfQuestionListRow(Integer id, String title) {
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
