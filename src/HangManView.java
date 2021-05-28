import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Component.HButton;
import Component.HLabel;
import Component.HTextField;
import Component.ImageLabel;

public class HangManView extends JFrame {
	private static final long serialVersionUID = -6149601216998660790L;
	private HButton submitBtn;
	private JTextField inputField;
	private HLabel hiddenText;
	private JPanel hangman;
	private GridBagLayout gBag;
	private HLabel successCountLabel;
	private HLabel failCountLabel ;
	private HLabel wrongCountLabel;

	HangManView() {
		setTitle("HangMan");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		hiddenText = new HLabel("question");
		hiddenText.setHeader();
		getContentPane().add(hiddenText, BorderLayout.NORTH);
		
		hangman = new JPanel();
		BufferedImage objectImage;
		try {
			objectImage = ImageIO.read(new File("./resource/hang1.jpg"));
			ImageLabel img = new ImageLabel();
			img.setIcon(new ImageIcon(objectImage));
			this.hangman.add(img);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block`
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getContentPane().add(hangman, BorderLayout.CENTER);
		
		JPanel side = new JPanel();
		side.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		gBag = new GridBagLayout();
		side.setBackground(Color.white);
		side.setLayout(gBag);
		HLabel success = new HLabel("success");
		success.setAnnounce();
		side.add(success);
		successCountLabel = new HLabel("0");
		successCountLabel.setNum();
		HLabel fail = new HLabel("fail");
		fail.setAnnounce();
		side.add(fail);
		failCountLabel = new HLabel("0");
		failCountLabel.setNum();
		HLabel wrong = new HLabel("wrong");
		wrong.setAnnounce();
		side.add(wrong);
		wrongCountLabel = new HLabel("5");
		wrongCountLabel.setNum();
		gbinsert(success, 0, 1, 1, 1);
		gbinsert(fail, 0, 3, 1, 1);
		gbinsert(wrong, 0, 5, 1, 1);
		gbinsert(successCountLabel, 0, 2, 1, 1);
		gbinsert(failCountLabel, 0, 4, 1, 1);
		gbinsert(wrongCountLabel, 0, 6, 1, 1);
		side.add(successCountLabel);
		side.add(failCountLabel);
		side.add(wrongCountLabel);
		getContentPane().add(side, BorderLayout.EAST);
		
		JPanel south = new JPanel();
		gBag = new GridBagLayout();
		south.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
		south.setLayout(gBag);
		south.setBackground(new Color(255, 224, 178, 100));
		HLabel ans = new HLabel("Á¤´ä");
		ans.setTextFieldLabel();
		inputField = new HTextField("", 10);
		this.submitBtn = new HButton("submit");
		gbinsert(ans, 0, 1, 1, 1);
		gbinsert(inputField, 1, 1, 1, 1);
		gbinsert(this.submitBtn, 2, 1, 1, 1);
		south.add(ans);
		south.add(inputField);
		south.add(this.submitBtn);
		getContentPane().add(south, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	public JButton getSubmitBtn() {
		return submitBtn;
	}

	public JLabel getHiddenText() {
		return hiddenText;
	}

	public void setHiddenText(String str) {
		hiddenText.setText(str);
	}

	public JTextField getInputField() {
		return inputField;
	}
	
   private void gbinsert(Component c, int x, int y, int w, int h){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill= GridBagConstraints.BOTH;
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        gBag.setConstraints(c,gbc);
    }
	
}
