package ru.shift.cli;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.shift.cli.model.CommandLineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static ru.shift.exception.Messages.*;

/**
 * Parser for parsing command-line arguments into CommandLineOptions.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandLineParser {

    private static final String APPEND_MODE_OPTION = "-a";
    private static final String FULL_STATS_OPTION = "-f";
    private static final String HYPHEN = "-";
    private static final String OUTPUT_PATH_OPTION = "-o";
    private static final String PREFIX_OPTION = "-p";
    private static final String SHORT_STATS_OPTION = "-s";
    private static final String EMPTY_STRING = "";

    /**
     * Parse command-line arguments into a structured {@link CommandLineOptions} record
     *
     * @param args Array of `command-line arguments
     * @return Populated instance of a {@link CommandLineOptions} record
     * @throws IllegalArgumentException if no input file paths specified
     */
    public static CommandLineOptions parseCommandLineOptions(String[] args) throws IllegalArgumentException {
        if (args.length == 0) {
            throw new IllegalArgumentException(NO_INPUT_FILES_SPECIFIED);
        }

        var optionsBuilder = CommandLineOptions.builder();
        List<String> inputFilePaths = new ArrayList<>();

        parseCommandLineArguments(args, optionsBuilder, inputFilePaths);

        if (inputFilePaths.isEmpty()) {
            throw new IllegalArgumentException(NO_INPUT_FILES_SPECIFIED);
        }

        optionsBuilder.inputFilePaths(inputFilePaths);
        return optionsBuilder.build();
    }

    /**
     * Separates options (-s, -f, etc.) from input file paths. Populates  input file paths list.
     *
     * @param args Array of command-line arguments
     * @param optionsBuilder Builder of a {@link CommandLineOptions} instance
     * @param inputFilePaths List of input file paths to populate
     */
    private static void parseCommandLineArguments(
            String[] args,
            CommandLineOptions.CommandLineOptionsBuilder optionsBuilder,
            List<String> inputFilePaths
    ) {
        int argIndex = 0;

        while (argIndex < args.length) {
            var arg = args[argIndex];

            if (!arg.startsWith("-")) {
                inputFilePaths.add(arg);
                argIndex++;
                continue;
            }

            argIndex = parseOption(args, argIndex, optionsBuilder);
        }
    }

    /**
     * Processes a single option and its value (if applicable)
     *
     * @param args Array of command-line arguments
     * @param currentArgIndex Index of a current command-line argument
     * @param optionsBuilder Builder of a {@link CommandLineOptions} instance
     * @return Index of the next command-line argument to parse
     */
    private static int parseOption(
            String[] args,
            int currentArgIndex,
            CommandLineOptions.CommandLineOptionsBuilder optionsBuilder
    ) {
        var option = args[currentArgIndex];
        return switch (option) {
            case SHORT_STATS_OPTION -> {
                optionsBuilder.isShortStats(true);
                yield currentArgIndex + 1;
            }
            case FULL_STATS_OPTION -> {
                optionsBuilder.isFullStats(true);
                yield currentArgIndex + 1;
            }
            case APPEND_MODE_OPTION -> {
                optionsBuilder.isAppendModeOn(true);
                yield currentArgIndex + 1;
            }
            case OUTPUT_PATH_OPTION -> parseOptionWithValue(
                    args,
                    currentArgIndex,
                    optionsBuilder::outputPath,
                    NO_OUTPUT_PATH_SPECIFIED
            );
            case PREFIX_OPTION -> parseOptionWithValue(
                    args,
                    currentArgIndex,
                    optionsBuilder::prefix,
                    NO_PREFIX_SPECIFIED
            );
            default -> {
                System.err.println(WARNING + String.format(UNKNOWN_OPTION, option));
                yield currentArgIndex + 1;
            }
        };
    }

    /**
     * Parses options that require a value (e.g., -o <path>).
     * <p>
     * Checks, if the next argument is applicable value. If not, prints warning message, sets default value
     * to the corresponding option and returns the index of the next argument. If value is applicable, returns
     * the index after the next argument.
     *
     * @param args Array of command-line arguments
     * @param currentIndex Index of a current command-line argument
     * @param valueConsumer Value that has to be set to the corresponding {@link CommandLineOptions} field
     * @param warningMessage Warning message that has to be printed in case parsing is not possible
     * @return Index of the next command-line argument to parse
     */
    private static int parseOptionWithValue(
            String[] args,
            int currentIndex,
            Consumer<String> valueConsumer,
            String warningMessage
    ) {
        if (currentIndex + 1 >= args.length || args[currentIndex + 1].startsWith(HYPHEN)) {
            System.err.println(WARNING + warningMessage);
            valueConsumer.accept(EMPTY_STRING);
            return currentIndex + 1;
        }

        valueConsumer.accept(args[currentIndex + 1]);
        return currentIndex + 2;
    }
}