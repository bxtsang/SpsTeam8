package com.google.sps.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A utility class for parsing HTML files into text strings.
 */
public class HtmlParser {

    /**
     * Parses the HTML file at the specified path and returns its contents as a string.
     * @param The specified HTML file path.
     * @return The specified HTML file's contents as a string.
     * @throws IOException If the HTML file cannot be found at the specified path.
     */
    public static String parseHtmlFromFile(String htmlFilePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(htmlFilePath));
        StringBuilder stringBuilder = new StringBuilder();

        String lineFromFile;
        while ((lineFromFile = bufferedReader.readLine()) != null) {
            stringBuilder.append(lineFromFile);
        }

        bufferedReader.close();

        return stringBuilder.toString();
    }
}
