package hangman.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GuessPanel extends JPanel {
    public static JButton[] letters = new JButton[52];

    /***
     * Ű �Է��� �޴� ȭ���̴�.
     * a���� z���� ��ư�� �����Ѵ�.
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
     * ��� Ű ��ư�� �׼Ǹ����ʸ� ����Ѵ�.
     * @param actionListener �׼� ������
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
     * ��ư�� ������ �������� �ٲ� ��, ��Ȱ��ȭ�� ��ư�� ���� �ٽ� Ȱ��ȭ �Ѵ�.
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
     * ������ Ű�� �ʷϻ����� ���� �����ϸ�, ��Ȱ��ȭ �Ѵ�.
     * @param pressedButton ������ Ű ��ư
     */
    public void setCorrectKey(JButton pressedButton) {
        pressedButton.setBackground(Color.GREEN);
        pressedButton.setEnabled(false);
    }

    /***
     * Ʋ�� Ű�� ���������� ���� �����ϸ�, ��Ȱ��ȭ �Ѵ�.
     * @param pressedButton ������ ��ư
     */
    public void setWrongKey(JButton pressedButton) {
        pressedButton.setBackground(Color.RED);
        pressedButton.setEnabled(false);
    }

}

