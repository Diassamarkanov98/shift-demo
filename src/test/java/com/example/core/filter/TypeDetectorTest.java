package com.example.core.filter;

import com.example.core.model.DataType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TypeDetectorTest {
    @Test
    public void testDetectInteger() {
        assertEquals(DataType.INTEGER, TypeDetector.detectType("123"));
        assertEquals(DataType.INTEGER, TypeDetector.detectType("-456"));
        assertEquals(DataType.INTEGER, TypeDetector.detectType("0"));
        assertEquals(DataType.INTEGER, TypeDetector.detectType("+789"));
        assertEquals(DataType.INTEGER, TypeDetector.detectType("1234567890123456789"));
    }

    @Test
    public void testDetectFloat() {
        assertEquals(DataType.FLOAT, TypeDetector.detectType("3.14"));
        assertEquals(DataType.FLOAT, TypeDetector.detectType("-0.001"));
        assertEquals(DataType.FLOAT, TypeDetector.detectType("1.23E-5"));
        assertEquals(DataType.FLOAT, TypeDetector.detectType("1.7976931348623157E308"));
        assertEquals(DataType.FLOAT, TypeDetector.detectType("4.9E-324"));
    }

    @Test
    public void testDetectString() {
        assertEquals(DataType.STRING, TypeDetector.detectType("hello"));
        assertEquals(DataType.STRING, TypeDetector.detectType("123abc"));
        assertEquals(DataType.STRING, TypeDetector.detectType("3.14.15"));
        assertEquals(DataType.STRING, TypeDetector.detectType(""));
        assertEquals(DataType.STRING, TypeDetector.detectType(" "));
    }
}