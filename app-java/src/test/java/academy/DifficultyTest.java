package academy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifficultyTest {

    @Test
    public void testGetDisplayName() {
        Difficulty difficulty1=Difficulty.EASY;
        Difficulty difficulty2=Difficulty.MEDIUM;
        Difficulty difficulty3=Difficulty.HARD;
        assertNotNull(difficulty1);
        assertNotNull(difficulty2);
        assertNotNull(difficulty3);
        assertTrue(difficulty1.getDisplayName().equals("легкий"));
        assertTrue(difficulty2.getDisplayName().equals("средний"));
        assertTrue(difficulty3.getDisplayName().equals("сложный"));
    }
    @Test
    public void TestGetMaxAttempts() {
        Difficulty difficulty1=Difficulty.EASY;
        Difficulty difficulty2=Difficulty.MEDIUM;
        Difficulty difficulty3=Difficulty.HARD;
        assertTrue(difficulty1.getMaxAttempts() == 8);
        assertTrue(difficulty2.getMaxAttempts() == 6);
        assertTrue(difficulty3.getMaxAttempts() == 4);
    }
@Test
    public void TestGetRandom() {
        assertNotNull(Difficulty.getRandom());
}



}
