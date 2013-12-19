package pl.konczak.etest.dto.student.userExam;

public class UserExamQuestionHeader
        implements Comparable<UserExamQuestionHeader> {

    private Integer id;
    private Integer orderNumber;

    public UserExamQuestionHeader(Integer id, Integer orderNumber) {
        this.id = id;
        this.orderNumber = orderNumber;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    @Override
    public int compareTo(UserExamQuestionHeader o) {
        int result = 1;
        if (getOrderNumber() < o.getOrderNumber()) {
            result = -1;
        } else if (getOrderNumber() == o.getOrderNumber()) {
            result = 0;
        }
        return result;
    }
}
