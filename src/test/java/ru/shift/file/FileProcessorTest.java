package ru.shift.file;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.shift.cli.model.CommandLineOptions;
import ru.shift.core.data.model.Data;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileProcessorTest {

    private FileProcessor fileProcessor;
    private Path tempDir;

    @BeforeEach
    void setUp(@TempDir Path tempDir) {
        this.tempDir = tempDir;
        fileProcessor = new FileProcessor();
    }

    @Test
    void readLinesFromFileWithSuccessWhenFileExists() throws IOException {
        var testFile = tempDir.resolve("test.txt");
        Files.write(testFile, List.of("line1", "line2", "line3"));

        var result = fileProcessor.readLinesFromFile(testFile.toString());

        assertEquals(List.of("line1", "line2", "line3"), result);
    }

    @Test
    void readLinesFromFileWithSuccessWhenFileNotExists() {
        var result = fileProcessor.readLinesFromFile("nonexistent.txt");

        assertTrue(result.isEmpty());
    }

    @Test
    void writeDataToFilesWithSuccess() {
        Data data = new Data();
        data.getIntegers().addAll(List.of(1L, 2L, 3L));
        data.getFloatingPointNumbers().addAll(List.of(1.1, 2.2));
        data.getStrings().addAll(List.of("a", "b"));

        CommandLineOptions options = CommandLineOptions.builder()
                .outputPath(tempDir.toString())
                .build();

        fileProcessor.writeDataToFiles(options, data);

        assertAll(
                () -> assertTrue(Files.exists(tempDir.resolve("integers.txt"))),
                () -> assertTrue(Files.exists(tempDir.resolve("floats.txt"))),
                () -> assertTrue(Files.exists(tempDir.resolve("strings.txt")))
        );
    }

    @Test
    void writeDataToFilesWithSuccessfulUseOfPrefixWhenSpecified() {
        Data data = new Data();
        data.getIntegers().add(1L);

        var options = CommandLineOptions.builder()
                .outputPath(tempDir.toString())
                .prefix("test-")
                .build();

        fileProcessor.writeDataToFiles(options, data);

        assertTrue(Files.exists(tempDir.resolve("test-integers.txt")));
    }

    @Test
    public void writeEmptyDataToFilesDataIsNotWritten() throws IOException {
        var testFile = tempDir.resolve("strings.txt");
        Files.write(testFile, List.of("initial content"));

        var options = CommandLineOptions.builder()
                .outputPath(tempDir.toString())
                .isAppendModeOn(false)
                .build();
        var processor = new FileProcessor();
        var data = new Data();

        processor.writeDataToFiles(options, data);

        var lines = Files.readAllLines(testFile);
        assertEquals(1, lines.size());
        assertEquals("initial content", lines.get(0));
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(tempDir.resolve("integers.txt"));
        Files.deleteIfExists(tempDir.resolve("floats.txt"));
        Files.deleteIfExists(tempDir.resolve("strings.txt"));
    }
}