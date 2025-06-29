package com.example.io;

import com.example.core.filter.DataFilter;
import com.example.core.stats.Statistics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class FileProcessor {
    private final DataFilter filter;
    private final Statistics stats;

    public FileProcessor() {
        this.filter = new DataFilter();
        this.stats = new Statistics();
    }

    public void processFiles(List<String> inputFiles) throws IOException {
        for (String inputFile : inputFiles) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(inputFile));
                filter.processLines(lines, stats);
            } catch (IOException e) {
                System.err.println("Error reading file " + inputFile + ": " + e.getMessage());
            }
        }
    }

    public void writeResults(String outputPath, String prefix, boolean appendMode) throws IOException {
        writeData(outputPath, prefix + "integers.txt", filter.getIntegers(), appendMode);
        writeData(outputPath, prefix + "floats.txt", filter.getFloats(), appendMode);
        writeData(outputPath, prefix + "strings.txt", filter.getStrings(), appendMode);
    }

    void writeData(String outputPath, String fileName, List<String> data, boolean appendMode) throws IOException {
        if (data.isEmpty()) return;

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }

        Path path = outputPath != null ? Paths.get(outputPath) : Paths.get("");
        Path filePath = path.resolve(fileName);

        if (!Files.exists(filePath)) {
            throw new IOException("File does not exist: " + filePath.toAbsolutePath());
        }

        if (!Files.isRegularFile(filePath)) {
            throw new IOException("Path is not a file: " + filePath.toAbsolutePath());
        }

        StandardOpenOption option = appendMode ?
                StandardOpenOption.APPEND : StandardOpenOption.TRUNCATE_EXISTING;

        Files.write(filePath, data, option);
    }

    public Statistics getStatistics() {
        return stats;
    }
}