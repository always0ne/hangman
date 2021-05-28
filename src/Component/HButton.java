package component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;

public class HButton extends JButton {
    public HButton(String name) {
        this.setText(name);
        this.setBackground(new Color(0xff, 0xb7, 0x4d, 200));
        Border btnBorder = BorderFactory.createLineBorder(new Color(255, 224, 178, 100), 1);
        this.setBorder(btnBorder);
    }
}
