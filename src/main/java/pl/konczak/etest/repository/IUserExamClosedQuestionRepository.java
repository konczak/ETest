package pl.konczak.etest.repository;

import pl.konczak.etest.entity.UserExamClosedQuestionEntity;

public interface IUserExamClosedQuestionRepository {

    UserExamClosedQuestionEntity getById(Integer id);
    
    void save(UserExamClosedQuestionEntity userExamClosedQuestion);
}
