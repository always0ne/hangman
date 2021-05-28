import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangManController {
	private final HangMan hangMan;
	private final HangManView hangManView;

	HangManController(HangMan hangMan, HangManView hangManView) {
		this.hangMan = hangMan;
		this.hangManView = hangManView;
		this.hangManView.getSubmitBtn().addActionListener(new SubmitBtnListener());
		this.hangManView.setCount(hangMan.getCount());
		initNewWord();
	}

	/*
	 * �ܾ� �ʱ�ȭ
	 */
	private void initNewWord() {
		String hiddenString = hangMan.initNewWord();
		hangManView.setHiddenText(hiddenString);
	}

	/*
	 * ���� Ȯ��
	 */
	private void submit(String submit) {
		boolean isAnswer = hangMan.isAnswer(submit);
		this.hangManView.setInputClear();
		if (isAnswer) {
			initNewWord();
			// DO Something
			this.hangManView.setCount(hangMan.getCount());
		} else {
			// DO Something
		}
		if (hangMan.isFail())
			initNewWord();
			this.hangManView.setCount(hangMan.getCount());
		if(hangMan.isGameEnd())
			System.out.println("������ ����Ǿ����ϴ�.");
	}

	private class SubmitBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = hangManView.getInputField().getText();
			submit(text);
		}
	}
	
}