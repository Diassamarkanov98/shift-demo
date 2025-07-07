package ru.shift.core.data;

import lombok.NoArgsConstructor;
import ru.shift.core.data.model.Data;

import java.util.List;

/**
 * Processes string lines from input files and categorizes data from them into numeric and string values.
 */
@NoArgsConstructor
public class DataProcessor {

    /**
     * Processes a list of strings and categorizes them into the provided Data object.
     * @param lines List of strings to process
     * @param data Data container to store categorized results
     */
    public void processData(List<String> lines, Data data) {
        for (var line : lines) {
            line = line.trim();

            if (line.isEmpty()) {
                continue;
            }

            processLineDataBasedOnType(line, data);
        }
    }

    /**
     * Processes passed string and categorizes it into the corresponding list of the provided Data object.
     * Tries to parse passed line as an integer number. Exits if succeeded.
     * Then tries to parse passed line as a floating point number. Exits if succeeded.
     * If parsing as a number did not succeed, saves line as a {@link String}.
     *
     * @param line String to process
     * @param data Data container to store categorized results
     */
    private void processLineDataBasedOnType(String line, Data data) {
        try {
            var integer = Long.parseLong(line);
            data.getIntegers().add(integer);
            return;
        } catch (NumberFormatException ignored) {}

        try {
            var floatingPointNumber = Double.parseDouble(line);
            data.getFloatingPointNumbers().add(floatingPointNumber);
            return;
        } catch (NumberFormatException ignored) {}

        data.getStrings().add(line);
    }
}