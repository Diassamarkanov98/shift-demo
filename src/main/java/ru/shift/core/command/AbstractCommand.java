package ru.shift.core.command;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import ru.shift.cli.model.CommandLineOptions;

/**
 * Abstract base class for {@link Command} implementations.
 * Provides common functionality and access to command options.
 */
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCommand implements Command {
    /**
     * Parsed command-line options
     */
    protected final CommandLineOptions options;
}
