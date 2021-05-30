package hangman;

import hangman.component.HangManImage;
import hangman.component.KeyBoard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;
    private final JLabel hiddenText;
    private final HangManImage hangman;
    private final KeyBoard keyBoard;

    HangManView() {
        setTitle("hangman.HangMan");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel center = new JPanel();
        hiddenText = new JLabel("question");
        hiddenText.setFont(new Font("MS Sans Serif", Font.BOLD, 30));
        hiddenText.setHorizontalAlignment(SwingConstants.CENTER);
        hiddenText.setBackground(new Color(0xc9, 0xb5, 0x54));
        hiddenText.setOpaque(true);

        hangman = new HangManImage();
        hangman.setBackground(new Color(0xc9, 0xb5, 0x54));
        center.setBackground(new Color(0xc9, 0xb5, 0x54));
        center.add(hiddenText);
        center.add(hangman);
        getContentPane().add(center, BorderLayout.CENTER);

        keyBoard = new KeyBoard();
        keyBoard.setBackground(new Color(0xc9, 0xb5, 0x54));
        keyBoard.setBorder(new EmptyBorder(0, 5, 20, 5));
        getContentPane().add(keyBoard, BorderLayout.SOUTH);
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
