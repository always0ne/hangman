import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HangMan {

	private List<String> words;
	private String answer;
	private String hiddenString;
	private int wrongCount;
	private int successCount;
	private int failCount;

	HangMan() {
		words = loadWords("./words.txt");
		wrongCount = 0;
		successCount = 0;
		failCount = 0;
	}

	/*
	 * 초기 .txt파일 불러와 리스트에 저장
	 */
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
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		hiddenString = newString.toString();
		return hiddenString;
	}

	/*
	 * 게임 종료 확인
	 */
	public boolean isGameEnd() {
		if (failCount == 3 || successCount == 10) {
			failCount = 0;
			successCount = 0;
			return true;
		} else
			return false;
	}

	/*
	 * 단어 맞추기 실패 확인
	 */
	public boolean isFail() {
		if (wrongCount == 5) {
			failCount++;
			return true;
		} else
			return false;
	}

	/*
	 * 단어 맞추기 성공
	 */
	public boolean isAnswer(String submit) {
		if (answer.equals(submit)) {
			successCount++;
			return true;
		} else {
			wrongCount++;
			return false;
		}
	}
}
