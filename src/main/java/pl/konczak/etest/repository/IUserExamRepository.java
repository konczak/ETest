package pl.konczak.etest.repository;

import java.util.List;
import pl.konczak.etest.entity.UserExamEntity;

public interface IUserExamRepository {

    UserExamEntity getById(Integer id);

    List<UserExamEntity> findAll();

    void save(UserExamEntity testTemplate);

    void delete(UserExamEntity testTemplate);
}
