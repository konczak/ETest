package pl.konczak.etest.dto.teacher.userExam;

import java.util.ArrayList;
import java.util.List;

public class UserExamPreview {

    private Integer id;
    private String testTemplateSubject;
    private String userFirstname;
    private String userLastname;
    private Integer resultPoints;
    private Integer maxPoints;
    private List<UserExamClosedQuestion> userExamClosedQuestions = new ArrayList<UserExamClosedQuestion>();

    public static class UserExamClosedQuestion {

        private Integer userExamClosedQuestionId;
        private Integer closedQuestionId;
        private String question;
        private Integer resultPoints;
        private Integer maxPoints;
        private List<UserExamClosedAnswer> userExamClosedAnswers = new ArrayList<UserExamClosedAnswer>();

        public static class UserExamClosedAnswer {

            private Integer userExamClosedAnswerId;
            private Integer closedAnswerId;
            private String answer;
            private boolean correct;
            private boolean userAnswer;

            public UserExamClosedAnswer(Integer userExamClosedAnswerId, Integer closedAnswerId,
                    String answer,
                    boolean correct, boolean userAnswer) {
                this.userExamClosedAnswerId = userExamClosedAnswerId;
                this.closedAnswerId = closedAnswerId;
                this.answer = answer;
                this.correct = correct;
                this.userAnswer = userAnswer;
            }

            public Integer getUserExamClosedAnswerId() {
                return userExamClosedAnswerId;
            }

            public Integer getClosedAnswerId() {
                return closedAnswerId;
            }

            public String getAnswer() {
                return answer;
            }

            public boolean isCorrect() {
                return correct;
            }

            public boolean isUserAnswer() {
                return userAnswer;
            }
        }

        public UserExamClosedQuestion(Integer userExamClosedQuestionId, Integer closedQuestionId,
                String question,
                Integer resultPoints, Integer maxPoints) {
            this.userExamClosedQuestionId = userExamClosedQuestionId;
            this.closedQuestionId = closedQuestionId;
            this.question = question;
            this.resultPoints = resultPoints;
            this.maxPoints = maxPoints;
        }

        public Integer getUserExamClosedQuestionId() {
            return userExamClosedQuestionId;
        }

        public Integer getClosedQuestionId() {
            return closedQuestionId;
        }

        public String getQuestion() {
            return question;
        }

        public Integer getResultPoints() {
            return resultPoints;
        }

        public Integer getMaxPoints() {
            return maxPoints;
        }

        public List<UserExamClosedAnswer> getUserExamClosedAnswers() {
            return userExamClosedAnswers;
        }

        public void addUserExamClosedAnswer(Integer userExamClosedAnswerId, Integer closedAnswerId,
                String answer, boolean correct, boolean userAnswer) {
            this.userExamClosedAnswers.add(
                    new UserExamClosedAnswer(userExamClosedAnswerId, closedAnswerId, answer, correct, userAnswer));
        }
    }

    public UserExamPreview(Integer id, String testTemplateSubject,
            String userFirstname, String userLastname,
            Integer resultPoints, Integer maxPoints) {
        this.id = id;
        this.testTemplateSubject = testTemplateSubject;
        this.userFirstname = userFirstname;
        this.userLastname = userLastname;
        this.resultPoints = resultPoints;
        this.maxPoints = maxPoints;
    }

    public Integer getId() {
        return id;
    }

    public String getTestTemplateSubject() {
        return testTemplateSubject;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public Integer getResultPoints() {
        return resultPoints;
    }

    public Integer getMaxPoints() {
        return maxPoints;
    }

    public List<UserExamClosedQuestion> getUserExamClosedQuestions() {
        return userExamClosedQuestions;
    }

    public UserExamClosedQuestion addUserExamClosedQuestion(Integer userExamClosedQuestionId, Integer closedQuestionId,
            String question, Integer resultPoints, Integer maxPoints) {
        UserExamClosedQuestion userExamClosedQuestion =
                new UserExamClosedQuestion(userExamClosedQuestionId, closedQuestionId, question, resultPoints, maxPoints);
        this.userExamClosedQuestions.add(userExamClosedQuestion);

        return userExamClosedQuestion;
    }
}
