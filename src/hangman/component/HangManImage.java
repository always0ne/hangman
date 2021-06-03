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
     * 행맨 이미지를 보여주는 패널이다.
     * 객체가 생성될 시 images 리스트에 모든 이미지를 로드 한 상태에서 이미지를 출력한다.
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
     * 틀린횟수에 맞게 이미지를 변경
     * @param num 틀린횟수
     */
    public void updateStep(int num) {
        image.setIcon(images.get(num));
    }
}
