
package academy;

import java.util.Random;

public enum Difficulty {
    EASY("легкий", 8),
    MEDIUM("средний", 6),
    HARD("сложный", 4);

    private final String displayName;
    private final int maxAttempts;
    private static final Random random=new Random();

    Difficulty(String displayName, int maxAttempts) {
        this.displayName = displayName;
        this.maxAttempts = maxAttempts;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public static Difficulty getRandom() {
        Difficulty[] values = values();
        return values[random.nextInt(values.length)];
    }


}
