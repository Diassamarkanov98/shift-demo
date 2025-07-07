package ru.shift.file;

import ru.shift.cli.model.CommandLineOptions;
import ru.shift.core.data.model.Data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static ru.shift.exception.Messages.*;

/**
 * Handles all file I/O operations including reading input and writing categorized output.
 */
public class FileProcessor {

    private static final String CARRIAGE_RETURN = "%n";
    private static final String EMPTY_STRING = "";
    private static final String FLOATS_FILE_NAME = "floats.txt";
    private static final String INTEGERS_FILE_NAME = "integers.txt";
    private static final String STRINGS_FILE_NAME = "strings.txt";

    /**
     * Reads all lines from the specified input file.
     *
     * @param inputFilePath Path to the input file
     * @return List of lines from the file, or empty list if file cannot be read
     */
    public List<String> readLinesFromFile(String inputFilePath) {
        try {
            return Files.readAllLines(Paths.get(inputFilePath));
        } catch (IOException e) {
            System.err.printf((FILE_READING_ERROR) + CARRIAGE_RETURN, inputFilePath, e.getMessage());
            return List.of();
        }
    }

    /**
     * Writes categorized data to appropriate output files.
     *
     * @param options Command line options affecting output
     * @param data Categorized data to write
     */
    public void writeDataToFiles(CommandLineOptions options, Data data) {
        writeIntegersToFile(options, data);
        writeFloatsToFile(options, data);
        writeStringsToFile(options, data);
    }

    private void writeIntegersToFile(CommandLineOptions options, Data data) {
        if (data.getIntegers().isEmpty()) {
            System.out.printf(NO_INTEGERS_FOUND, INTEGERS_FILE_NAME);
            return;
        }

        var integers = data.getIntegers().stream()
                .map(Object::toString)
                .toList();
        writeDataToFile(options, INTEGERS_FILE_NAME, integers);
    }

    private void writeFloatsToFile(CommandLineOptions options, Data data) {
        if (data.getFloatingPointNumbers().isEmpty()) {
            System.out.printf(NO_FLOATS_FOUND, FLOATS_FILE_NAME);
            return;
        }

        var floats = data.getFloatingPointNumbers().stream()
                .map(Object::toString)
                .toList();
        writeDataToFile(options, FLOATS_FILE_NAME, floats);
    }

    private void writeStringsToFile(CommandLineOptions options, Data data) {
        if (data.getStrings().isEmpty()) {
            System.out.printf(NO_STRINGS_FOUND, STRINGS_FILE_NAME);
            return;
        }

        writeDataToFile(options, STRINGS_FILE_NAME, data.getStrings());
    }

    private void writeDataToFile(CommandLineOptions options, String fileName, List<String> lines) {
        var filePath = generateFilePath(options, fileName);
        filePath = ensureParentDirectoryExist(fileName, filePath);

        var openOption = options.isAppendModeOn()
                ? StandardOpenOption.APPEND
                : StandardOpenOption.TRUNCATE_EXISTING;

        if (!Files.exists(filePath)) {
            createOutputFile(filePath);
        }

        writeDataToOutputFile(lines, filePath, openOption);
    }

    private Path generateFilePath(CommandLineOptions options, String fileName) {
        var baseOutputPath = (options.outputPath() == null || options.outputPath().trim().isEmpty())
                ? Paths.get(EMPTY_STRING)
                : Paths.get(options.outputPath());
        var prefix = (options.prefix() == null || options.prefix().trim().isEmpty())
                ? EMPTY_STRING
                : options.prefix();

        return baseOutputPath.resolve(prefix + fileName);
    }

    private Path ensureParentDirectoryExist(String fileName, Path filePath) {
        if (filePath.getParent() != null && !Files.exists(filePath.getParent())) {
            try {
                Files.createDirectories(filePath.getParent());
            } catch (IOException exception) {
                var fallbackPath = Paths.get(fileName);
                System.err.printf(COULD_NOT_CREATE_DIRECTORY, filePath.getParent(), exception);

                return fallbackPath;
            }
        }

        return filePath;
    }

    private void createOutputFile(Path filePath) {
        try {
            Files.createFile(filePath);
        } catch (IOException e) {
            System.err.printf(COULD_NOT_CREATE_FILE, filePath, e.getMessage());
        }
    }

    private void writeDataToOutputFile(List<String> lines, Path filePath, StandardOpenOption openOption) {
        try {
            Files.write(filePath, lines, openOption);
        } catch (IOException e) {
            System.err.printf(COULD_NOT_WRITE_TO_FILE, filePath, e.getMessage());
        }
    }

}