package academy;

import java.util.HashMap;
import java.util.HashMap;
import java.util.Map;
public class HintProvider {
    private final Map<String, String> hints;

    public HintProvider() {
        hints = new HashMap<>();
        initializeHints();
    }
    private void initializeHints() {
            hints.put("кот", "Домашний питомец, который любит молоко");
            hints.put("собака", "Лучший друг человека");
            hints.put("лев", "Царь зверей, большая кошка");
            hints.put("тигр", "Крупная полосатая кошка");
            hints.put("слон", "Самое большое сухопутное животное");
            hints.put("жираф", "Самое высокое животное с длинной шеей");
            hints.put("обезьяна", "Животное, похожее на человека");
            hints.put("зебра", "Черно-белая лошадь");
            hints.put("медведь", "Крупный хищник, впадает в спячку");
            hints.put("волк", "Лесной хищник, живет в стае");


            hints.put("яблоко", "Популярный фрукт, бывает красным, зеленым или желтым");
            hints.put("банан", "Длинный желтый фрукт");
            hints.put("апельсин", "Цитрусовый фрукт оранжевого цвета");
            hints.put("виноград", "Растет гроздьями, из него делают вино");
            hints.put("лимон", "Очень кислый цитрусовый фрукт");
            hints.put("манго", "Тропический фрукт со сладкой мякотью");
            hints.put("персик", "Пушистый фрукт с косточкой");
            hints.put("груша", "Фрукт, похожий на лампочку");
            hints.put("ананас", "Тропический фрукт с колючей кожурой");
            hints.put("киви", "Маленький коричневый фрукт с зеленой мякотью");


            hints.put("врач", "Лечит людей");
            hints.put("учитель", "Работает в школе");
            hints.put("инженер", "Проектирует технику и сооружения");
            hints.put("программист", "Пишет код для компьютеров");
            hints.put("повар", "Готовит еду");
            hints.put("художник", "Рисует картины");
            hints.put("музыкант", "Играет на музыкальных инструментах");
            hints.put("строитель", "Строит дома");
            hints.put("водитель", "Управляет автомобилем");
            hints.put("ученый", "Занимается наукой");

    }
    public String getHint(String word) {
        return hints.getOrDefault(word.toLowerCase(), "Подсказка недоступна для этого слова");
    }
    public boolean hasHint(String word) {
        return hints.containsKey(word.toLowerCase());
    }
}
