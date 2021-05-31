package hangman;

import hangman.component.*;

import javax.swing.*;
import java.awt.*;

public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;
    private String hiddenText;
    private final HangManImage hangman;

	private HangManQuestion hangmanPanel;
	private HangManButton guessPanel;

	HangManView() {
        this.setSize(1100, 800);
        this.setTitle("Hangman");
        this.setIconImage(new ImageIcon("images\\rope.png").getImage());
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hiddenText = "question";
        HangManQuestion hangmanPanel=new HangManQuestion(hiddenText);
        this.hangmanPanel=hangmanPanel;
        hangmanPanel.setBounds(0, 0, 700, 800);
        HangManButton guessPanel=new HangManButton();
        this.guessPanel=guessPanel;
        guessPanel.setBounds(700, 0, 400, 800);

        hangman = new HangManImage();
        this.add(this.hangman);

        this.add(this.hangmanPanel);
        this.add(this.guessPanel);
        this.setResizable(false);
        this.setVisible(true);
    }

    public HangManButton getKeyBoard() {
        return guessPanel;
    }

    private void setCount(CountDto dto) {
    	//guessPanel.setCount(dto);
        ImageIcon s= hangman.updateStep(dto.getWrongCount());
        hangmanPanel.addLabel(s);
    }

    public void initNewWord(String hiddenString, CountDto counts) {
    	guessPanel.reset();
    	hangmanPanel.getStringLabel().setText(hiddenString);
        setCount(counts);
    }

    public void updateCorrect(String maskingAnswer, JButton pressedButton, CountDto counts) {
    	hangmanPanel.getStringLabel().setText(maskingAnswer);
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
