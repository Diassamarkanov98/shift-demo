package ru.shift.core.command.impl;

import ru.shift.cli.model.CommandLineOptions;
import ru.shift.core.command.AbstractCommand;
import ru.shift.core.command.Command;
import ru.shift.core.data.DataProcessor;
import ru.shift.core.data.model.Data;
import ru.shift.core.stats.StatisticsPrinter;
import ru.shift.file.FileProcessor;

/**
 * {@link Command} implementation for processing input files and generating categorized output.
 * <p>
 * Reads input files, categorizes content into integers, floats and strings, writes results to separate files,
 * and displays statistics based on passed command-line options.
 */
public class ProcessFilesCommand extends AbstractCommand {
    private final FileProcessor fileProcessor;  // Handles file I/O operations
    private final DataProcessor dataProcessor;
    private final StatisticsPrinter statisticsPrinter;// Processes and categorizes data

    public ProcessFilesCommand(CommandLineOptions options) {
        super(options);
        fileProcessor = new FileProcessor();
        dataProcessor = new DataProcessor();
        statisticsPrinter = new StatisticsPrinter();
    }

    /**
     * Executes the file processing command:
     * <ol>
     *   <li>Reads and processes all input files</li>
     *   <li>Writes categorized data to output files</li>
     *   <li>Prints requested statistics</li>
     * </ol>
     */
    @Override
    public void execute() {
        var data = new Data();

        for (var inputFilePath : options.inputFilePaths()) {
            var lines = fileProcessor.readLinesFromFile(inputFilePath);

            if (!lines.isEmpty()) {
                dataProcessor.processData(lines, data);
            }
        }

        fileProcessor.writeDataToFiles(options, data);
        statisticsPrinter.printStatistics(data, options.isFullStats(), options.isShortStats());
    }
}
