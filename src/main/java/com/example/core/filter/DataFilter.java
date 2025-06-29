package com.example.core.filter;

import com.example.core.model.DataType;
import com.example.core.stats.Statistics;

import java.util.ArrayList;
import java.util.List;

public class DataFilter {
    private final List<String> integers = new ArrayList<>();
    private final List<String> floats = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    public void processLines(List<String> lines, Statistics stats) {
        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            DataType type = TypeDetector.detectType(line);
            switch (type) {
                case INTEGER:
                    integers.add(line);
                    stats.collectIntegerStats(line);
                    break;
                case FLOAT:
                    floats.add(line);
                    stats.collectFloatStats(line);
                    break;
                case STRING:
                    strings.add(line);
                    stats.collectStringStats(line);
                    break;
            }
        }
    }

    public List<String> getIntegers() {
        return integers;
    }

    public List<String> getFloats() {
        return floats;
    }

    public List<String> getStrings() {
        return strings;
    }
}