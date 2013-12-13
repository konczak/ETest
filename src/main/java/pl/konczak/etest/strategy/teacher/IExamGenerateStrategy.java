package pl.konczak.etest.strategy.teacher;

import pl.konczak.etest.entity.ExamEntity;

public interface IExamGenerateStrategy {

    ExamEntity generateUserExams(ExamEntity examEntity);
}
