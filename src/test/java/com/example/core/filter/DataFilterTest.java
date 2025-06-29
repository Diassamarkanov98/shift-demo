package com.example.core.filter;

import com.example.core.model.DataType;
import com.example.core.stats.Statistics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DataFilterTest {
    @Test
    public void testProcessLines() {
        DataFilter filter = new DataFilter();
        Statistics stats = new Statistics();

        List<String> lines = Arrays.asList(
                "123",          // integer
                "3.14",         // float
                "hello",        // string
                "-42",          // integer
                "1.23E-5",      // float
                "another string",
                ""              // empty line (should be ignored)
        );

        filter.processLines(lines, stats);

        assertEquals(2, filter.getIntegers().size());
        assertEquals("123", filter.getIntegers().get(0));
        assertEquals("-42", filter.getIntegers().get(1));

        assertEquals(2, filter.getFloats().size());
        assertEquals("3.14", filter.getFloats().get(0));
        assertEquals("1.23E-5", filter.getFloats().get(1));

        assertEquals(2, filter.getStrings().size());
        assertEquals("hello", filter.getStrings().get(0));
        assertEquals("another string", filter.getStrings().get(1));

        assertEquals(2, stats.getIntegerCount());
        assertEquals(2, stats.getFloatCount());
        assertEquals(2, stats.getStringCount());
    }

    @Test
    public void testEmptyInput() {
        DataFilter filter = new DataFilter();
        Statistics stats = new Statistics();

        filter.processLines(List.of(), stats);

        assertTrue(filter.getIntegers().isEmpty());
        assertTrue(filter.getFloats().isEmpty());
        assertTrue(filter.getStrings().isEmpty());
    }
}