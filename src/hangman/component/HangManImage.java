package hangman.component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.util.ArrayList;
import java.util.List;

public class HangManImage extends JPanel {
    List<ImageIcon> images;
    JLabel image;

    /***
     * ��� �̹����� �����ִ� �г��̴�.
     * ��ü�� ������ �� images ����Ʈ�� ��� �̹����� �ε� �� ���¿��� �̹����� ����Ѵ�.
     */
    public HangManImage() {
        images = new ArrayList<ImageIcon>();
        for (int i = 0; i < 6; i++) {
            try {
                ImageIcon objectImage = new ImageIcon("./resource/images/hangman" + i + ".png");
                images.add(objectImage);
            } catch (Exception ignored) {
            }
        }
        image = new JLabel(images.get(0));
        add(image);
    }

    /***
     * Ʋ��Ƚ���� �°� �̹����� ����
     * @param num Ʋ��Ƚ��
     */
    public void updateStep(int num) {
        image.setIcon(images.get(num));
    }
}
