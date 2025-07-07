package ru.shift.core.stats;

import lombok.Getter;
import ru.shift.core.data.model.Data;

/**
 * Handles generation and display of data statistics.
 */
@Getter
public class StatisticsPrinter {

    private static final String FULL_STATISTICS_TEMPLATE = """
            Full statistics:
            Integers:
              Count: %d
              Min: %s
              Max: %s
              Sum: %d
              Avg: %.2f
            Floats:
              Count: %d
              Min: %s
              Max: %s
              Sum: %.2f
              Avg: %.2f
            Strings:
              Count: %d
              Shortest: %s char(s)
              Longest: %s char(s)
            """;
    private static final String SHORT_STATISTICS_TEMPLATE = """
            Sort statistics:
            Integers: %d element(s)
            Floats: %d element(s)
            Strings: %d element(s)
            """;

    /**
     * Prints statistics based on the data and selected options.
     *
     * @param data Categorized data to analyze
     * @param isFullStats Whether to show full statistics
     * @param isShortStats Whether to show short statistics
     */
    public void printStatistics(Data data, boolean isFullStats, boolean isShortStats) {
        if (isFullStats) {
            printFullStatistics(data);
        } else if (isShortStats) {
            printShortStatistics(data);
        }
    }

    private void printFullStatistics(Data data) {
        var integerStats = data.getIntegers().stream()
                .mapToLong(Long::longValue)
                .summaryStatistics();
        var floatStats = data.getFloatingPointNumbers().stream()
                .mapToDouble(Double::doubleValue)
                .summaryStatistics();
        var shortestStringLength = data.getStrings().stream()
                .mapToInt(String::length)
                .min();
        var longestStringLength = data.getStrings().stream()
                .mapToInt(String::length)
                .max();

        System.out.printf(
                FULL_STATISTICS_TEMPLATE,
                integerStats.getCount(),
                integerStats.getCount() > 0 ? integerStats.getMin() : "N/A",
                integerStats.getCount() > 0 ? integerStats.getMax() : "N/A",
                integerStats.getCount() > 0 ? integerStats.getSum() : 0,
                integerStats.getCount() > 0 ? integerStats.getAverage() : 0.0,
                floatStats.getCount(),
                floatStats.getCount() > 0 ? String.format("%.2f", floatStats.getMin()) : "N/A",
                floatStats.getCount() > 0 ? String.format("%.2f", floatStats.getMax()) : "N/A",
                floatStats.getCount() > 0 ? floatStats.getSum() : 0.0,
                floatStats.getCount() > 0 ? floatStats.getAverage() : 0.0,
                data.getStrings().size(),
                shortestStringLength.isPresent() ? shortestStringLength.getAsInt() : "N/A",
                longestStringLength.isPresent() ? longestStringLength.getAsInt() : "N/A"
        );
    }

    private void printShortStatistics(Data data) {
        System.out.printf(
                SHORT_STATISTICS_TEMPLATE,
                data.getIntegers().size(),
                data.getFloatingPointNumbers().size(),
                data.getStrings().size()
        );
    }

}