package pl.konczak.etest.service;

import java.util.List;

import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionListRow;

public interface IClosedQuestionListPrepareService {

    List<ClosedQuestionListRow> findAll();
}
