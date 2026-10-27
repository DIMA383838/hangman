package academy;



public class HangmanDrawing {

    private static final String[] FULL_STAGES = {
        "  ____\n |    |\n |\n |\n |\n |\n_|_",
        "  ____\n |    |\n |    O\n |\n |\n |\n_|_",
        "  ____\n |    |\n |    O\n |    |\n |    |\n |\n_|_",
        "  ____\n |    |\n |    O\n |   /|\n |    |\n |\n_|_",
        "  ____\n |    |\n |    O\n |   /|\\\n |    |\n |\n_|_",
        "  ____\n |    |\n |    O\n |   /|\\\n |    |\n |   /\n_|_",
        "  ____\n |    |\n |    O\n |   /|\\\n |    |\n |   / \\\n_|_",
        "  ____\n |    |\n |    O\n |   /|\\\n |    |\n |  _/ \\\n_|_",
        "  ____\n |    |\n |    O\n |   /|\\\n |    |\n |  _/ \\_\n_|_"
    };

    private static final int[][] STAGE_MAPPING = {
        {0, 1, 2, 3, 4, 5, 6, 7, 8},
        {0, 1, 2, 4, 5, 6, 8},
        {0, 1, 4, 6, 8}
    };

    public static String getDrawing(int remainingAttempts, int maxAttempts) {
        int mistakes = maxAttempts - remainingAttempts;
        int levelIndex = getLevelIndex(maxAttempts);
        int[] mapping = STAGE_MAPPING[levelIndex];

        mistakes = Math.max(0, Math.min(mistakes, mapping.length - 1));
        return FULL_STAGES[mapping[mistakes]];
    }

    public static int getLevelIndex(int maxAttempts) {
        return switch (maxAttempts) {
            case 8 -> 0;
            case 6 -> 1;
            case 4 -> 2;
            default -> 1;
        };
    }

    public static String getFullDrawing() {
        return FULL_STAGES[FULL_STAGES.length - 1];
    }
}
