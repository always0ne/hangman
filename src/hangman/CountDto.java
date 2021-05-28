package hangman;

public class CountDto {
    private final int wrongCount;
    private final int successCount;
    private final int failCount;

    CountDto(int wrongCount, int successCount, int failCount) {
        this.wrongCount = wrongCount;
        this.successCount = successCount;
        this.failCount = failCount;
    }

    public int getWrongCount() {
        return wrongCount;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public int getFailCount() {
        return failCount;
    }
}
