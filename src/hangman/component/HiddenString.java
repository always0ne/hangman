package hangman.component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class HiddenString extends JPanel {

    private final JLabel hiddenString;

    public HiddenString() {
        setBackground(new Color(201, 181, 84));
        hiddenString = new JLabel();
        hiddenString.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 60)); //Comic Sans
        add(hiddenString);
    }

    public void updateHiddenString(String newString) {
        this.hiddenString.setText(newString);
    }
}
