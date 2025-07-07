package ru.shift.core.command;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.shift.cli.model.CommandLineOptions;
import ru.shift.core.command.impl.ProcessFilesCommand;

/**
 * Factory class for creating {@link Command} instances based on options.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommandFactory {
    /**
     * Creates an appropriate command based on the given options.
     * @param options Parsed command-line options
     * @return {@link Command} instance ready for execution
     */
    public static Command createCommand(CommandLineOptions options) {
        return new ProcessFilesCommand(options);
    }
}