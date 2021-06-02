package hangman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HangManController {
    private final HangMan hangMan;
    private final HangManView hangManView;

    /***
     * HangManController의 생성자.
     * 프로그램의 모든 제어를 위해 model과 view를 전부 소유하며, 유일해야하기때문에 생성자로 주입받는다.
     * 또한 HangManView 객체의 사용자 입력 인터페이스인 버튼에 이벤트 리스너를 등록한다.
     * @param hangMan 게임로직을 담당하는 HangMan 객체
     * @param hangManView 게임의 화면을 담당하는 HangManView 객체
     */
    HangManController(HangMan hangMan, HangManView hangManView) {
        this.hangMan = hangMan;
        this.hangManView = hangManView;
        this.hangManView.getGuessPanel().setKeyAction(new keyBtnListener());
        initNewWord();
    }

    /***
     * 문제를 초기화 하는 기능
     * hangMan 객체에 새로운 단어 초기화를 요청한 후, View에 반영한다.
     */
    private void initNewWord() {
        hangManView.initNewWord(hangMan.initNewWord());
        hangManView.updateCount(hangMan.getCounts());
    }

    /***
     * hangMan 객체에 정답을 제출하는 역할울 한다.
     * keyBtnListener가 버튼 입력을 감지하여 호출한다.
     * 정답여부를 확인한 후, View를 업데이트 하며, 다음단어로 넘어가는지 확인한 후 게임이 끝나느지 확인한다.
     * @param pressedButton 입력된 버튼
     */
    private void submitAnswer(JButton pressedButton) {
        boolean correct = hangMan.checkAnswer(pressedButton.getText().charAt(0));
        updateView(correct, pressedButton);
        checkGoNextWord(correct);
        checkIsGameEnd();
    }

    /***
     * 정답 제출 후 화면을 업데이트 하는 메소드이다.
     * 상황에 맞게 view에 업데이트 요청을 한 후, 카운트를 업데이트 한다.
     * @param correct 정답여부
     * @param pressedButton 입력된 버튼
     */
    private void updateView(boolean correct, JButton pressedButton) {
        if (correct)
            hangManView.updateCorrect(hangMan.getMaskingAnswer(), pressedButton);

        else
            hangManView.updateWrong(pressedButton);

        hangManView.updateCount(hangMan.getCounts());
    }

    /***
     * HangMan 객체에 요청하여 단어가 완성되거나 실패횟수를 넘겨서 다음단어로 넘어가도 되는지 확인한다.
     * 만약 다음 단어로 넘어가는 상황이면 성공과 실패여부를 알림창으로 띄우는 요청을 한 후
     * 새로운 단어로 초기화 한다.
     * @param correct 정답여부
     */
    private void checkGoNextWord(boolean correct) {
        if (hangMan.checkGoNextWord()) {
            if (correct)
                hangManView.alert("���� ����", "������ϴ�. ���� ������ �Ѿ�ϴ�.");
            else
                hangManView.alert("���� Ʋ��", "��ȸ�� ��� �����߽��ϴ�. ���� ������ �Ѿ�ϴ�.");
            initNewWord();
        }
    }

    /***
     * HangMan 객체에 요청하여 게임의 종료되어야 하는지 확인한다.
     * 게임이 종료되어야 하면 알림창을 띄운 후 해당창이 닫아지면 게임이 종료된다.
     */
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