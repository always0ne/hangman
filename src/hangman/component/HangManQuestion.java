package hangman.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HangManQuestion extends JPanel {

    private final JLabel question;
    private final JLabel successCountLabel;
    private final JLabel failCountLabel;
    
    public HangManQuestion(String hiddenString) {
        this.setBackground(new Color(201, 181, 84));
        this.setOpaque(true);
        question = new JLabel(hiddenString);
        question.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 60)); //Comic Sans
        question.setBackground(new Color(201, 181, 84));
        question.setOpaque(true);
        question.setVerticalTextPosition(JLabel.TOP);
        question.setHorizontalTextPosition(JLabel.CENTER);
        this.add(question);
        
        Box box = Box.createVerticalBox();
        Box horizontalBox=Box.createHorizontalBox();
        
        JLabel label1 = new JLabel("Success:");
        successCountLabel = new JLabel("10");
        
        label1.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));
        label1.setAlignmentX(Component.RIGHT_ALIGNMENT);

        successCountLabel.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));
        
        horizontalBox.add(label1);
        horizontalBox.add(successCountLabel);
        

        Box horizontalBox2=Box.createHorizontalBox();

        JLabel label3 = new JLabel("Fail:    ");
        failCountLabel = new JLabel("10");
        
        label3.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));
        label3.setAlignmentX(Component.RIGHT_ALIGNMENT);
       
        failCountLabel.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));
        
        horizontalBox2.add(label3);
        horizontalBox2.add(failCountLabel);
        
        box.add(horizontalBox);
        box.add(horizontalBox2);

        this.add(box);
    }

    public void setQuestion(String q) {
    	this.question.setText(q);
    }

    public void setSuccessCount(String cnt) {
    	this.successCountLabel.setText(cnt);
    }
    
    public void setFailCount(String cnt) {
    	this.failCountLabel.setText(cnt);
    }

    public void addLabel(ImageIcon icon) {
    	this.question.setIcon(icon);
    }
}
