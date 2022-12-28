package org.jjm;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

    /**
     * Method treats words with hyphen (like ex-wife) and apostrophe (like it's) as single
     *
     * @param content is a text where we count words in
     * @return map with word & number of occurrences as key-value pairs
     */
    public static Map<String, Long> countWordsWithHyphenAndApostrophe(String content) {
        return countWordsByPattern("\\w+[-'\\w*]*", content);
    }

    /**
     * Method treats words with hyphen (like ex-wife) and apostrophe (like it's) as separate words
     *
     * @param content is a text where we count words in
     * @return map with word & number of occurrences as key-value pairs
     */
    public static Map<String, Long> countWords(String content) {
        return countWordsByPattern("\\w+", content);
    }

    private static Map<String, Long> countWordsByPattern(String pattern, String content) {
        return Pattern.compile(pattern)
                .matcher(content)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static List<Map.Entry<String, Long>> sortByValue(Map<String, Long> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

}
