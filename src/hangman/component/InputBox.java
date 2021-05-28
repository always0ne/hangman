package hangman.component;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class InputBox extends JPanel{

    private final GridBagLayout gBag;
    private JButton submitBtn;
    private JTextField inputField;

    public InputBox(){
        gBag = new GridBagLayout();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(gBag);
        setBackground(new Color(255, 224, 178, 100));

        initAnswerLabel();
        initInputField();
        initSubmitBtn();
    }

    private void initAnswerLabel(){
        JLabel ans = new JLabel("정답");
        Border border = BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 5);
        ans.setBorder(border);
        ans.setHorizontalAlignment(JLabel.RIGHT);
        gbInsert(ans, 0, 1, 1, 1);
        add(ans);
    }

    private void initInputField(){
        inputField = new JTextField("", 10);
        inputField.setBorder(BorderFactory.createLineBorder(new Color(255, 224, 178, 100), 1));
        gbInsert(inputField, 1, 1, 1, 1);
        add(inputField);
    }

    private void initSubmitBtn(){
        submitBtn = new JButton("submit");
        submitBtn.setBackground(new Color(0xff, 0xb7, 0x4d, 200));
        submitBtn.setBorder(BorderFactory.createLineBorder(new Color(255, 224, 178, 100), 1));
        gbInsert(this.submitBtn, 2, 1, 1, 1);
        add(this.submitBtn);
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


    public JButton getSubmitBtn() {
        return submitBtn;
    }

    public JTextField getInputField() {
        return inputField;
    }

    public void clearInput(){
        inputField.setText("");
    }
}
