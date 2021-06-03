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
     * ��� ���� ��ü
     * ������ �� ��ü�� ������ �Ѵ�.
     * @param path words ���� ���
     */
    HangMan(String path) {
        loadWords(path);
        wrongCount = 0;
        successCount = 0;
        failCount = 0;
        maskingIndex = new ArrayList<Integer>();
    }

    /***
     * words ������ �ε��Ͽ� 2���� �̻��� �ܾ words ����Ʈ�� �߰��Ѵ�.
     * @param path words ���� ���
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
     * Ʋ�� ī��Ʈ�� �ʱ�ȭ �� ��, �������� �ܾ ã�� �������� �����ϸ�,
     * ������ ���ڿ��� �����Ѵ�.
     * @return ������ ���ڿ�
     */
    public String initNewWord() {
        wrongCount = 0;
        answer = words.get((int) (Math.random() * words.size()));
        System.out.println(answer); // for debug
        return generateHiddenString();
    }

    /***
     * �������� ������ �ܾ��� ���̸� ���� 30%������ �ܾ ����� ���� ���� �ܾ� ���� ���Ѵ�.
     * �� �� maskingIndex�� ����ŷ�� ������ ��ġ�� ��� �� �� - ���ڷ� ġȯ�Ѵ�.
     * @return ������ ���ڿ�
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
     * ���� Ȯ���ϴ� ���.
     * maskingIndex ����Ʈ�� ��ȸ�ϸ�, ��ġ�� ���ڿ��� �ִ��� Ȯ���ϰ�,
     * ��ġ�ϸ� - ���ڸ� �������ڷ� �������� �� deleteList�� �߰��ϸ�
     * �ش��۾��� ���� ������ deleteList�� �ִ� ������ maskingIndex���� �����Ѵ�.
     * @param pressedKey �Էµ� ����
     * @return ���俩��
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
     * ���� �ܾ�� ����ŷ�� �ܾ ��ġ�ϴ��� Ȯ�� ��,
     * ī��Ʈ���� �����Ͽ� �����ܾ�� �Ѿ���� �˸���.
     * @return ���� �ܾ�� �Ѿ����
     */
    public boolean checkGoNextWord() {
        if (answer.equals(maskingAnswer))
            return handleRightAnswer();
        else
            return handleWrongAnswer();
    }

    /***
     * ���� Ƚ���� �߰��ϰ�, �߸��Է��� ī��Ʈ�� 0���� �ʱ�ȭ �Ͽ� true�� �����Ѵ�.
     * @return true
     */
    private boolean handleRightAnswer() {
        successCount++;
        wrongCount = 0;
        return true;
    }

    /***
     * Ʋ�� Ƚ���� Ȯ���ϰ�, ��ȸ�� �ʰ��Ǿ��� �� ����Ƚ���� ������ �� true�� �����Ѵ�.
     * Ʋ�� Ƚ���� ������ �������� ������ �����ܾ�� �Ѿ �ʿ䰡 ���� ������ flase�� �����Ѵ�.
     * @return ���� �ܾ�� �Ѿ����
     */
    private boolean handleWrongAnswer() {
        if (wrongCount == 5) {
            failCount++;
            return true;
        } else
            return false;
    }

    /***
     * ī��Ʈ�� ���Ͽ� ������ �������� Ȯ������.
     * @return ������ ��������
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
