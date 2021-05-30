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
        getContentPane().add(keyBoard, BorderLayout.EAST);

        setVisible(true);
    }

    public KeyBoard getKeyBoard() {
    	return keyBoard;
    }

    public void setHiddenText(String str) {
    	keyBoard.reset();
        hiddenText.setText(str);
    }
    
    public void changeHiddenText(String str) {
        hiddenText.setText(str);
    }

    public void setCount(CountDto dto) {
    	keyBoard.setCount(dto);
        hangman.updateStep(dto.getWrongCount());
    }
}
