package hangman.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import hangman.CountDto;

public class KeyBoard extends JPanel{
    private final GridBagLayout gBag;
	private static JButton[] key = new JButton[26];
    private final JLabel successCountLabel;
    private final JLabel failCountLabel;
	
	public KeyBoard() {
		gBag = new GridBagLayout();
		this.setLayout(gBag);
        JLabel success = generateTextLabel("success");
        add(success);
        gbInsert(success, 0, 1, 2, 1);

        successCountLabel = generateNumberLabel();
        gbInsert(successCountLabel, 0, 2, 2, 1);
        add(successCountLabel);

        JLabel fail = generateTextLabel("fail");
        add(fail);
        gbInsert(fail, 0, 3, 2, 1);

        failCountLabel = generateNumberLabel();
        gbInsert(failCountLabel, 0, 4, 2, 1);
        add(failCountLabel);
		
		for(int i = 0; i < 26; i++) {
			key[i] = new JButton(String.valueOf((char)(i+97)));
			key[i].setFocusable(false);
			gbInsert(key[i], i%2, i/2+5, 1, 1);
			this.add(key[i]);
			key[i].setBackground(Color.GRAY);
		}
	}
	
	public void setKeyAction(ActionListener a) {
		for(int i = 0; i < 26; i++) {
			key[i].addActionListener(a);
		}
	}
	
	public void reset() {
		for(int i = 0; i < 26; i++) {
			key[i].setBackground(Color.GRAY);
			key[i].setEnabled(true);
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

    public void setCount(CountDto dto) {
        successCountLabel.setText(String.valueOf(dto.getSuccessCount()));
        failCountLabel.setText(String.valueOf(dto.getFailCount()));
    }
    
    public void wrong(JButton pressedButton) {
        pressedButton.setBackground(Color.GREEN);
        pressedButton.setEnabled(false);
    }
    
    private JLabel generateNumberLabel() {
        JLabel label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }
    
    private JLabel generateTextLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        return label;
    }
	
    private void gbInsert(Component c, int x, int y, int w, int h) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gBag.setConstraints(c, gbc);
    }

}
