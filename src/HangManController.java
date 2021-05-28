import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;


public class HangManController {
    private final HangMan hangMan;
    private final HangManView hangManView;

    HangManController(HangMan hangMan, HangManView hangManView) {
        this.hangMan = hangMan;
        this.hangManView = hangManView;
        this.hangManView.getSubmitBtn().addActionListener(new SubmitBtnListener());
        this.hangManView.setCount(hangMan.getCount());
        this.hangManView.getInputField().addKeyListener(new inputFieldKeyListener());
        initNewWord();
    }

    /*
     * 단어 초기화
     */
    private void initNewWord() {
        String hiddenString = hangMan.initNewWord();
        hangManView.setHiddenText(hiddenString);
    }

    /*
     * 정답 확인
     */
    private void submit() {
        String text = hangManView.getInputField().getText();
        if (hangMan.submitAndGoNextWord(text))
            initNewWord();
        hangManView.setCount(hangMan.getCount());
        hangManView.clearInput();
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