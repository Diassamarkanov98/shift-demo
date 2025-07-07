package ru.shift.cli;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineParserTest {

    @Test
    public void parseOnlyInputFilesWithSuccess() {
        var args = new String[]{"file1.txt", "file2.txt"};
        var options = CommandLineParser.parseCommandLineOptions(args);

        assertEquals(2, options.inputFilePaths().size());
        assertEquals("file1.txt", options.inputFilePaths().get(0));
        assertEquals("file2.txt", options.inputFilePaths().get(1));
        assertFalse(options.isShortStats());
        assertFalse(options.isFullStats());
        assertFalse(options.isAppendModeOn());
        assertNull(options.outputPath());
        assertNull(options.prefix());
    }

    @Test
    public void parseWithOptionsWithSuccess() {
        var args = new String[]{"-s", "-a", "-o", "output", "-p", "pre_", "file.txt"};
        var options = CommandLineParser.parseCommandLineOptions(args);

        assertEquals(1, options.inputFilePaths().size());
        assertEquals("file.txt", options.inputFilePaths().get(0));
        assertTrue(options.isShortStats());
        assertFalse(options.isFullStats());
        assertTrue(options.isAppendModeOn());
        assertEquals("output", options.outputPath());
        assertEquals("pre_", options.prefix());
    }

    @Test
    public void parseFullStatsWithSuccess() {
        var args = new String[]{"-f", "file.txt"};
        var options = CommandLineParser.parseCommandLineOptions(args);

        assertFalse(options.isShortStats());
        assertTrue(options.isFullStats());
    }

    @Test
    public void parseMissingArgumentAfterOptionResultsInEmptyString() {
        var args = new String[]{"-o", "-f", "file.txt"};
        var options = CommandLineParser.parseCommandLineOptions(args);

        assertEquals("", options.outputPath());
    }

    @Test
    public void parseNoInputFilesWithException() {
        String[] args = {"-s", "-f"};

        assertThrows(IllegalArgumentException.class, () -> CommandLineParser.parseCommandLineOptions(args));
    }
}