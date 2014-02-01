package pl.konczak.etest.strategy.teacher;

import pl.konczak.etest.entity.UserExamEntity;

public interface IUserExamCheckStrategy {

    void check(UserExamEntity userExam);
}
