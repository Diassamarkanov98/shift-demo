package com.example.core.stats;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StatisticsTest {
    @Test
    public void testCollectIntegerStats() {
        Statistics stats = new Statistics();

        stats.collectIntegerStats("100");
        assertEquals(1, stats.getIntegerCount());

        stats.collectIntegerStats("-50");
        assertEquals(2, stats.getIntegerCount());

        stats.collectIntegerStats("0");
        assertEquals(3, stats.getIntegerCount());
    }

    @Test
    public void testCollectFloatStats() {
        Statistics stats = new Statistics();

        stats.collectFloatStats("3.14");
        assertEquals(1, stats.getFloatCount());

        stats.collectFloatStats("-0.001");
        assertEquals(2, stats.getFloatCount());

        stats.collectFloatStats("1.23E-5");
        assertEquals(3, stats.getFloatCount());
    }

    @Test
    public void testCollectStringStats() {
        Statistics stats = new Statistics();

        stats.collectStringStats("short");
        assertEquals(1, stats.getStringCount());

        stats.collectStringStats("a bit longer string");
        assertEquals(2, stats.getStringCount());

        stats.collectStringStats("");
        assertEquals(3, stats.getStringCount());
    }

    @Test
    public void testFullStatistics() {
        Statistics stats = new Statistics();

        // Integers
        stats.collectIntegerStats("10");
        stats.collectIntegerStats("20");
        stats.collectIntegerStats("30");

        // Floats
        stats.collectFloatStats("1.1");
        stats.collectFloatStats("2.2");

        // Strings
        stats.collectStringStats("a");
        stats.collectStringStats("bb");
        stats.collectStringStats("ccc");

        assertEquals(3, stats.getIntegerCount());
        assertEquals(2, stats.getFloatCount());
        assertEquals(3, stats.getStringCount());

        stats.printFullStatistics();
    }
}