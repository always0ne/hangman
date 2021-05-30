package hangman;

import hangman.component.HangManImage;
import hangman.component.KeyBoard;

import javax.swing.*;
import java.awt.*;

public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;
    private final JLabel hiddenText;
    private final HangManImage hangman;
    private final KeyBoard keyBoard;

    HangManView() {
        setTitle("hangman.HangMan");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hiddenText = new JLabel("question");
        hiddenText.setHorizontalAlignment(SwingConstants.CENTER);
        hiddenText.setBackground(new Color(0xff, 0x98, 0));
        getContentPane().add(hiddenText, BorderLayout.NORTH);

        hangman = new HangManImage();
        getContentPane().add(hangman, BorderLayout.CENTER);

        keyBoard = new KeyBoard();
        keyBoard.setBackground(new Color(201, 181, 84));
        getContentPane().add(keyBoard, BorderLayout.EAST);
        setResizable(false);
        setVisible(true);
    }

    public KeyBoard getKeyBoard() {
        return keyBoard;
    }

    private void setCount(CountDto dto) {
        keyBoard.setCount(dto);
        hangman.updateStep(dto.getWrongCount());
    }

    public void initNewWord(String hiddenString, CountDto counts) {
        keyBoard.reset();
        hiddenText.setText(hiddenString);
        setCount(counts);
    }

    public void updateCorrect(String maskingAnswer, JButton pressedButton, CountDto counts) {
        hiddenText.setText(maskingAnswer);
        getKeyBoard().setCorrectKey(pressedButton);
        setCount(counts);
    }

    public void updateInCorrect(JButton pressedButton, CountDto counts) {
        getKeyBoard().setWrongKey(pressedButton);
        setCount(counts);
    }

    public void alert(String title, String message) {
        JOptionPane.showMessageDialog(
                this, message,
                title, JOptionPane.INFORMATION_MESSAGE
        );
    }
}
