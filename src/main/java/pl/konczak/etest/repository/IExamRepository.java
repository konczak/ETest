package pl.konczak.etest.repository;

import java.util.List;

import pl.konczak.etest.entity.ExamEntity;

public interface IExamRepository {

    ExamEntity getById(Integer id);

    List<ExamEntity> findAll();

    void save(ExamEntity testTemplate);

    void delete(ExamEntity testTemplate);
}
