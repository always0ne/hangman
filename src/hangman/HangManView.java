package hangman;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hangman.component.*;


public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;
    private final HangManImage hangmanImage;

	private final HangManQuestion hangmanQuestion;
	private final GuessPanel guessPanel;

	HangManView() {
		
		setSize(520, 700);
		setTitle("Hangman");
		setIconImage(new ImageIcon("images\\rope.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        hangmanQuestion =new HangManQuestion( "question");
        hangmanQuestion.setBounds(0, 0, 510, 500);
        add(hangmanQuestion);

        guessPanel=new GuessPanel();
        guessPanel.setBounds(0, 500, 510, 200);
        add(guessPanel);

        hangmanImage = new HangManImage();
        add(hangmanImage);

        setVisible(true);
    }

    public GuessPanel getGuessPanel() {
        return guessPanel;
    }

    public void setCount(CountDto dto) {
    	hangmanQuestion.setSuccessCount(String.valueOf(dto.getSuccessCount()));
    	hangmanQuestion.setFailCount(String.valueOf(dto.getFailCount()));

        hangmanQuestion.addLabel(hangmanImage.updateStep(dto.getWrongCount()));
    }

    public void initNewWord(String hiddenString) {
    	guessPanel.reset();
    	hangmanQuestion.setQuestion(hiddenString);
    }

    public void updateCorrect(String maskingAnswer, JButton pressedButton) {
    	hangmanQuestion.setQuestion(maskingAnswer);
        guessPanel.setCorrectKey(pressedButton);
    }

    public void updateInCorrect(JButton pressedButton) {
        guessPanel.setWrongKey(pressedButton);
    }

    public void alert(String title, String message) {
        JOptionPane.showMessageDialog(
                this, message,
                title, JOptionPane.INFORMATION_MESSAGE
        );
    }
}
