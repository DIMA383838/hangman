package academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class HangmanSessionTest {
    private HangmanSession session;

    @BeforeEach
    public void setUp() {
        session = new HangmanSession("тест", 6);
    }

    @Test
    public void testInitialState() {
        assertEquals("____", session.getCurrentGuess());
        assertEquals(6, session.getRemainingAttempts());
        assertEquals(6, session.getMaxAttempts());
        assertFalse(session.isGameOver());
        assertFalse(session.isGameWon());
        assertTrue(session.getGuessedLetters().isEmpty());
    }

    @Test
    public void testCorrectGuess() {
        boolean result = session.makeGuess('т');
        assertTrue(result);
        assertEquals("т__т", session.getCurrentGuess());
        assertEquals(6, session.getRemainingAttempts());
        assertFalse(session.isGameOver());
    }

    @Test
    public void testIncorrectGuess() {
        boolean result = session.makeGuess('х');
        assertFalse(result);
        assertEquals("____", session.getCurrentGuess());
        assertEquals(5, session.getRemainingAttempts());
        assertFalse(session.isGameOver());
    }

    @Test
    public void testDuplicateGuess() {
        session.makeGuess('т');
        boolean result = session.makeGuess('т');
        assertFalse(result); // Дубликат должен возвращать false
        assertEquals("т__т", session.getCurrentGuess());
        assertEquals(6, session.getRemainingAttempts());
    }

    @Test
    public void testWinGame() {
        session.makeGuess('т');
        session.makeGuess('е');
        session.makeGuess('с');

        assertTrue(session.isGameOver());
        assertTrue(session.isGameWon());
        assertEquals("тест", session.getCurrentGuess());
    }

    @Test
    public void testLoseGame() {
        // Делаем 6 неправильных попыток
        session.makeGuess('а');
        session.makeGuess('б');
        session.makeGuess('в');
        session.makeGuess('г');
        session.makeGuess('д');
        session.makeGuess('ж');

        assertTrue(session.isGameOver());
        assertFalse(session.isGameWon());
        assertEquals(0, session.getRemainingAttempts());
    }

    @Test
    public void testCaseInsensitiveGuesses() {
        boolean result1 = session.makeGuess('Т');
        boolean result2 = session.makeGuess('Е');

        assertTrue(result1);
        assertTrue(result2);


        assertEquals("те_т", session.getCurrentGuess());
    }
    @Test
    public void testGetGuessedLetters() {
        session.makeGuess('т');
        session.makeGuess('е');
        session.makeGuess('х');

        List<Character> guessed = session.getGuessedLetters();
        assertEquals(3, guessed.size());
        assertTrue(guessed.contains('т'));
        assertTrue(guessed.contains('е'));
        assertTrue(guessed.contains('х'));
    }
}
