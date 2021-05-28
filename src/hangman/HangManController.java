package hangman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class HangManController {
    private final HangMan hangMan;
    private final HangManView hangManView;

    HangManController(HangMan hangMan, HangManView hangManView) {
        this.hangMan = hangMan;
        this.hangManView = hangManView;
        this.hangManView.getInputField().getSubmitBtn().addActionListener(new SubmitBtnListener());
        this.hangManView.setCount(hangMan.getCounts());
        this.hangManView.getInputField().getInputField().addKeyListener(new inputFieldKeyListener());
        initNewWord();
    }

    private void initNewWord() {
        String hiddenString = hangMan.initNewWord();
        hangManView.setHiddenText(hiddenString);
    }

    private void submit() {
        String text = hangManView.getInputField().getInputField().getText();
        if (hangMan.submitAndGoNextWord(text))
            initNewWord();
        hangManView.setCount(hangMan.getCounts());
        hangManView.getInputField().clearInput();
        if (hangMan.isGameEnd()) {
            JOptionPane.showMessageDialog(
                    hangManView,
                    "게임을 종료합니다.",
                    "게임 끝",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }

    private class SubmitBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            submit();
        }
    }

    private class inputFieldKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                submit();
            }
        }
    }
}