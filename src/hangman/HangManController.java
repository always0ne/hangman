package hangman;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangManController {
    private final HangMan hangMan;
    private final HangManView hangManView;

    HangManController(HangMan hangMan, HangManView hangManView) {
        this.hangMan = hangMan;
        this.hangManView = hangManView;
        this.hangManView.getKeyBoard().setKeyAction(new keyBtnListener());
        initNewWord();
    }

    private void initNewWord() {
        hangManView.initNewWord(hangMan.initNewWord(), hangMan.getCounts());
    }

    private void submitAnswer(JButton pressedButton) {
        boolean correct = hangMan.checkAnswer(pressedButton.getText().charAt(0));
        if (correct)
            hangManView.updateCorrect(hangMan.getMaskingAnswer(), pressedButton, hangMan.getCounts());
        else
            hangManView.updateInCorrect(pressedButton, hangMan.getCounts());

        if (hangMan.checkGoNextWord()) {
            if (correct)
                JOptionPane.showMessageDialog(
                        hangManView, "맞췄습니다. 다음 문제로 넘어갑니다.",
                        "문제 맞춤", JOptionPane.INFORMATION_MESSAGE
                );
            else {
                JOptionPane.showMessageDialog(
                        hangManView, "기회를 모두 소진했습니다. 다음 문제로 넘어갑니다.",
                        "문제 틀림", JOptionPane.INFORMATION_MESSAGE
                );
            }
            initNewWord();
        }

        if (hangMan.isGameEnd()) {
            JOptionPane.showMessageDialog(
                    hangManView, "게임을 종료합니다.",
                    "게임 끝", JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }

    private class keyBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            submitAnswer((JButton) e.getSource());
        }
    }
}