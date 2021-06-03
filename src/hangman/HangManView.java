package hangman;

import hangman.component.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;

    private final HiddenString hiddenString;
    private final HangManImage hangmanImage;
    private final ScoreBoard scoreBoard;
    private final GuessPanel guessPanel;

    /***
     * HangMan 게임의 화면을 담당한다.
     * 생성자에서는 화면을 구성하는데 필요한 요소들을 초기화 하는 과정을 거친다.
     * HiddenString객체는 문제 문자열을 표시
     * HangManImage 객체는 이미지를 표시
     * ScoreBoard 객체는 점수판을 표시
     * GuessPanel은 키 입력을 받는 부분을 표시한다.
     */
    HangManView() {
        setSize(520, 700);
        setTitle("Hangman");
        setIconImage(new ImageIcon("images\\rope.png").getImage());
        setLayout(null);
        getContentPane().setBackground(new Color(201, 181, 84));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        hiddenString = new HiddenString();
        hiddenString.setBounds(0, 0, 520, 100);
        add(hiddenString);

        hangmanImage = new HangManImage();
        hangmanImage.setBounds(0, 95, 407, 388);
        add(hangmanImage);

        scoreBoard = new ScoreBoard();
        scoreBoard.setBounds(400, 325, 120, 50);
        add(scoreBoard);

        guessPanel = new GuessPanel();
        guessPanel.setBounds(0, 500, 510, 200);
        add(guessPanel);

        setVisible(true);
    }

    /***
     * 키 입력의 이벤트리스너를 컨트롤러에서 등록하기 위해 생성한 getter 이다.
     * @return 키 입력 받는 패널
     */
    public GuessPanel getGuessPanel() {
        return guessPanel;
    }

    /***
     * 새로운 단어가 초기화될때 화면을 새로 그리는 기능을 한다.
     * 키 입력을 받는 guessPanel의 상태를 전부 초기화 한 후, 문자열을 업데이트 한다.
     * @param hiddenString 단어가 숨겨진 문자열
     */
    public void initNewWord(String hiddenString) {
        guessPanel.reset();
        this.hiddenString.updateHiddenString(hiddenString);
    }

    /***
     * 카운트 정보를 업데이트 한다.
     * 접수판에 점수를 업데이트 요청을 한 후, 이미지 변경 요청을 한다.
     * @param dto 카운트 정보를 가지고 있는 Data Transfer Object
     */
    public void updateCount(CountDto dto) {
        scoreBoard.updateScoreBoard(dto);
        hangmanImage.updateStep(dto.getWrongCount());
    }

    /***
     * 누른 키가 정답이었을 때 업데이트된 문자열을 갱신하며,
     * 키 입력을 받는 guessPanel에 맞는 키 표시를 요청한다.
     * @param maskingAnswer 업데이트 된 문자열
     * @param pressedButton 변경할 버튼
     */
    public void updateCorrect(String maskingAnswer, JButton pressedButton) {
        hiddenString.updateHiddenString(maskingAnswer);
        guessPanel.setCorrectKey(pressedButton);
    }

    /***
     * 누른 키가 정답이 아닐 때 guessPanel에 틀린 키 표시를 요청한다.
     * @param pressedButton 변경할 버튼
     */
    public void updateWrong(JButton pressedButton) {
        guessPanel.setWrongKey(pressedButton);
    }

    /***
     * 사용자에게 간단한 알림창을 제공한다.
     * @param title 창 제목
     * @param message 창 메시지
     */
    public void alert(String title, String message) {
        JOptionPane.showMessageDialog(
                this, message,
                title, JOptionPane.INFORMATION_MESSAGE
        );
    }
}
