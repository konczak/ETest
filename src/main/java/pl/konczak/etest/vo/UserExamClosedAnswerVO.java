package pl.konczak.etest.vo;

import pl.konczak.etest.core.Validate;

public class UserExamClosedAnswerVO {

    private Integer id;
    private boolean correct;

    public UserExamClosedAnswerVO(Integer id, boolean correct) {
        Validate.notNull(id);
        this.id = id;
        this.correct = correct;
    }

    public Integer getId() {
        return id;
    }

    public boolean isCorrect() {
        return correct;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UserExamClosedAnswerVO other = (UserExamClosedAnswerVO) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("id <%s> correct <%s>", id, correct);
    }
}
