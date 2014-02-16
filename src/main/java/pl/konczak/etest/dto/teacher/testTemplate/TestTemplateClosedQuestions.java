package pl.konczak.etest.dto.teacher.testTemplate;

import java.util.HashMap;
import java.util.Map;
import pl.konczak.etest.core.Validate;

public class TestTemplateClosedQuestions {

    private Integer id;
    private String subject;
    private Map<Integer, ClosedQuestionInternal> closedQuestions =
            new HashMap<Integer, ClosedQuestionInternal>();

    public static class ClosedQuestionInternal {

        private String question;
        private boolean alreadyIn;
        private boolean mandatory;

        public ClosedQuestionInternal(String question) {
            this.question = question;
        }

        public String getQuestion() {
            return question;
        }

        public boolean isAlreadyIn() {
            return alreadyIn;
        }

        public boolean isMandatory() {
            return mandatory;
        }

        public void markAsAlreadyIn() {
            this.alreadyIn = true;
        }

        public void markAsMandatory() {
            Validate.isTrue(alreadyIn, "Cannot mark question as mandatory if it is not aleadyIn");
            this.mandatory = true;
        }
    }

    public TestTemplateClosedQuestions(Integer id, String subject) {
        this.id = id;
        this.subject = subject;
    }

    public Integer getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public Map<Integer, ClosedQuestionInternal> getClosedQuestions() {
        return closedQuestions;
    }

    public void addClosedQuestion(Integer closedQuestionId, String question) {
        this.closedQuestions.put(closedQuestionId, new ClosedQuestionInternal(question));
    }

    public void markClosedQuestionAsAlreadyIn(Integer closedQuestionId) {
        Validate.isTrue(closedQuestions.containsKey(closedQuestionId), String.format(
                "ClosedQuestion <%s> is not in map yet"
                + " so cannot mark as already in", closedQuestionId));
        ClosedQuestionInternal closedQuestion = closedQuestions.get(closedQuestionId);
        closedQuestion.markAsAlreadyIn();
    }

    public void markClosedQuestionAsMandatory(Integer closedQuestionId) {
        Validate.isTrue(closedQuestions.containsKey(closedQuestionId), String.format(
                "ClosedQuestion <%s> is not in map yet"
                + " so cannot mark as mandatory", closedQuestionId));
        ClosedQuestionInternal closedQuestion = closedQuestions.get(closedQuestionId);
        Validate.isTrue(closedQuestion.isAlreadyIn(), String.format(
                "ClosedQuestion <%s> is in map but not marked as alreadyIn"
                + " so cannot mark as mandatory", closedQuestionId));
        closedQuestion.markAsMandatory();
    }

    public Integer getCountOfClosedQuestionsAlreadyIn() {
        Integer countOfClosedQuestionsAlreadyIn = 0;
        for (Map.Entry<Integer, ClosedQuestionInternal> entry : closedQuestions.entrySet()) {
            if (entry.getValue().isAlreadyIn()) {
                countOfClosedQuestionsAlreadyIn++;
            }
        }
        return countOfClosedQuestionsAlreadyIn;
    }

    public Integer getCountOfClosedQuestionsMandatory() {
        Integer countOfClosedQuestionsMandatory = 0;
        for (Map.Entry<Integer, ClosedQuestionInternal> entry : closedQuestions.entrySet()) {
            if (entry.getValue().isMandatory()) {
                countOfClosedQuestionsMandatory++;
            }
        }
        return countOfClosedQuestionsMandatory;
    }
}
