package Component;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class HLabel extends JLabel{
	public HLabel(String name){
		this.setText(name);
	}
	
	public void setHeader() {
		this.setHorizontalAlignment(JLabel.CENTER);
		this.setBackground(new Color(0xff, 0x98, 0));
	}
	
	public void setAnnounce() {
		this.setHorizontalAlignment(JLabel.CENTER);
		Border border = BorderFactory.createLineBorder(Color.white, 5);
		this.setBorder(border);
	}
	
	public void setNum() {
		this.setHorizontalAlignment(JLabel.CENTER);
	}
	
	public void setTextFieldLabel() {
		Border border = BorderFactory.createLineBorder(new Color(0, 0, 0, 0), 5);
		this.setBorder(border);
		this.setHorizontalAlignment(JLabel.RIGHT);
	}
}
