package ru.shift.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Contains constant messages used throughout the application.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Messages {

    public static final String NO_INPUT_FILES_SPECIFIED = "Cannot continue execution: No input files specified";
    public static final String NO_OUTPUT_PATH_SPECIFIED = "No output path specified after -o";
    public static final String NO_PREFIX_SPECIFIED = "No prefix specified after -p";
    public static final String WARNING = "Warning: ";
    public static final String UNKNOWN_OPTION = "Unknown option '%s'";
    public static final String FILE_READING_ERROR = "Error reading file '%s': %s";
    public static final String NO_INTEGERS_FOUND = "Warning: no integers found. File %s will not be created.";
    public static final String NO_FLOATS_FOUND = "Warning: no floats found. File %s will not be created.";
    public static final String NO_STRINGS_FOUND = "Warning: no strings found. File %s will not be created.";
    public static final String COULD_NOT_CREATE_DIRECTORY = "Warning. Could not create directory: %s. Error: %s";
    public static final String COULD_NOT_CREATE_FILE = "Warning. Could not create file: %s. Error: %s";
    public static final String COULD_NOT_WRITE_TO_FILE = "Warning. Could not write to a file: %s. Error: %s";

}
