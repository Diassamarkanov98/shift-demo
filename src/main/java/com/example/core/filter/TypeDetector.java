package com.example.core.filter;

import com.example.core.model.DataType;

public class TypeDetector {
    public static DataType detectType(String value) {
        if (isInteger(value)) {
            return DataType.INTEGER;
        } else if (isFloat(value)) {
            return DataType.FLOAT;
        } else {
            return DataType.STRING;
        }
    }
    private static boolean isInteger(String value) {
        try {
            Long.parseLong(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private static boolean isFloat(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}