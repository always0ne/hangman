package hangman.component;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HangManImage extends JPanel {
    List<ImageIcon> images;
    JLabel image;

    public HangManImage() {
        images = new ArrayList<ImageIcon>();
        for (int i = 0; i < 5; i++) {
            try {
                BufferedImage objectImage = ImageIO.read(new File("./resource/hang" + i + ".jpg"));
                images.add(new ImageIcon(objectImage));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        image = new JLabel(images.get(0));
        add(image);
    }

    public void updateStep(int num) {
        image.setIcon(images.get(num));
    }
}
