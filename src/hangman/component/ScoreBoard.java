package hangman.component;

import hangman.CountDto;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard extends JPanel {
    private final GridBagLayout gBag;
    private final JLabel successCountLabel;
    private final JLabel failCountLabel;
    private final JLabel wrongCountLabel;

    public ScoreBoard() {
        gBag = new GridBagLayout();
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.white);
        setLayout(gBag);

        JLabel success = generateTextLabel("success");
        add(success);
        gbInsert(success, 0, 1, 1, 1);

        successCountLabel = generateNumberLabel();
        gbInsert(successCountLabel, 0, 2, 1, 1);
        add(successCountLabel);

        JLabel fail = generateTextLabel("fail");
        add(fail);
        gbInsert(fail, 0, 3, 1, 1);

        failCountLabel = generateNumberLabel();
        gbInsert(failCountLabel, 0, 4, 1, 1);
        add(failCountLabel);

        JLabel wrong = generateTextLabel("wrong");
        add(wrong);
        gbInsert(wrong, 0, 5, 1, 1);

        wrongCountLabel = generateNumberLabel();
        gbInsert(wrongCountLabel, 0, 6, 1, 1);
        add(wrongCountLabel);
    }

    private JLabel generateTextLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.white, 5));
        return label;
    }

    private JLabel generateNumberLabel() {
        JLabel label = new JLabel("");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public void setCount(CountDto dto) {
        successCountLabel.setText(String.valueOf(dto.getSuccessCount()));
        failCountLabel.setText(String.valueOf(dto.getFailCount()));
        wrongCountLabel.setText(String.valueOf(dto.getWrongCount()));
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
