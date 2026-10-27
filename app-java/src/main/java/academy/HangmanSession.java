package academy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HangmanSession {
    private String secretWord;
    private StringBuilder currentGuess;
    private int maxAttempts;
    private int remainingAttempts;
    private Set<Character> guessedLetters;
    private boolean gameOver;
    private boolean gameWon;
    private HintProvider hintProvider;
    private boolean hintUsed;
    private int hintPenalty;

    public HangmanSession(String secretWord, int maxAttempts) {
        this.secretWord = normalizeWord(secretWord);
        this.maxAttempts = maxAttempts;
        this.hintProvider = new HintProvider();
        this.hintPenalty = 1;
        initializeGameState();

    }
    public String getHint() {
        if (gameOver || hintUsed || !hintProvider.hasHint(secretWord)) {
            return null;
        }

        hintUsed = true;
        remainingAttempts -= hintPenalty;

        if (remainingAttempts <= 0) {
            gameOver = true;
        }

        return hintProvider.getHint(secretWord);
    }
    public boolean isHintAvailable() {
        return !hintUsed && !gameOver && hintProvider.hasHint(secretWord);
    }

    public boolean isHintUsed() {
        return hintUsed;
    }

    public int getHintPenalty() {
        return hintPenalty;
    }

    private String normalizeWord(String word) {
        return word.toLowerCase();
    }
    private void initializeGameState() {
        this.remainingAttempts = maxAttempts;
        this.guessedLetters = new HashSet<>();
        this.gameOver = false;
        this.gameWon = false;
        this.currentGuess = createInitialMask();
    }
    private StringBuilder createInitialMask() {
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            mask.append('_');
        }
        return mask;
    }
    boolean makeGuess(char letter) {
        if (gameOver) {
            return false;
        }

        char lowerLetter = Character.toLowerCase(letter);


        if (guessedLetters.contains(lowerLetter)) {
            return false;
        }

        guessedLetters.add(lowerLetter);
        boolean correctGuess = updateWordMask(lowerLetter);

        if (!correctGuess) {
            handleIncorrectGuess();
        } else {
            checkForWin();
        }

        return correctGuess;
    }
    private boolean updateWordMask(char letter) {
        boolean correctGuess = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                currentGuess.setCharAt(i, secretWord.charAt(i));
                correctGuess = true;
            }
        }
        return correctGuess;
    }
    public void handleIncorrectGuess() {
        remainingAttempts--;
        if (remainingAttempts <= 0) {
            gameOver = true;
        }
    }
    private void checkForWin() {
        if (currentGuess.toString().equals(secretWord)) {
            gameWon = true;
            gameOver = true;
        }
    }


    public void setGameWon() {
        if (!gameOver) {
            gameWon = true;
            gameOver = true;

            currentGuess = new StringBuilder(secretWord);
        }
    }


    public void setGameOver() {
        gameOver = true;
    }

    public String getCurrentGuess() {
        return currentGuess.toString();
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isGameWon() {
        return gameWon;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public List<Character> getGuessedLetters() {
        return new ArrayList<>(guessedLetters);
    }
}
