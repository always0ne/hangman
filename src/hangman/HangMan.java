package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HangMan {

    private final List<String> words;
    private String answer;
    private int wrongCount;
    private int successCount;
    private int failCount;

    HangMan(String path) {
        words = loadWords(path);
        wrongCount = 0;
        successCount = 0;
        failCount = 0;
    }

    private List<String> loadWords(String path) {
        List<String> fileInputs = new ArrayList<String>();
        File file = new File(path);

        FileReader fileReader;
        try {
            fileReader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(fileReader);
            String line = "";
            // bufferedReader의 마지막까지 list에 넣음
            while ((line = bufReader.readLine()) != null)
                // 2글자 이하 단허들은 걸러냄
                if (line.length() > 2)
                    fileInputs.add(line);
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInputs;
    }

    /*
     * 랜덤한 단어 선정
     */
    public String initNewWord() {
        wrongCount = 0;
        answer = words.get((int) (Math.random() * words.size()));
        System.out.println(answer); // for debug
        return generateHiddenString();
    }

    /*
     * 단어의 일부 "-"로 변경
     */
    private String generateHiddenString() {
        int hiddenNum = (int) (Math.random() * ((double) answer.length() * 0.3 - 1)) + 1;
        StringBuilder newString = new StringBuilder(answer);
        for (int i = 0; i < hiddenNum; i++)
            newString.setCharAt((int) (Math.random() * answer.length()), '-');
        return newString.toString();
    }

    /*
     * 게임 종료 확인
     */
    public boolean isGameEnd() {
        return (failCount == 3 || successCount == 10);
    }

    public boolean submitAndGoNextWord(String submit) {
        if (answer.equals(submit))
            return handleRightAnswer();
        else
            return handleWrongAnswer();
    }

    private boolean handleRightAnswer() {
        successCount++;
        wrongCount = 0;
        return true;
    }

    private boolean handleWrongAnswer() {
        if (++wrongCount == 5) {
            failCount++;
            wrongCount = 0;
            return true;
        } else
            return false;
    }

    public CountDto getCounts() {
        return new CountDto(wrongCount, successCount, failCount);
    }
}
