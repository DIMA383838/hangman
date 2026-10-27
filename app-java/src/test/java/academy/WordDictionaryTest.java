package academy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class WordDictionaryTest {
    private WordDictionary dictionary;

    public WordDictionaryTest() {
        dictionary = new WordDictionary();
    }

    @Test
    public void testGetCategories() {
        List<String> categories = dictionary.getCategories();
        assertNotNull(categories);
        assertTrue(categories.size() >= 3);
        assertTrue(categories.contains("животные"));
        assertTrue(categories.contains("фрукты"));
        assertTrue(categories.contains("профессии"));
    }

    @Test
    public void testGetRandomCategory() {
        String category = dictionary.getRandomCategory();
        assertNotNull(category);
        assertTrue(List.of("профессии","животные","фрукты").contains(category));
    }

    @Test
    public void testGetRandomWord() {
        String word = dictionary.getRandomWord("животные");
        assertNotNull(word);
        assertTrue(word.length() >= 3);

        List<String> animals = List.of("кот", "собака", "лев", "тигр", "слон",
            "жираф", "обезьяна", "зебра", "медведь", "волк");
        assertTrue(animals.contains(word));
    }

    @Test
    public void testGetRandomWordCategory() {
        String word = dictionary.getRandomWord("несуществующая");
        assertNotNull(word);
    }

    @Test
    public void testCaseInsensitiveCategory() {
        String word1 = dictionary.getRandomWord("ЖИВОТНЫЕ");
        String word2 = dictionary.getRandomWord("животные");
        assertNotNull(word1);
        assertNotNull(word2);

        List<String> animals = List.of("кот", "собака", "лев", "тигр", "слон",
            "жираф", "обезьяна", "зебра", "медведь", "волк");
        assertTrue(animals.contains(word1));
        assertTrue(animals.contains(word2));
    }
}
