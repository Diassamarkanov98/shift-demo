package com.example;

import com.example.cli.CliOptions;
import com.example.cli.CliParser;
import com.example.core.filter.DataFilter;
import com.example.core.stats.Statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            CliOptions options = CliParser.parse(args);
            DataFilter filter = new DataFilter();
            Statistics stats = new Statistics();

            processFiles(options, filter, stats);
            printStatistics(options, stats);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Usage: java -jar util.jar [-s|-f] [-a] [-o path] [-p prefix] file1 file2 ...");
        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private static void processFiles(CliOptions options, DataFilter filter, Statistics stats) throws IOException {
        for (String inputFile : options.getInputFiles()) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(inputFile));
                filter.processLines(lines, stats);
            } catch (IOException e) {
                System.err.println("Error reading file " + inputFile + ": " + e.getMessage());
            }
        }

        writeResults(options, filter);
    }

    private static void writeResults(CliOptions options, DataFilter filter) throws IOException {
        writeData(options, "integers.txt", filter.getIntegers());
        writeData(options, "floats.txt", filter.getFloats());
        writeData(options, "strings.txt", filter.getStrings());
    }

    private static void writeData(CliOptions options, String fileName, List<String> data) throws IOException {
        if (data.isEmpty()) {
            return;
        }

        if (fileName == null) {
            throw new IllegalArgumentException("File name cannot be null");
        }

        Path basePath = (options.getOutputPath() == null || options.getOutputPath().trim().isEmpty())
                ? Paths.get("")
                : Paths.get(options.getOutputPath());

        String prefix = options.getPrefix() != null ? options.getPrefix() : "";
        Path filePath = basePath.resolve(prefix + fileName);

        if (filePath.getParent() != null && !Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        StandardOpenOption option = options.isAppendMode()
                ? StandardOpenOption.APPEND
                : StandardOpenOption.TRUNCATE_EXISTING;

        if (!Files.exists(filePath) && !options.isAppendMode()) {
            Files.createFile(filePath);
        }

        Files.write(filePath, data, option);
    }

    private static void printStatistics(CliOptions options, Statistics stats) {
        if (options.isShortStats()) {
            System.out.println("Short statistics:");
            System.out.println("Integers: " + stats.getIntegerCount());
            System.out.println("Floats: " + stats.getFloatCount());
            System.out.println("Strings: " + stats.getStringCount());
        } else if (options.isFullStats()) {
            System.out.println("Full statistics:");
            stats.printFullStatistics();
        }
    }
}