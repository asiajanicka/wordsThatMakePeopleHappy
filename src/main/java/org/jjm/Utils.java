package org.jjm;

import org.apache.commons.lang3.StringUtils;

import javax.swing.text.html.parser.Entity;
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
        List<String> words = Pattern.compile("\\w+[-'\\w*]*")
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

    /**
     * Method treats words with hyphen (like ex-wife) and apostrophe (like it's) as separate words
     *
     * @param content is a text where we count words in
     * @return map with word & number of occurrences as key-value pairs
     */
    public static HashMap<String, Long> countWords(String content) {
        String cont = content.replaceAll("\\W", " ").replaceAll("\\s+", ",");
        List<String> words = Arrays.asList(StringUtils.split(cont, ","));
        HashMap<String, Long> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, words.stream().filter(w -> w.equals(word)).count());
        }
        return countMap;
    }

    public static List<Map.Entry<String,Long>> sortByValue(Map<String, Long> map) {
        return map.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());
    }
}
