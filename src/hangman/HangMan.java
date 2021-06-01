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
    private String maskingAnswer;
    private final List<Integer> maskingIndex;
    private int wrongCount;
    private int successCount;
    private int failCount;

    HangMan(String path) {
        words = loadWords(path);
        wrongCount = 0;
        successCount = 0;
        failCount = 0;
        maskingIndex = new ArrayList<Integer>();
    }

    private List<String> loadWords(String path) {
        List<String> fileInputs = new ArrayList<String>();
        File file = new File(path);

        FileReader fileReader;
        try {
            fileReader = new FileReader(file);

            BufferedReader bufReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                if (line.length() > 2)
                    fileInputs.add(line.toLowerCase());
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileInputs;
    }

    public String initNewWord() {
        wrongCount = 0;
        answer = words.get((int) (Math.random() * words.size()));
        System.out.println(answer); // for debug
        return generateHiddenString();
    }

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

    public boolean checkGoNextWord() {
        if (answer.equals(maskingAnswer))
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
        if (wrongCount == 5) {
            failCount++;
            return true;
        } else
            return false;
    }

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
