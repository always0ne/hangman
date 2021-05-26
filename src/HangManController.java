import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HangManController {
	private final HangMan hangMan;
	private final HangManView hangManView;

	HangManController(HangMan hangMan, HangManView hangManView) {
		this.hangMan = hangMan;
		this.hangManView = hangManView;
		this.hangManView.getSubmitBtn().addActionListener(new SubmitBtnListener());
		initNewWord();
	}

	private void initNewWord() {
		String hiddenString = hangMan.initNewWord();
		hangManView.setHiddenText(hiddenString);
	}

	private void submit(String submit) {
		boolean isAnswer = hangMan.isAnswer(submit);
		if (isAnswer) {
			initNewWord();
			// DO Something

		} else {
			// DO Something
		}
		if (hangMan.isFail())
			initNewWord();
		if(hangMan.isGameEnd())
			System.out.println("게임이 종료되었습니다.");
	}

	private class SubmitBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = hangManView.getInputField().getText();
			submit(text);
		}
	}
}