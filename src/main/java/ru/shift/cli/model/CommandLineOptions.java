package ru.shift.cli.model;

import lombok.*;

import java.util.List;

/**
 * Immutable record representing parsed command-line options.
 * Field values are set using builder.
 *
 * @param inputFilePaths List of input file paths to process
 * @param outputPath Output directory path
 * @param prefix Prefix for output filenames
 * @param isAppendModeOn Flag indicating append mode
 * @param isShortStats Flag indicating short statistics should be shown
 * @param isFullStats Flag indicating full statistics should be shown
 */
@Builder
public record CommandLineOptions(
        List<String> inputFilePaths,
        String outputPath,
        String prefix,
        boolean isAppendModeOn,
        boolean isShortStats,
        boolean isFullStats
) {}