package org.jjm;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            String content = StringUtils
                    .toRootLowerCase(
                            FileUtils.readFileToString(
                                    new File("src/main/resources/myfile.txt"), "UTF-8"
                            )
                    );
            FileUtils.write(new File("src/main/resources/result.txt"),
                    countWordsWithHyphenAndApostrophe(content).toString(), "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method treats words with hyphen (like ex-wife) and apostrophe (like it's) as single
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
}
