package hangman;

public class CountDto {
    private final int wrongCount;
    private final int successCount;
    private final int failCount;

    /***
     * 점수 정보를 전송하는데 사용하는 객체이다.
     * 모든 정보는 생성되는 순간 불변이며, getter를 통해 접근할수밖에 없다.
     * @param wrongCount 틀린 횟수
     * @param successCount 성공한 횟수
     * @param failCount 실패한 횟수ㄴ
     */
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
