package pl.konczak.etest.service.impl;

import pl.konczak.etest.service.IClosedQuestionListPrepareService;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.konczak.etest.dto.question.closedQuestion.ClosedQuestionListRow;
import pl.konczak.etest.entity.ClosedQuestionEntity;
import pl.konczak.etest.entity.UserEntity;
import pl.konczak.etest.entity.UserPersonalDataEntity;
import pl.konczak.etest.repository.IClosedQuestionRepository;

@Service
public class ClosedQuestionListPrepareService
        implements IClosedQuestionListPrepareService {

    @Autowired
    private IClosedQuestionRepository closedQuestionRepository;

    @Transactional(readOnly = true)
    @Override
    public List<ClosedQuestionListRow> findAll() {
        List<ClosedQuestionEntity> closedQuestionEntities = closedQuestionRepository.findAll();
        List<ClosedQuestionListRow> closedQuestionListRows = new ArrayList<ClosedQuestionListRow>();

        for (ClosedQuestionEntity entity : closedQuestionEntities) {
            ClosedQuestionListRow closedQuestionListRow = toListRow(entity);
            closedQuestionListRows.add(closedQuestionListRow);
        }

        return closedQuestionListRows;
    }

    private ClosedQuestionListRow toListRow(ClosedQuestionEntity entity) {
        ClosedQuestionListRow closedQuestionListRow = new ClosedQuestionListRow();

        closedQuestionListRow.setId(entity.getId());
        closedQuestionListRow.setQuestion(entity.getQuestion());
        UserEntity author = entity.getAuthor();
        UserPersonalDataEntity personalData = author.getUserPersonalData();
        closedQuestionListRow.setAuthorId(author.getId());
        closedQuestionListRow.setAuthorFirstname(personalData.getFirstname());
        closedQuestionListRow.setAuthorLastname(personalData.getLastname());

        return closedQuestionListRow;
    }
}
