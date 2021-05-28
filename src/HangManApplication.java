public class HangManApplication {

    public static void main(String[] args) {
        new HangManController(new HangMan("./words.txt"), new HangManView());
    }
}