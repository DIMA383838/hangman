package academy;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class HangmanGame {
    private WordDictionary dictionary;
    private HangmanSession session;
    private Scanner scanner;

    public HangmanGame() {
        this.dictionary = new WordDictionary();
        this.scanner = new Scanner(System.in);
    }

    public void startInteractiveGame() {
        System.out.println("=== ДОБРО ПОЖАЛОВАТЬ В ИГРУ 'ВИСЕЛИЦА' ===\n");

        System.out.println("Полная виселица выглядит так:");
        System.out.println(HangmanDrawing.getFullDrawing());
        System.out.println();

        String category = selectCategory();
        Difficulty difficulty = selectDifficulty();
        int maxAttempts = difficulty.getMaxAttempts();
        String secretWord = dictionary.getRandomWord(category);
        session = new HangmanSession(secretWord, maxAttempts);

        System.out.println("\n=== ИГРА НАЧИНАЕТСЯ ===");
        System.out.println("Категория: " + category);
        System.out.println("Уровень сложности: " + difficulty.getDisplayName());
        System.out.println("Количество попыток: " + maxAttempts);
        System.out.println("Длина слова: " + secretWord.length() + " букв\n");
        gameLoop();
    }

    private String selectCategory() {
        System.out.println("Доступные категории:");
        List<String> categories = dictionary.getCategories();
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        System.out.print("\nВыберите категорию (введите номер или нажмите Enter для случайного выбора): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            String randomCategory = dictionary.getRandomCategory();
            System.out.println("Выбрана случайная категория: " + randomCategory);
            return randomCategory;
        }

        try {
            int choice = Integer.parseInt(input) - 1;
            if (choice >= 0 && choice < categories.size()) {
                return categories.get(choice);
            } else {
                String randomCategory = dictionary.getRandomCategory();
                System.out.println("Некорректный номер категории. Выбрана случайная категория: " + randomCategory);
                return randomCategory;
            }
        } catch (NumberFormatException e) {
            String randomCategory = dictionary.getRandomCategory();
            System.out.println("Некорректный ввод. Выбрана случайная категория: " + randomCategory);
            return randomCategory;
        }
    }

    private Difficulty selectDifficulty() {
        System.out.println("\nУровни сложности:");
        System.out.println("1. Легкий (8 попыток)");
        System.out.println("2. Средний (6 попыток)");
        System.out.println("3. Сложный (4 попытки)");

        System.out.print("Выберите уровень сложности (введите номер 1-3 или нажмите Enter для случайного выбора): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            Difficulty randomDifficulty = Difficulty.getRandom();
            System.out.println("Выбран случайный уровень сложности: " + randomDifficulty.getDisplayName());
            return randomDifficulty;
        }

        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    return Difficulty.EASY;
                case 2:
                    return Difficulty.MEDIUM;
                case 3:
                    return Difficulty.HARD;
                default:
                    Difficulty randomDifficulty = Difficulty.getRandom();
                    System.out.println("Некорректный номер. Выбран случайный уровень сложности: " + randomDifficulty.getDisplayName());
                    return randomDifficulty;
            }
        } catch (NumberFormatException e) {
            Difficulty randomDifficulty = Difficulty.getRandom();
            System.out.println("Некорректный ввод. Выбран случайный уровень сложности: " + randomDifficulty.getDisplayName());
            return randomDifficulty;
        }
    }

    private void gameLoop() {
        while (!session.isGameOver()) {
            displayGameState();
            processInput();
        }

        displayFinalResult();
    }

    private void displayGameState() {
        System.out.println("\n" + HangmanDrawing.getDrawing(session.getRemainingAttempts(), session.getMaxAttempts()));
        System.out.println("\nСлово: " + session.getCurrentGuess());
        System.out.println("Осталось попыток: " + session.getRemainingAttempts());
        System.out.println("Буквы, которые уже были названы: " + session.getGuessedLetters());

        if (session.isHintAvailable()) {
            System.out.println("💡 Доступна подсказка! Введите '?' чтобы получить подсказку (стоимость: " + session.getHintPenalty() + " попытка)");
        } else if (session.isHintUsed()) {
            System.out.println("💡 Подсказка уже использована");
        }

        System.out.print("Введите букву, слово целиком или '?' для подсказки: ");
    }
    private void processHintRequest() {
        if (!session.isHintAvailable()) {
            System.out.println("Подсказка недоступна!");
            return;
        }

        String hint = session.getHint();
        if (hint != null) {
            System.out.println("\n💡 ПОДСКАЗКА: " + hint);
            System.out.println("Вы потеряли " + session.getHintPenalty() + " попытку за подсказку.");

            if (session.getRemainingAttempts() <= 0) {
                System.out.println("У вас закончились попытки!");
            }
        } else {
            System.out.println("Подсказка для этого слова недоступна.");
        }
    }

    private void processInput() {
        String input = scanner.nextLine().trim();

        if (input.equals("?")) {
            processHintRequest();
            return;
        }

        if (input.length() > 1) {
            processWholeWordGuess(input);
            return;
        }

        if (input.length() != 1) {
            System.out.println("Пожалуйста, введите букву, слово целиком или '?' для подсказки!");
            return;
        }

        char letter = input.charAt(0);
        if (!Character.isLetter(letter)) {
            System.out.println("Пожалуйста, введите букву!");
            return;
        }

        boolean correct = session.makeGuess(letter);
        if (!correct&&!session.getSecretWord().contains(Character.toString(letter))) {
            System.out.println("Буквы '" + letter + "' нет в слове!");
        } else {
            System.out.println("Правильно! Буква '" + letter + "' есть в слове.");
        }
    }

    private void processWholeWordGuess(String guessedWord) {
        if (guessedWord.equalsIgnoreCase(session.getSecretWord())) {
            session.setGameWon();
            System.out.println("ПОЗДРАВЛЯЕМ! Вы угадали слово: " + session.getSecretWord());
        } else {
            session.handleIncorrectGuess();
            System.out.println("К сожалению, слово '" + guessedWord + "' неверное!");

            if (session.getRemainingAttempts() <= 0) {
                session.setGameOver();
            }
        }
    }

    private void displayFinalResult() {
        displayHangmanDrawing();
        displayGameOverHeader();
        displayGameResult();
        displayThankYouMessage();
    }

    private void displayHangmanDrawing() {
        System.out.println("\n" + HangmanDrawing.getDrawing(session.getRemainingAttempts(), session.getMaxAttempts()));
    }

    private void displayGameOverHeader() {
        System.out.println("\n=== ИГРА ОКОНЧЕНА ===");
    }

    private void displayGameResult() {
        if (session.isGameWon()) {
            displayWinMessage();
        } else {
            displayLoseMessage();
        }
        if (session.isHintUsed()) {
            System.out.println("ℹ️  Вы использовали подсказку во время игры.");
        }
    }

    private void displayWinMessage() {
        System.out.println("ПОЗДРАВЛЯЕМ! Вы угадали слово: " + session.getSecretWord());
    }

    private void displayLoseMessage() {
        System.out.println("К сожалению, вы проиграли.");
        System.out.println("Загаданное слово было: " + session.getSecretWord());
    }

    private void displayThankYouMessage() {
        System.out.println("\nСпасибо за игру!");
    }

    public static String checkGuess(String guessedWord, String secretWord)  {
        if (secretWord == null || guessedWord == null) {
            throw new IllegalArgumentException("Слова не могут быть null");
        }

        if (secretWord.length() != guessedWord.length()) {
            throw new IllegalArgumentException("Слова должны быть одинаковой длины");
        }

        HashSet<Character> secret = new HashSet<>();

        for(int i=0;i<secretWord.length();i++){
            secret.add(secretWord.charAt(i));
        }

        boolean isFullyGuessed = secretWord.equals(guessedWord);

        StringBuilder resultBuilder2 = new StringBuilder();

        for (int i = 0; i < guessedWord.length(); i++) {
            char guessedChar = guessedWord.charAt(i);

            if (secret.contains(guessedChar) ) {
                resultBuilder2.append(guessedChar);
            } else {
                resultBuilder2.append('*');
            }

        }

        String resultCode = isFullyGuessed ? "POS" : "NEG";

        return resultBuilder2.toString() + ";" + resultCode;
    }

    public static String processNonInteractiveMode(String[] args)  {
        if (args.length < 2) {
            return null;
        }
        String secretWord = args[0].toLowerCase();
        String guessedWord = args[1].toLowerCase();

        return checkGuess(secretWord, guessedWord);
    }
}
