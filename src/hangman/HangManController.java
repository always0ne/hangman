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
        hangManView.initNewWord(hangMan.initNewWord());
        hangManView.setCount(hangMan.getCounts());
    }

    private void submitAnswer(JButton pressedButton) {
        boolean correct = hangMan.checkAnswer(pressedButton.getText().charAt(0));
        updateView(correct, pressedButton);
        checkGoNextWord(correct);
        checkIsGameEnd();
    }

    private void updateView(boolean correct, JButton pressedButton) {
        if (correct) {
            hangManView.updateCorrect(hangMan.getMaskingAnswer(), pressedButton);
            hangManView.setCount(hangMan.getCounts());
        }
        else {
            hangManView.updateInCorrect(pressedButton);
            hangManView.setCount(hangMan.getCounts());
        }
    }

    private void checkGoNextWord(boolean correct) {
        if (hangMan.checkGoNextWord()) {
            if (correct)
                hangManView.alert("���� ����", "������ϴ�. ���� ������ �Ѿ�ϴ�.");
            else
                hangManView.alert("���� Ʋ��", "��ȸ�� ��� �����߽��ϴ�. ���� ������ �Ѿ�ϴ�.");
            initNewWord();
        }
    }

    private void checkIsGameEnd() {
        if (hangMan.isGameEnd()) {
            hangManView.alert("���� ��", "������ �����մϴ�.");
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