package hangman.component;
import javax.swing.*;

import hangman.CountDto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangManButton extends JPanel {
	public static JButton[] letters= new JButton[52];

    public HangManButton() {
        int k=0;
        for (char i = 'a'; i <= 'z'; i++) {
            letters[k] = new JButton(String.valueOf(i));
            letters[k].setFocusable(false);
            letters[k].setFont(new Font("./resource/Bold.ttf", Font.BOLD, 20));
            this.add(letters[k]);
            k++;
        }
        this.setBackground(new Color(210, 161, 26));
        this.setOpaque(true);
    }
    public void setKeyAction(ActionListener a) {
    	try {
	        for (int i = 0; i < 26; i++) {
	        	letters[i].addActionListener(a);
	        }
    	}catch(NullPointerException e) {
    		
    	}
    }

    public void reset() {
    	try {
        for (int i = 0; i < 26; i++) {
        	letters[i].setBackground(new JButton().getBackground());
        	letters[i].setEnabled(true);
        }
    	}catch(NullPointerException e) {
    		
    	}
    }

    public void setCorrectKey(JButton pressedButton) {
        pressedButton.setBackground(Color.GREEN);
        pressedButton.setEnabled(false);
    }

    public void setWrongKey(JButton pressedButton) {
        pressedButton.setBackground(Color.RED);
        pressedButton.setEnabled(false);
    }


    private JLabel generateTextLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
}

