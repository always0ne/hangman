package hangman.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GuessPanel extends JPanel {
	public static JButton[] letters= new JButton[52];

    public GuessPanel() {
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

}

