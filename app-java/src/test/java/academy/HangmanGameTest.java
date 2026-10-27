package academy;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class HangmanGameTest {

    @ParameterizedTest
    @MethodSource("provideWordPairsForGuessChecking")
    void testCheckGuess(String secretWord, String guessedWord, String expectedResult) {
        String result = HangmanGame.checkGuess(secretWord, guessedWord);
        assertEquals(expectedResult, result);
    }

    private static Stream<Arguments> provideWordPairsForGuessChecking() {
        return Stream.of(
            Arguments.of("кот", "кот", "кот;POS"),
            Arguments.of("слон", "слон", "слон;POS"),
            Arguments.of("волокно", "барахло", "*оло**о;NEG"),
            Arguments.of("кот", "кит", "к*т;NEG"),
            Arguments.of("дом", "сом", "*ом;NEG")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidWordPairs")
    void testCheckGuessWithInvalidArguments(String secretWord, String guessedWord) {
        assertThrows(IllegalArgumentException.class, () -> {
            HangmanGame.checkGuess(secretWord, guessedWord);
        });
    }

    private static Stream<Arguments> provideInvalidWordPairs() {
        return Stream.of(
            Arguments.of("слово", "длинноеслово"),
            Arguments.of("короткое", "длинное"),
            Arguments.of("", "слово"),
            Arguments.of("слово", "")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    void testCheckGuessWithNullArguments(String nullOrEmpty) {
        assertThrows(IllegalArgumentException.class, () -> {
            HangmanGame.checkGuess(nullOrEmpty, "слово");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            HangmanGame.checkGuess("слово", nullOrEmpty);
        });
    }
}

