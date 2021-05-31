package hangman.component;
import javax.swing.*;
import java.awt.*;

public class HangManQuestion extends JPanel {

    private static String wordWithHiddenLetters;
    private static JLabel jLabel;
    

    public HangManQuestion(String newstr) {
        this.setBackground(new Color(201, 181, 84));
        this.setOpaque(true);
        StringBuilder sb = new StringBuilder();
        sb.append(newstr);
        wordWithHiddenLetters = sb.toString();
        jLabel = new JLabel(wordWithHiddenLetters);
        jLabel.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 60)); //Comic Sans
        jLabel.setBackground(new Color(201, 181, 84));
        jLabel.setOpaque(true);
        jLabel.setVerticalTextPosition(JLabel.TOP);
        jLabel.setHorizontalTextPosition(JLabel.CENTER);
        this.add(jLabel);
    }
    public JLabel getStringLabel() {
    	return jLabel;
    }
    public void addLabel(ImageIcon icon) {
    	this.jLabel.setIcon(icon);
    }
}
