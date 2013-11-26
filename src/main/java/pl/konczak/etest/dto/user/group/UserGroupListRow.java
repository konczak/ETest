package pl.konczak.etest.dto.user.group;

public class UserGroupListRow {

    private Integer id;
    private String title;
    private Integer membersCount;

    public UserGroupListRow(Integer id, String title, Integer membersCount) {
        this.id = id;
        this.title = title;
        this.membersCount = membersCount;
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

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
    }
}
