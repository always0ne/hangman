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
                        "맞췄습니다. 다음 문제로 넘어갑니다.",
                        "문제 맞춤",
                        JOptionPane.INFORMATION_MESSAGE
                );
                initNewWord();
        	}
    	}else {
        	hangManView.getKeyBoard().setWrongKey(pressedButton);
    		if(hangMan.handleWrongAnswer()) {
                JOptionPane.showMessageDialog(
                        hangManView,
                        "기회를 모두 소진했습니다. 다음 문제로 넘어갑니다.",
                        "문제 틀림",
                        JOptionPane.INFORMATION_MESSAGE
                );
                initNewWord();
    		}
    	}
        hangManView.setCount(hangMan.getCounts());

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
    
    private class keyBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
          JButton pressedButton = (JButton) e.getSource();
          pressKey(pressedButton);
        }
    }
}