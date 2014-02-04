package pl.konczak.etest.bo;

import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;

import pl.konczak.etest.entity.ExamEntity;

public interface IExamBO {

    ExamEntity add(Integer testTemplateId, Integer userGroupId, String titleSufix, Integer examinerId,
            LocalDateTime activeFrom, LocalDateTime activeTo,
            Integer maxClosedQuestionsPerExam, Integer maxClosedAnswersPerClosedQuestion);

    void check(Integer examId);

    void prolong(Integer examId, Seconds seconds);
    
    void terminate(Integer examId);
}
