package academy;

public class Main {

    public static void main(String[] args) throws Exception {
        // Неинтерактивный режим (2 аргумента)
        if (args.length == 2) {
            String result = HangmanGame.processNonInteractiveMode(args);
            if (result != null) {
                System.out.println(result);
            }
        }

        // Интерактивный режим (без аргументов)
        else if (args.length == 0) {
            HangmanGame game = new HangmanGame();
            game.startInteractiveGame();
        }

        else {
            System.out.println("Неверное количество аргументов!");
            System.out.println("Для неинтерактивного режима укажите два аргумента: загаданное слово и угаданное слово");
            System.out.println("Для интерактивного режима запустите программу без аргументов");
        }
    }
}
