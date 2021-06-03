package hangman;

public class HangManApplication {

    /***
     * HangMan은 MVC 구조로 이루어져 있으며,
     * 게임 로직은 HangMan이 담당하고.(Model)
     * 프로그램의 화면은 HangManView가 담당하며,(View)
     * 프로그램의 모든 제어는 HangManController가 담당한다.(Controller)
     * Application은 시작시 HangMan 객체와 HangManView 객체를 생성하여 HangmanController를 생성 한다.
     * @param args
     */
    public static void main(String[] args) {
        new HangManController(new HangMan("./words.txt"), new HangManView());
    }
}