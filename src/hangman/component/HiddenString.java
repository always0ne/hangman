package hangman.component;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

public class HiddenString extends JPanel {

    private final JLabel hiddenString;

    /***
     * 문제 문자열을 출력하는 패널이다.
     */
    public HiddenString() {
        setBackground(new Color(201, 181, 84));
        hiddenString = new JLabel();
        hiddenString.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 60)); //Comic Sans
        add(hiddenString);
    }

    /***
     * 문제 문자열을 업데이트 한다.
     * @param newString 새로운 문자열
     */
    public void updateHiddenString(String newString) {
        this.hiddenString.setText(newString);
    }
}
