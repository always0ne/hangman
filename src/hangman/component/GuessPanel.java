package hangman.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GuessPanel extends JPanel {
    public static JButton[] letters = new JButton[52];

    /***
     * 키 입력을 받는 화면이다.
     * a부터 z까지 버튼을 생성한다.
     */
    public GuessPanel() {
        int k = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            letters[k] = new JButton(String.valueOf(i));
            letters[k].setFocusable(false);
            letters[k].setFont(new Font("./resource/Bold.ttf", Font.BOLD, 20));
            this.add(letters[k]);
            k++;
        }
        this.setBackground(new Color(210, 161, 26));
        this.setOpaque(true);
    }

    /***
     * 모든 키 버튼에 액션리스너를 등록한다.
     * @param actionListener 액션 리스너
     */
    public void setKeyAction(ActionListener actionListener) {
        try {
            for (int i = 0; i < 26; i++) {
                letters[i].addActionListener(actionListener);
            }
        } catch (NullPointerException ignored) {
        }
    }

    /***
     * 버튼의 배경색을 정상으로 바꾼 후, 비활성화된 버튼을 전부 다시 활성화 한다.
     */
    public void reset() {
        try {
            for (int i = 0; i < 26; i++) {
                letters[i].setBackground(new JButton().getBackground());
                letters[i].setEnabled(true);
            }
        } catch (NullPointerException ignored) {
        }
    }

    /***
     * 정답인 키는 초록색으로 색을 변경하며, 비활성화 한다.
     * @param pressedButton 정답인 키 버튼
     */
    public void setCorrectKey(JButton pressedButton) {
        pressedButton.setBackground(Color.GREEN);
        pressedButton.setEnabled(false);
    }

    /***
     * 틀린 키는 빨간색으로 색을 변경하며, 비활성화 한다.
     * @param pressedButton 실패한 버튼
     */
    public void setWrongKey(JButton pressedButton) {
        pressedButton.setBackground(Color.RED);
        pressedButton.setEnabled(false);
    }

}

