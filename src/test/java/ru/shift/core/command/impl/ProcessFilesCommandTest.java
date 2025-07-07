package ru.shift.core.command.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import ru.shift.cli.model.CommandLineOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProcessFilesCommandTest {

    @Test
    void executeProcessFilesAndCreateOutputFilesWithSuccess(@TempDir Path tempDir) throws IOException {
        var inputFile1 = tempDir.resolve("input1.txt");
        Files.write(inputFile1, List.of("123", "45.67", "text"));

        var inputFile2 = tempDir.resolve("input2.txt");
        Files.write(inputFile2, List.of("-100", "3.14", "more text"));

        var options = CommandLineOptions.builder()
                .inputFilePaths(List.of(inputFile1.toString(), inputFile2.toString()))
                .outputPath(tempDir.toString())
                .build();

        var command = new ProcessFilesCommand(options);
        command.execute();

        assertAll(
                () -> assertTrue(Files.exists(tempDir.resolve("integers.txt"))),
                () -> assertTrue(Files.exists(tempDir.resolve("floats.txt"))),
                () -> assertTrue(Files.exists(tempDir.resolve("strings.txt")))
        );

        var integers = Files.readAllLines(tempDir.resolve("integers.txt"));
        assertAll(
                () -> assertEquals(2, integers.size()),
                () -> assertTrue(integers.contains("123")),
                () -> assertTrue(integers.contains("-100"))
        );

        var floats = Files.readAllLines(tempDir.resolve("floats.txt"));
        assertAll(
                () -> assertEquals(2, floats.size()),
                () -> assertTrue(floats.contains("45.67")),
                () -> assertTrue(floats.contains("3.14"))
        );

        var strings = Files.readAllLines(tempDir.resolve("strings.txt"));
        assertAll(
                () -> assertEquals(2, strings.size()),
                () -> assertTrue(strings.contains("text")),
                () -> assertTrue(strings.contains("more text"))
        );
    }

    @Test
    void executeHandleEmptyFilesWithSuccess(@TempDir Path tempDir) throws IOException {
        var inputFile = tempDir.resolve("empty.txt");
        Files.write(inputFile, List.of());

        var options = CommandLineOptions.builder()
                .inputFilePaths(List.of(inputFile.toString()))
                .outputPath(tempDir.toString())
                .build();

        var command = new ProcessFilesCommand(options);
        command.execute();

        assertAll(
                () -> assertFalse(Files.exists(tempDir.resolve("integers.txt"))),
                () -> assertFalse(Files.exists(tempDir.resolve("floats.txt"))),
                () -> assertFalse(Files.exists(tempDir.resolve("strings.txt")))
        );
    }
}