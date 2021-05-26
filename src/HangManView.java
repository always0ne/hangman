import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class HangManView extends JFrame {
	private static final long serialVersionUID = -6149601216998660790L;
	private JButton submitBtn;
	private JTextField inputField;
	private JLabel hiddenText;

	HangManView() {
		setTitle("HangMan");
		setSize(500, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.submitBtn = new JButton("submit");
		getContentPane().add(submitBtn, BorderLayout.CENTER);

		inputField = new JTextField();
		getContentPane().add(inputField, BorderLayout.EAST);
		inputField.setColumns(10);

		hiddenText = new JLabel("question");
		getContentPane().add(hiddenText, BorderLayout.NORTH);
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
