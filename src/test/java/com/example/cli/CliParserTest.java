package com.example.cli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CliParserTest {
    @Test
    public void testParseBasicArguments() {
        String[] args = {"file1.txt", "file2.txt"};
        CliOptions options = CliParser.parse(args);

        assertEquals(2, options.getInputFiles().size());
        assertEquals("file1.txt", options.getInputFiles().get(0));
        assertEquals("file2.txt", options.getInputFiles().get(1));
        assertFalse(options.isShortStats());
        assertFalse(options.isFullStats());
        assertFalse(options.isAppendMode());
        assertNull(options.getOutputPath());
        assertNull(options.getPrefix());
    }

    @Test
    public void testParseWithOptions() {
        String[] args = {"-s", "-a", "-o", "output", "-p", "pre_", "file.txt"};
        CliOptions options = CliParser.parse(args);

        assertEquals(1, options.getInputFiles().size());
        assertEquals("file.txt", options.getInputFiles().get(0));
        assertTrue(options.isShortStats());
        assertFalse(options.isFullStats());
        assertTrue(options.isAppendMode());
        assertEquals("output", options.getOutputPath());
        assertEquals("pre_", options.getPrefix());
    }

    @Test
    public void testParseFullStats() {
        String[] args = {"-f", "file.txt"};
        CliOptions options = CliParser.parse(args);

        assertFalse(options.isShortStats());
        assertTrue(options.isFullStats());
    }

    @Test
    public void testParseMissingArgumentAfterOption() {
        String[] args = {"-o"};
        assertThrows(IllegalArgumentException.class, () -> CliParser.parse(args));
    }

    @Test
    public void testParseNoInputFiles() {
        String[] args = {"-s", "-f"};
        assertThrows(IllegalArgumentException.class, () -> CliParser.parse(args));
    }
}