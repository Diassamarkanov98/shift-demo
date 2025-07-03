package com.example.core.stats;

import lombok.Getter;

@Getter
public class Statistics {
    private int integerCount;
    private long integerMin;
    private long integerMax;
    private long integerSum;

    private int floatCount;
    private double floatMin;
    private double floatMax;
    private double floatSum;

    private int stringCount;
    private int stringMinLength;
    private int stringMaxLength;

    public void collectIntegerStats(String value) {
        long num = Long.parseLong(value);
        integerCount++;
        integerSum += num;

        if (integerCount == 1) {
            integerMin = num;
            integerMax = num;
        } else {
            if (num < integerMin) integerMin = num;
            if (num > integerMax) integerMax = num;
        }
    }

    public void collectFloatStats(String value) {
        double num = Double.parseDouble(value);
        floatCount++;
        floatSum += num;

        if (floatCount == 1) {
            floatMin = num;
            floatMax = num;
        } else {
            if (num < floatMin) floatMin = num;
            if (num > floatMax) floatMax = num;
        }
    }

    public void collectStringStats(String value) {
        int length = value.length();
        stringCount++;

        if (stringCount == 1) {
            stringMinLength = length;
            stringMaxLength = length;
        } else {
            if (length < stringMinLength) stringMinLength = length;
            if (length > stringMaxLength) stringMaxLength = length;
        }
    }

    public int getIntegerCount() {
        return integerCount;
    }

    public int getFloatCount() {
        return floatCount;
    }

    public int getStringCount() {
        return stringCount;
    }

    public void printFullStatistics() {
        System.out.println("Integers:");
        System.out.println("  Count: " + integerCount);
        System.out.println("  Min: " + integerMin);
        System.out.println("  Max: " + integerMax);
        System.out.println("  Sum: " + integerSum);
        System.out.println("  Avg: " + (integerCount == 0 ? 0 : (double) integerSum / integerCount));

        System.out.println("Floats:");
        System.out.println("  Count: " + floatCount);
        System.out.println("  Min: " + floatMin);
        System.out.println("  Max: " + floatMax);
        System.out.println("  Sum: " + floatSum);
        System.out.println("  Avg: " + (floatCount == 0 ? 0 : floatSum / floatCount));

        System.out.println("Strings:");
        System.out.println("  Count: " + stringCount);
        System.out.println("  Shortest: " + stringMinLength + " chars");
        System.out.println("  Longest: " + stringMaxLength + " chars");
    }
}