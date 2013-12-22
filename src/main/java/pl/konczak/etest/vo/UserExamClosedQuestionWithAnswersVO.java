package pl.konczak.etest.vo;

import java.util.HashSet;
import java.util.Set;
import pl.konczak.etest.core.Validate;

public class UserExamClosedQuestionWithAnswersVO {

    private Integer id;
    private Set<UserExamClosedAnswerVO> closedAnswers = new HashSet<UserExamClosedAnswerVO>();

    public UserExamClosedQuestionWithAnswersVO(Integer id) {
        Validate.notNull(id);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Set<UserExamClosedAnswerVO> getClosedAnswers() {
        return closedAnswers;
    }

    public void addClosedAnswer(Integer closedAnswerId, boolean correct) {
        this.closedAnswers.add(new UserExamClosedAnswerVO(closedAnswerId, correct));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UserExamClosedQuestion <");
        stringBuilder.append(id);
        stringBuilder.append("> closedAnswers [");
        for (UserExamClosedAnswerVO userExamClosedAnswerVO : closedAnswers) {
            stringBuilder.append("UserExamClosedAnswer {");
            stringBuilder.append(userExamClosedAnswerVO);
            stringBuilder.append("}");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
