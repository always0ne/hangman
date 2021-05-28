package hangman;

import hangman.component.HangManImage;
import hangman.component.InputBox;
import hangman.component.ScoreBoard;

import javax.swing.*;
import java.awt.*;

public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;
    private final JLabel hiddenText;
    private final HangManImage hangman;
    private final ScoreBoard scoreBoard;
    private final InputBox inputBox;

    HangManView() {
        setTitle("hangman.HangMan");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hiddenText = new JLabel("question");
        hiddenText.setHorizontalAlignment(SwingConstants.CENTER);
        hiddenText.setBackground(new Color(0xff, 0x98, 0));
        getContentPane().add(hiddenText, BorderLayout.NORTH);

        hangman = new HangManImage();
        getContentPane().add(hangman, BorderLayout.CENTER);

        scoreBoard = new ScoreBoard();
        getContentPane().add(scoreBoard, BorderLayout.EAST);

        inputBox = new InputBox();
        getContentPane().add(inputBox, BorderLayout.SOUTH);

        setVisible(true);
    }

    public InputBox getInputField() {
        return inputBox;
    }

    public void setHiddenText(String str) {
        hiddenText.setText(str);
    }

    public void setCount(CountDto dto) {
        scoreBoard.setCount(dto);
        hangman.updateStep(dto.getWrongCount());
    }
}
