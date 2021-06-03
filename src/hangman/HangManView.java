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
     * HangMan ������ ȭ���� ����Ѵ�.
     * �����ڿ����� ȭ���� �����ϴµ� �ʿ��� ��ҵ��� �ʱ�ȭ �ϴ� ������ ��ģ��.
     * HiddenString��ü�� ���� ���ڿ��� ǥ��
     * HangManImage ��ü�� �̹����� ǥ��
     * ScoreBoard ��ü�� �������� ǥ��
     * GuessPanel�� Ű �Է��� �޴� �κ��� ǥ���Ѵ�.
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
     * Ű �Է��� �̺�Ʈ�����ʸ� ��Ʈ�ѷ����� ����ϱ� ���� ������ getter �̴�.
     * @return Ű �Է� �޴� �г�
     */
    public GuessPanel getGuessPanel() {
        return guessPanel;
    }

    /***
     * ���ο� �ܾ �ʱ�ȭ�ɶ� ȭ���� ���� �׸��� ����� �Ѵ�.
     * Ű �Է��� �޴� guessPanel�� ���¸� ���� �ʱ�ȭ �� ��, ���ڿ��� ������Ʈ �Ѵ�.
     * @param hiddenString �ܾ ������ ���ڿ�
     */
    public void initNewWord(String hiddenString) {
        guessPanel.reset();
        this.hiddenString.updateHiddenString(hiddenString);
    }

    /***
     * ī��Ʈ ������ ������Ʈ �Ѵ�.
     * �����ǿ� ������ ������Ʈ ��û�� �� ��, �̹��� ���� ��û�� �Ѵ�.
     * @param dto ī��Ʈ ������ ������ �ִ� Data Transfer Object
     */
    public void updateCount(CountDto dto) {
        scoreBoard.updateScoreBoard(dto);
        hangmanImage.updateStep(dto.getWrongCount());
    }

    /***
     * ���� Ű�� �����̾��� �� ������Ʈ�� ���ڿ��� �����ϸ�,
     * Ű �Է��� �޴� guessPanel�� �´� Ű ǥ�ø� ��û�Ѵ�.
     * @param maskingAnswer ������Ʈ �� ���ڿ�
     * @param pressedButton ������ ��ư
     */
    public void updateCorrect(String maskingAnswer, JButton pressedButton) {
        hiddenString.updateHiddenString(maskingAnswer);
        guessPanel.setCorrectKey(pressedButton);
    }

    /***
     * ���� Ű�� ������ �ƴ� �� guessPanel�� Ʋ�� Ű ǥ�ø� ��û�Ѵ�.
     * @param pressedButton ������ ��ư
     */
    public void updateWrong(JButton pressedButton) {
        guessPanel.setWrongKey(pressedButton);
    }

    /***
     * ����ڿ��� ������ �˸�â�� �����Ѵ�.
     * @param title â ����
     * @param message â �޽���
     */
    public void alert(String title, String message) {
        JOptionPane.showMessageDialog(
                this, message,
                title, JOptionPane.INFORMATION_MESSAGE
        );
    }
}
