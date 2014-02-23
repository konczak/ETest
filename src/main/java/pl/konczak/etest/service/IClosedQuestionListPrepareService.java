package pl.konczak.etest.service;

import java.util.List;

import pl.konczak.etest.dto.teacher.closedQuestion.ClosedQuestionListRow;

public interface IClosedQuestionListPrepareService {

    List<ClosedQuestionListRow> findAll();
}
