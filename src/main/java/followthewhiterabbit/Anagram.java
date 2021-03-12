package followthewhiterabbit;

import java.util.Arrays;
import java.util.function.Predicate;

public class Anagram implements Predicate<String> {
    private final String word;

    public Anagram(String word) {
        this.word = word;
    }

    @Override
    public boolean test(String match) {
        if (match.length() == word.length()) {
            char[] matcherArray = match.toCharArray();
            char[] wordArray = word.toCharArray();

            Arrays.sort(matcherArray);
            Arrays.sort(wordArray);

            return Arrays.equals(matcherArray, wordArray);
        }

        return false;
    }
}
