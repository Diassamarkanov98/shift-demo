package ru.shift.core.data;

import org.junit.jupiter.api.BeforeEach;
import ru.shift.core.data.model.Data;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class DataProcessorTest {
    private DataProcessor dataProcessor;
    private Data data;

    @BeforeEach
    public void setUp() {
        dataProcessor = new DataProcessor();
        data = new Data();
    }

    @Test
    public void processDataWithSuccess() {
        var lines = Arrays.asList(
                "123",
                "3.14",
                "hello",
                "-42",
                "1.23E-5",
                "another string",
                ""
        );

        dataProcessor.processData(lines, data);

        assertEquals(2, data.getIntegers().size());
        assertEquals(123, data.getIntegers().get(0));
        assertEquals(-42, data.getIntegers().get(1));

        assertEquals(2, data.getFloatingPointNumbers().size());
        assertEquals(3.14, data.getFloatingPointNumbers().get(0));
        assertEquals(1.23E-5, data.getFloatingPointNumbers().get(1));

        assertEquals(2, data.getStrings().size());
        assertEquals("hello", data.getStrings().get(0));
        assertEquals("another string", data.getStrings().get(1));
    }

    @Test
    void processDataSkipEmptyLines() {
        var lines = List.of("", "123", "   ", "text");

        dataProcessor.processData(lines, data);

        assertEquals(List.of(123L), data.getIntegers());
        assertEquals(List.of("text"), data.getStrings());
        assertTrue(data.getFloatingPointNumbers().isEmpty());
    }
}