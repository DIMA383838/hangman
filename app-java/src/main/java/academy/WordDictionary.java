package academy;

import java.util.*;

public class WordDictionary {
    private Map<String, List<String>> categories;
    private final Random random = new Random();
    public WordDictionary() {
        categories = new HashMap<>();
        initializeDictionary();
    }

    private void initializeDictionary() {

        List<String> animals = Arrays.asList(
            "кот", "собака", "лев", "тигр", "слон",
            "жираф", "обезьяна", "зебра", "медведь", "волк"
        );
        categories.put("животные", animals);


        List<String> fruits = Arrays.asList(
            "яблоко", "банан", "апельсин", "виноград", "лимон",
            "манго", "персик", "груша", "ананас", "киви"
        );
        categories.put("фрукты", fruits);


        List<String> professions = Arrays.asList(
            "врач", "учитель", "инженер", "программист", "повар",
            "художник", "музыкант", "строитель", "водитель", "ученый"
        );
        categories.put("профессии", professions);


    }

    public List<String> getCategories() {
        return new ArrayList<>(categories.keySet());
    }

    public String getRandomCategory() {
        List<String> categoryList = getCategories();
        return categoryList.get(random.nextInt(categoryList.size()));
    }

    public String getRandomWord(String category) {
        List<String> words = categories.get(category.toLowerCase());
        if (words == null) {
            List<String> allCategories = getCategories();
            String randomCategory = allCategories.get(random.nextInt(allCategories.size()));
            return getRandomWord(randomCategory);
        }
        return words.get(random.nextInt(words.size()));
    }
}
