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

    public GuessPanel getGuessPanel() {
        return guessPanel;
    }

    public void initNewWord(String hiddenString) {
        guessPanel.reset();
        this.hiddenString.updateHiddenString(hiddenString);
    }

    public void setCount(CountDto dto) {
        scoreBoard.updateScoreBoard(dto);
        hangmanImage.updateStep(dto.getWrongCount());
    }

    public void updateCorrect(String maskingAnswer, JButton pressedButton) {
        hiddenString.updateHiddenString(maskingAnswer);
        guessPanel.setCorrectKey(pressedButton);
    }

    public void updateWrong(JButton pressedButton) {
        guessPanel.setWrongKey(pressedButton);
    }

    public void alert(String title, String message) {
        JOptionPane.showMessageDialog(
                this, message,
                title, JOptionPane.INFORMATION_MESSAGE
        );
    }
}
