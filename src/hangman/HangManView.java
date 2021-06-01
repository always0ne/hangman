package hangman;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import hangman.component.*;


public class HangManView extends JFrame {
    private static final long serialVersionUID = -6149601216998660790L;
    private String hiddenText;
    private final HangManImage hangman;

	private HangManQuestion hangmanPanel;
	private HangManButton guessPanel;

	HangManView() {
		
		this.setSize(520, 700);
		this.setTitle("Hangman");
		this.setIconImage(new ImageIcon("images\\rope.png").getImage());
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        hiddenText = "question";
        HangManQuestion hangmanPanel=new HangManQuestion(hiddenText);
        this.hangmanPanel=hangmanPanel;
        HangManButton guessPanel=new HangManButton();
        hangmanPanel.setBounds(0, 0, 510, 500);
        this.guessPanel=guessPanel;
        guessPanel.setBounds(0, 500, 510, 200);

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

    public void setCount(CountDto dto) {
    	hangmanPanel.setSuccessCount(String.valueOf(dto.getSuccessCount()));
    	hangmanPanel.setFailCount(String.valueOf(dto.getFailCount()));

        hangmanPanel.addLabel(hangman.updateStep(dto.getWrongCount()));
    }

    public void initNewWord(String hiddenString) {
    	guessPanel.reset();
    	hangmanPanel.setQuestion(hiddenString);
    }

    public void updateCorrect(String maskingAnswer, JButton pressedButton) {
    	hangmanPanel.setQuestion(maskingAnswer);
        getKeyBoard().setCorrectKey(pressedButton);
    }

    public void updateInCorrect(JButton pressedButton) {
        getKeyBoard().setWrongKey(pressedButton);
    }

    public void alert(String title, String message) {
        JOptionPane.showMessageDialog(
                this, message,
                title, JOptionPane.INFORMATION_MESSAGE
        );
    }
}
