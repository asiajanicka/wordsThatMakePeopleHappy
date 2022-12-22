package org.jjm;

import java.util.*;
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
    public static HashMap<String, Long> countWordsWithHyphenAndApostrophe(String content) {
        return countWordsByPattern("\\w+[-'\\w*]*", content);
    }


    /**
     * Method treats words with hyphen (like ex-wife) and apostrophe (like it's) as separate words
     *
     * @param content is a text where we count words in
     * @return map with word & number of occurrences as key-value pairs
     */
    public static HashMap<String, Long> countWords(String content) {
        return countWordsByPattern("\\w+", content);
    }

    private static HashMap<String, Long> countWordsByPattern(String pattern, String content) {
        List<String> words = Pattern.compile(pattern)
                .matcher(content)
                .results()
                .map(MatchResult::group)
                .collect(Collectors.toList());
        HashSet<String> uniqueWords = new HashSet<>(words);
        HashMap<String, Long> countMap = new HashMap<>();
        for (String word : uniqueWords) {
            countMap.put(word, words.stream().filter(w -> w.equals(word)).count());
        }
        return countMap;
    }

    public static List<Map.Entry<String, Long>> sortByValue(Map<String, Long> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }

}
