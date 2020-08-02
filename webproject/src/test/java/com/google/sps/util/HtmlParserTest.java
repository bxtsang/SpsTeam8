package com.google.sps.util;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Test class for HtmlParser.
 */
public class HtmlParserTest {
    private static final String HTML_STUB = "<!DOCTYPE html><head></head><body><div>Stub</div></body></html>";
    private static final String FILE_NAME = "testFile.html";

    // This must be public
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void parseHtmlFromFile_returnsParsedHtml() throws IOException {
        File file = temporaryFolder.newFile(FILE_NAME);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        bufferedWriter.write(HTML_STUB);
        bufferedWriter.close();

        assertEquals(HTML_STUB, HtmlParser.parseHtmlFromFile(file.getAbsolutePath()));
    }
}
