package hangman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HangManController {
    private final HangMan hangMan;
    private final HangManView hangManView;

    /***
     * HangManController�� ������.
     * ���α׷��� ��� ��� ���� model�� view�� ���� �����ϸ�, �����ؾ��ϱ⶧���� �����ڷ� ���Թ޴´�.
     * ���� HangManView ��ü�� ����� �Է� �������̽��� ��ư�� �̺�Ʈ �����ʸ� ����Ѵ�.
     * @param hangMan ���ӷ����� ����ϴ� HangMan ��ü
     * @param hangManView ������ ȭ���� ����ϴ� HangManView ��ü
     */
    HangManController(HangMan hangMan, HangManView hangManView) {
        this.hangMan = hangMan;
        this.hangManView = hangManView;
        this.hangManView.getGuessPanel().setKeyAction(new keyBtnListener());
        initNewWord();
    }

    /***
     * ������ �ʱ�ȭ �ϴ� ���
     * hangMan ��ü�� ���ο� �ܾ� �ʱ�ȭ�� ��û�� ��, View�� �ݿ��Ѵ�.
     */
    private void initNewWord() {
        hangManView.initNewWord(hangMan.initNewWord());
        hangManView.updateCount(hangMan.getCounts());
    }

    /***
     * hangMan ��ü�� ������ �����ϴ� ���ҿ� �Ѵ�.
     * keyBtnListener�� ��ư �Է��� �����Ͽ� ȣ���Ѵ�.
     * ���俩�θ� Ȯ���� ��, View�� ������Ʈ �ϸ�, �����ܾ�� �Ѿ���� Ȯ���� �� ������ �������� Ȯ���Ѵ�.
     * @param pressedButton �Էµ� ��ư
     */
    private void submitAnswer(JButton pressedButton) {
        boolean correct = hangMan.checkAnswer(pressedButton.getText().charAt(0));
        updateView(correct, pressedButton);
        checkGoNextWord(correct);
        checkIsGameEnd();
    }

    /***
     * ���� ���� �� ȭ���� ������Ʈ �ϴ� �޼ҵ��̴�.
     * ��Ȳ�� �°� view�� ������Ʈ ��û�� �� ��, ī��Ʈ�� ������Ʈ �Ѵ�.
     * @param correct ���俩��
     * @param pressedButton �Էµ� ��ư
     */
    private void updateView(boolean correct, JButton pressedButton) {
        if (correct)
            hangManView.updateCorrect(hangMan.getMaskingAnswer(), pressedButton);

        else
            hangManView.updateWrong(pressedButton);

        hangManView.updateCount(hangMan.getCounts());
    }

    /***
     * HangMan ��ü�� ��û�Ͽ� �ܾ �ϼ��ǰų� ����Ƚ���� �Ѱܼ� �����ܾ�� �Ѿ�� �Ǵ��� Ȯ���Ѵ�.
     * ���� ���� �ܾ�� �Ѿ�� ��Ȳ�̸� ������ ���п��θ� �˸�â���� ���� ��û�� �� ��
     * ���ο� �ܾ�� �ʱ�ȭ �Ѵ�.
     * @param correct ���俩��
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
     * HangMan ��ü�� ��û�Ͽ� ������ ����Ǿ�� �ϴ��� Ȯ���Ѵ�.
     * ������ ����Ǿ�� �ϸ� �˸�â�� ��� �� �ش�â�� �ݾ����� ������ ����ȴ�.
     */
    private void checkIsGameEnd() {
        if (hangMan.isGameEnd()) {
            hangManView.alert("���� ��", "������ �����մϴ�.");
            System.exit(0);
        }
    }

    /***
     * Ű ��ư �׼� ������
     * ��ư�� �۵��Ǿ������� �̺�Ʈ�� �����ϴ� ������ �Ѵ�.
     */
    private class keyBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            submitAnswer((JButton) e.getSource());
        }
    }
}