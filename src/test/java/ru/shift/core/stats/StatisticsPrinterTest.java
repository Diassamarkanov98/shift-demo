package ru.shift.core.stats;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.shift.core.data.model.Data;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsPrinterTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private final StatisticsPrinter printer = new StatisticsPrinter();

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @BeforeEach
    public void setUp() {
        outContent.reset();
    }

    @Test
    void printFullStatisticsWithSuccess() {
        Data data = new Data();
        data.getIntegers().addAll(List.of(10L, 20L, 30L));
        data.getFloatingPointNumbers().addAll(List.of(1.5, 2.5));
        data.getStrings().addAll(List.of("short", "very long string"));

        var expectedMessage = """
            Full statistics:
            Integers:
              Count: 3
              Min: 10
              Max: 30
              Sum: 60
              Avg: 20,00
            Floats:
              Count: 2
              Min: 1,50
              Max: 2,50
              Sum: 4,00
              Avg: 2,00
            Strings:
              Count: 2
              Shortest: 5 char(s)
              Longest: 16 char(s)
            """;

        printer.printStatistics(data, true, false);
        assertEquals(expectedMessage, outContent.toString());
    }

    @Test
    void printShortStatisticsWithSuccess() {
        var data = new Data();
        data.getIntegers().add(1L);
        data.getFloatingPointNumbers().add(1.1);
        data.getStrings().add("test");

        var expectedMessage = """
            Sort statistics:
            Integers: 1 element(s)
            Floats: 1 element(s)
            Strings: 1 element(s)
            """;

        printer.printStatistics(data, false, true);
        assertEquals(expectedMessage, outContent.toString());
    }

    @AfterAll
    public static void restoreStreams() {
        System.setOut(originalOut);
    }
}