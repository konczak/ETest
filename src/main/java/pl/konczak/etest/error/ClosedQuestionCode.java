package pl.konczak.etest.error;

public enum ClosedQuestionCode
        implements ErrorCode {

    DOES_NOT_HAVE_ANSWERS(101),
    DOES_NOT_HAVE_ANY_CORRECT_ANSWERS(102);
    private int number;

    private ClosedQuestionCode(int number) {
        this.number = number;
    }

    @Override
    public int getNumber() {
        return number;
    }
}
