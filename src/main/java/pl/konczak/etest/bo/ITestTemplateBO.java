package pl.konczak.etest.bo;

import pl.konczak.etest.entity.TestTemplateEntity;

public interface ITestTemplateBO {

    TestTemplateEntity add(String subject, Integer authorId);

    TestTemplateEntity addClosedQuestion(Integer id, Integer closedQuestionId);

    TestTemplateEntity removeClosedQuestion(Integer id, Integer closedQuestionId);

    TestTemplateEntity changeClosedQuestionStatusOfMandatory(
            Integer id, Integer closedQuestionId, boolean mandatory);

    void remove(Integer id);
}
