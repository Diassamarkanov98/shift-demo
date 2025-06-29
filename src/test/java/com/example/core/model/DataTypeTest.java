package com.example.core.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DataTypeTest {
    @Test
    public void testEnumValues() {
        DataType[] values = DataType.values();
        assertEquals(3, values.length);
        assertEquals(DataType.INTEGER, values[0]);
        assertEquals(DataType.FLOAT, values[1]);
        assertEquals(DataType.STRING, values[2]);
    }

    @Test
    public void testValueOf() {
        assertEquals(DataType.INTEGER, DataType.valueOf("INTEGER"));
        assertEquals(DataType.FLOAT, DataType.valueOf("FLOAT"));
        assertEquals(DataType.STRING, DataType.valueOf("STRING"));
    }
}