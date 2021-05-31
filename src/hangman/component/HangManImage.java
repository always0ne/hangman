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
        for (int i = 0; i < 6; i++) {
            try {
                ImageIcon objectImage = new ImageIcon("./resource/images/hangman" + i + ".png");
                images.add(objectImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        image = new JLabel(images.get(0));
        add(image);
    }

    public ImageIcon updateStep(int num) {
        return images.get(num);
    }
}
