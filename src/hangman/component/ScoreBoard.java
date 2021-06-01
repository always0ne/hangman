package hangman.component;

import hangman.CountDto;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Box;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;

public class ScoreBoard extends JPanel {

    private final JLabel successCountLabel;
    private final JLabel failCountLabel;

    public ScoreBoard() {
        setBackground(new Color(201, 181, 84));
        setOpaque(true);
        Box box = Box.createVerticalBox();

        Box successBox = Box.createHorizontalBox();

        JLabel successLabel = new JLabel("success: ");
        successLabel.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));
        successLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        successBox.add(successLabel);

        successCountLabel = new JLabel("10");
        successCountLabel.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));
        successBox.add(successCountLabel);
        box.add(successBox);

        Box failBox = Box.createHorizontalBox();

        JLabel failLabel = new JLabel("Fail:    ");
        failCountLabel = new JLabel("10");

        failLabel.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));
        failLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);

        failCountLabel.setFont(new Font("./resource/Bold.ttf", Font.BOLD, 15));

        failBox.add(failLabel);
        failBox.add(failCountLabel);
        box.add(failBox);
        add(box);
    }

    public void updateScoreBoard(CountDto dto) {
        successCountLabel.setText(String.valueOf(dto.getSuccessCount()));
        failCountLabel.setText(String.valueOf(dto.getFailCount()));
    }
}
