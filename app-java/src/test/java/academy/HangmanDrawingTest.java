package academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class HangmanDrawingTest {

    @Test
    public void testGetDrawingEasyLevel() {
        Difficulty difficulty = Difficulty.EASY;
        String drawing0 = HangmanDrawing.getDrawing(8, difficulty.getMaxAttempts());
        assertNotNull(drawing0);
        assertTrue(drawing0.contains("____"));

        String drawing4 = HangmanDrawing.getDrawing(4, difficulty.getMaxAttempts());
        assertNotNull(drawing4);
        assertTrue(drawing4.contains("/|\\"));

        String drawing8 = HangmanDrawing.getDrawing(0, difficulty.getMaxAttempts());
        assertNotNull(drawing8);
        assertTrue(drawing8.contains("_/ \\_"));
    }

    @Test
    public void testGetDrawingMediumLevel() {
        Difficulty difficulty = Difficulty.MEDIUM;
        String drawing0 = HangmanDrawing.getDrawing(6, difficulty.getMaxAttempts());
        assertNotNull(drawing0);
        assertTrue(drawing0.contains("____"));

        String drawing3 = HangmanDrawing.getDrawing(3, difficulty.getMaxAttempts());
        assertNotNull(drawing3);
        assertTrue(drawing3.contains("/|\\"));

        String drawing6 = HangmanDrawing.getDrawing(0, difficulty.getMaxAttempts());
        assertNotNull(drawing6);
        assertTrue(drawing6.contains("_/ \\_"));
    }

    @Test
    public void testGetDrawingHardLevel() {
        Difficulty difficulty = Difficulty.HARD;
        String drawing0 = HangmanDrawing.getDrawing(4, difficulty.getMaxAttempts());
        assertNotNull(drawing0);
        assertTrue(drawing0.contains("____"));

        String drawing2 = HangmanDrawing.getDrawing(2, difficulty.getMaxAttempts());
        assertNotNull(drawing2);
        assertTrue(drawing2.contains("/|\\"));

        String drawing4 = HangmanDrawing.getDrawing(0, difficulty.getMaxAttempts());
        assertNotNull(drawing4);
        assertTrue(drawing4.contains("_/ \\_"));
    }

    @Test
    public void testGetDrawingInvalidMaxAttempts() {

        String drawing = HangmanDrawing.getDrawing(3, 10);
        assertNotNull(drawing);
        assertTrue(drawing.contains("_/ \\_"));
    }

    @Test
    public void testGetFullDrawing() {
        String drawing0 = HangmanDrawing.getFullDrawing();
        assertTrue(drawing0.contains(" ____\n" +
            " |    |\n" +
            " |    O\n" +
            " |   /|\\\n" +
            " |    |\n" +
            " |  _/ \\_\n" +
            "_|_"));
        assertNotNull(drawing0);
    }

    @Test
    public void testGetDrawingEdgeCases() {
        String drawingNegative = HangmanDrawing.getDrawing(-1, 8);
        assertNotNull(drawingNegative);
    }

    @Test
    public void testGetLevelIndex1() {
        Difficulty difficulty1 = Difficulty.EASY;
        Difficulty difficulty2 = Difficulty.MEDIUM;
        Difficulty difficulty3 = Difficulty.HARD;
        assertEquals(HangmanDrawing.getLevelIndex(difficulty1.getMaxAttempts()), 0);
        assertEquals(HangmanDrawing.getLevelIndex(difficulty2.getMaxAttempts()), 1);
        assertEquals(HangmanDrawing.getLevelIndex(difficulty3.getMaxAttempts()), 2);
        assertEquals(HangmanDrawing.getLevelIndex(100), 1);

    }

    @ParameterizedTest
    @CsvSource
        ({  "8, 0",
            "6, 1",
            "4, 2",
            "100, 1"})
    public void testGetLevelIndex2(int maxAttempts, int expectedIndex) {
        assertEquals(expectedIndex, HangmanDrawing.getLevelIndex(maxAttempts));
    }
}

