package pl.konczak.etest.error;

public enum UserExamCode
        implements ErrorCode {

    EXAM_ALREADY_FINISHED(201),
    QUESTION_ALREADY_ANSWERED(202);
    private int number;

    private UserExamCode(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
