package hangman;

public class HangManApplication {

    /***
     * HangMan�� MVC ������ �̷���� ������,
     * ���� ������ HangMan�� ����ϰ�.(Model)
     * ���α׷��� ȭ���� HangManView�� ����ϸ�,(View)
     * ���α׷��� ��� ����� HangManController�� ����Ѵ�.(Controller)
     * Application�� ���۽� HangMan ��ü�� HangManView ��ü�� �����Ͽ� HangmanController�� ���� �Ѵ�.
     * @param args
     */
    public static void main(String[] args) {
        new HangManController(new HangMan("./words.txt"), new HangManView());
    }
}