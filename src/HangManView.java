import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Component.HButton;
import Component.HLabel;

public class HangManView extends JFrame {
	private static final long serialVersionUID = -6149601216998660790L;
	private HButton submitBtn;
	private JTextField inputField;
	private HLabel hiddenText;
//	private GridBagLayout gBag;
	HangManView() {
		setTitle("HangMan");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.submitBtn = new HButton("submit");
		getContentPane().add(submitBtn, BorderLayout.CENTER);

		inputField = new JTextField();
		getContentPane().add(inputField, BorderLayout.EAST);
		inputField.setColumns(10);

		hiddenText = new HLabel("question");
		hiddenText.setHeader();
		getContentPane().add(hiddenText, BorderLayout.NORTH);
		
//		JPanel south = new JPanel();
//		gBag = new GridBagLayout();
//		south.setBorder(BorderFactory.createEmptyBorder(10 , 10 , 10 , 10));
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
	
}
