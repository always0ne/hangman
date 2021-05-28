package Component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class HTextField extends JTextField{
	public HTextField(String s, int columns){
		this.setText(s);
		this.setColumns(columns);
		Border txtBorder = BorderFactory.createLineBorder(new Color(255, 224, 178, 100), 1);
		this.setBorder(txtBorder);
	}
}
