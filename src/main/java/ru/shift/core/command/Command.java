package ru.shift.core.command;

/**
 * Interface representing an executable command.
 */
public interface Command {
    /**
     * Executes the command's main logic.
     * @throws Exception if an error occurs during execution
     */
    void execute() throws Exception;
}
