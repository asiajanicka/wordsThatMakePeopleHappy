package org.jjm;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        if (args.length != 0 && new File(args[0]).exists()) {
            File outputFile = (args.length == 2) ? new File(args[1]) : new File("./output/result.txt");
            try {
                String content = StringUtils.toRootLowerCase(FileUtils.readFileToString(new File(args[0]), "UTF-8"));
                FileUtils.writeLines(outputFile, Utils.sortByValue(Utils.countWordsWithHyphenAndApostrophe(content)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else
            throw new IllegalArgumentException("Run program with at least one argument - path pointing to file with words");
    }
}
