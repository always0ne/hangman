package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HangMan {

    private final List<String> words;
    private String answer;
    private String maskingAnswer;
    private int wrongCount;
    private int successCount;
    private int failCount;
    private boolean[] ansLetter = new boolean[26];
    
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
    	Arrays.fill(ansLetter, false);
        int hiddenNum = (int) (Math.random() * ((double) answer.length() * 0.3 - 1)) + 1;
        StringBuilder newString = new StringBuilder(answer);
        for (int i = 0; i < hiddenNum; i++) {
        	int num = (int) (Math.random() * answer.length());
        	ansLetter[newString.charAt(num)-97] = true;
            newString.setCharAt(num, '-');
        }
        maskingAnswer = newString.toString();
        System.out.println(maskingAnswer);
        return maskingAnswer;
    }

    public boolean isGameEnd() {
        return (failCount == 3 || successCount == 10);
    }
    
    public String checkPressedKey(String pressKey) {
        StringBuilder newString = new StringBuilder(maskingAnswer);
    	for(int i = 0; i < this.answer.length(); i++) {
    		if(answer.charAt(i) != maskingAnswer.charAt(i) && answer.charAt(i) == pressKey.charAt(0)) {
            	ansLetter[pressKey.charAt(0)-97] = false;
                newString.setCharAt(i, pressKey.charAt(0));
            }
    	}
    	maskingAnswer = newString.toString();
        return maskingAnswer;
    }
    
    public boolean isAnswer(String pressKey) {
    	return ansLetter[pressKey.charAt(0)-97];
    }

    public boolean handleRightAnswer() {
    	if(maskingAnswer.equals(answer)) {
	        successCount++;
	        wrongCount = 0;
	        return true;
    	}else {
    		return false;
    	}
    }

    public boolean handleWrongAnswer() {
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
