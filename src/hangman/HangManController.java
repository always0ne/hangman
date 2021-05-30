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
        this.hangManView.setCount(hangMan.getCounts());
        initNewWord();
    }

    private void initNewWord() {
        String hiddenString = hangMan.initNewWord();
        hangManView.setHiddenText(hiddenString);
    }

    private void pressKey(JButton pressedButton) {
    	if(hangMan.isAnswer(pressedButton.getText())) {
        	String text = hangMan.checkPressedKey(pressedButton.getText());	
        	hangManView.changeHiddenText(text);
        	hangManView.getKeyBoard().setCorrectKey(pressedButton);
        	if(hangMan.handleRightAnswer()) {
                JOptionPane.showMessageDialog(
                        hangManView,
                        "������ϴ�. ���� ������ �Ѿ�ϴ�.",
                        "���� ����",
                        JOptionPane.INFORMATION_MESSAGE
                );
                initNewWord();
        	}
    	}else {
        	hangManView.getKeyBoard().setWrongKey(pressedButton);
    		if(hangMan.handleWrongAnswer()) {
                JOptionPane.showMessageDialog(
                        hangManView,
                        "��ȸ�� ��� �����߽��ϴ�. ���� ������ �Ѿ�ϴ�.",
                        "���� Ʋ��",
                        JOptionPane.INFORMATION_MESSAGE
                );
                initNewWord();
    		}
    	}
        hangManView.setCount(hangMan.getCounts());

        if (hangMan.isGameEnd()) {
            JOptionPane.showMessageDialog(
                    hangManView,
                    "������ �����մϴ�.",
                    "���� ��",
                    JOptionPane.INFORMATION_MESSAGE
            );
            System.exit(0);
        }
    }
    
    private class keyBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          JButton pressedButton = (JButton) e.getSource();
          pressKey(pressedButton);
        }
    }
}