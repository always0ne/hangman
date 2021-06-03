package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HangMan {

    private List<String> words;
    private String answer;
    private String maskingAnswer;
    private final List<Integer> maskingIndex;
    private int wrongCount;
    private int successCount;
    private int failCount;

    /***
     * 행멘 게임 객체
     * 게임은 이 객체가 관리를 한다.
     * @param path words 파일 경로
     */
    HangMan(String path) {
        loadWords(path);
        wrongCount = 0;
        successCount = 0;
        failCount = 0;
        maskingIndex = new ArrayList<Integer>();
    }

    /***
     * words 파일을 로드하여 2글자 이상의 단어를 words 리스트에 추가한다.
     * @param path words 파일 경로
     */
    private void loadWords(String path) {
        words = new ArrayList<String>();
        File file = new File(path);

        FileReader fileReader;
        try {
            fileReader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                if (line.length() > 2)
                    words.add(line.toLowerCase());
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 틀림 카운트를 초기화 한 후, 랜덤으로 단어를 찾아 정답으로 지정하며,
     * 숨겨진 문자열을 리턴한다.
     * @return 숨겨진 문자열
     */
    public String initNewWord() {
        wrongCount = 0;
        answer = words.get((int) (Math.random() * words.size()));
        System.out.println(answer); // for debug
        return generateHiddenString();
    }

    /***
     * 정답으로 지정된 단어의 길이를 구해 30%이하의 단어만 숨기기 위해 숨길 단어 수를 정한다.
     * 그 후 maskingIndex에 마스킹된 문자의 위치를 기록 한 후 - 문자로 치환한다.
     * @return 숨겨진 문자열
     */
    private String generateHiddenString() {
        int hiddenNum = (int) (Math.random() * ((double) answer.length() * 0.3 - 1)) + 1;
        StringBuilder newString = new StringBuilder(answer);
        maskingIndex.clear();
        for (int i = 0; i < hiddenNum; i++) {
            int num = (int) (Math.random() * answer.length());
            newString.setCharAt(num, '-');
            maskingIndex.add(num);
        }
        maskingAnswer = newString.toString();
        return maskingAnswer;
    }

    /***
     * 정답 확인하는 기능.
     * maskingIndex 리스트를 순회하며, 일치한 문자열이 있는지 확인하고,
     * 일치하면 - 문자를 원래문자로 돌려놓은 후 deleteList에 추가하며
     * 해당작업이 전부 끝나면 deleteList에 있는 값들을 maskingIndex에서 제거한다.
     * @param pressedKey 입력된 문자
     * @return 정답여부
     */
    public boolean checkAnswer(char pressedKey) {
        int maskingNum = maskingIndex.size();
        List<Integer> deleteList = new ArrayList<>();
        StringBuilder newString = new StringBuilder(maskingAnswer);
        for (int index : maskingIndex)
            if (answer.charAt(index) == pressedKey) {
                newString.setCharAt(index, pressedKey);
                deleteList.add(index);
            }
        for (int index : deleteList)
            maskingIndex.remove(Integer.valueOf(index));
        maskingAnswer = newString.toString();

        if (maskingNum > maskingIndex.size())
            return true;
        else {
            wrongCount++;
            return false;
        }
    }

    /***
     * 현재 단어와 마스킹된 단어가 일치하는지 확인 후,
     * 카운트값을 조작하여 다음단어로 넘어가는지 알린다.
     * @return 다음 단어로 넘어가는지
     */
    public boolean checkGoNextWord() {
        if (answer.equals(maskingAnswer))
            return handleRightAnswer();
        else
            return handleWrongAnswer();
    }

    /***
     * 성공 횟수를 추가하고, 잘못입력한 카운트를 0으로 초기화 하여 true를 리턴한다.
     * @return true
     */
    private boolean handleRightAnswer() {
        successCount++;
        wrongCount = 0;
        return true;
    }

    /***
     * 틀린 횟수를 확인하고, 기회가 초과되었을 시 실패횟수를 증가한 후 true를 리턴한다.
     * 틀린 횟수가 조건을 충족하지 않으면 다음단어로 넘어갈 필요가 없기 때문에 flase를 리턴한다.
     * @return 다음 단어로 넘어가는지
     */
    private boolean handleWrongAnswer() {
        if (wrongCount == 5) {
            failCount++;
            return true;
        } else
            return false;
    }

    /***
     * 카운트를 비교하여 게임이 끝났는지 확인힌다.
     * @return 게임이 끝났는지
     */
    public boolean isGameEnd() {
        return (failCount == 3 || successCount == 10);
    }

    public CountDto getCounts() {
        return new CountDto(wrongCount, successCount, failCount);
    }

    public String getMaskingAnswer() {
        return maskingAnswer;
    }
}
